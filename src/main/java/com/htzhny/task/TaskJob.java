package com.htzhny.task;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.htzhny.dao.OrderDao;
import com.htzhny.entity.TaskJobResult;
import com.htzhny.service.OrderService;  
@Lazy(false) 
//@Controller
@Component
public class TaskJob {  
//	@Autowired
	private OrderService orderDao;
	/**
	 * 每隔五秒实行一次 （test）
	 */
    @Scheduled(cron = "0 * * * 1 ?")  
    public void job1() {  
        System.out.println("任务进行中。。。");  
    }  
    /**
	 * 每天下午6点计算昨天6点到今天6点  订单状态为待确认
	 * 订单商品数量是否达到规定值，达到了更改订单状态 ，否则推送消息通知用户手动退款
	 * 
	 */
//    @Scheduled(cron = "0 0 18 * * ?")  
    @Scheduled(cron = "0/10 * * * 1 ?")  
    public void update() {  
        System.out.println("每天18点执行。。。");
        List<TaskJobResult> list= orderDao.selectOrder();
        System.out.println(list.size());
    }  
}  