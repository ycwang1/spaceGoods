package com.htzhny.entity;

public class OrderQuery {
	private String id;
	private String address;//收货地址
	private String delivery_time;//送货时间
	private double order_low_price;//订单所有商品总共的最低价
	private double order_high_price;//订单所有商品总共的最高价
	private double order_real_price;//供货商给出的实际报价
	private int order_status;//0:失效 （取消订单）1:待报价 2：待确认 3：待发货 4：待收货 5：售后处理中 6：已完成 
	private String create_time;//创建时间
	private int user_id;//订单所属用户id
	private String user_company;
	private int pay_status;//支付状态 0:未支付 1：已支付
	private int bill_status;// 1：本月账单，2：上月待支付账单 3：历史账单
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDelivery_time() {
		return delivery_time;
	}
	public void setDelivery_time(String delivery_time) {
		this.delivery_time = delivery_time;
	}
	public double getOrder_low_price() {
		return order_low_price;
	}
	public void setOrder_low_price(double order_low_price) {
		this.order_low_price = order_low_price;
	}
	public double getOrder_high_price() {
		return order_high_price;
	}
	public void setOrder_high_price(double order_high_price) {
		this.order_high_price = order_high_price;
	}
	public double getOrder_real_price() {
		return order_real_price;
	}
	public void setOrder_real_price(double order_real_price) {
		this.order_real_price = order_real_price;
	}
	public int getOrder_status() {
		return order_status;
	}
	public void setOrder_status(int order_status) {
		this.order_status = order_status;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_company() {
		return user_company;
	}
	public void setUser_company(String user_company) {
		this.user_company = user_company;
	}
	public int getPay_status() {
		return pay_status;
	}
	public void setPay_status(int pay_status) {
		this.pay_status = pay_status;
	}
	

	public int getBill_status() {
		return bill_status;
	}
	public void setBill_status(int bill_status) {
		this.bill_status = bill_status;
	}
	@Override
	public String toString() {
		return "OrderQuery [id=" + id + ", address=" + address + ", delivery_time=" + delivery_time
				+ ", order_low_price=" + order_low_price + ", order_high_price=" + order_high_price
				+ ", order_real_price=" + order_real_price + ", order_status=" + order_status + ", create_time="
				+ create_time + ", user_id=" + user_id + ", user_company=" + user_company + ", pay_status=" + pay_status
				+ "]";
	}
	public OrderQuery(String id, String address, String delivery_time, double order_low_price, double order_high_price,
			double order_real_price, int order_status, String create_time, int user_id, String user_company,
			int pay_status,int bill_status) {
		super();
		this.id = id;
		this.address = address;
		this.delivery_time = delivery_time;
		this.order_low_price = order_low_price;
		this.order_high_price = order_high_price;
		this.order_real_price = order_real_price;
		this.order_status = order_status;
		this.create_time = create_time;
		this.user_id = user_id;
		this.user_company = user_company;
		this.pay_status = pay_status;
		this.bill_status=bill_status;
	}
	public OrderQuery() {
		super();
	}
	
}
