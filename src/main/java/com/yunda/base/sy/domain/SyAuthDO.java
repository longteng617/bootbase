package com.yunda.base.sy.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 升阳权限控制表
 * 
 * @author long
 * @email zhanghan813@163.com
 * @date 2018-08-31 11:26:10
 */
public class SyAuthDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//授权码
	private String authCode;
	//
	private String userName;
	//Mac地址
	private String macAddress;
	//
	private String createBy;
	//
	private Date createTime;
	//
	private String updateBy;
	//
	private Date updateTime;
	//删除标识
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
	 * 设置：授权码
	 */
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	/**
	 * 获取：授权码
	 */
	public String getAuthCode() {
		return authCode;
	}
	/**
	 * 设置：
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：Mac地址
	 */
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	/**
	 * 获取：Mac地址
	 */
	public String getMacAddress() {
		return macAddress;
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
	 * 设置：删除标识
	 */
	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/**
	 * 获取：删除标识
	 */
	public Integer getDeleteFlag() {
		return deleteFlag;
	}
}
