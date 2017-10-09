package model;

import java.math.BigDecimal;

public class TransactionSummary {
	
	private String type;
	private String category;
	private String who;
	private String usage;
	private Integer fye;
	private BigDecimal amount;
	private BigDecimal tax;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	public Integer getFye() {
		return fye;
	}
	public void setFye(Integer fye) {
		this.fye = fye;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getTax() {
		return tax;
	}
	public void setTax(BigDecimal taxExpense) {
		this.tax = taxExpense;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	
	
	

}
