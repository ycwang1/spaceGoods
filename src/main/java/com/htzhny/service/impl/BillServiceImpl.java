package com.htzhny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htzhny.dao.BillDao;
import com.htzhny.entity.Bill;
import com.htzhny.service.BillService;
@Service
@Transactional
public class BillServiceImpl implements BillService{
	@Resource
	private BillDao billDao;

	@Override
	public Integer addBill(Bill bill) {
		return billDao.addBill(bill);
	}

	@Override
	public Bill selectBillByUserId(Integer user_id,Integer flag) {
		return billDao.selectBillByUserId(user_id,flag);
	}

	@Override
	public Integer updateMonthPayMoney(double month_pay_money,Integer user_id) {
		
		return billDao.updateMonthPayMoney(month_pay_money, user_id);
	}

	@Override
	public List<Bill> selectAllBill(Integer flag) {
		return billDao.selectAllBill(flag);
	}


}
