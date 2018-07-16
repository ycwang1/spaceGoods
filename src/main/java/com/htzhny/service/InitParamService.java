package com.htzhny.service;

import java.util.List;

import com.htzhny.entity.InitParam;

public interface InitParamService {
	public List<InitParam> selectParam(Integer status);
}
