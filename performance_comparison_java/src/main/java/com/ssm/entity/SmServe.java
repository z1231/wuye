package com.ssm.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sm_serve")
public class SmServe {
	@TableId(value = "id", type = IdType.AUTO)
	private int id;
	private String name;
	private String mobile;
	private String address;
	private String serveMan;
	private String serveMobile;
	private String thing;
	private Date createDate;
	private int num;
	private int money;
	private String serveStatus;
	private String moneyStatus;
	private String code;
	private String upCode;

	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUpCode() {
		return upCode;
	}

	public void setUpCode(String upCode) {
		this.upCode = upCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getServeMan() {
		return serveMan;
	}

	public void setServeMan(String serveMan) {
		this.serveMan = serveMan;
	}

	public String getServeMobile() {
		return serveMobile;
	}

	public void setServeMobile(String serveMobile) {
		this.serveMobile = serveMobile;
	}

	public String getThing() {
		return thing;
	}

	public void setThing(String thing) {
		this.thing = thing;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getServeStatus() {
		return serveStatus;
	}

	public void setServeStatus(String serveStatus) {
		this.serveStatus = serveStatus;
	}

	public String getMoneyStatus() {
		return moneyStatus;
	}

	public void setMoneyStatus(String moneyStatus) {
		this.moneyStatus = moneyStatus;
	}

	@Override
	public String toString() {
		return "SmServe [id=" + id + ", name=" + name + ", address=" + address + ", serveMan=" + serveMan
				+ ", serveMobile=" + serveMobile + ", thing=" + thing + ", createDate=" + createDate + ", num=" + num
				+ ", money=" + money + ", serveStatus=" + serveStatus + ", moneyStatus=" + moneyStatus + "]";
	}

}
