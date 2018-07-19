package com.htzhny.service;

import java.util.List;

import com.htzhny.entity.Order;
import com.htzhny.entity.OrderQuery;
import com.htzhny.entity.PageBean;
import com.htzhny.entity.TaskJobResult;

public interface OrderService{
	public PageBean<OrderQuery> selectUserOrderByStatus(Integer currentPage,Integer status,Integer user_id);
	public Integer selectCountByStatus(Integer status);
	public Integer selectCountByUserByStatus(Integer order_status,Integer user_id);
	public PageBean<OrderQuery> selectAllOrderByStatus( Integer currentPage,Integer status);
	public Integer addOrder(Order order);
	public Integer updateStatus(Integer status,String id);
	public Integer updateRealPrice(double price,String id);
	public Integer updatePayStatus(Integer pay_status,String id);
	public PageBean<OrderQuery> selectUserOrderByBillStatus(Integer currentPage,Integer bill_status,Integer user_id);
	public PageBean<OrderQuery> selectAllOrderByBillStatus(Integer currentPage,Integer bill_status);

	public Order selectOneOrderById(String id);
	public Integer updatePayStatusByUser(Integer user_id);
	public List<OrderQuery> selectUserOrderByStatusNoPage(Integer status,Integer user_id);
	public List<OrderQuery> selectUserOrder(Integer user_id);
	public PageBean<OrderQuery> selectUserOrderByOrderStatus(Integer currentPage,List status,Integer user_id);
	
	public Integer selectUserUnPayOrderCount(Integer user_id);
	public PageBean<OrderQuery> selectUserUnPayOrder(Integer currentPage, Integer user_id);
	public Integer addCurtOrder(Order order);
	public Order selectUserCurtOrder(Integer userId);
	public Integer updateOrder(String delivery_time,String address,String id);
	public List<TaskJobResult> selectOrder();
	public Integer updateOrderStatusByGoodsId(Integer goodsId);
}