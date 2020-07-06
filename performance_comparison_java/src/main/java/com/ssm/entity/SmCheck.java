package com.ssm.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
@TableName("sm_check")
public class SmCheck {
	@TableId(value = "id", type = IdType.AUTO)
	private int id;
	private String name;
	private String gender;
	private String org;
	private String work;
	private Date createDate;
	private String status;
	private int lateNum;
	private int earlyNum;
	private int absentNum;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getLateNum() {
		return lateNum;
	}
	public void setLateNum(int lateNum) {
		this.lateNum = lateNum;
	}
	public int getEarlyNum() {
		return earlyNum;
	}
	public void setEarlyNum(int earlyNum) {
		this.earlyNum = earlyNum;
	}
	public int getAbsentNum() {
		return absentNum;
	}
	public void setAbsentNum(int absentNum) {
		this.absentNum = absentNum;
	}
	@Override
	public String toString() {
		return "SmCheck [id=" + id + ", name=" + name + ", gender=" + gender + ", org=" + org + ", work=" + work
				+ ", createDate=" + createDate + ", status=" + status + ", lateNum=" + lateNum + ", earlyNum="
				+ earlyNum + ", absentNum=" + absentNum + "]";
	}
	

}
