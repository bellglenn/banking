package viewmodel;

import org.apache.commons.lang.StringUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import mappers.UserMapper;
import model.User;

public class UserVM {

	private User user = new User();
	private String confirm;

	@WireVariable
	UserMapper userMapper;

	private Component view;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) throws Exception {
		this.view = view;
	}

	@Command
	public void add() throws Exception {
		if (user.getUsr() == null) {
			Messagebox.show("Please enter a user name");
			return;
		}
		if (user.getGrp() == null) {
			Messagebox.show("Please enter a user group");
			return;
		}
		if (user.getPwd() == null || confirm == null) {
			Messagebox.show("Please enter password and confirmation");
			return;
		}
		if (!StringUtils.equals(user.getPwd(), confirm)) {
			Messagebox.show("Password and confirm do not match");
			return;
		}
		user.setPwd(EncryptionUtil.encrypt(user.getPwd()));
		userMapper.insert(user);
		view.detach();
	}

	@Command
	public void cancel() {
		view.detach();
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public User getUser() {
		return user;
	}
	
	

}
