package com.stattek.admin.web.modules.sys.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.stattek.admin.common.util.MyCons;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stattek.admin.common.util.MyUtil;
import com.stattek.admin.common.vo.MyUserVO;
import com.stattek.admin.common.wrapper.Dto;
import com.stattek.admin.common.wrapper.Dtos;

/**
 * 登录页
 * 
 * @author xiongchun
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@RequestMapping("login")
    public String login(ModelMap map) {
		
        return "login";  
    }
	
	/**
	 * 登录验证
	 * @param request
	 * @param httpSession
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "login/go", method = { RequestMethod.POST}, produces = "application/json")
	@ResponseBody
	public Dto validate(HttpServletRequest request, HttpSession httpSession) {
		Dto inDto = Dtos.newDto(request);
		Dto outDto = Dtos.newDto(loginService.validate(inDto));
		if (StringUtils.equals(MyCons.YesOrNo.YES.getValue().toString(), outDto.getString("code"))) {
			MyUserVO myUser = new MyUserVO();
			MyUtil.copyProperties(outDto.get("myUser"), myUser);
			myUser.setSessionId(httpSession.getId());
			myUser.setRoleIds((List<String>)outDto.get("roleIds"));
			httpSession.setAttribute(MyCons.My_USER, myUser);
		}
		inDto.put2("code", 1).put2("message", "success");
		return inDto;
	}
	
	/**
	 * 注销
	 * @param httpSession
	 * @return
	 */
	@RequestMapping("logout")
	@ResponseBody
    public Dto logout(HttpSession httpSession) {
		httpSession.invalidate();
        return Dtos.newDto().put2("code", "1").put2("msg", "用户安全退出成功");
    }
	
}
