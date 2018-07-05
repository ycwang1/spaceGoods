package com.htzhny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htzhny.dao.Goods_typeDao;
import com.htzhny.entity.Goods_type;
import com.htzhny.service.Good_typeService;
import com.htzhny.service.GoodsService;
@Service
@Transactional
public class Goods_typeServiceImpl implements Good_typeService{
	@Autowired
	private Goods_typeDao goods_typeDao;

	@Override
	public Integer addGoods_type(Goods_type goods_type) {
		return  goods_typeDao.addGoods_type(goods_type);
	}

	@Override
	public Integer updateGoods_type_name(Goods_type goods_type) {
		return goods_typeDao.updateGoods_type_name(goods_type);
	}

	@Override
	public Integer updateGoods_type_status(Goods_type goods_type) {
	
		return goods_typeDao.updateGoods_type_status(goods_type);
	}
	@Transactional(readOnly=true)
	@Override
	public List<Goods_type> showAllType() {
		return goods_typeDao.showAllType();
	}


}
