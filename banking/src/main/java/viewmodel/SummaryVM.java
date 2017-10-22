package viewmodel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

import mappers.BankTransactionMapper;
import mappers.CategoryMapper;
import mappers.UserMapper;
import model.Category;
import model.TransactionSummary;

public class SummaryVM extends BaseVM {

	@WireVariable
	private BankTransactionMapper bankTransactionMapper;
	@WireVariable
	private CategoryMapper categoryMapper;
	@WireVariable
	private UserMapper userMapper;
	private List<TransactionSummary> summaries = new ArrayList<>();
	private List<TransactionSummary> grpSummaries = new ArrayList<>();
	private Set<String> types = new TreeSet<>();
	private Set<String> categories = new TreeSet<>();
	private TransactionSummary filter = new TransactionSummary();
	
	@Command
	public void clear() throws Exception {
		filter = new TransactionSummary();
		BindUtils.postNotifyChange(null,  null,  this,  "filter");
		refresh();
	}
	
	private static String[] HEADER = new String[]{"usr","fye","type", "category", "amount", "deductable"};
	
	@Command
	public void exportSummary() throws Exception {
		export(makeCsvFileName("summary"), bankTransactionMapper.summary(getSession()));
	}
	
	private String makeCsvFileName(String name) {
		StringBuilder bob = new StringBuilder();
		bob.append(System.getProperty("user.home")).append("/Downloads/");
		bob.append(name).append("_");
		bob.append(getSession().getUsr());
		bob.append("_");
		bob.append(getSession().getFye());
		bob.append(".csv");
		return bob.toString();
	}
	
	@Command
	public void exportGroup() throws Exception {
		String grp = userMapper.find(getSession().getUsr()).getGrp();
		export(makeCsvFileName("group"), bankTransactionMapper.groupSummary(grp, getSession().getFye()));
	}
	
	private void export(String fileName, List<TransactionSummary> list) throws Exception {
		List<List<String>> rows = cube(list);
		File file = FileUtil.write(fileName, HEADER, rows);
		Filedownload.save(file, null);
		Messagebox.show(file.getAbsolutePath() + " saved");
	}
	
	private List<List<String>> cube(List<TransactionSummary> summaries) throws Exception {
		List<List<String>> rows =  new ArrayList<List<String>>();
		for (TransactionSummary summary : summaries) {
			List<String> row = new ArrayList<String>();
			row.add(summary.getUsr());
			row.add(String.valueOf(summary.getFye()));
			row.add(summary.getType());
			row.add(summary.getCategory());
			row.add(String.valueOf(summary.getAmount()));
			row.add(String.valueOf(summary.getDeductable()));
			rows.add(row);
		}
		return rows;
	}
	
	
	@Command
	public void refresh() throws Exception {
		types.clear();
		categories.clear();
		List<Category> list = categoryMapper.findAll(getSession());
		for (Category category : list) {
			types.add(category.getType());
			categories.add(category.getName());
		}
		summaries.clear();
		if (getSession() == null) {
			Messagebox.show("wtf session is null");
			return;
		}
		summaries.addAll(filter(bankTransactionMapper.summary(getSession()), filter));
		grpSummaries.clear();
		String grp = userMapper.find(getSession().getUsr()).getGrp();
		grpSummaries.addAll(filter(bankTransactionMapper.groupSummary(grp, getSession().getFye()), filter));
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
