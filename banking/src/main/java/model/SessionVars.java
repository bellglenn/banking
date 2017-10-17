package model;

public class SessionVars {
	
	private Integer fye;
	private String usr;
	
	public boolean isValid() {
		return fye != null && usr != null;  
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
