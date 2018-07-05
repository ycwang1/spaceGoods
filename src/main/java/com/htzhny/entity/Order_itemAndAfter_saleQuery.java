package com.htzhny.entity;

public class Order_itemAndAfter_saleQuery {
	private String after_sale_id;
	private String goods_name;
	private Integer goods_amount;//商品数量
	private double goods_low_price; //最低价
	private double goods_high_price;//最高价
	private double goods_real_price;//单个商品实际报价
	private String after_sale_reason;//申请售后理由
	private int handle_status; //1：未受理 2：处理中 3：处理完成
	private String handle_way; //售后处理方式
	private String handle_person;//售后处理人
	private String handle_time; //受理时间
	private String company;
	private String prove_img;//证明图片
	private String delivery_time;
	private String goods_img; //商品图片
	
	public String getGoods_img() {
		return goods_img;
	}
	public void setGoods_img(String goods_img) {
		this.goods_img = goods_img;
	}
	public String getAfter_sale_id() {
		return after_sale_id;
	}
	public void setAfter_sale_id(String after_sale_id) {
		this.after_sale_id = after_sale_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getDelivery_time() {
		return delivery_time;
	}
	public void setDelivery_time(String delivery_time) {
		this.delivery_time = delivery_time;
	}
	public Integer getGoods_amount() {
		return goods_amount;
	}
	public void setGoods_amount(Integer goods_amount) {
		this.goods_amount = goods_amount;
	}
	public double getGoods_low_price() {
		return goods_low_price;
	}
	public void setGoods_low_price(double goods_low_price) {
		this.goods_low_price = goods_low_price;
	}
	public double getGoods_high_price() {
		return goods_high_price;
	}
	public void setGoods_high_price(double goods_high_price) {
		this.goods_high_price = goods_high_price;
	}
	public double getGoods_real_price() {
		return goods_real_price;
	}
	public void setGoods_real_price(double goods_real_price) {
		this.goods_real_price = goods_real_price;
	}
	public String getAfter_sale_reason() {
		return after_sale_reason;
	}
	public void setAfter_sale_reason(String after_sale_reason) {
		this.after_sale_reason = after_sale_reason;
	}
	public int getHandle_status() {
		return handle_status;
	}
	public void setHandle_status(int handle_status) {
		this.handle_status = handle_status;
	}
	public String getHandle_way() {
		return handle_way;
	}
	public void setHandle_way(String handle_way) {
		this.handle_way = handle_way;
	}
	public String getHandle_person() {
		return handle_person;
	}
	public void setHandle_person(String handle_person) {
		this.handle_person = handle_person;
	}
	public String getHandle_time() {
		return handle_time;
	}
	public void setHandle_time(String handle_time) {
		this.handle_time = handle_time;
	}
	public String getProve_img() {
		return prove_img;
	}
	public void setProve_img(String prove_img) {
		this.prove_img = prove_img;
	}
	
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}




	public Order_itemAndAfter_saleQuery(String after_sale_id, String goods_name, Integer goods_amount,
			double goods_low_price, double goods_high_price, double goods_real_price, String after_sale_reason,
			int handle_status, String handle_way, String handle_person, String handle_time, String company,
			String prove_img, String delivery_time, String goods_img) {
		super();
		this.after_sale_id = after_sale_id;
		this.goods_name = goods_name;
		this.goods_amount = goods_amount;
		this.goods_low_price = goods_low_price;
		this.goods_high_price = goods_high_price;
		this.goods_real_price = goods_real_price;
		this.after_sale_reason = after_sale_reason;
		this.handle_status = handle_status;
		this.handle_way = handle_way;
		this.handle_person = handle_person;
		this.handle_time = handle_time;
		this.company = company;
		this.prove_img = prove_img;
		this.delivery_time = delivery_time;
		this.goods_img = goods_img;
	}
	public Order_itemAndAfter_saleQuery(String after_sale_id, String goods_name, Integer goods_amount,
			double goods_low_price, double goods_high_price, double goods_real_price, String after_sale_reason,
			int handle_status, String handle_way, String handle_person, String handle_time, String company,
			String prove_img, String delivery_time) {
		super();
		this.after_sale_id = after_sale_id;
		this.goods_name = goods_name;
		this.goods_amount = goods_amount;
		this.goods_low_price = goods_low_price;
		this.goods_high_price = goods_high_price;
		this.goods_real_price = goods_real_price;
		this.after_sale_reason = after_sale_reason;
		this.handle_status = handle_status;
		this.handle_way = handle_way;
		this.handle_person = handle_person;
		this.handle_time = handle_time;
		this.company = company;
		this.prove_img = prove_img;
		this.delivery_time = delivery_time;
	}
	@Override
	public String toString() {
		return "Order_itemAndAfter_saleQuery [after_sale_id=" + after_sale_id + ", goods_name=" + goods_name
				+ ", goods_amount=" + goods_amount + ", goods_low_price=" + goods_low_price + ", goods_high_price="
				+ goods_high_price + ", goods_real_price=" + goods_real_price + ", after_sale_reason="
				+ after_sale_reason + ", handle_status=" + handle_status + ", handle_way=" + handle_way
				+ ", handle_person=" + handle_person + ", handle_time=" + handle_time + ", company=" + company
				+ ", prove_img=" + prove_img + ", delivery_time=" + delivery_time + ", goods_img=" + goods_img + "]";
	}
	public Order_itemAndAfter_saleQuery() {
		super();
	}

}
