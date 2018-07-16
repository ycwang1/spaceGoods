package com.htzhny.util;
/**
 * 购物车数据传递工具类
 * @author mEssA9e
 *
 */

public class CartItemUtil {
	private Integer goods_id;
	private Integer amount;
	private double price;//商品价格
	private String id;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public CartItemUtil(Integer goods_id, Integer amount) {
		super();
		this.goods_id = goods_id;
		this.amount = amount;
	}
	
	public CartItemUtil(Integer goods_id, Integer amount, double price) {
		super();
		this.goods_id = goods_id;
		this.amount = amount;
		this.price = price;
	}

	public CartItemUtil(Integer amount, double price, String id) {
		super();
		this.amount = amount;
		this.price = price;
		this.id = id;
	}
	public CartItemUtil() {
		super();
	}
	@Override
	public String toString() {
		return "CartItemUtil [goods_id=" + goods_id + ", amount=" + amount + ", price=" + price + "]";
	}
	
	
}
