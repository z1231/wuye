package com.ssm.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
@TableName("sm_car")
public class SmCar {
	@TableId(value = "id", type = IdType.AUTO)
	private int id;
	private String name;
	private String address;
	private String mobile;
	private String idCard;
	private Date time;
	private BigDecimal money;
	private String moneyStatus;
	private Date createDate;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "SmCar [id=" + id + ", name=" + name + ", address=" + address + ", mobile=" + mobile + ", idCard="
				+ idCard + ", time=" + time + ", money=" + money + ", moneyStatus=" + moneyStatus + ", createDate="
				+ createDate + "]";
	}
	

}
