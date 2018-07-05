package com.htzhny.util;
/**
 * 购物车数据传递工具类
 * @author mEssA9e
 *
 */

public class CartItemUtil {
	private Integer goods_id;
	private Integer amount;
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
	@Override
	public String toString() {
		return "CartItemUtil [goods_id=" + goods_id + ", amount=" + amount + "]";
	}
	public CartItemUtil() {
		super();
	}
	
}
