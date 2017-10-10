package model;

import java.math.BigDecimal;
import java.util.Date;

public class BankTransaction {

	private Integer id;
	private String who;
	private Date when;
	private String bank;
	private String description;
	private BigDecimal amount;
	private Integer fye;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
