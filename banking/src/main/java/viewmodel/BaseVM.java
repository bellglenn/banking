package viewmodel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

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
	
	protected void export(String fileName, String[] header, List<List<String>> rows) throws Exception {
		File file = FileUtil.write(makeCsvFileName(fileName), header, rows);
		Filedownload.save(file, null);
		Messagebox.show(file.getAbsolutePath() + " saved");
	}
	
	protected String makeCsvFileName(String name) {
		StringBuilder bob = new StringBuilder();
		bob.append(System.getProperty("user.home")).append("/Downloads/");
		bob.append(name).append("_");
		bob.append(session.getUsr());
		bob.append("_");
		bob.append(session.getFye());
		bob.append(".csv");
		return bob.toString();
	}
	

	public Session getSession() {
		return session;
	}

	public abstract void refresh() throws Exception;

}
