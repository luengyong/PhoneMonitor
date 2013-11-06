package com.feiyu.entity;


public class SMSEntity
{
private String sms_number;//短信联系人号码
	
	private String sms_time;//短信联系时间
	
	private int sms_type;//短信状态，如：接收或发送

	public SMSEntity(){
		
	}

	public String getSms_number()
	{
		return sms_number;
	}

	public void setSms_number(String sms_number)
	{
		this.sms_number = sms_number;
	}

	public String getSms_time()
	{
		return sms_time;
	}

	public void setSms_time(String sms_time)
	{
		this.sms_time = sms_time;
	}
	
	public int getSms_type()
	{
		return sms_type;
	}


	public void setSms_type(int sms_type)
	{
		this.sms_type = sms_type;
	}

	public String tostring(){
		return sms_number+" : "+sms_time;
	}
}
