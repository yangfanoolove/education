package com.stattek.admin.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.stattek.admin.common.wrapper.VO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 枚举参数VO值对象
 * 
 * @author xiongchun
 *
 */
@Getter
@Setter
public class EnumVO extends VO {

	private static final long serialVersionUID = 1L;
	/**
	 * 流水号
	 */
	private Integer id;	
	/**
	 * 枚举类型KEY
	 */
	private String enum_key;	
	/**
	 * 枚举类型名称
	 */
	private String enum_name;	
	/**
	 * 枚举元素KEY
	 */
	private String element_key;	
	/**
	 * 枚举元素值
	 */
	private String element_value;	
	/**
	 * 是否启用
	 */
	private String is_enable;	
	/**
	 * 所属分类
	 */
	private String type;	
	/**
	 * 排序号
	 */
	private Integer sort_no;	
	/**
	 * 更新时间
	 */
    @JSONField (format="yyyy-MM-dd HH:mm:ss")
	private Date update_time;	
	/**
	 * 备注
	 */
	private String remark;
}
