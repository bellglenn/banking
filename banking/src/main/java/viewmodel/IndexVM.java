package viewmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import mappers.CurrentFyeUsersMapper;
import model.CurrentFyeUsers;

public class IndexVM extends BaseVM {
	private Integer fye;
	private boolean attributesSet = false;
	
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
	
	@NotifyChange("attributesSet")
	@Command
	public void refresh() throws Exception {
		if (fye == null || getWhoSelected() == null || "*".equalsIgnoreCase(getWhoSelected())) {
			return;
		}
		CurrentFyeUsers current = new CurrentFyeUsers();
		current.setFye(fye);
		current.setUsers(getWhoSelected());
		currentFyeUsersMapper.delete();
		currentFyeUsersMapper.insert(current);
		attributesSet = true;
	}
	
	@NotifyChange("adding")
	@Command
	public void addUser() {
		adding = true;
	}
	
	@NotifyChange({"users", "adding", "attributesSet"})
	@Command
	public void setUser() throws Exception {
		getWhos().add(getWhoSelected());
		adding = false;
		refresh();
		BindUtils.postGlobalCommand(null, null, "doRefresh", new HashMap<String, Object>());
	}
	
	
	public List<Integer> getYears() {
		return years;
	}


	public Integer getFye() {
		return fye;
	}
	public void setFye(Integer fye) {
		this.fye = fye;
	}

	public boolean isAttributesSet() {
		return attributesSet;
	}

}
