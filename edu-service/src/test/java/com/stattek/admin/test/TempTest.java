package com.stattek.admin.test;

import com.stattek.admin.common.util.MyCons;
import com.stattek.admin.common.util.MyUtil;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes=TempTest.class)
@ComponentScan(basePackages={"com.gitee.myclouds"})*/
public class TempTest {
	
	/*@Test*/
	public  void test1() {
		System.out.println(1);
	}

	public static void main(String[] args) {
		String res = MyUtil.password(MyCons.PWD_KEY, "root");
		System.out.println(res);
	}

}
