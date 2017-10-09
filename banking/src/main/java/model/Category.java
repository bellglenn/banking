package model;

import java.math.BigDecimal;

public class Category {
	
	private String name;
	private BigDecimal deduction;
	private String type;
	private String users;
	private String usage;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getDeduction() {
		return deduction;
	}
	public void setDeduction(BigDecimal deduction) {
		this.deduction = deduction;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	
	

}
