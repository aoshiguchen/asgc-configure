package com.github.aoshiguchen.framework.configure;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassUtil {
		
	private static final Set<Class<?>> baseClass = new HashSet<>();
	
	private static final Map<Class<?>,Class<?>> packClassMap = new HashMap<>();
	
	private static final Map<Class<?>,Class<?>> baseClassMap = new HashMap<>();
	
	static{
		baseClass.add(byte.class);
		baseClass.add(short.class);
		baseClass.add(int.class);
		baseClass.add(long.class);
		baseClass.add(float.class);
		baseClass.add(double.class);
		baseClass.add(char.class);
		baseClass.add(boolean.class);
		
		baseClass.add(Byte.class);
		baseClass.add(Short.class);
		baseClass.add(Integer.class);
		baseClass.add(Long.class);
		baseClass.add(Float.class);
		baseClass.add(Double.class);
		baseClass.add(Character.class);
		baseClass.add(Boolean.class);
		
		baseClass.add(String.class);
		
		//----------------------------------
		
		packClassMap.put(byte.class, Byte.class);
		packClassMap.put(short.class, Short.class);
		packClassMap.put(int.class, Integer.class);
		packClassMap.put(long.class, Long.class);
		packClassMap.put(float.class, Float.class);
		packClassMap.put(double.class, Double.class);
		packClassMap.put(char.class, Character.class);
		packClassMap.put(boolean.class, Boolean.class);
		
		packClassMap.put(Byte.class, Byte.class);
		packClassMap.put(Short.class, Short.class);
		packClassMap.put(Integer.class, Integer.class);
		packClassMap.put(Long.class, Long.class);
		packClassMap.put(Float.class, Float.class);
		packClassMap.put(Double.class, Double.class);
		packClassMap.put(Character.class, Character.class);
		packClassMap.put(Boolean.class, Boolean.class);
		
		packClassMap.put(String.class, String.class);
		
		//----------------------------------
		
		baseClassMap.put(byte.class, byte.class);
		baseClassMap.put(short.class, short.class);
		baseClassMap.put(int.class, int.class);
		baseClassMap.put(long.class, long.class);
		baseClassMap.put(float.class, float.class);
		baseClassMap.put(double.class, double.class);
		baseClassMap.put(char.class, char.class);
		baseClassMap.put(boolean.class, boolean.class);
		
		baseClassMap.put(Byte.class, byte.class);
		baseClassMap.put(Short.class, short.class);
		baseClassMap.put(Integer.class, int.class);
		baseClassMap.put(Long.class, long.class);
		baseClassMap.put(Float.class, float.class);
		baseClassMap.put(Double.class, double.class);
		baseClassMap.put(Character.class, char.class);
		baseClassMap.put(Boolean.class, boolean.class);
		
		baseClassMap.put(String.class, String.class);
		
	}
	
	public static Class<?> getPackClass(Class<?> clazz){
		
		if(packClassMap.containsKey(clazz)){
			return packClassMap.get(clazz);
		}
		
		return clazz;
	}
	
	public static Class<?> getBaseClass(Class<?> clazz){
		
		if(baseClassMap.containsKey(clazz)){
			return baseClassMap.get(clazz);
		}
		
		return clazz;
	}
	
	public static <T> T valueOf(Class<T> clazz,Object obj){
		T res = null;
		
		clazz = (Class<T>)getPackClass(clazz);
		
		Method method = null;
		

		method = getMethod(clazz,"valueOf", obj.getClass());

		if(null == method){
			method = getMethod(clazz,"valueOf",getBaseClass(obj.getClass()));
		}
		
		if(null == method){
			method = getMethod(clazz,"valueOf",Object.class);
		}
		
		if(null == method){
			throw new RuntimeException();
		}
		
		try {
			res = (T) method.invoke(null, obj);
		} catch (Exception e) {

		} 
		
		return res;
	}
	
	public static Method getMethod(Class<?> clazz,String name,Class<?> type){
		
		Method method = null;
		
		try {
			method = clazz.getDeclaredMethod(name, type);
		} catch (NoSuchMethodException e) {

		} 
		
		return method;
	}
	
	public static boolean isBaseClass(Class<?> clazz){
		
		return baseClass.contains(clazz);
	}
	
	public static List<String> getFieldNameList(Class<?> clazz){
		if(null != clazz){
			List<String> list = new ArrayList<>();
			
			while(clazz != Object.class){
				Field[] fields = clazz.getDeclaredFields();
				
				for(Field item : fields){
					list.add(item.getName());
				}
				
				clazz = clazz.getSuperclass();
			}
			
			return list;
		}
		
		return null;
	}
	
	public static Field getField(Class<?> clazz,String fieldName){
		Field field = null;
		
		while(clazz != Object.class){
			
			try {
				field = clazz.getDeclaredField(fieldName);
				
				break;
				
			} catch (Exception e) {
				//e.printStackTrace();
			} 
			
			clazz = clazz.getSuperclass();
		}
		
		return field;
	}
	
	public static Method getMethod(Class<?> clazz,String methodName,Class<?> ...parameterTypes){
		Method method = null;
		
		while(clazz != Object.class){
			
			try {
				method = clazz.getDeclaredMethod(methodName, parameterTypes);
				
				break;
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			clazz = clazz.getSuperclass();
		}
		
		return method;
	}
	
	public static void setFieldValue(Object obj,String fieldName,Object value){
		
		Field field = getField(obj.getClass(), fieldName);
		
		if(field != null){
			
			try {
				
				field.setAccessible(true);
				
				field.set(obj, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static Object getFieldValue(Object obj,String fieldName){
		Object res = null;
		
		Class<?> clazz = obj.getClass();
		Field field = getField(clazz, fieldName);

		try {
			field.setAccessible(true);
			
			res = field.get(obj);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return res;
	}
	
	public static boolean isWrapClass(Class<?> clz) { 
       
		return clz.isPrimitive();
	} 
}
