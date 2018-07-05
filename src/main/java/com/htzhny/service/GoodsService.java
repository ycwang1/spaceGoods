package com.htzhny.service;

import java.util.List;

import com.htzhny.entity.Goods;
import com.htzhny.entity.PageBean;

public interface GoodsService {
	public Integer addGoods(Goods goods);
	public Integer updateGoods(Goods goods);
	public Integer selectCount();
	public Integer selectCountByType(Integer typeId);
	public Goods selectGoodsById(Integer id);
	public PageBean<Goods> selectGoodsByType(Integer currentPage,Integer typeId);
	public PageBean<Goods> selectAllGoods(Integer currentPage);
	public List<Goods> selectRecommendedGoods();
	public Integer updateGoodsRecommendedstatus(String goods_id,Integer goods_Recommended);
	public List<Goods> selectAll();
	public List<Goods> selectGoodsStatus(Integer good_status);
	public Integer updateGoodsStatus(Integer goods_status,Integer goods_id);
	public PageBean<Goods> selectGoodsByName(Integer currentPage,String goodsName);
	public Integer selectCountByGoodsName(String goodsName);
}
