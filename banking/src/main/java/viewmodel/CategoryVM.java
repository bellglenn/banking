package viewmodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
	private List<Category> categories = new ArrayList<>();
	@WireVariable
	private CategoryMapper categoryMapper;
	@WireVariable
	private CurrentFyeUsersMapper currentFyeUsersMapper;
	private boolean adding = false;
	
	@AfterCompose
	public void afterCompose() {
		refresh();
	}
	
	@Command
	public void refresh() {
		categories.clear();
		categories.addAll(categoryMapper.findAll());
		BindUtils.postNotifyChange(null, null, this, "categories");
	}
	
	@NotifyChange({"adding", "categories"})
	@Command
	public void list() {
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
	public void delete(@BindingParam("category") Category category) {
		categoryMapper.delete(category);
		list();
	}
	
	@NotifyChange({"adding", "categories"})
	@Command
	public void insert() {
		if (category.getName() == null || category.getType() == null || category.getUsage() == null) {
			Messagebox.show("Please fill in name, type and usage");
			return;
		}
		category.setName(category.getName().toUpperCase());
		category.setType(category.getType().toUpperCase());
		category.setUsers(currentFyeUsersMapper.findAll().get(0).getUsers());
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
	
}
