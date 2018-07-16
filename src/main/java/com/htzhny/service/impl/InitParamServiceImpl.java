package com.htzhny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htzhny.dao.AddressDao;
import com.htzhny.dao.BillDao;
import com.htzhny.dao.InitParamDao;
import com.htzhny.entity.Bill;
import com.htzhny.entity.InitParam;
import com.htzhny.service.InitParamService;
@Service
@Transactional
public class InitParamServiceImpl implements InitParamService{
	@Resource
	InitParamDao initParamDao;
	@Override
	public List<InitParam> selectParam(Integer status) {
		// TODO Auto-generated method stub
		return initParamDao.selectParam(status);
	}


}
