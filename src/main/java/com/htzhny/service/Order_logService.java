package com.htzhny.service;

import java.util.List;

import com.htzhny.entity.OrderLog;

public interface Order_logService {
	public Integer addLog(OrderLog orderLog);
	public List<OrderLog>findOneOrderLog(String order_id);
	public OrderLog findOrderLog(String order_id,Integer event);
}
