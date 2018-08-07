package com.qq.common;

public class Message implements java.io.Serializable{

	private String MesType;
	/**
	 *MesType = 1 :登录成功
	 *MesType = 2 :登录失败
	 *MesType = 3 :一般信息
	 */

	private String sender;
	private String getter;
	private String con;
	private String time;
	
	
	public String getMesType() {
		return MesType;
	}

	public void setMesType(String mesType) {
		MesType = mesType;
	}

	
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
