package com.htzhny.entity;

public class Order_itemQuery {
	private String id;
	private Integer goods_id;
	private String goods_name;//��Ʒ��
	private String order_id;//����ID
	private double goods_amount;//��Ʒ����
	private double goods_low_price;//��������Ʒ�ܹ�����ͼ�
	private double goods_high_price;//��������Ʒ�ܹ�����߼�
	private double goods_real_price;//������Ʒʵ�ʱ���
	private Integer is_after_sale;
	private String goods_img;//��ƷͼƬ
	
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_img() {
		return goods_img;
	}
	public void setGoods_img(String goods_img) {
		this.goods_img = goods_img;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public double getGoods_amount() {
		return goods_amount;
	}
	public void setGoods_amount(double goods_amount) {
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

	

	public Integer getIs_after_sale() {
		return is_after_sale;
	}
	public void setIs_after_sale(Integer is_after_sale) {
		this.is_after_sale = is_after_sale;
	}
	public Order_itemQuery(String id, Integer goods_id, String goods_name, String order_id, double goods_amount,
			double goods_low_price, double goods_high_price, double goods_real_price) {
		super();
		this.id = id;
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.order_id = order_id;
		this.goods_amount = goods_amount;
		this.goods_low_price = goods_low_price;
		this.goods_high_price = goods_high_price;
		this.goods_real_price = goods_real_price;
	}
	public Order_itemQuery(String id, Integer goods_id, String goods_name, String order_id, double goods_amount,
			double goods_low_price, double goods_high_price, double goods_real_price, Integer is_after_sale) {
		super();
		this.id = id;
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.order_id = order_id;
		this.goods_amount = goods_amount;
		this.goods_low_price = goods_low_price;
		this.goods_high_price = goods_high_price;
		this.goods_real_price = goods_real_price;
		this.is_after_sale = is_after_sale;
	}
	
	public Order_itemQuery(String id, Integer goods_id, String goods_name, String order_id, double goods_amount,
			double goods_low_price, double goods_high_price, double goods_real_price, Integer is_after_sale,String goods_img) {
		super();
		this.id = id;
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.order_id = order_id;
		this.goods_amount = goods_amount;
		this.goods_low_price = goods_low_price;
		this.goods_high_price = goods_high_price;
		this.goods_real_price = goods_real_price;
		this.is_after_sale = is_after_sale;
		this.goods_img = goods_img;
	}
	
	public Order_itemQuery() {
		super();
	}
	@Override
	public String toString() {
		return "Order_itemQuery [id=" + id + ", goods_id=" + goods_id + ", goods_name=" + goods_name + ", order_id="
				+ order_id + ", goods_amount=" + goods_amount + ", goods_low_price=" + goods_low_price
				+ ", goods_high_price=" + goods_high_price + ", goods_real_price=" + goods_real_price
				+ ", is_after_sale=" + is_after_sale + ", goods_img=" + goods_img + "]";
	}
	
}