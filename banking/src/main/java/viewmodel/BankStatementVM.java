package viewmodel;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import mappers.SessionVarsMapper;
import model.BankStatement;
import model.BankTransaction;

public class BankStatementVM extends BaseVM {

	public static final String ZUL = "zul/bankStatement.zul";

	private BankStatement statement = new BankStatement();
	private List<BankStatement> statements = new ArrayList<>();

	// NB follow the naming convention to avoid null pointer exceptions
	@WireVariable
	BankTransactionMapper bankTransactionMapper;
	@WireVariable
	private SessionVarsMapper sessionVarsMapper;
	
	private File file;
	private List<BankTransaction> transactions = new ArrayList<BankTransaction>();
	private boolean inserting = false;
	
	@AfterCompose
	public void afterCompose() throws Exception {
		refresh();
	}

	@Command
	public void refresh() throws Exception {
		statements.clear();
		statements.addAll(bankTransactionMapper.getStatements());
		inserting = false;
		statement = new BankStatement();
		statement.setFye(sessionVarsMapper.getSessionVars().getFye());
		statement.setUsr(sessionVarsMapper.getSessionVars().getUsr());
		transactions.clear();
		BindUtils.postNotifyChange(null, null, this, "statements");
		BindUtils.postNotifyChange(null, null, this, "inserting");
		BindUtils.postNotifyChange(null, null, this, "statement");
	}

	public BankStatement getStatement() {
		return statement;
	}
	

	public List<BankStatement> getStatements() {
		return statements;
	}

	@Command
	@NotifyChange("statements")
	public void deleteStatement(@BindingParam("statement") BankStatement statement) {
		if (invalidStatement(statement)) {
			Messagebox.show("Please select account and bank");
			return;
		}
		bankTransactionMapper.deleteStatement(statement);
		statements.clear();
		statements.addAll(bankTransactionMapper.getStatements());
	}

	private boolean invalidStatement(BankStatement statement) {
		return statement.getBank() == null || statement.getAccount() == null;
	}

	@NotifyChange({"statement", "inserting"})
	@Command
	public void insertStatement() throws Exception {
		inserting = true;
	}
	
	@NotifyChange({ "statements", "statement", "inserting" })
	@Command
	public void loadStatement(@BindingParam("evt") UploadEvent evt) throws Exception {
		if (evt != null && evt.getMedia() != null) {
			Media media = evt.getMedia();
			if (media.getName().endsWith("csv")) {
				file = FileUtil.writeFile(media);
			} else {
				Messagebox.show("Please select a csv file");
			}
		}
		if (invalidStatement(statement)) {
			Messagebox.show("Please fill in all the fields");
			return;
		}
		extractTransactions();

		Messagebox.show(
				"Insert transactions from " + file.getAbsolutePath() + " for "
						+ statement.getAccount() + " " + statement.getBank() + " ?",
				"Confirm Dialog", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener<Event>() {
					@Override
					public void onEvent(Event evt) throws Exception {
						if (evt.getName().equals("onOK")) {
							int i = 0;
							bankTransactionMapper.deleteStatement(statement);
							for (BankTransaction bt : transactions) {
								bankTransactionMapper.insert(bt);
								i++;
							}
							Messagebox.show(i + " transactions inserted.");
							refresh();
						}
					}
				});

	}

	void extractTransactions() throws Exception {
		List<String[]> lines = FileUtil.readCsvFile(file);
		// Messagebox.show("Lines read = " + lines.size());
		int lineCount = 0;
		for (String[] line : lines) {
			lineCount++;
			if ("SUNCORP".equals(statement.getBank().toUpperCase())) {
				if (lineCount > 2) {
					if (line[0] == null || line[1] == null || line[2] == null) {
						throw new Exception("when, description or amount is null at line " + lineCount);
					}
					BankTransaction bankTransaction = new BankTransaction();
					bankTransaction.setTdate(new SimpleDateFormat("dd/MM/yyyy").parse(line[0]));
					String desc = line[1].replaceAll("\"", "").replaceAll("  ", " ").replaceAll("  ", " ").trim();
					desc = refactorSuncorpDescription(desc);
					bankTransaction.setDescription(desc);
					String amt = line[2].replaceFirst("\\$", "").replaceFirst(",", "").trim();
					bankTransaction.setAmount(new BigDecimal(amt));
					addTransaction(bankTransaction);
				} 
			}
			if ("ING".equals(statement.getBank().toUpperCase())) {
				if (lineCount > 1) {
					if (line[0] == null || line[1] == null) {
						throw new Exception("when, description or amount is null at line " + lineCount);
					}
					BankTransaction bankTransaction = new BankTransaction();
					bankTransaction.setTdate(new SimpleDateFormat("dd/MM/yyyy").parse(line[0]));
					String desc = line[1].replaceAll("\"", "").replaceAll("  ", " ").trim();
					bankTransaction.setDescription(desc);
					String amt;
					if (line[2].length() == 0) {
						amt = line[3];
					} else {
						amt = line[2];
					}
					bankTransaction.setAmount(new BigDecimal(amt.replaceFirst("\\$", "").replaceFirst(",", "").trim()));
					addTransaction(bankTransaction);
				} 
			}

		}

	}

	private void addTransaction(BankTransaction bankTransaction) {
		bankTransaction.setAccount(statement.getAccount());
		bankTransaction.setBank(statement.getBank());
		bankTransaction.setFye(statement.getFye());
		bankTransaction.setUsr(statement.getUsr());
		transactions.add(bankTransaction);
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

	public boolean isInserting() {
		return inserting;
	}

}
