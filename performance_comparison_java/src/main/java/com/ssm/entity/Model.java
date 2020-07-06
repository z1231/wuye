package com.ssm.entity;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author hello
 * @since 2020-03-08
 */
public class Model {

	private List<SmLogin> userdata;
	private List<SmReper> reperdata;
	private List<SmLogin> servedata;

	public List<SmLogin> getUserdata() {
		return userdata;
	}

	public void setUserdata(List<SmLogin> userdata) {
		this.userdata = userdata;
	}

	public List<SmReper> getReperdata() {
		return reperdata;
	}

	public void setReperdata(List<SmReper> reperdata) {
		this.reperdata = reperdata;
	}

	public List<SmLogin> getServedata() {
		return servedata;
	}

	public void setServedata(List<SmLogin> servedata) {
		this.servedata = servedata;
	}

}
