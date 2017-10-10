package viewmodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import mappers.CategoryMapper;
import mappers.CurrentFyeUsersMapper;
import model.Category;

public class CategoryVM extends BaseVM {

	private Category category;
	private Category filter = new Category();
	private List<Category> categories = new ArrayList<>();
	@WireVariable
	private CategoryMapper categoryMapper;
	@WireVariable
	private CurrentFyeUsersMapper currentFyeUsersMapper;
	private boolean adding = false;
	private Set<String> types = new HashSet<>();
	
	@AfterCompose
	public void afterCompose() throws Exception {
		refresh();
	}
	
	@Command
	public void refresh() throws Exception {
		categories.clear();
		categories.addAll(filter(categoryMapper.findAll(), filter));
		for (Category category : categories) {
			types.add(category.getType());
		}
		filter = new Category();
		BindUtils.postNotifyChange(null, null, this, "categories");
		BindUtils.postNotifyChange(null, null, this, "filter");
	}
	
	@NotifyChange({"adding", "categories", "filter"})
	@Command
	public void list() throws Exception {
		adding = false;
		refresh();
	}
	
	@NotifyChange({"adding", "category"})
	@Command
	public void add() {
		adding = true;
		category = new Category();
		category.setDeduction(new BigDecimal(0));
	}
	
	@NotifyChange({"adding", "categories"})
	@Command
	public void delete(@BindingParam("category") Category category) throws Exception {
		categoryMapper.delete(category);
		list();
	}
	
	@NotifyChange({"adding", "categories"})
	@Command
	public void insert() throws Exception {
		if (category.getName() == null || category.getType() == null) {
			Messagebox.show("Please fill in name, type and usage");
			return;
		}
		category.setName(category.getName().toUpperCase());
		category.setType(category.getType().toUpperCase());
		category.setUsers(currentFyeUsersMapper.findAll().get(0).getUsers());
		category.setFye(currentFyeUsersMapper.findAll().get(0).getFye());
		categoryMapper.insert(category);
		list();
	}
	
	public boolean isAdding() {
		return adding;
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

	public Category getFilter() {
		return filter;
	}

	public Set<String> getTypes() {
		return types;
	}
	
	
}
