package com.github.aoshiguchen.framework.configure;


import static com.github.aoshiguchen.framework.configure.StringUtil.getSuffix;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.github.aoshiguchen.framework.configure.ex.ConfigReaderException;
import com.github.aoshiguchen.framework.configure.impl.IniConfigReaderImpl;
import com.github.aoshiguchen.framework.configure.impl.JsonConfigReaderImpl;
import com.github.aoshiguchen.framework.configure.impl.PropConfigReaderImpl;
import com.github.aoshiguchen.framework.configure.impl.TxtConfigReaderImpl;
import com.github.aoshiguchen.framework.configure.impl.XmlConfigReaderImpl;

/**
 * ConfigReader工厂
 * @author aoshiguchen
 */
public class ConfigReaderFactory {
	
	private static Logger logger = Logger.getLogger(ConfigReaderFactory.class);
	
	private static final class SingletonHolder{
		private static final IConfigReader iniConfigReader = new IniConfigReaderImpl();
		private static final IConfigReader jsonConfigReader = new JsonConfigReaderImpl();
		private static final IConfigReader propConfigReader = new PropConfigReaderImpl();
		private static final IConfigReader txtConfigReader = new TxtConfigReaderImpl();
		private static final IConfigReader xmlConfigReader = new XmlConfigReaderImpl();
	}
	
	private static final Map<String,Configure> cache = new ConcurrentHashMap<String, Configure>();
	
	public static IConfigReader getIniConfigReader(){
		
		return SingletonHolder.iniConfigReader;
	}
	
	public static IConfigReader getJsonConfigReader(){
		
		return SingletonHolder.jsonConfigReader;
	}
	
	public static IConfigReader getPropConfigReader(){
		
		return SingletonHolder.propConfigReader;
	}
	
	public static IConfigReader getTxtConfigReader(){
		
		return SingletonHolder.txtConfigReader;
	}
	
	public static IConfigReader getXmlConfigReader(){
		
		return SingletonHolder.xmlConfigReader;
	}
	
	/**
	 * 根据文件路径和文件类型获取配置
	 * @param path 文件路径
	 * @param type 文件类型 (ini,json,prop,txt,xml)
	 * @return 配置
	 */
	public static Configure getConfigure(String path,String type){
		
		logger.info("getConfigure:" + path + "," + type);
		
		Configure configure = null;
		IConfigReader configReader = null;
		
		if("ini".equalsIgnoreCase(type)){
			configReader = getIniConfigReader();
		}else if("json".equalsIgnoreCase(type)){
			configReader = getJsonConfigReader();
		}else if("properties".equalsIgnoreCase(type)){
			configReader = getPropConfigReader();
		}else if("txt".equalsIgnoreCase(type)){
			configReader = getTxtConfigReader();
		}else if("xml".equalsIgnoreCase(type)){
			configReader = getXmlConfigReader();
		}else{
			configReader = getPropConfigReader();
		}
		
		List<String> pathList = new ArrayList<String>(){
			{
				this.add(path);
				this.add(ConfigReaderFactory.class.getClassLoader().getResource("").getPath() + path);
				this.add(ConfigReaderFactory.class.getResource("/").getPath() + path);
				this.add(path + ".properties");
				this.add(ConfigReaderFactory.class.getClassLoader().getResource("").getPath() + path + ".properties");
				this.add(ConfigReaderFactory.class.getResource("/").getPath() + path + ".properties");
			}
		};
		
		for(String p : pathList){
			configure = readAll(configReader, p);
			if(null != configure){
				break;
			}
		}
		
		if(null == configure){
			throw new ConfigReaderException(path + "is not exists !");
		}
		
		return configure;
	}
	
	private static Configure readAll(IConfigReader configReader,String path){
		try{			
			logger.info("scanf path :" + path);
			return configReader.readAll(path);
		}catch(Exception e){
			return null;
		}
	}
	
	/**
	 * 根据文件路径获取配置
	 * <p>
	 * 根据文件后缀确定文件类型：<br>
	 * 无匹配则按照prop类型 <br>
	 * @param path 文件路径
	 * @return 配置
	 */
	public static  Configure getConfigure(String path){
		
		logger.info("getConfigure:" + path);
		
		if(cache.containsKey(path)){
			return cache.get(path);
		}
			
		String suffix = getSuffix(path);
		String type = "";
		
		if("ini".equalsIgnoreCase(suffix)){
			type = "ini";
		}else if("json".equalsIgnoreCase(suffix)){
			type = "json";
		}else if("properties".equalsIgnoreCase(suffix)){
			type = "properties";
		}else if("txt".equalsIgnoreCase(suffix)){
			type = "txt";
		}else if("xml".equalsIgnoreCase(suffix)){
			type = "xml";
		}else{
			type = "properties";
		}
		
		Configure configure = getConfigure(path, type);
		cache.put(path, configure);
		
		return configure;
	}
	
}
