package viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import mappers.CatTransLnkMapper;
import mappers.CategoryMapper;
import model.CatTransLnk;
import model.CatTransV;
import model.Category;

public class CatTransLnkVM {

	private CatTransLnk catTransLnk = new CatTransLnk();
	private List<Category> categories = new ArrayList<>();
	private Category category = new Category();

	@WireVariable
	CatTransLnkMapper catTransLnkMapper;
	@WireVariable
	CategoryMapper categoryMapper;

	private Component view;

	@Init
	public void init(@ExecutionArgParam("transaction") CatTransV transaction) {
		catTransLnk.setSearch(transaction.getDescription());
		catTransLnk.setFye(transaction.getFye());
		catTransLnk.setUsr(transaction.getUsr());
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws Exception {
		this.view = view;
		categories.clear();
		categories.addAll(categoryMapper.findAll());
	}

	@Command
	public void add() {
		if (category.getName() == null) {
			Messagebox.show("Please select a category");
			return;
		}
		catTransLnk.setCategory(category.getName());
		catTransLnkMapper.insert(catTransLnk);
		view.detach();
	}

	@Command
	public void cancel() {
		view.detach();
	}

	public CatTransLnk getCatTransLnk() {
		return catTransLnk;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
