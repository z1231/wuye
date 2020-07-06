package com.ssm.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sm_login")
public class SmLogin {
	@TableId(value = "id", type = IdType.AUTO)
	private int id;
	private String code;
	private String password;
	private String role;
	private String status;
	private String name;
	private String mobile;
	private String idCard;
	private Date createDate;
	private String email;
	private String work;
	private String address;
	private String org;
	private double profee;
	private double waterfee;
	private double heatfee;
	private String feeStatus;
	private String endDate;
	private double carMoney;
	private String carStatus;
	private double allNum;

	public double getAllNum() {
		return allNum;
	}

	public void setAllNum(double allNum) {
		this.allNum = allNum;
	}

	public double getProfee() {
		return profee;
	}

	public void setProfee(double profee) {
		this.profee = profee;
	}

	public double getWaterfee() {
		return waterfee;
	}

	public void setWaterfee(double waterfee) {
		this.waterfee = waterfee;
	}

	public double getHeatfee() {
		return heatfee;
	}

	public void setHeatfee(double heatfee) {
		this.heatfee = heatfee;
	}

	public String getFeeStatus() {
		return feeStatus;
	}

	public void setFeeStatus(String feeStatus) {
		this.feeStatus = feeStatus;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public double getCarMoney() {
		return carMoney;
	}

	public void setCarMoney(double carMoney) {
		this.carMoney = carMoney;
	}

	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "SmLogin [id=" + id + ", code=" + code + ", password=" + password + ", role=" + role + ", createDate="
				+ createDate + ", status=" + status + ", name=" + name + ", mobile=" + mobile + ", idCard=" + idCard
				+ ", email=" + email + ", work=" + work + ", address=" + address + "]";
	}

}
