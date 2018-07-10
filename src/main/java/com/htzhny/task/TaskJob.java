package com.htzhny.task;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;    
import org.springframework.stereotype.Component;  
@Lazy(false) 
@Component("qbScheduler")
public class TaskJob {  
	/**
	 * ÿ������ʵ��һ��
	 */
    @Scheduled(cron = "0/5 * * * * ?")  
    public void job1() {  
        System.out.println("��������С�����");  
    }  
}  