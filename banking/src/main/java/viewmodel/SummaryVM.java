package viewmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import mappers.BankTransactionMapper;
import mappers.CategoryMapper;
import model.Category;
import model.TransactionSummary;

public class SummaryVM extends BaseVM {

	@WireVariable
	private BankTransactionMapper bankTransactionMapper;
	@WireVariable
	private CategoryMapper categoryMapper;
	private List<TransactionSummary> summaries = new ArrayList<>();
	private List<TransactionSummary> grpSummaries = new ArrayList<>();
	private Set<String> types = new TreeSet<>();
	private Set<String> categories = new TreeSet<>();
	private TransactionSummary filter = new TransactionSummary();
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws Exception {
		refresh();
	}

	@Command
	public void clear() throws Exception {
		filter = new TransactionSummary();
		BindUtils.postNotifyChange(null,  null,  this,  "filter");
		refresh();
	}
	
	
	@Command
	public void refresh() throws Exception {
		types.clear();
		categories.clear();
		List<Category> list = categoryMapper.findAll();
		for (Category category : list) {
			types.add(category.getType());
			categories.add(category.getName());
		}
		summaries.clear();
		summaries.addAll(filter(bankTransactionMapper.summary(), filter));
		grpSummaries.clear();
		grpSummaries.addAll(filter(bankTransactionMapper.groupSummary(), filter));
		BindUtils.postNotifyChange(null,  null,  this,  "types");
		BindUtils.postNotifyChange(null,  null,  this,  "categories");
		BindUtils.postNotifyChange(null,  null,  this,  "summaries");
		BindUtils.postNotifyChange(null,  null,  this,  "grpSummaries");
	}

	public List<TransactionSummary> getSummaries() {
		return summaries;
	}

	public Set<String> getTypes() {
		return types;
	}

	

	public Set<String> getCategories() {
		return categories;
	}


	public List<TransactionSummary> getGrpSummaries() {
		return grpSummaries;
	}

	public TransactionSummary getFilter() {
		return filter;
	}

}
