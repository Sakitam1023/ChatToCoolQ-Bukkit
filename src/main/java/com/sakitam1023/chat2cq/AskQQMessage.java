package com.sakitam1023.chat2cq;

public class AskQQMessage {
	private String act;
	private String groupid;
	private String QQID;
	private String msg;
	
	public String getAct() {
		return act;
	}
	
	public void setAct(String act) {
    	this.act = act;
	}
	
	public String getGroupid() {
		return groupid;
	}
	
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
	public String getQQID() {
		return QQID;
	}
	
	public void setQQID(String qQID) {
		QQID = qQID;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
