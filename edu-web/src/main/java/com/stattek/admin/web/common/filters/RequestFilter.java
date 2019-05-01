package com.stattek.admin.web.common.filters;

import com.stattek.admin.common.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 外层通用请求过滤器
 * 
 * @author yangfan
 *
 */
@WebFilter(filterName = "RequestFilter", urlPatterns = { "/*" })
@Slf4j
public class RequestFilter implements Filter {
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		logReqInfo(httpServletRequest);
		filterChain.doFilter(servletRequest, servletResponse);
		return;
	}

	/**
	 * 打印通用请求日志信息
	 * 
	 * @param httpServletRequest
	 */
	private void logReqInfo(HttpServletRequest httpServletRequest) {
		String uri = httpServletRequest.getRequestURI();
		String sessionId = httpServletRequest.getRequestedSessionId();
		sessionId = sessionId == null ? StringUtils.EMPTY : sessionId;
		String clirntIp = MyUtil.getClientIpAddr(httpServletRequest);
		log.info("收到请求 > IP:{} | SESSION:{} | URI:{}", clirntIp, sessionId, uri);
	}
}
