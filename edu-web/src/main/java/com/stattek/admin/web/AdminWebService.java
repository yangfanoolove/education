package com.stattek.admin.web;

import com.stattek.admin.web.common.filters.AuthFilter;
import com.stattek.admin.web.common.filters.LoginFilter;
import com.stattek.admin.web.common.filters.RequestFilter;
import com.stattek.admin.common.MyCxt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan(basePackages={"com.stattek.admin"})
@Import(MyCxt.class)
public class AdminWebService {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(AdminWebService.class, args);
	}
	
	@Bean  
    public FilterRegistrationBean  requestFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
        filterRegistrationBean.setFilter(new RequestFilter());
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;  
    } 
	
	@Bean  
    public FilterRegistrationBean  loginFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
        filterRegistrationBean.setFilter(new LoginFilter());
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;  
    } 
	
	@Bean  
    public FilterRegistrationBean  AuthFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
        filterRegistrationBean.setFilter(new AuthFilter());
        filterRegistrationBean.setOrder(3);
        return filterRegistrationBean;  
    }
	
}
