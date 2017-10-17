package viewmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import mappers.SessionVarsMapper;
import mappers.UsrGrpMapper;
import model.SessionVars;
import model.UsrGrp;

public class IndexVM {
	private List<String> usrs = new ArrayList<>();
	private List<Integer> fyes = new ArrayList<>();

	private boolean attributesSet = false;
	private UsrGrp usrGrp = new UsrGrp();
	private SessionVars sessionVars = new SessionVars();
	
	
	@WireVariable
	private UsrGrpMapper usrGrpMapper;
	@WireVariable
	private SessionVarsMapper sessionVarsMapper;
	
	private boolean adding = false;
	
	public boolean isAdding() {
		return adding;
	}

	public void setAdding(boolean adding) {
		this.adding = adding;
	}

	@Init
	public void init() {
		for (int i = 2017; i < 2030; i++) {
			fyes.add(new Integer(i));
		}
	}
	
	@AfterCompose
	public void afterCompose() throws Exception {
		setUsers();
		refresh();
	}

	private void setUsers() {
		usrs.clear();
		List<UsrGrp> current = usrGrpMapper.findAll();
		for (UsrGrp usr : current) {
			usrs.add(usr.getUsr());
		}
	}
	
	@NotifyChange({"attributesSet"})
	@Command
	public void refresh() throws Exception {
		if (!sessionVars.isValid()) {
			attributesSet = false;
			return;
		}
		sessionVarsMapper.clear();
		sessionVarsMapper.insert(sessionVars);
		attributesSet = true;
		BindUtils.postGlobalCommand(null, null, "doRefresh", new HashMap<String, Object>());
	}

	@NotifyChange("adding")
	@Command
	public void addUser() {
		adding = true;
	}
	
	@NotifyChange({"users", "adding", "attributesSet"})
	@Command
	public void setUser() throws Exception {
		if (usrGrp.getGrp() == null || usrGrp.getUsr() == null) {
			Messagebox.show("Please select a user and group");
			return;
		}
		usrGrpMapper.insert(usrGrp);
		adding = false;
		setUsers();
		BindUtils.postGlobalCommand(null, null, "doRefresh", new HashMap<String, Object>());
	}

	public List<Integer> getFyes() {
		return fyes;
	}


	public boolean isAttributesSet() {
		return attributesSet;
	}

	public List<String> getUsrs() {
		return usrs;
	}

	public UsrGrp getUsrGrp() {
		return usrGrp;
	}

	public void setUsrGrp(UsrGrp usrGrp) {
		this.usrGrp = usrGrp;
	}

	public SessionVars getSessionVars() {
		return sessionVars;
	}
	
	

}
