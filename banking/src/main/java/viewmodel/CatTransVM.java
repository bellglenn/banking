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
import mappers.CatTransVMapper;
import mappers.CategoryMapper;
import mappers.SessionVarsMapper;
import model.CatTransV;
import model.Category;

public class CatTransVM extends BaseVM {

	private List<CatTransV> transactions = new ArrayList<>();
	private CatTransV transaction;
	private int nullCount = 0;
	private boolean adding = false;
	private Set<String> categories = new TreeSet<>();
	private Set<String> allCategories = new TreeSet<>();
	private CatTransV filter = new CatTransV();

	@WireVariable
	CatTransVMapper catTransVMapper;
	@WireVariable
	CategoryMapper categoryMapper;
	@WireVariable
	BankTransactionMapper bankTransactionMapper;
	@WireVariable
	SessionVarsMapper sessionVarsMapper;

	@Command
	public void changeEditableStatus(@BindingParam("transaction") CatTransV transaction) {
		transaction.setEditingStatus(!transaction.isEditingStatus());
		refreshRowTemplate(transaction);
	}

	@Command
	public void confirm(@BindingParam("transaction") CatTransV transaction) {
		changeEditableStatus(transaction);
		bankTransactionMapper.delete(transaction.getId());
		bankTransactionMapper.insert(transaction);
		refreshRowTemplate(transaction);
	}

	public void refreshRowTemplate(CatTransV transaction) {
		transactions.set(transactions.indexOf(transaction), transaction);
		BindUtils.postNotifyChange(null, null, this, "transactions");
	}

	@Command
	public void addLink(@BindingParam("transaction") CatTransV transaction) throws Exception {
		Map<String, CatTransV> map = new HashMap<>();
		map.put("transaction", transaction);
		final Window dialog = (Window) Executions.createComponents("zul/catTransLnk.zul", null, map);
		dialog.doModal();
		refresh();
	}

	@Command
	public void clear() throws Exception {
		filter = new CatTransV();
		BindUtils.postNotifyChange(null, null, this, "filter");
		refresh();
	}

	@Command
	public void clearCat(@BindingParam("transaction") CatTransV transaction) throws Exception {
		transaction.setCategory(null);
		catTransVMapper.saveCategory(transaction);
		refresh();
	}

	@Command
	public void refresh() throws Exception {
		List<Category> cats = categoryMapper.findAll();
		for (Category category : cats) {
			allCategories.add(category.getName());
		}
		categories.clear();
		List<CatTransV> list = catTransVMapper.findAll();
		List<CatTransV> searched = new ArrayList<>();
		for (CatTransV cat : list) {
			if (cat.getCategory() == null && cat.getLnkcat() != null) {
				cat.setCategory(cat.getLnkcat());
				catTransVMapper.saveCategory(cat);
			}
			if (cat.getCategory() != null) {
				categories.add(cat.getCategory());
			}
			if (filter.getCategory() == null && filter.getDescription() == null) {
				searched.add(cat);
			} else {
				if (((filter.getCategory() != null
						&& StringUtils.equals(cat.getCategory(), filter.getCategory()))
						|| filter.getCategory() == null)
						&& ((filter.getDescription() != null
								&& StringUtils.contains(cat.getDescription(), filter.getDescription()))
								|| filter.getDescription() == null)) {
					searched.add(cat);
				}
			}
		}
		transactions.clear();
		transactions.addAll(searched);
		nullCount = catTransVMapper.getNullCategoryCount();
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
		transaction = new CatTransV();
		transaction.setUsr(sessionVarsMapper.getSessionVars().getUsr());
		transaction.setFye(sessionVarsMapper.getSessionVars().getFye());
		transaction.setAccount("Cash");
		transaction.setBank("Cash");
	}

	@NotifyChange("adding")
	@Command
	public void insert() throws Exception {
		if (transaction.getAccount() == null || transaction.getDescription() == null || transaction.getAmount() == null
				|| transaction.getCategory() == null) {
			Messagebox.show("Please fill in name, fin year, description and amount");
			return;
		}
		transaction.setTdate(new SimpleDateFormat("dd/MM/yyyy").parse("30/06/" + transaction.getFye()));
		transaction.setAmount(transaction.getAmount().negate());
		bankTransactionMapper.insert(transaction);
		list();
	}

	public boolean isAdding() {
		return adding;
	}

	public CatTransV getTransaction() {
		return transaction;
	}

	public List<CatTransV> getTransactions() {
		return transactions;
	}

	public int getNullCount() {
		return nullCount;
	}

	public Set<String> getCategories() {
		return categories;
	}

	public CatTransV getFilter() {
		return filter;
	}

	public Set<String> getAllCategories() {
		return allCategories;
	}

}
