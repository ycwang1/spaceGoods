package com.htzhny.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���ڲ���������
 * @author Administrator
 *
 */
public class DateUtil {
	private DateUtil() {}  
    private static DateUtil single=null;  
    //��̬��������   
    public static DateUtil getInstance() {  
         if (single == null) {    
             single = new DateUtil();  
         }    
        return single;  
    }  
    /**
     * ��ȡ��ǰʱ�䣺��ʽ��yyyy-MM-dd HH:mm:ss
     * @return
     */
  	public String getNow(){
  		Date dt =new Date(); 
		String formatDate = "";  
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH��ʾ24Сʱ�ƣ�  
	    formatDate = dFormat.format(dt); 
//	    String create_time = DateFormat.getDateInstance().format(dt);  
	    return formatDate;	
  	}
}
