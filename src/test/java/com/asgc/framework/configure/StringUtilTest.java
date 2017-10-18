package com.asgc.framework.configure;

import org.junit.Test;
import static com.asgc.framework.configure.StringUtil.*;

public class StringUtilTest {

	@Test
	public void test1(){
		System.out.println(getSuffix("a.txt"));
		System.out.println(getSuffix("b.properties"));
		System.out.println(getSuffix("c"));
		System.out.println(getSuffix("d.."));
		System.out.println(getSuffix("e.xx.ss..txt"));
	}
	
}
