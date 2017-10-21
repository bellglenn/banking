package model;

import java.sql.Timestamp;

public class Session {
	
	private Integer fye;
	private String usr;
	private Timestamp logon;

	public Timestamp getLogon() {
		return logon;
	}
	public void setLogon(Timestamp logon) {
		this.logon = logon;
	}
	
	public Integer getFye() {
		return fye;
	}
	public void setFye(Integer fye) {
		this.fye = fye;
	}
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
}
