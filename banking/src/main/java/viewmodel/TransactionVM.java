package viewmodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import mappers.BankTransactionMapper;
import mappers.CategoryMapper;
import model.BankTransaction;
import model.Category;

public class TransactionVM extends BaseVM {

	private List<TransactionStatus> transactions = new ArrayList<>();
	private TransactionStatus transaction;
	private int nullCount = 0;
	private boolean adding = false;
	private Set<String> categories = new TreeSet<>();
	private Set<String> allCategories = new TreeSet<>();
	private BankTransaction filter = new BankTransaction();

	@WireVariable
	CategoryMapper categoryMapper;
	@WireVariable
	BankTransactionMapper bankTransactionMapper;

	@Command
	public void changeEditableStatus(@BindingParam("transaction") TransactionStatus transaction) {
		transaction.setEditingStatus(!transaction.isEditingStatus());
		refreshRowTemplate(transaction);
	}

	@Command
	public void confirm(@BindingParam("transaction") TransactionStatus transaction) {
		changeEditableStatus(transaction);
		bankTransactionMapper.delete(transaction.getTransaction().getId());
		bankTransactionMapper.insert(transaction.getTransaction());
		refreshRowTemplate(transaction);
	}

	public void refreshRowTemplate(TransactionStatus transaction) {
		transactions.set(transactions.indexOf(transaction), transaction);
		BindUtils.postNotifyChange(null, null, this, "transactions");
	}

	@Command
	public void addLink(@BindingParam("transaction") TransactionStatus transaction) throws Exception {
		Map<String, BankTransaction> map = new HashMap<>();
		map.put("transaction", transaction.getTransaction());
		final Window dialog = (Window) Executions.createComponents("zul/catTransLnk.zul", null, map);
		dialog.doModal();
		refresh();
	}

	@Command
	public void clear() throws Exception {
		filter = new BankTransaction();
		BindUtils.postNotifyChange(null, null, this, "filter");
		refresh();
	}

	@Command
	public void clearCat(@BindingParam("transaction") TransactionStatus transaction) throws Exception {
		transaction.getTransaction().setCategory(null);
		bankTransactionMapper.update(transaction.getTransaction());
		refresh();
	}

	@Command
	public void refresh() throws Exception {
		List<Category> cats = categoryMapper.findAll(getSession());
		for (Category category : cats) {
			allCategories.add(category.getName());
		}
		categories.clear();
		List<BankTransaction> list = bankTransactionMapper.findAll(getSession());
		List<TransactionStatus> searched = new ArrayList<>();
		for (BankTransaction transaction : list) {
			TransactionStatus transactionStatus = new TransactionStatus();
			transactionStatus.setTransaction(transaction);
			if (transaction.getCategory() != null) {
				categories.add(transaction.getCategory());
			}
			if (filter.getCategory() == null && filter.getDescription() == null) {
				searched.add(transactionStatus);
			} else {
				if (((filter.getCategory() != null
						&& StringUtils.equals(transaction.getCategory(), filter.getCategory()))
						|| filter.getCategory() == null)
						&& ((filter.getDescription() != null
								&& StringUtils.contains(transaction.getDescription(), filter.getDescription()))
								|| filter.getDescription() == null)) {
					searched.add(transactionStatus);
				}
			}
		}
		transactions.clear();
		transactions.addAll(searched);
		nullCount = bankTransactionMapper.getNullCategoryCount();
		BindUtils.postNotifyChange(null, null, this, "transactions");
		BindUtils.postNotifyChange(null, null, this, "nullCount");
		BindUtils.postNotifyChange(null, null, this, "categories");
		BindUtils.postNotifyChange(null, null, this, "allCategories");
		BindUtils.postNotifyChange(null, null, this, "transaction");
	}

	@NotifyChange("adding")
	@Command
	public void list() throws Exception {
		adding = false;
		refresh();
	}

	@NotifyChange({ "adding", "transaction" })
	@Command
	public void add() {
		adding = true;
		transaction = new TransactionStatus();
		transaction.getTransaction().setUsr(getSession().getUsr());
		transaction.getTransaction().setFye(getSession().getFye());
		transaction.getTransaction().setAccount("Cash");
		transaction.getTransaction().setBank("Cash");
	}

	@NotifyChange("adding")
	@Command
	public void insert() throws Exception {
		if (transaction.getTransaction().getAccount() == null 
				|| transaction.getTransaction().getDescription() == null || transaction.getTransaction().getAmount() == null
				|| transaction.getTransaction().getCategory() == null) {
			Messagebox.show("Please fill in name, fin year, description and amount");
			return;
		}
		transaction.getTransaction().setTdate(new SimpleDateFormat("dd/MM/yyyy").parse("30/06/" + transaction.getTransaction().getFye()));
		transaction.getTransaction().setAmount(transaction.getTransaction().getAmount().negate());
		bankTransactionMapper.insert(transaction.getTransaction());
		list();
	}

	public boolean isAdding() {
		return adding;
	}

	public TransactionStatus getTransaction() {
		return transaction;
	}

	public List<TransactionStatus> getTransactions() {
		return transactions;
	}

	public int getNullCount() {
		return nullCount;
	}

	public Set<String> getCategories() {
		return categories;
	}

	public BankTransaction getFilter() {
		return filter;
	}

	public Set<String> getAllCategories() {
		return allCategories;
	}

}
