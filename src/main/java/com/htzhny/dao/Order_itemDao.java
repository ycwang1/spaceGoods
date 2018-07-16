package com.htzhny.dao;

import java.util.List;

import com.htzhny.entity.Order_item;
import com.htzhny.entity.Order_itemQuery;

public interface Order_itemDao {
	public Integer addOrder_item(Order_item item);
	public Integer updateRealPrice(Order_item item);
	public List<Order_itemQuery> selectAllByOrderId( Integer start,Integer size,String id);
	public Integer selectCount(String id);
	public List<Order_item> selectAllItem(String order_id);
	public Integer deleteOrderItem(String id);
	public Integer addCompleteOrderItem(Order_item item);
	public Integer updateAfterSale(Integer is_after_sale,String id);
	public Integer selectCountByGoodsId(Integer goods_id);
	public Integer updateAmountById(Integer amount,String orderId);
}