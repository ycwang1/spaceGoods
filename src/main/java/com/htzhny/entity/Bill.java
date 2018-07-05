package com.htzhny.entity;
/**
 * 账单类
 * @author mEssA9e
 *
 */
public class Bill {
	private String id; //账单id
	private Integer user_id;//账单所属用户id
	private String onemonth;//账单所属月份
	private double month_pay_money;//每月应该支付的金额
	private Integer flag;//是否当月账单 1:当月账单 2：上月未支付账单，3：历史账单
	private String remake;//备注
	
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getOnemonth() {
		return onemonth;
	}
	public void setOnemonth(String onemonth) {
		this.onemonth = onemonth;
	}
	public double getMonth_pay_money() {
		return month_pay_money;
	}
	public void setMonth_pay_money(double month_pay_money) {
		this.month_pay_money = month_pay_money;
	}
	public String getRemake() {
		return remake;
	}
	public void setRemake(String remake) {
		this.remake = remake;
	}
	

	public Bill() {
		super();
	}
	public Bill(String id, Integer user_id, String onemonth, double month_pay_money, Integer flag) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.onemonth = onemonth;
		this.month_pay_money = month_pay_money;
		this.flag = flag;
	}
	public Bill(String id, Integer user_id, String onemonth, double month_pay_money, Integer flag, String remake) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.onemonth = onemonth;
		this.month_pay_money = month_pay_money;
		this.flag = flag;
		this.remake = remake;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", user_id=" + user_id + ", onemonth=" + onemonth + ", month_pay_money="
				+ month_pay_money + ", flag=" + flag + ", remake=" + remake + "]";
	}

	
}
