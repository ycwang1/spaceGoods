package com.htzhny.service;

import java.util.List;

import com.htzhny.entity.After_sale;
import com.htzhny.entity.After_saleQuery;
import com.htzhny.entity.Order_itemAndAfter_saleQuery;
import com.htzhny.entity.PageBean;

public interface After_saleService {
	public Integer addAfter_sale(After_sale after_sale);
	public Integer updateAfter_sale(After_sale after_sale);
	public Integer selectCountByUser(Integer user_id);
	public Integer selectCount();
	public PageBean<After_saleQuery> findByPage(Integer currentPage,Integer user_id);
	public PageBean<After_saleQuery> findByPageByAdmin(Integer currentPage);
	public PageBean<After_saleQuery> findByPageByTime(Integer currentPage,String time);
	public PageBean<Order_itemAndAfter_saleQuery> selectOneById(Integer currentPage,String order_id);
	public Integer updateHandleStatus(String after_sale_id,Integer handle_status);
	public After_sale selectById(String after_sale_id);
	
}
