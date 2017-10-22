package viewmodel;

import model.BankTransaction;

public class TransactionStatus {
	
	private boolean editingStatus = false;
	private BankTransaction transaction;
	
	public boolean isEditingStatus() {
		return editingStatus;
	}
	public void setEditingStatus(boolean editingStatus) {
		this.editingStatus = editingStatus;
	}
	public BankTransaction getTransaction() {
		return transaction;
	}
	public void setTransaction(BankTransaction transaction) {
		this.transaction = transaction;
	}
	
	
}
