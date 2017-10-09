package model;

import java.math.BigDecimal;
import java.util.Date;

public class CatTransV implements Comparable<CatTransV> {
	
	private String category;
	private String search;
	private Date when;
	private String description;
	private BigDecimal amount;
	private String who;
	private String bank;
	private Integer fye;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public Date getWhen() {
		return when;
	}
	public void setWhen(Date when) {
		this.when = when;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public Integer getFye() {
		return fye;
	}
	public void setFye(Integer fye) {
		this.fye = fye;
	}
	@Override
	public int compareTo(CatTransV catTransV) {
		return catTransV.getDescription().compareTo(description);
	}
	
	
}
