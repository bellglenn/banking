package viewmodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import mappers.CategoryMapper;
import model.Category;

public class CategoryVM extends BaseVM {

	private CategoryStatus category;
	private String type;
	private ListModelList<CategoryStatus> categories = new ListModelList<>();
	@WireVariable
	private CategoryMapper categoryMapper;
	private boolean adding = false;
	private Set<String> types = new HashSet<>();

	@Command
	public void download() throws Exception {
		String[] HEADER = new String[]{"usr","fye", "category", "search"};
		export("category", HEADER, cube(categoryMapper.findAll(getSession())));
	}
	
	private List<List<String>> cube(List<Category> list) throws Exception {
		List<List<String>> rows =  new ArrayList<List<String>>();
		for (Category item : list) {
			List<String> row = new ArrayList<String>();
			row.add(item.getUsr());
			row.add(String.valueOf(item.getFye()));
			row.add(item.getName());
			row.add(item.getType());
			row.add(String.valueOf(item.getDeduction()));
			rows.add(row);
		}
		return rows;
	}
	
	@Command
	public void refresh() throws Exception {
		categories.clear();
		List<Category> list = categoryMapper.findAll(getSession());
		for (Category category : list) {
			if (type == null) {
				categories.add(new CategoryStatus(category, false));
			} else {
				if (type.equals(category.getType())) {
					categories.add(new CategoryStatus(category, false));
				}
			}
			types.add(category.getType());
		}
		BindUtils.postNotifyChange(null, null, this, "categories");
		BindUtils.postNotifyChange(null, null, this, "types");
	}

	@Command
	public void changeEditableStatus(@BindingParam("category") CategoryStatus category) {
		category.setEditingStatus(!category.isEditingStatus());
		refreshRowTemplate(category);
	}

	@Command
	public void confirm(@BindingParam("category") CategoryStatus category) {
		changeEditableStatus(category);
		categoryMapper.update(category.getCategory());
		refreshRowTemplate(category);
	}

	public void refreshRowTemplate(CategoryStatus category) {
		// replace the element in the collection by itself to trigger a model
		// update
		categories.set(categories.indexOf(category), category);
		BindUtils.postNotifyChange(null, null, this, "categories");
	}

	@NotifyChange("adding")
	@Command
	public void list() throws Exception {
		adding = false;
		refresh();
	}

	@NotifyChange({ "adding", "category" })
	@Command
	public void add() {
		adding = true;
		category = new CategoryStatus(new Category(), true);
		category.getCategory().setDeduction(new BigDecimal(0));
	}

	@NotifyChange({ "adding", "categories" })
	@Command
	public void delete(@BindingParam("category") CategoryStatus category) throws Exception {
		try {
			categoryMapper.delete(category.getCategory());
		} catch (Exception e) {
			Messagebox.show("All links for \"" + category.getCategory().getName() + "\" must be deleted beforehand");
		}
		list();
	}

	@NotifyChange({ "adding", "categories" })
	@Command
	public void insert() throws Exception {
		if (category.getCategory().getName() == null || category.getCategory().getType() == null
				|| category.getCategory().getDeduction() == null) {
			Messagebox.show("Please fill in name, type and deduction");
			return;
		}
		category.getCategory().setName(category.getCategory().getName().toUpperCase());
		category.getCategory().setType(category.getCategory().getType().toUpperCase());
		category.getCategory().setUsr(getSession().getUsr());
		category.getCategory().setFye(getSession().getFye());
		categoryMapper.insert(category.getCategory());
		list();
	}

	public boolean isAdding() {
		return adding;
	}

	public CategoryStatus getCategory() {
		return category;
	}

	public void setCategory(CategoryStatus category) {
		this.category = category;
	}

	public List<CategoryStatus> getCategories() {
		return categories;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<String> getTypes() {
		return types;
	}

}
