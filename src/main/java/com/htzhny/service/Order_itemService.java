package com.htzhny.service;

import java.util.List;

import com.htzhny.entity.Order_item;
import com.htzhny.entity.Order_itemQuery;
import com.htzhny.entity.PageBean;

public interface Order_itemService {
	public Integer addOrder_item(Order_item item);
	public Integer updateRealPrice(Order_item item);
	public PageBean<Order_itemQuery> selectAllByOrderId(Integer currentPage,String id);
	public Integer selectCount(String id);
	public List<Order_item> selectAllItem(String id);
	public Integer deleteOrderItem(String id);
	public Integer addCompleteOrderItem(Order_item item);
	public Integer updateAfterSale(Integer is_after_sale,String id);
}