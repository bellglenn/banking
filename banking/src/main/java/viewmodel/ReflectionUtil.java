package viewmodel;

import java.lang.reflect.Method;

public class ReflectionUtil {
	
	public static Object invokeGetter(Object obj, String getter) throws Exception {
		Class clazz = obj.getClass();
		Method method = clazz.getMethod(getter);
		if (method != null) {
			return method.invoke(obj);
		}
		return null;
		
	}

}
