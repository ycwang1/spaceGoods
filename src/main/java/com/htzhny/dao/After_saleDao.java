
package com.htzhny.dao;


import java.util.List;

import com.htzhny.entity.After_sale;
import com.htzhny.entity.After_saleQuery;
import com.htzhny.entity.Order_itemAndAfter_saleQuery;

public interface After_saleDao {
	public Integer addAfter_sale(After_sale after_sale);
	public Integer updateAfter_sale(After_sale after_sale);
	public Integer selectCountByUser(Integer user_id);
	public Integer selectCount();
	public List<After_saleQuery> selectAllRecord(Integer start, Integer size,Integer user_id);
	public List<After_saleQuery> selectAllByAdmin(Integer start, Integer size);
	public List<After_saleQuery> selectAllByTime(Integer start,Integer size,String time);
	public List<Order_itemAndAfter_saleQuery> selectOneById(Integer start,Integer size,String order_id);
	public Integer updateHandleStatus(String after_sale_id,Integer handle_status);
	public After_sale selectById(String after_sale_id);
	
}
