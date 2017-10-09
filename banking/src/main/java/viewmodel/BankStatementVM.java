package viewmodel;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import mappers.BankTransactionMapper;
import mappers.CurrentFyeUsersMapper;
import model.BankStatement;
import model.BankStatementV;
import model.BankTransaction;

public class BankStatementVM extends BaseVM {

	public static final String ZUL = "zul/bankStatement.zul";

	private BankStatement bankStatement = new BankStatement();
	private List<BankStatementV> statements = new ArrayList<>();
	private Integer fye;
	private String users;

	// NB follow the naming convention to avoid null pointer exceptions
	@WireVariable
	BankTransactionMapper bankTransactionMapper;
	@WireVariable
	CurrentFyeUsersMapper currentFyeUsersMapper;
	
	private boolean inserting = false;
	
	@AfterCompose
	public void afterCompose() throws Exception {
		refresh();
	}

	@Command
	public void refresh() throws Exception {
		fye = currentFyeUsersMapper.findAll().get(0).getFye();
		users = currentFyeUsersMapper.findAll().get(0).getUsers();
		statements.clear();
		statements.addAll(filter(bankTransactionMapper.getBankStatements(fye, users)));
		inserting = false;
		BindUtils.postNotifyChange(null, null, this, "statements");
		BindUtils.postNotifyChange(null, null, this, "inserting");
	}

	public BankStatement getBankStatement() {
		return bankStatement;
	}

	@Command
	@NotifyChange("statements")
	public void deleteStatement(@BindingParam("statement") BankStatementV statement) {
		if (statement.getBank() == null || statement.getWho() == null || statement.getFye() == null) {
			Messagebox.show("Please select year, name and bank");
			return;
		}
		bankTransactionMapper.deleteStatement(new Integer(statement.getFye()), statement.getWho(), statement.getBank(), users);
		statements.clear();
		statements.addAll(bankTransactionMapper.getBankStatements(fye, users));
	}

	@NotifyChange({"bankStatement", "inserting"})
	@Command
	public void insertStatement() throws Exception {
		bankStatement.setName(users);
		bankStatement.setYear(fye.toString());
		inserting = true;
	}
	
	@NotifyChange({ "statements", "bankStatement", "inserting" })
	@Command
	public void loadStatement(@BindingParam("evt") UploadEvent evt) throws Exception {
		if (evt != null && evt.getMedia() != null) {
			Media media = evt.getMedia();
			if (media.getName().endsWith("csv")) {
				bankStatement.setFile(FileUtil.writeFile(media));
				BindUtils.postNotifyChange(null, null, this, "bankStatement");
			} else {
				Messagebox.show("Please select a csv file");
			}
		}
		if (!bankStatement.isReadable()) {
			Messagebox.show("Please fill in all the fields and select a file");
			return;
		}
		extractTransactions(bankStatement.getFile());

		Messagebox.show(
				"Insert transactions from " + bankStatement.getFile().getAbsolutePath() + " for "
						+ bankStatement.getName() + " " + bankStatement.getBank() + " " + bankStatement.getYear() + " "
						+ bankStatement.getDescription() + " ?",
				"Confirm Dialog", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(Event evt) throws Exception {
						if (evt.getName().equals("onOK")) {
							int i = 0;
							for (BankTransaction bt : bankStatement.getTransactions()) {
								bankTransactionMapper.insert(bt);
								i++;
							}
							Messagebox.show(i + " transactions inserted.");
							bankStatement = new BankStatement();
							refresh();
						}
					}
				});

	}

	void extractTransactions(File file) throws Exception {
		List<String[]> lines = FileUtil.readCsvFile(file);
		// Messagebox.show("Lines read = " + lines.size());
		int lineCount = 0;
		for (String[] line : lines) {
			lineCount++;
			if ("SUNCORP".equals(bankStatement.getBank().toUpperCase())) {
				if (lineCount > 2) {
					if (line[0] == null || line[1] == null || line[2] == null) {
						throw new Exception("when, description or amount is null at line " + lineCount);
					}
					BankTransaction bankTransaction = new BankTransaction();
					bankTransaction.setWhen(new SimpleDateFormat("dd/MM/yyyy").parse(line[0]));
					String desc = line[1].replaceAll("\"", "").replaceAll("  ", " ").replaceAll("  ", " ").trim();
					desc = refactorSuncorpDescription(desc);
					bankTransaction.setDescription(desc);
					String amt = line[2].replaceFirst("\\$", "").replaceFirst(",", "").trim();
					bankTransaction.setAmount(new BigDecimal(amt));
					bankTransaction.setId(new Integer(bankTransactionMapper.getNextId()));
					bankTransaction.setWho(bankStatement.getName());
					bankTransaction.setBank(bankStatement.getBank());
					bankTransaction.setFye(new Integer(bankStatement.getYear()));
					bankTransaction.setUsers(users);
					bankStatement.getTransactions().add(bankTransaction);
				} else {
					bankStatement.setDescription(bankStatement.getDescription() + Arrays.toString(line) + "\n");
				}
			}
			if ("ING".equals(bankStatement.getBank().toUpperCase())) {
				if (lineCount > 1) {
					if (line[0] == null || line[1] == null) {
						throw new Exception("when, description or amount is null at line " + lineCount);
					}
					BankTransaction bankTransaction = new BankTransaction();
					bankTransaction.setWhen(new SimpleDateFormat("dd/MM/yyyy").parse(line[0]));
					String desc = line[1].replaceAll("\"", "").replaceAll("  ", " ").trim();
					bankTransaction.setDescription(desc);
					String amt;
					if (line[2].length() == 0) {
						amt = line[3];
					} else {
						amt = line[2];
					}
					bankTransaction.setAmount(new BigDecimal(amt.replaceFirst("\\$", "").replaceFirst(",", "").trim()));
					bankTransaction.setId(new Integer(bankTransactionMapper.getNextId()));
					bankTransaction.setWho(bankStatement.getName());
					bankTransaction.setBank(bankStatement.getBank());
					bankTransaction.setFye(new Integer(bankStatement.getYear()));
					bankTransaction.setUsers(users);
					bankStatement.getTransactions().add(bankTransaction);
				} else {
					bankStatement.setDescription(bankStatement.getDescription() + Arrays.toString(line) + "\n");
				}
			}

		}

	}

	String refactorSuncorpDescription(String desc) {
		String[] types = { "VISA PURCHASE", "DIRECT CREDIT", "EFTPOS DEP", "EFTPOS WDL" };
		for (int i = 0; i < types.length; i++) {
			if (desc.startsWith(types[i])) {
				return desc.replaceFirst(types[i], "").trim() + " - " + types[i];
			}
		}
		return desc;
	}

	public List<BankStatementV> getStatements() {
		return statements;
	}

	public boolean isInserting() {
		return inserting;
	}

}
