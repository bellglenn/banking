package viewmodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import mappers.BankTransactionMapper;
import model.TransactionSummary;

public class SummaryVM extends BaseVM {

	@WireVariable
	private BankTransactionMapper bankTransactionMapper;
	private List<TransactionSummary> summaries = new ArrayList<>();
	private List<TransactionSummary> usrsums = new ArrayList<>();
	private Set<String> types = new HashSet<>();
	private Set<String> categories = new HashSet<>();
	private TransactionSummary filter = new TransactionSummary();
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws Exception {
		refresh();
		setAttributeLists();
	}

	private void setAttributeLists() throws Exception {
		clear();
		types.clear();
		types.add(filter.getType());
		categories.clear();
		categories.add(filter.getCategory());
		for (TransactionSummary transactionSummary : summaries) {
			types.add(transactionSummary.getType());
			categories.add(transactionSummary.getCategory());
			getWhos().add(transactionSummary.getWho());
		}
		BindUtils.postNotifyChange(null,  null,  this,  "types");
		BindUtils.postNotifyChange(null,  null,  this,  "categories");
		BindUtils.postNotifyChange(null,  null,  this,  "whos");
	}
	
	@Command
	public void clear() throws Exception {
		filter = new TransactionSummary();
		BindUtils.postNotifyChange(null,  null,  this,  "filter");
	}
	
	
	@Command
	public void refresh() throws Exception {
		summaries.clear();
		summaries.addAll(filter(bankTransactionMapper.summary(), filter));
		usrsums.clear();
		usrsums.addAll(filter(bankTransactionMapper.usersSummary(), filter));
		BindUtils.postNotifyChange(null,  null,  this,  "types");
		BindUtils.postNotifyChange(null,  null,  this,  "summaries");
		BindUtils.postNotifyChange(null,  null,  this,  "usrsums");
		setAttributeLists();
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


	public TransactionSummary getFilter() {
		return filter;
	}

	public List<TransactionSummary> getUsrsums() {
		return usrsums;
	}


}
