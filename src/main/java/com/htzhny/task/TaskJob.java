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
	 * ÿ������ʵ��һ�� ��test��
	 */
    @Scheduled(cron = "0 * * * 1 ?")  
    public void job1() {  
        System.out.println("��������С�����");  
    }  
    /**
	 * ÿ������6���������6�㵽����6��  ����״̬Ϊ��ȷ��
	 * ������Ʒ�����Ƿ�ﵽ�涨ֵ���ﵽ�˸��Ķ���״̬ ������������Ϣ֪ͨ�û��ֶ��˿�
	 * 
	 */
//    @Scheduled(cron = "0 0 18 * * ?")  
    @Scheduled(cron = "0/10 * * * 1 ?")  
    public void update() {  
        System.out.println("ÿ��18��ִ�С�����");
        List<TaskJobResult> list= orderDao.selectOrder();
        System.out.println(list.size());
    }  
}  