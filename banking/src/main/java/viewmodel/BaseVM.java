package viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.GlobalCommand;

public abstract class BaseVM {
	
	@GlobalCommand
	public void doRefresh() throws Exception {
		refresh();
	}

	protected <T> List<T> filter(List<T> current, T selection) throws Exception {
		if (selection == null) {
			return current;
		}
		List<T> list = new ArrayList<>();
		for (T t : current) {
			if (ReflectionUtil.fieldsEqualOrNull(t, selection)) {
				list.add(t);
			}
		}
		return list;
	}
	

	public abstract void refresh() throws Exception;

}
