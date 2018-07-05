package com.htzhny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htzhny.dao.After_saleDao;
import com.htzhny.dao.Order_itemDao;
import com.htzhny.entity.After_sale;
import com.htzhny.entity.After_saleQuery;
import com.htzhny.entity.Order_itemAndAfter_saleQuery;
import com.htzhny.entity.PageBean;
import com.htzhny.service.After_saleService;



@Service
@Transactional
public class After_saleServiceImpl implements After_saleService{
	private int pageSize =10;//每页显示10条记录
	@Resource
	private After_saleDao after_saleDao;
	@Resource
	private Order_itemDao order_itemDao;
	public Integer addAfter_sale(After_sale after_sale){
		return after_saleDao.addAfter_sale(after_sale);
	}

	@Override
	public Integer updateAfter_sale(After_sale after_sale) {
		return after_saleDao.updateAfter_sale(after_sale);
	}
	@Override
	public Integer selectCountByUser(Integer user_id) {
		return after_saleDao.selectCountByUser(user_id);
	}

	@Transactional(readOnly=true)
	@Override
	public PageBean<After_saleQuery> findByPage(Integer currentPage,Integer user_id) {
		
		PageBean<After_saleQuery> pageBean=new PageBean<After_saleQuery>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
//		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = after_saleDao.selectCountByUser(user_id);
	    pageBean.setTotalCount(totalCount);
	    //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //封装每页显示的数据
        List<After_saleQuery> lists = after_saleDao.selectAllRecord(start,size, user_id);
        pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public PageBean<After_saleQuery> findByPageByAdmin(Integer currentPage) {
		PageBean<After_saleQuery> pageBean=new PageBean<After_saleQuery>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
//		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = after_saleDao.selectCount();
	    pageBean.setTotalCount(totalCount);
	    //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //封装每页显示的数据
        List<After_saleQuery> lists = after_saleDao.selectAllByAdmin(start, size);
        pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public Integer selectCount() {
		return after_saleDao.selectCount();
	}

	@Override
	public PageBean<After_saleQuery> findByPageByTime(Integer currentPage, String time) {
		PageBean<After_saleQuery> pageBean=new PageBean<After_saleQuery>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
//		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = after_saleDao.selectCount();
	    pageBean.setTotalCount(totalCount);
	    //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //封装每页显示的数据
        List<After_saleQuery> lists = after_saleDao.selectAllByTime(start, size,time);
        pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public  PageBean<Order_itemAndAfter_saleQuery> selectOneById(Integer currentPage,String id) {
		PageBean<Order_itemAndAfter_saleQuery> pageBean=new PageBean<Order_itemAndAfter_saleQuery>();
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
        List<Order_itemAndAfter_saleQuery> lists = after_saleDao.selectOneById(start, size,id);
        pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public Integer updateHandleStatus(String after_sale_id, Integer handle_status) {
		return after_saleDao.updateHandleStatus(after_sale_id, handle_status);
	}

	@Override
	public After_sale selectById(String after_sale_id) {
		return after_saleDao.selectById(after_sale_id);
	}





}
