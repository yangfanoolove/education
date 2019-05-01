package com.stattek.admin.web.modules.sys.enums;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stattek.admin.common.wrapper.Dto;
import com.stattek.admin.common.wrapper.Dtos;

/**
 * 枚举类型管理
 * 
 * @author xiongchun
 *
 */
@Controller
@RequestMapping("sys/enums")
public class EnumsController {

	@Autowired
	private EnumsService enumsService;

	@RequestMapping("init")
	public String init(ModelMap map) {

		return "modules/sys/enums";
	}

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	@ResponseBody
	public String list(HttpServletRequest request) {
		Dto inDto = Dtos.newDto(request);
		String jsonString = enumsService.list(inDto);
		return jsonString;
	}
	
	/**
	 * 查询实体
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "get", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	@ResponseBody
	public String get(HttpServletRequest request) {
		Dto inDto = Dtos.newDto(request);
		return enumsService.get(inDto.getInteger("id"));
	}	
	
	/**
	 * 新增
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save", method = { RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public Dto save(HttpServletRequest request) {
		Dto inDto = Dtos.newDto(request);
		return Dtos.newDto(enumsService.save(inDto));
	}
	
	/**
	 * 修改
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "update", method = { RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public Dto update(HttpServletRequest request) {
		Dto inDto = Dtos.newDto(request);
		return Dtos.newDto(enumsService.update(inDto));
	}
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete", method = { RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public Dto delete(HttpServletRequest request) {
		Dto inDto = Dtos.newDto(request);
		return Dtos.newDto(enumsService.delete(inDto));
	}
	
}
