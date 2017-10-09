package viewmodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.bind.annotation.GlobalCommand;

public abstract class BaseVM {
	
	@GlobalCommand
	public void doRefresh() throws Exception {
		refresh();
	}

	private String whoSelected = "*";
	private Set<String> whos = new HashSet<>();
	{ 
		whos.add("Glenn");
		whos.add("Jackson");
		whos.add("Jennie");
		whos.add("Jennie Glenn");
		whos.add("*");
	}

	public Set<String> getWhos() {
		return whos;
	}

	public String getWhoSelected() {
		return whoSelected;
	}

	public void setWhoSelected(String whoSelected) {
		this.whoSelected = whoSelected;
	}

	protected <T> List<T> filter(List<T> current) throws Exception {
		if ("*".equalsIgnoreCase(whoSelected)) {
			return current;
		}
		List<T> list = new ArrayList<>();
		for (T t : current) {
			String who = (String) ReflectionUtil.invokeGetter(t, "getWho");
			if (!StringUtils.isEmpty(who) && who.equalsIgnoreCase(whoSelected)) {
				list.add(t);
			}
		}
		return list;
	}

	public abstract void refresh() throws Exception;

}
