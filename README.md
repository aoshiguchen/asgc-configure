# asgc-configure

**asgc-configure**是傲世孤尘开源的一个配置管理器。特点概述：
 
- **使用简单** ：一行代码读取配置，支持注解，很方便集成到spring容器中。
- **例程丰富** ：对于本工具的使用提供了详细的示例程序。

-------------------
## 如何使用asgc-configure
 

### 1、配置工厂读取 (ConfigReaderFactory)
配置文件(config.properties)内容如下：
projectName=asgc-configure

jdbc.host=118.89.63.66
jdbc.port=3306
jdbc.dbName=eforum
jdbc.driver_class=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://${jdbc.host}:${jdbc.port}/${jdbc.dbName}
jdbc.username=root
jdbc.password=123456

> 读取jdbc.url (支持${}格式变量引用)

``` java
Configure configure = ConfigReaderFactory.getConfigure("config");
		
System.out.println(configure.getString("jdbc.url"));
```



### 2、注解式读取
对于上面的配置内容，使用注解读取的代码如下：

``` java
import org.junit.Test;

import com.github.aoshiguchen.framework.configure.annotation.Config;

public class ConfigUtilTest {
	
	@Config
	private String projectName;
	
	@Config("jdbc.port")
	private int port;
	
	@Config("jdbc.url")
	private String url;
	
	{
		/**
		 * 与spring整合时，可以将这行代码置入spring前置处理器
		 * sprign容器初始化时就会自动注入
		 */
		ConfigUtil.inject("config", this);
	}
	
	//注入式读取配置
	@Test
	public void test1(){
		System.out.println(projectName);
		System.out.println(port);
		System.out.println(url);
	}
	
	//直接读取
	@Test
	public void test2(){
		Configure configure = ConfigReaderFactory.getConfigure("config");
		
		System.out.println(configure.getString("jdbc.host"));
		System.out.println(configure.getString("jdbc.driver_class"));
		System.out.println(configure.getString("jdbc.username"));
	}
	
}

```


## 联系我们

笔者QQ    `1052045476`

nodejs交流群    `527393872`

java交流群    `527393872`

c/c++语言交流群    `251975693`

