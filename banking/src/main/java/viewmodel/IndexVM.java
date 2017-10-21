package viewmodel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import mappers.SessionMapper;
import mappers.UsrGrpMapper;
import model.Session;
import model.UsrGrp;

public class IndexVM {
	private List<String> usrs = new ArrayList<>();
	private List<Integer> fyes = new ArrayList<>();

	private boolean attributesSet = false;
	private UsrGrp user = new UsrGrp();
	private String previousUser;
	private Session session = new Session();
	private boolean loggedIn = false;
	private String password;
	private String status;

	@WireVariable
	private UsrGrpMapper usrGrpMapper;
	@WireVariable
	private SessionMapper sessionMapper;

	@Init
	public void init() {
		for (int i = 2017; i < 2030; i++) {
			fyes.add(new Integer(i));
		}
	}

	@NotifyChange("attributesSet")
	@Command
	public void refresh() throws Exception {
		if (!loggedIn) {
			return;
		}
		usrs.clear();
		List<UsrGrp> current = usrGrpMapper.findUsersInGroup(user.getGrp());
		for (UsrGrp curr : current) {
			usrs.add(curr.getUsr());
		}
		BindUtils.postNotifyChange(null, null, this, "usrs");

		if (session.getFye() == null) {
			attributesSet = false;
			return;
		}
		sessionMapper.clearUsr(previousUser);
		previousUser = session.getUsr();
		sessionMapper.insert(session);
		attributesSet = true;
		Map<String, Object> args = new HashMap<String, Object>();
		args.put(BaseVM.SESSION, session);
		BindUtils.postGlobalCommand(null, null, "doRefresh", args);
	}

	@Command
	public void signout() throws Exception {
		user.setLogon(null);
		usrGrpMapper.update(user);
		for (String usr : usrs) {
			sessionMapper.clearUsr(usr);
		}
		user = new UsrGrp();
		password = null;
		loggedIn = false;
		BindUtils.postNotifyChange(null, null, this, "password");
		BindUtils.postNotifyChange(null, null, this, "user");
		BindUtils.postNotifyChange(null, null, this, "loggedIn");
	}

	@Command
	public void signup() throws Exception {
	}

	@Command
	public void signin() throws Exception {
		if (session.getUsr() == null || password == null) {
			Messagebox.show("Please fill in user and password");
			return;
		}
		user = usrGrpMapper.find(session.getUsr());
		if (user.getPwd() == null) {
			Messagebox.show("No password for " + session.getUsr() + "?");
			user.setPwd(password);
		}
		if (!password.equals(user.getPwd())) {
			Messagebox.show("Invalid user or password.");
			return;
		}
		Timestamp time = new Timestamp(System.currentTimeMillis());
		session.setLogon(time);
		user.setLogon(time);
		usrGrpMapper.update(user);
		previousUser = user.getUsr();
		loggedIn = true;
		BindUtils.postNotifyChange(null, null, this, "loggedIn");
		status = session.getUsr() + " logged in at " + session.getLogon();
		BindUtils.postNotifyChange(null, null, this, "status");
		refresh();
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
		return user;
	}

	public Session getSession() {
		return session;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

}
