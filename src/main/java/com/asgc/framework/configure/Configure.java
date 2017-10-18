package com.asgc.framework.configure;

import java.util.Map;

import com.asgc.framework.configure.ex.ConfigReaderException;
import static com.asgc.framework.configure.MapUtil.*;

/**
 * map的封装，便于取值
 * @author aoshiguchen
 * @time 2017-03-12	
 */
public class Configure{
	
	private Map<String,String> config;
	
	public Configure(Map<String,String> config){
		
		if(null == config){
			throw new ConfigReaderException("config is null !");
		}
		
		this.config = config;
		mapVarProc(config);
	}
	
	public String get(String key){
		
		return config.get(key);
	}
	
	public void set(String key,String value){
		
		config.put(key, value);
	}
	
	public String getString(String key){
		
		return get(key);
	}
	
	public int getInt(String key){
		
		return Integer.valueOf(get(key));
	}
	
	public Long getLong(String key){
		
		return Long.valueOf(get(key));
	}
	
	public Float getFloat(String key){
		
		return Float.valueOf(get(key));
	}
	
	public Double getDouble(String key){
		
		return Double.valueOf(get(key));
	}

	@Override
	public String toString() {
		return config.toString();
	}

}
