package viewmodel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import mappers.UserMapper;
import model.BankTransaction;
import model.Session;
import model.User;

public class IndexVM  {
	private List<String> usrs = new ArrayList<>();
	private List<Integer> fyes = new ArrayList<>();

	private boolean attributesSet = false;
	private User user = new User();
	private Session session = new Session();
	private boolean loggedIn = false;
	private String password;
	private String status;
	private boolean groupVisible = false;

	@WireVariable
	private UserMapper userMapper;

	@Init
	public void init() {
		for (int i = 2017; i < 2030; i++) {
			fyes.add(new Integer(i));
		}
	}
	
	@AfterCompose
	public void afterCompose() throws Exception {
		refresh();
	}

	@NotifyChange({"attributesSet", "groupVisible"})
	@Command
	public void refresh() throws Exception {
		if (!loggedIn) {
			return;
		}
		usrs.clear();
		List<User> current = userMapper.findUsersForGroup(user.getGrp());
		groupVisible = current.size() > 1;
		for (User curr : current) {
			usrs.add(curr.getUsr());
		}
		BindUtils.postNotifyChange(null, null, this, "usrs");

		if (session.getFye() == null) {
			attributesSet = false;
			return;
		}
		Map<String, Object> args = new HashMap<String, Object>();
		args.put(BaseVM.SESSION, session);
		BindUtils.postGlobalCommand(null, null, "doRefresh", args);
		attributesSet = true;
	}

	@Command
	public void signout() throws Exception {
		user.setLogon(null);
		userMapper.update(user);
		user = new User();
		password = null;
		loggedIn = false;
		session.setUsr(null);
		BindUtils.postNotifyChange(null, null, this, "session");
		BindUtils.postNotifyChange(null, null, this, "password");
		BindUtils.postNotifyChange(null, null, this, "user");
		BindUtils.postNotifyChange(null, null, this, "loggedIn");
	}

	@Command
	public void signup() {
		Map<String, BankTransaction> map = new HashMap<>();
		final Window dialog = (Window) Executions.createComponents("zul/user.zul", null, null);
		dialog.doModal();
	}

	@Command
	public void signin() throws Exception {
		if (session.getUsr() == null || password == null) {
			Messagebox.show("Please fill in user and password");
			return;
		}
		user = userMapper.find(session.getUsr());
		if (user == null) {
			Messagebox.show("User \"" + session.getUsr() + " not found.");
		}
		if (user.getPwd() == null) {
			Messagebox.show("No password for " + session.getUsr() + "?");
			user.setPwd(password);
		}
		if (!EncryptionUtil.encrypt(password).equals(user.getPwd())) {
			Messagebox.show("Invalid user or password.");
			return;
		}
		Timestamp time = new Timestamp(System.currentTimeMillis());
		user.setLogon(time);
		userMapper.update(user);
		loggedIn = true;
		BindUtils.postNotifyChange(null, null, this, "loggedIn");
		status = session.getUsr() + " logged in " + time;
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

	public boolean isGroupVisible() {
		return groupVisible;
	}

}
