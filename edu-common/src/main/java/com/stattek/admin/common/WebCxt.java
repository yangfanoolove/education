package com.stattek.admin.common;


import com.stattek.admin.common.vo.MyUserVO;
import com.stattek.admin.common.util.MyCons;
import com.stattek.admin.common.wrapper.Dto;
import com.stattek.admin.common.wrapper.impl.HashDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;

/**
 * Web上下文
 * 
 * @author xiongchun
 */
public class WebCxt {

	/**
	 * 将Request请求参数封装为Dto对象
	 * 
	 * @param request
	 * @return
	 */
	public static Dto getParamAsDto(HttpServletRequest request) {
		Dto dto = new HashDto();
		Map<String, String[]> map = request.getParameterMap();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Iterator<String> keyIterator = (Iterator) map.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = (String) keyIterator.next();
			String value = map.get(key)[0];
			dto.put(key, value);
		}
		return dto;
	}
	
	/**
	 * 获取当前用户数据对象
	 * 
	 * @param httpSession 当前会话
	 * @return 当前用户数据对象
	 */
	public static MyUserVO getMyUserVO(HttpSession httpSession) {
		MyUserVO curUser = (MyUserVO)httpSession.getAttribute(MyCons.My_USER);
		return curUser;
	}
	
}
