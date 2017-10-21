package viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;

import model.Session;

public abstract class BaseVM {
	
	public static String SESSION = "session";
	private Session session;

	@GlobalCommand
	public void doRefresh(@BindingParam("session") Session session) throws Exception {
		this.session = session;
		refresh();
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
	
	
	

	public Session getSession() {
		return session;
	}

	public abstract void refresh() throws Exception;

}
