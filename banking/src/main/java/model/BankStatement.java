package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BankStatement {
	private String bank;
	private String name;
	private String year;
	private String users;
	private File file;
	private List<BankTransaction> transactions = new ArrayList<BankTransaction>();
	private String description = "";

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFilePath() {
		return file.getAbsolutePath();
	}

	public boolean isReadable() {
		return bank != null && name != null && year != null && file != null;
	}

	public List<BankTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<BankTransaction> transactions) {
		this.transactions = transactions;
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public String toString() {
		StringBuilder bob = new StringBuilder();
		bob.append(name).append(" ").append(year).append("\n");
		for (BankTransaction transaction : transactions) {
			bob.append(transaction.toString());
		}

		return bob.toString();
	}
	
}
