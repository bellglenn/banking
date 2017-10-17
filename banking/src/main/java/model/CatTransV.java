package model;

public class CatTransV extends BankTransaction {
	
	private String search;
	private String lnkcat;
	private boolean editingStatus = false;
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getLnkcat() {
		return lnkcat;
	}
	public void setLnkcat(String lnkcat) {
		this.lnkcat = lnkcat;
	}
	public boolean isEditingStatus() {
		return editingStatus;
	}
	public void setEditingStatus(boolean editingStatus) {
		this.editingStatus = editingStatus;
	}
	
}
