package model;

import java.math.BigDecimal;

public class Category {
	
	private String name;
	private BigDecimal deduction;
	private String type;
	private String users;
	private Integer fye;
	
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
	public Integer getFye() {
		return fye;
	}
	public void setFye(Integer fye) {
		this.fye = fye;
	}

}
