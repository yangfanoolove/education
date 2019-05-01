package com.stattek.admin.web.common.filters;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.stattek.admin.common.WebCxt;
import com.stattek.admin.common.util.FilterUtil;
import com.stattek.admin.common.vo.MyUserVO;
import com.stattek.admin.common.wrapper.Dto;
import com.stattek.admin.common.wrapper.Dtos;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Admin登录权限过滤器
 * 
 * @author yangfan
 *
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = { "/*" })
@Slf4j
public class LoginFilter implements Filter {

	// 排除列表(startwith的匹配逻辑)
	private final String[] excludeKeysArray = { "/act", "/login", "/css/", "/img/", "/js/", "/theme/","/favicon.ico"};

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	/**
	 * 过滤器处理流程
	 * 
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
		
		// 排除列表
		if (FilterUtil.checkExcludes(httpServletRequest.getRequestURI(), excludeKeysArray)) {
			filterChain.doFilter(servletRequest, servletResponse);
			return;
		}

		// 应用过滤器算法
		boolean isPass = true;
		isPass = checkSession(httpServletRequest, httpServletResponse);
		if (isPass) {
			filterChain.doFilter(servletRequest, servletResponse);
		} else {
			if(!StrUtil.equalsIgnoreCase("XMLHttpRequest", httpServletRequest.getHeader("X-Requested-With"))) {
				//非ajax请求。ajax的跳转由前端处理
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
			}
			return;
		}

	}

	/**
	 * 检查用户是否登录
	 * 
	 * @param httpServletRequest
	 */
	private boolean checkSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws IOException {
		boolean isPass = true;
		String uri = httpServletRequest.getRequestURI();
		HttpSession httpSession = httpServletRequest.getSession();
		MyUserVO myUserVO = WebCxt.getMyUserVO(httpSession);
		if (myUserVO == null) {
			//未授权
			isPass = false;
			httpServletResponse.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
			if(StrUtil.equalsIgnoreCase("XMLHttpRequest", httpServletRequest.getHeader("X-Requested-With"))) {
				//ajax请求
				Dto dto = Dtos.newDto().put2("status", HttpStatus.HTTP_UNAUTHORIZED).put2("message", "身份认证未通过（未登录或会话已超时）");
				httpServletResponse.getWriter().write(JSON.toJSONString(dto));
			}
			log.info("拦截请求(身份认证未通过)-> 请求URI：{}", uri);
			
		}
		return isPass;
	}

	@Override
	public void destroy() {

	}

}
