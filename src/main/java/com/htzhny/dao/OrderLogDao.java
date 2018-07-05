package com.htzhny.dao;

import java.util.List;

import com.htzhny.entity.OrderLog;

public interface OrderLogDao {
	public Integer addLog(OrderLog orderLog);
	public List<OrderLog>findOneOrderLog(String order_id);
	public OrderLog findOrderLog(String order_id,Integer event);
}
