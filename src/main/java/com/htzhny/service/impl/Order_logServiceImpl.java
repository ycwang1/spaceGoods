package com.htzhny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htzhny.dao.OrderLogDao;
import com.htzhny.entity.OrderLog;
import com.htzhny.service.Order_logService;
@Service
@Transactional
public class Order_logServiceImpl implements Order_logService{
	@Resource
	private OrderLogDao logDao;
	@Override
	public Integer addLog(OrderLog orderLog) {
		return logDao.addLog(orderLog);
	}
	@Transactional(readOnly=true)
	@Override
	public List<OrderLog> findOneOrderLog(String order_id) {
		return logDao.findOneOrderLog(order_id);
	}
	@Override
	public OrderLog findOrderLog(String order_id, Integer event) {
		return logDao.findOrderLog(order_id, event);
	}

}
