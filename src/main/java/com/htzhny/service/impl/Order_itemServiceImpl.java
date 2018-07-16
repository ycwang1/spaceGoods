package com.htzhny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htzhny.dao.Order_itemDao;
import com.htzhny.entity.Order;
import com.htzhny.entity.Order_item;
import com.htzhny.entity.Order_itemQuery;
import com.htzhny.entity.PageBean;
import com.htzhny.service.Order_itemService;
@Service
@Transactional
public class Order_itemServiceImpl implements Order_itemService{
	private int pageSize =10;//每页显示10条记录
	@Autowired
	private Order_itemDao order_itemDao;
	@Override
	public Integer addOrder_item(Order_item item) {
		return order_itemDao.addOrder_item(item);
	}
	@Override
	public Integer updateRealPrice(Order_item item) {
		return order_itemDao.updateRealPrice(item);
	}
	  @Override
	
	public PageBean<Order_itemQuery> selectAllByOrderId(Integer currentPage,String id) {
		PageBean<Order_itemQuery> pageBean=new PageBean<Order_itemQuery>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
//		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = order_itemDao.selectCount(id);
	    pageBean.setTotalCount(totalCount);
	    //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //封装每页显示的数据
        List<Order_itemQuery> lists = order_itemDao.selectAllByOrderId(start, size, id);
        pageBean.setLists(lists);
		return pageBean;
	}
	
	@Override
	public Integer selectCount(String id) {
		return order_itemDao.selectCount(id);
	}
	@Override
	public List<Order_item> selectAllItem(String id) {
		return order_itemDao.selectAllItem(id);
	}
	@Override
	public Integer deleteOrderItem(String id) {
		
		return order_itemDao.deleteOrderItem(id);
	}
	@Override
	public Integer addCompleteOrderItem(Order_item item) {
		return order_itemDao.addCompleteOrderItem(item);
	}
	@Override
	public Integer updateAfterSale(Integer is_after_sale,String id) {
		return order_itemDao.updateAfterSale(is_after_sale,id);
	}
	@Override
	public Integer selectCountByGoodsId(Integer goods_id) {
		// TODO Auto-generated method stub
		return order_itemDao.selectCountByGoodsId(goods_id);
	}
	@Override
	public Integer updateAmountById(Integer amount, String orderId) {
		// TODO Auto-generated method stub
		return order_itemDao.updateAmountById(amount, orderId);
	}




}