package model;

import java.sql.Timestamp;

public class User {
	
	private String usr;
	private String grp;
	private String pwd;
	private Timestamp logon;

	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
	public String getGrp() {
		return grp;
	}
	public void setGrp(String grp) {
		this.grp = grp;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Timestamp getLogon() {
		return logon;
	}
	public void setLogon(Timestamp logon) {
		this.logon = logon;
	}
	
	
}
