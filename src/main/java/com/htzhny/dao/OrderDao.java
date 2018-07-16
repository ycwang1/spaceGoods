package com.htzhny.dao;

import java.util.List;
import java.util.Map;

import com.htzhny.entity.Order;
import com.htzhny.entity.OrderQuery;
import com.htzhny.entity.TaskJobResult;

public interface OrderDao {
	public List<OrderQuery> selectUserOrderByStatus( Integer start,Integer size,Integer status,Integer user_id);
	public Integer selectCountByStatus(Integer order_status);
	public Integer selectCountByUserByStatus(Integer order_status,Integer user_id);
	public List<OrderQuery> selectAllOrderByStatus( Integer start,Integer size,Integer status);
	public Integer addOrder(Order order);
	public Integer updateStatus(Integer status,String id);
	public Integer updateRealPrice(double price,String id);
	public Integer updatePayStatus(Integer pay_status,String id);
	public List<OrderQuery> selectUserOrderByBillStatus(Integer start,Integer size,Integer bill_status,Integer user_id);
	public List<OrderQuery> selectAllOrderByBillStatus(Integer start,Integer size,Integer bill_status);
	public Integer selectCountByUserByBillStatus(Integer bill_status,Integer user_id);
	public Integer selectCountByBillStatus(Integer bill_status);
	public Order selectOneOrderById(String id);
	public Integer updatePayStatusByUser(Integer user_id);
	public List<OrderQuery> selectUserOrderByStatusNoPage(Integer status,Integer user_id);
	public List<OrderQuery> selectUserOrder(Integer user_id);
	public Integer updateBillStatus(Integer bill_status); 
	public List<OrderQuery> selectUserOrderByOrderStatus( Map map);
	public Integer selectCountByOrderStatus(Map map);
	
	public List<OrderQuery> selectUserUnPayOrder(Integer start,Integer size,Integer user_id);
	public Integer selectUserUnPayOrderCount(Integer user_id);
	public Integer addCurtOrder(Order order);
	public Order selectUserCurtOrder(Integer userId);
	public Integer updateOrder(String delivery_time,String address,String id);
	public List<TaskJobResult> selectOrder();
}
