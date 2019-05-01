package com.stattek.admin.modules.cache;

import java.util.List;
import java.util.Map;

import com.stattek.admin.domain.mymodule.MyModuleEntity;
import com.stattek.admin.domain.mymodule.MyModuleMapper;
import com.stattek.admin.common.util.MyCons;
import com.stattek.admin.common.wrapper.Dto;
import com.stattek.admin.common.wrapper.Dtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 通用数据缓存服务
 * 
 * @author xiongchun
 *
 */
@Service
@Slf4j
public class CacheMiscService {

	@Autowired
	private MyModuleMapper myModuleMapper;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	/**
	 * 缓存全量模块菜单表数据<br>
	 * 用途 1：计算面包屑导航路径提示文本；
	 */
	public Dto cacheModules() {
		List<MyModuleEntity> myModuleEntities = myModuleMapper.list(null);
		Map<String, String> cacheMap = Maps.newHashMap();
		for (MyModuleEntity myModuleEntity : myModuleEntities) {
			cacheMap.put(myModuleEntity.getId().toString(), JSON.toJSONString(myModuleEntity));
		}
		stringRedisTemplate.opsForHash().putAll(MyCons.CacheKeyOrPrefix.MyModule.getValue(), cacheMap);
		stringRedisTemplate.opsForHash().put(MyCons.CacheKeyOrPrefix.LastCacheTime.getValue(),
				MyCons.CacheKeyOrPrefix.MyModule.getValue(), DateUtil.now());
		String msgString = "完成模块菜单数据的全量缓存或刷新";
		log.info(msgString);
		return Dtos.newDto().put2("code", "1").put2("msg", msgString);
	}

}
