package com.yunda.base.sy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 升阳个人信息查询库
 * 
 * @author long
 * @email zhanghan813@163.com
 * @date 2018-08-30 14:43:43
 */
public class SyPersonInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String personId;
	//
	private String personName;
	//
	private String personInfoUrl;
	//
	private Date createTime;
	//
	private String createBy;
	//
	private Date updateTime;
	//
	private String updateBy;
	//0 未删除  1 已删除
	private Integer deleteFlag;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	/**
	 * 获取：
	 */
	public String getPersonId() {
		return personId;
	}
	/**
	 * 设置：
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	/**
	 * 获取：
	 */
	public String getPersonName() {
		return personName;
	}
	/**
	 * 设置：
	 */
	public void setPersonInfoUrl(String personInfoUrl) {
		this.personInfoUrl = personInfoUrl;
	}
	/**
	 * 获取：
	 */
	public String getPersonInfoUrl() {
		return personInfoUrl;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：
	 */
	public String getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：0 未删除  1 已删除
	 */
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * 获取：0 未删除  1 已删除
	 */
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
}
