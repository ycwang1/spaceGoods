package com.htzhny.util;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.htzhny.session.MySessionContext;

public class OrderUtil {
	private OrderUtil() {}  
    private static OrderUtil single=null;  
    //静态工厂方法   
    public static OrderUtil getInstance() {  
         if (single == null) {    
             single = new OrderUtil();  
         }    
        return single;  
    }  
    //生成订单id（流水号） by wyc
  	public String getOrderId(){
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

  		return sBuffer.append(year).append(month).append(day).append(hour)
  		.append(minute).append(second).append(mm).toString();
  		
  	}
  	private static String getString(int number){
  		return number>10?number+"":"0"+number;
  	}
  	private static String getMillsecond(int number){
  		return number>10?number>100?number+"":"0"+number:"00"+number;
  	}

}
