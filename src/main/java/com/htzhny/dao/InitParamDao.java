package com.htzhny.dao;

import java.util.List;

import com.htzhny.entity.Bill;
import com.htzhny.entity.InitParam;

public interface InitParamDao {
	
	public List<InitParam> selectParam(Integer status);
}
