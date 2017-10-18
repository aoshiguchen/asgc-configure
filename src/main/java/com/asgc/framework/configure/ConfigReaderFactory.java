package com.asgc.framework.configure;

import com.asgc.framework.configure.ex.ConfigReaderException;
import com.asgc.framework.configure.impl.*;
import org.apache.log4j.Logger;

import static com.asgc.framework.configure.StringUtil.getSuffix;

/**
 * ConfigReader工厂
 * @author aoshiguchen
 * @time 2017-03-12	
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
	 * @param path
	 * @param type 文件类型 (ini,json,prop,txt,xml)
	 * @return
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
		
		try{
			logger.info("scanf path :" + path);
			configure = configReader.readAll(path);
		}catch(Exception e1){
			try{
				String classRootPath = ConfigReaderFactory.class.getClassLoader().getResource("").getPath();
				logger.info("scanf path :" + classRootPath + path);
				configure = configReader.readAll(classRootPath + path);
			}catch(Exception e2){
				throw new ConfigReaderException(path + "is not exists !");
			}
		}
		
		return configure;
	}
	
	/**
	 * 根据文件路径获取配置
	 * 根据文件后缀确定文件类型：
	 * .ini -> ini
	 * .json -> json
	 * .properties -> prop
	 * .txt -> txt
	 * .xml -> xml
	 * 无匹配则按照prop类型
	 * @param path
	 * @return
	 */
	public static  Configure getConfigure(String path){
		
		logger.info("getConfigure:" + path);
			
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
			
		return getConfigure(path, type);
	}
	
}
