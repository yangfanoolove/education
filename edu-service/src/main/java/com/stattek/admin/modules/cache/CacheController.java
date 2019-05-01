package com.stattek.admin.modules.cache;

import java.util.Map;

import com.stattek.admin.common.wrapper.Dto;
import com.stattek.admin.common.wrapper.Dtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 缓存模块 服务发布
 * 
 * @author xiongchun
 *
 */
@RestController
@RequestMapping("cache")
public class CacheController {

	@Autowired
	private CacheCfgService cacheCfgService;
	@Autowired
	private CacheMiscService cacheMiscService;
	@Autowired
	private CacheAuthService cacheAuthService;
	
	/**
	 * 同步配置类缓存(键值|枚举)
	 * 
	 * @param inMap
	 * @return
	 */
	@RequestMapping(value = "syncCfgCache",method = { RequestMethod.POST}, produces = "application/json")
	public Dto syncCfgCache(@RequestParam Map<String,Object> inMap){
		return cacheCfgService.syncCfgCache(Dtos.newDto(inMap));
	}
	
	/**
	 * 刷新全局模块菜单缓存
	 * 
	 * @param inMap
	 * @return
	 */
	@RequestMapping(value = "cacheModules",method = { RequestMethod.POST}, produces = "application/json")
	public Dto cacheModules(@RequestParam Map<String,Object> inMap){
		return cacheMiscService.cacheModules();
	}
	
	/**
	 * 刷新所有角色授权数据缓存
	 * 
	 * @param inMap
	 * @return
	 */
	@RequestMapping(value = "cacheRolesAuth",method = { RequestMethod.POST}, produces = "application/json")
	public Dto cacheRolesAuth(@RequestParam Map<String,Object> inMap){
		return cacheAuthService.cacheOrRefreshAllRolesAuthToSet();
	}
	
}
