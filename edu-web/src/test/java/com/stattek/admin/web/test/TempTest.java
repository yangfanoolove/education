package com.stattek.admin.web.test;

import com.stattek.admin.common.util.MyCons;
import com.stattek.admin.common.util.MyUtil;

public class TempTest {

	public static void main(String[] args) {
		System.out.println(MyUtil.password(MyCons.PWD_KEY, "96e79218965eb72c92a549dd5a330112"));
	}

}
