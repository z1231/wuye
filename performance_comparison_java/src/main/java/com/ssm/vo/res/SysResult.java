package com.ssm.vo.res;

/**
 * 系统返回值对象
 * @author 000
 *
 */

public class SysResult {
	private Integer status;	//200表示成功 201表示失败
	private String msg;		//后台返回值数据提示信息
	private Object data;	//后台返回任意对象
	private Object userdata;
	private Object reperdata;
	private Object servedata;
	
	/**成功 */
	public static SysResult ok() {
		return new SysResult(200,null,null);
	}
	/**
	 * 成功
	 * @param data 需要返回的数据
	 * @return
	 */
	public static SysResult ok(Object data) {
		return new SysResult(200,null,data);
	}
	
	public static SysResult ok(Object data,Object userdata,Object reperdata,Object servedata) {
		return new SysResult(200, null, data, userdata, reperdata, servedata);
	}
	
	/**
	 * 成功
	 * @param msg 提示信息
	 * @param data 需要返回的数据
	 * @return
	 */
	public static SysResult ok(String msg,Object data) {
		return new SysResult(200,msg,data);
	}
	/**失败 */
	public static SysResult fail() {
		return new SysResult(201,null,null);
	}
	/**
	 * 失败
	 * @param msg 提示信息
	 * @return
	 */
	public static SysResult fail(String msg) {
		return new SysResult(201,msg,null);
	}
	public SysResult(Integer status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	public SysResult(Integer status, String msg, Object data, Object userdata, Object reperdata, Object servedata) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
		this.userdata = userdata;
		this.reperdata = reperdata;
		this.servedata = servedata;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
