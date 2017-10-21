package model;

import java.math.BigDecimal;

public class TransactionSummary extends Session {
	
	private String type;
	private String category;
	private BigDecimal amount;
	private BigDecimal deductable;
	
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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getDeductable() {
		return deductable;
	}
	public void setDeductable(BigDecimal deductable) {
		this.deductable = deductable;
	}
}
