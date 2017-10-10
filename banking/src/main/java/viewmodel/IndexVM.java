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

import mappers.CurrentFyeUsersMapper;
import model.CurrentFyeUsers;

public class IndexVM {
	private List<String> users = new ArrayList<>();
	private boolean attributesSet = false;
	private String usr;
	private Integer fye;
	
	private List<Integer> years = new ArrayList<>();
	
	@WireVariable
	private CurrentFyeUsersMapper currentFyeUsersMapper;
	
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
			years.add(i);
		}
	}
	
	@AfterCompose
	public void afterCompose() throws Exception {
		setUsers();
		refresh();
	}

	private void setUsers() {
		users.clear();
		List<CurrentFyeUsers> current = currentFyeUsersMapper.findAll();
		for (CurrentFyeUsers currentFyeUsers : current) {
			users.add(currentFyeUsers.getUsers());
			if ("Y".equals(currentFyeUsers.getSelected())) {
				fye = currentFyeUsers.getFye();
				usr = currentFyeUsers.getUsers();
			}
		}
		BindUtils.postGlobalCommand(null, null, "doRefresh", new HashMap<String, Object>());
	}
	
	@NotifyChange({"attributesSet"})
	@Command
	public void refresh() throws Exception {
		if (fye == null || usr == null) {
			attributesSet = false;
			return;
		}
		currentFyeUsersMapper.deselectAll();
		currentFyeUsersMapper.updateSelection(newFilter());
		attributesSet = true;
		BindUtils.postGlobalCommand(null, null, "doRefresh", new HashMap<String, Object>());
	}

	private CurrentFyeUsers newFilter() {
		CurrentFyeUsers filter = new CurrentFyeUsers();
		filter.setSelected("Y");
		filter.setFye(fye);
		filter.setUsers(usr);
		return filter;
	}
	
	@NotifyChange("adding")
	@Command
	public void addUser() {
		adding = true;
	}
	
	@NotifyChange({"users", "adding", "attributesSet"})
	@Command
	public void setUser() throws Exception {
		if (fye == null || usr == null) {
			Messagebox.show("Please select a year and user group");
			return;
		}
		currentFyeUsersMapper.deselectAll();
		currentFyeUsersMapper.insert(newFilter());
		adding = false;
		setUsers();
		BindUtils.postGlobalCommand(null, null, "doRefresh", new HashMap<String, Object>());
	}
	
	
	public List<Integer> getYears() {
		return years;
	}


	public boolean isAttributesSet() {
		return attributesSet;
	}

	public List<String> getUsers() {
		return users;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public Integer getFye() {
		return fye;
	}

	public void setFye(Integer fye) {
		this.fye = fye;
	}

	
	
}
