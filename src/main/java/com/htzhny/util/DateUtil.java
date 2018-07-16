package com.htzhny.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
}
