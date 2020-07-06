package com.ssm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author hello
 * @since 2020-03-08
 */
public class PcUser {

	private int id;

	/**
	 * 用户名
	 */
	private String name;

	/**
	 * 用户code
	 */
	private String code;

	/**
	 * 密码
	 */
	private String duty;

	/**
	 * 电话
	 */
	private String mobile;

	/**
	 * org
	 */
	private String org;

	/**
	 * 当前页
	 */
	private int currentPage;
	/**
	 * 大小
	 */
	private int pageSize;

	/**
	 * 原密码
	 */
	private String pwd;

	/**
	 * 新密码
	 */
	private String newPwd;

	/**
	 * 身份证号
	 */
	private String idCard;

	/**
	 * 角色
	 */
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
