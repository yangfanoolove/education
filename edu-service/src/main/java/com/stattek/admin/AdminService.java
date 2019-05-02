package com.stattek.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.stattek.admin.modules.cache.SysInitCacheService;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan(basePackages = {"com.stattek.admin"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class AdminService {
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(AdminService.class, args);
		SysInitCacheService cacheService = context.getBean(SysInitCacheService.class);
		cacheService.init();
	}

}
