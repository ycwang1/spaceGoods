package com.htzhny.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
//		double a=2.23;
//		Integer b=2;
//		System.out.println(a*b);
		
	//	String formatDate = "";  
	//	DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；  
	 //   formatDate = dFormat.format(dt); 
	 //   String time = DateFormat.getDateInstance().format(dt);  
		getOrderId();
	}
	//生成订单id（流水号） by wyc
	public static void getOrderId(){
		StringBuffer sBuffer = new StringBuffer();
		//时分秒
		Calendar cal = Calendar.getInstance();
		String year  = getString(cal.get(Calendar.YEAR));
		String month = getString(cal.get(Calendar.MONTH)+1);
		String day = getString(cal.get(Calendar.DAY_OF_MONTH));
		String hour = getString(cal.get(Calendar.HOUR_OF_DAY));
		String minute = getString(cal.get(Calendar.MINUTE));
		String second = getString(cal.get(Calendar.SECOND));
		String mm = getMillsecond(cal.get(Calendar.MILLISECOND));
		System.out.println(getMillsecond(1));
		System.out.println(getMillsecond(11));
		System.out.println(getMillsecond(121));
		sBuffer.append(year).append(month).append(day).append(hour).append(minute).append(second).append(mm);
		System.out.println(sBuffer);
	}
	public static String getString(int number){
		return number>10?number+"":"0"+number;
	}
	private static String getMillsecond(int number){
  		return number>10?number>100?number+"":"0"+number:"00"+number;
  	}
	private static void getMsg(){
		//String res = "{'session_key':'Xlv5dDHRrAK1mfp\/c6EH\/g==','openid':'oXVLM4j_4aq3j7kJIHl3vUD2OxeU'}";
		String res1 = "{'openid':'oXVLM4j_4aq3j7kJIHl3vUD2OxeU','session_key':'Xlv5dDHRrAK1mfp/c6EH/g=='}";
		
		
	}
}
