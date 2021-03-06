package viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import mappers.CatTransLnkMapper;
import mappers.CategoryMapper;
import model.CatTransLnk;
import model.Category;

public class LinkVM extends BaseVM {

	private CatTransLnk catTransLnk;
	private List<CatTransLnk> links = new ArrayList<>();
	private List<Category> categories = new ArrayList<>();
	private Category category = new Category();
	@WireVariable
	private CatTransLnkMapper catTransLnkMapper;
	@WireVariable
	CategoryMapper categoryMapper;
	private boolean adding = false;
	
	@Command
	public void download() throws Exception {
		String[] HEADER = new String[]{"usr","fye", "category", "search"};
		export("link", HEADER, cube(catTransLnkMapper.findAll(getSession())));
	}
	
	private List<List<String>> cube(List<CatTransLnk> links) throws Exception {
		List<List<String>> rows =  new ArrayList<List<String>>();
		for (CatTransLnk link : links) {
			List<String> row = new ArrayList<String>();
			row.add(link.getUsr());
			row.add(String.valueOf(link.getFye()));
			row.add(link.getCategory());
			row.add(link.getSearch());
			rows.add(row);
		}
		return rows;
	}
	
	@Command
	public void refresh() {
		links.clear();
		links.addAll(catTransLnkMapper.findAll(getSession()));
		categories.clear();
		categories.addAll(categoryMapper.findAll(getSession()));
		BindUtils.postNotifyChange(null, null, this, "links");
		BindUtils.postNotifyChange(null, null, this, "categories");
	}
	
	@NotifyChange("adding")
	@Command
	public void list() {
		adding = false;
		refresh();
	}
	
	@NotifyChange({"adding", "catTransLnk", "category"})
	@Command
	public void add() {
		adding = true;
		catTransLnk = new CatTransLnk();
		category = new Category();
	}
	
	@NotifyChange({"adding", "links"})
	@Command
	public void delete(@BindingParam("catTransLnk") CatTransLnk catTransLnk) {
		catTransLnkMapper.delete(catTransLnk);
		list();
	}
	
	@NotifyChange({"adding", "links"})
	@Command
	public void insert() {
		if (category.getName() == null || catTransLnk.getSearch() == null) {
			Messagebox.show("Please fill in category and search");
			return;
		}
		catTransLnk.setUsr(getSession().getUsr());
		catTransLnk.setFye(getSession().getFye());
		catTransLnk.setCategory(category.getName());
		catTransLnkMapper.insert(catTransLnk);
		list();
	}
	
	public boolean isAdding() {
		return adding;
	}

	public CatTransLnk getCatTransLnk() {
		return catTransLnk;
	}
	public void setCatTransLnk(CatTransLnk catTransLnk) {
		this.catTransLnk = catTransLnk;
	}
	public List<CatTransLnk> getLinks() {
		return links;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategories() {
		return categories;
	}
	
	
	
}
