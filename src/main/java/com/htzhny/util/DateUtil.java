package com.htzhny.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
  	/**
  	 * �Ź�ʣ��ʱ���ȡ
  	 * @param dstr
  	 * @return
  	 * @throws ParseException
  	 */
  	public long getMills(String dstr) throws ParseException{
  		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
  		dstr="2018-07-17";
		Date date=sdf.parse(dstr);
		long  s1=date.getTime();//��ʱ��תΪ����
		long s2=System.currentTimeMillis();//�õ���ǰ�ĺ���
		int dates = 2;
		int day=(int) ((s2-s1)/1000/60/60/24);
		int days = day % dates;
		
		Date today = new Date();
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, +days==0?2:1);//+�����ʱ���һ��
        calendar.set(Calendar.HOUR, -12);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        today = calendar.getTime();
        return calendar.getTimeInMillis()-System.currentTimeMillis();
  	}
}
