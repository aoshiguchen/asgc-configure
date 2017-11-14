package com.github.aoshiguchen.framework.configure;

import java.lang.reflect.Field;

import com.github.aoshiguchen.framework.configure.annotation.Config;

/**
 * 配置工具类
 * @author aoshiguchen
 */
public class ConfigUtil {

	public static void inject(Configure configure,Object obj){
		Class<?> cls = obj.getClass();

		for(Field field : cls.getDeclaredFields()){
			
			if(field.isAnnotationPresent(Config.class)){
				
				Config configAnnotation = field.getAnnotation(Config.class);
				String key = configAnnotation.value();
				if("".equals(key)){
					key = field.getName();
				}
				String value = configure.getString(key);
								
				try {
					if(!field.isAccessible()){
						field.setAccessible(true);
					}
					
					if(field.getType() == String.class){
						field.set(obj,value);
					}else{
						Object v = ClassUtil.valueOf(field.getType(),value);
						field.set(obj,v);
					}
					
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				
			}
			
		}
		
	}
	
}
