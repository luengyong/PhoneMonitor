package com.feiyu.entity;

public class CallEntity {
	private String name;
	
	private String phone_num;
	
	private String time;
	
	private int type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String tostring(){
		return name+";"+phone_num+";"+time+";"+type;
	}
}
