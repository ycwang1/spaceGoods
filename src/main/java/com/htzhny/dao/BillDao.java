package com.htzhny.dao;

import java.util.List;

import com.htzhny.entity.Bill;

public interface BillDao {
	
	public Integer addBill(Bill bill);
	public Bill selectBillByUserId(Integer user_id,Integer flag);
	public Integer updateMonthPayMoney(double month_pay_money,Integer user_id);
	public List<Bill> selectAllBill(Integer flag);
	public Integer updateFlag(Bill bill);
}
