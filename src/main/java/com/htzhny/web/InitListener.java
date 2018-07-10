package com.htzhny.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.htzhny.task.TaskJob;

public class InitListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("项目初始化");
		//开启初始化任务
		ApplicationContext context=new ClassPathXmlApplicationContext("task.xml"); 
//		ApplicationContext context=new ClassPathXmlApplicationContext("spring-mvc.xml"); 
		

		
	}

}
