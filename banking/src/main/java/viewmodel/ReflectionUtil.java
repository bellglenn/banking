package viewmodel;

import java.lang.reflect.Field;
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
				String val = (String) methods[i].invoke(value);
				String select = (String) methods[i].invoke(selected);
				if (val != null && select != null) {
					if (!val.equalsIgnoreCase(select)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static boolean fieldsEqualOrNull(Object value, Object selected) throws Exception {
		if (value == null || selected == null) {
			return true;
		}
		Field[] valFields = value.getClass().getDeclaredFields();
		Field[] selFields = selected.getClass().getDeclaredFields();
		for (int i = 0; i < valFields.length; i++) {
			if (valFields[i].getType().equals(selected.getClass())) {
				Object val = valFields[i].get(value);
				for (Field field : selFields) {
					if (valFields[i].getName().equals(field.getName())) {
						Object select = field.get(selected);
						if (val == null && select == null) {
							return true;
						}
						if (val != null && select != null) {
							if (val.equals(select)) {
								return true;
							}
						}

					}
				}
			}
		}
		return false;
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
					String val = (String) methods[i].invoke(t);
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
