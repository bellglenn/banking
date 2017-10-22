package viewmodel;

import model.Category;

public class CategoryStatus {
	private Category category;
	private boolean editingStatus = false;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public boolean isEditingStatus() {
		return editingStatus;
	}
	public void setEditingStatus(boolean editingStatus) {
		this.editingStatus = editingStatus;
		
		
	}
	public CategoryStatus(Category category, boolean editingStatus) {
		super();
		this.category = category;
		this.editingStatus = editingStatus;
	}
	
	
}
