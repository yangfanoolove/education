package com.stattek.admin.web.modules.sys.index;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.stattek.admin.common.WebCxt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stattek.admin.common.vo.MenuVO;
import com.stattek.admin.common.vo.MyUserVO;
import com.stattek.admin.common.wrapper.Dto;
import com.stattek.admin.common.wrapper.Dtos;

/**
 * 首页
 * 
 * @author xiongchun
 *
 */
@Controller
public class IndexController {
	
	@Autowired
	private IndexService indexService;

	/**
	 * 首页入口
	 * @param httpSession
	 * @param request
	 * @return
	 */
	@RequestMapping("/")
    public String index(HttpSession httpSession, HttpServletRequest request) {
		MyUserVO curUser = WebCxt.getMyUserVO(httpSession);
		Dto inDto = Dtos.newDto(request).put2("user_id", curUser.getId());
		inDto.put("ctxPath", httpSession.getServletContext().getContextPath());
		List<MenuVO> menuVOs = indexService.listMenus(inDto);
		//扩大访问域到session，所有会话内页面使用
		httpSession.setAttribute("level1Menus", menuVOs);
        return "index";  
    }
	
}
