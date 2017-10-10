package viewmodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;
import org.zkoss.bind.annotation.GlobalCommand;

public abstract class BaseVM {
	
	@GlobalCommand
	public void doRefresh() throws Exception {
		refresh();
	}

	private Set<String> whos = new HashSet<>();
	{ 
		whos.add(null);
	}

	public Set<String> getWhos() {
		return whos;
	}

	protected <T> List<T> filter(List<T> current, T selection) throws Exception {
		if (selection == null) {
			return current;
		}
		List<T> list = new ArrayList<>();
		for (T t : current) {
			if (ReflectionUtil.sameStringGettersEqualOrNull(t, selection)) {
				list.add(t);
			}
		}
		return list;
	}

	public abstract void refresh() throws Exception;

}
