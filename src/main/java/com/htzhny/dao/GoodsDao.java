package com.htzhny.dao;

import java.util.List;

import com.htzhny.entity.Goods;

public interface GoodsDao {
	public Integer addGoods(Goods goods);
	public Integer updateGoods(Goods goods);
	public Integer selectCount();
	public Integer selectCountByType(Integer typeId);
	public Goods selectGoodsById(Integer id);
	public List<Goods> selectGoodsByType(Integer start,Integer size,Integer typeId);
	public List<Goods> selectAllGoods(Integer start,Integer size);
	public List<Goods> selectRecommendedGoods();
	public Integer updateGoodsRecommendedstatus(String goods_id,Integer goods_Recommended);
	public List<Goods> selectAll();
	public List<Goods> selectGoodsStatus(Integer good_status);
	public Integer updateGoodsStatus(Integer goods_status,Integer id);
	public List<Goods> selectGoodsByName(Integer start,Integer size,String goodsName);
	public Integer selectCountByGoodsName(String goodsName);
}
