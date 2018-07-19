package com.htzhny.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期操作工具类
 * @author Administrator
 *
 */
public class DateUtil {
	private DateUtil() {}  
    private static DateUtil single=null;  
    //静态工厂方法   
    public static DateUtil getInstance() {  
         if (single == null) {    
             single = new DateUtil();  
         }    
        return single;  
    }  
    /**
     * 获取当前时间：格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
  	public String getNow(){
  		Date dt =new Date(); 
		String formatDate = "";  
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；  
	    formatDate = dFormat.format(dt); 
//	    String create_time = DateFormat.getDateInstance().format(dt);  
	    return formatDate;	
  	}
  	/**
  	 * 团购剩余时间获取
  	 * @param dstr
  	 * @return
  	 * @throws ParseException
  	 */
  	public long getMills(String dstr) throws ParseException{
  		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
  		dstr="2018-07-17";
		Date date=sdf.parse(dstr);
		long  s1=date.getTime();//将时间转为毫秒
		long s2=System.currentTimeMillis();//得到当前的毫秒
		int dates = 2;
		int day=(int) ((s2-s1)/1000/60/60/24);
		int days = day % dates;
		
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, +days==0?2:1);//+今天的时间加一天
        calendar.set(Calendar.HOUR, -12);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        today = calendar.getTime();
        return calendar.getTimeInMillis()-System.currentTimeMillis();
  	}
}
