package viewmodel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReflectionUtil {
	
	public static boolean sameStringGettersEqualOrNull(Object value, Object selected) throws Exception {
		if (value == null || selected == null) {
			return true;
		}
		Method[] methods = value.getClass().getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().startsWith("get") && methods[i].getReturnType().isAssignableFrom(String.class)) {
				String val = (String)methods[i].invoke(value);
				String select = (String)methods[i].invoke(selected);
				if (val != null && select != null) {
					if (!val.equalsIgnoreCase(select)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static <T> Map<String, Set<String>> getStringValuesMap(List<T> list) throws Exception {
		Map<String, Set<String>> map = new HashMap<>();
		if (list.isEmpty()) {
			return map;
		}
		Method[] methods = list.get(0).getClass().getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().startsWith("get") && methods[i].getReturnType().isAssignableFrom(String.class)) {
				String key = methods[i].getName().replaceFirst("get", "").toLowerCase();
				for (T t : list) {
					String val = (String)methods[i].invoke(t);
					if (map.get(key) == null) {
						Set<String> set = new HashSet<>();
						set.add(val);
						map.put(key, set);
					} else {
						map.get(key).add(val);
					}
				}
			}
		}
		return map;
	}

}
