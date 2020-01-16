package com.android.sms.timedtext;

public class SMSSchedulerPendingIntent {
	private int mId;
	private int mHour;
	private int mMinutes;
	private int mSeconds;
	private int mYear;
	private int mMonth;
	private int mDay;
	private String mFrequency;
	private String mNumberToSend;
	private String mReceiverName;
	private String mMessage;
	
	public int getId(){
		return mId;
	}
	
	public void setId(int id){
		mId = id;
	}
	
	public int getHour(){
		return mHour;
	}
	
	public void setHour(int hour){
		mHour = hour;
	}
	
	public int getMinutes(){
		return mMinutes;
	}
	
	public void setMinutes(int minutes){
		mMinutes = minutes;
	}
	
	public int getSeconds(){
		return mSeconds;
	}
	
	public void setSeconds(int seconds){
		mSeconds = seconds;
	}
	
	public int getYear(){
		return mYear;
	}
	
	public void setYear(int year){
		mYear = year;
	}
	
	public int getMonth(){
		return mMonth;
	}
	
	public void setMonth(int month){
		mMonth = month;
	}
	
	public int getDay(){
		return mDay;
	}
	
	public void setDay(int day){
		mDay = day;
	}
	
	public String getFrequency(){
		return mFrequency;
	}
	
	public void setFrequency(String frequency){
		mFrequency = frequency;
	}
	
	public String getNumberToSend(){
		return mNumberToSend;
	}
	
	public void setNumberToSend(String number){
		mNumberToSend = number;
	}
	
	public String getReceiverName(){
		return mReceiverName;
	}
	public void setReceiverName(String name){
		mReceiverName = name;
	}
	
	public String getMessage(){
		return mMessage;
	}
	
	public void setMessage(String message){
		mMessage = message;
	}
}
