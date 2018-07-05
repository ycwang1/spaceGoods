package com.htzhny.dao;

import java.util.List;

import com.htzhny.entity.Goods_type;

public interface Goods_typeDao {
	public Integer addGoods_type(Goods_type goods_type);
	public Integer updateGoods_type_name(Goods_type goods_type);
	public Integer updateGoods_type_status(Goods_type goods_type);
	public List<Goods_type> showAllType();
	
}
