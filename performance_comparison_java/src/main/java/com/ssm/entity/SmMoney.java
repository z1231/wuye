package com.ssm.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
@TableName("sm_money")
public class SmMoney {
	@TableId(value = "id", type = IdType.AUTO)
	private int id;
	private String name;
	private String address;
	private String mobile;
	private String idCard;
	private Date time;
	private Date createDate;
	private BigDecimal money;
	private String moneyStatus;
	private BigDecimal profee;
	private BigDecimal waterfee;
	private BigDecimal heatfee;
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
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	public String getMoneyStatus() {
		return moneyStatus;
	}
	public void setMoneyStatus(String moneyStatus) {
		this.moneyStatus = moneyStatus;
	}
	public BigDecimal getProfee() {
		return profee;
	}
	public void setProfee(BigDecimal profee) {
		this.profee = profee;
	}
	public BigDecimal getWaterfee() {
		return waterfee;
	}
	public void setWaterfee(BigDecimal waterfee) {
		this.waterfee = waterfee;
	}
	public BigDecimal getHeatfee() {
		return heatfee;
	}
	public void setHeatfee(BigDecimal heatfee) {
		this.heatfee = heatfee;
	}

}
