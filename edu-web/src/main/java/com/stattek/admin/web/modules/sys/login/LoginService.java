package com.stattek.admin.web.modules.sys.login;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登录服务FeignClient客户端
 * 
 * @author xiongchun
 *
 */
@Service
@FeignClient(value = "stattek-admin-service")
public interface LoginService {

	/**
	 * 登录验证服务
	 * @param inDto
	 * @return
	 */
	@RequestMapping(value = "/login/validate",method = RequestMethod.POST)
	Map<String, Object> validate(@RequestParam("inDto") Map<String,Object> inDto);
	
}
