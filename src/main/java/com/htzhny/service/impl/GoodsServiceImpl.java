package com.htzhny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htzhny.dao.GoodsDao;
import com.htzhny.entity.After_saleQuery;
import com.htzhny.entity.Goods;
import com.htzhny.entity.PageBean;
import com.htzhny.service.GoodsService;


@Service
@Transactional
public class GoodsServiceImpl implements GoodsService{
	private int pageSize =10;//每页显示10条记录
	@Resource
	GoodsDao goodsDao;

	@Override
	public Integer addGoods(Goods goods) {
		return goodsDao.addGoods(goods);
	}

	@Override
	public Integer updateGoods(Goods goods) {
		return goodsDao.updateGoods(goods);
	}

	@Override
	@Transactional(readOnly=true)
	public Goods selectGoodsById(Integer id) {
		return goodsDao.selectGoodsById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public PageBean<Goods> selectGoodsByType(Integer currentPage,Integer typeId) {
		PageBean<Goods> pageBean=new PageBean<Goods>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
//		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = goodsDao.selectCountByType(typeId);
	    pageBean.setTotalCount(totalCount);
	    //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //封装每页显示的数据
        List<Goods> lists = goodsDao.selectGoodsByType(start,size, typeId);
        pageBean.setLists(lists);
		return pageBean;
	
	}

	@Override
	public Integer selectCount() {
		return goodsDao.selectCount();
	}

	@Override
	public Integer selectCountByType(Integer typeId) {
		return goodsDao.selectCountByType(typeId);
	}

	@Override
	public PageBean<Goods> selectAllGoods(Integer currentPage) {
		PageBean<Goods> pageBean=new PageBean<Goods>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
//		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = goodsDao.selectCount();
	    pageBean.setTotalCount(totalCount);
	    //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //封装每页显示的数据
        List<Goods> lists = goodsDao.selectAllGoods(start, size);
        pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public List<Goods> selectRecommendedGoods() {
		
		return goodsDao.selectRecommendedGoods();
	}

	@Override
	public Integer updateGoodsRecommendedstatus(String goods_id,Integer goods_Recommended) {
		
		return goodsDao.updateGoodsRecommendedstatus(goods_id, goods_Recommended);
	}

	@Override
	public List<Goods> selectAll() {
		return goodsDao.selectAll();
	}

	@Override
	public List<Goods> selectGoodsStatus(Integer good_status) {
		return goodsDao.selectGoodsStatus(good_status);
	}

	@Override
	public Integer updateGoodsStatus(Integer goods_status,Integer goods_id) {
		return goodsDao.updateGoodsStatus(goods_status, goods_id);
	}

	@Override
	public PageBean<Goods> selectGoodsByName(Integer currentPage,String goodsName) {
		// TODO Auto-generated method stub
		PageBean<Goods> pageBean=new PageBean<Goods>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
//		int pageSize=2;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = goodsDao.selectCountByGoodsName(goodsName);
	    pageBean.setTotalCount(totalCount);
	    //封装总页数
        double tc = totalCount;
        Double num =Math.ceil(tc/pageSize);//向上取整
        pageBean.setTotalPage(num.intValue());
        Integer start=(currentPage-1)*pageSize;
        Integer size=pageBean.getPageSize();
        //封装每页显示的数据
        List<Goods> lists = goodsDao.selectGoodsByName(start, size,goodsName);
        pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public Integer selectCountByGoodsName(String goodsName) {
		// TODO Auto-generated method stub
		return  goodsDao.selectCountByGoodsName( goodsName);
	}
}
