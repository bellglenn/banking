package viewmodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import mappers.BankTransactionMapper;
import mappers.CatTransVMapper;
import mappers.CategoryMapper;
import model.BankTransaction;
import model.CatTransV;

public class CatTransVM extends BaseVM {

	private List<CatTransV> transactions = new ArrayList<>();
	private BankTransaction bankTransaction;
	private int nullCount = 0;
	private boolean adding = false;

	@WireVariable
	CatTransVMapper catTransVMapper;
	@WireVariable
	CategoryMapper categoryMapper;
	@WireVariable
	BankTransactionMapper bankTransactionMapper;

	@AfterCompose
	public void afterCompose() throws Exception {
		refresh();
	}

	@Command
	public void addCategoryLookup(@BindingParam("transaction") CatTransV transaction) {
		Map<String, CatTransV> map = new HashMap<>();
		map.put("transaction", transaction);
		final Window dialog = (Window) Executions.createComponents("zul/catTransLnk.zul", null, map);
		dialog.doModal();
	}

	@Command
	public void refresh() throws Exception {
		transactions.clear();
		transactions.addAll(filter(catTransVMapper.findAll()));
		nullCount = catTransVMapper.getNullCategoryCount();
		BindUtils.postNotifyChange(null, null, this, "transactions");
		BindUtils.postNotifyChange(null, null, this, "nullCount");
		BindUtils.postNotifyChange(null, null, this, "filter");
	}
	
	@NotifyChange({"adding", "transactions"})
	@Command
	public void list() throws Exception {
		adding = false;
		refresh();
	}
	
	@NotifyChange({"adding", "bankTransaction"})
	@Command
	public void add() {
		adding = true;
		bankTransaction = new BankTransaction();
	}
	
	@NotifyChange({"adding", "transactions"})
	@Command
	public void insert() throws Exception {
		if (bankTransaction.getWho() == null || bankTransaction.getFye() == null ||
				bankTransaction.getDescription() == null || bankTransaction.getAmount() == null) {
			Messagebox.show("Please fill in name, fin year, description and amount");
			return;
		}
		bankTransaction.setWhen(new SimpleDateFormat("dd/MM/yyyy").parse("30/06/" + bankTransaction.getFye()));
		bankTransaction.setId(bankTransactionMapper.getNextId());
		bankTransactionMapper.insert(bankTransaction);
		list();
	}
	
	public boolean isAdding() {
		return adding;
	}


	public BankTransaction getBankTransaction() {
		return bankTransaction;
	}

	public void setBankTransaction(BankTransaction bankTransaction) {
		this.bankTransaction = bankTransaction;
	}

	public List<CatTransV> getTransactions() {
		return transactions;
	}

	public int getNullCount() {
		return nullCount;
	}

}
