package com.htzhny.entity;
/**
 * 
 * @author mEssA9e
 *图片
 */
public class Img {
	private int id;
	private int goods_id;//所属商品id
	private String img_url;//图片地址
	private int img_status;//图片状态
	private int img_type;//1：商品图片 2：售后证明图片 3：用户头像
	private int after_sale_id;//售后事件id
	private int user_id;//用户id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public int getImg_status() {
		return img_status;
	}
	public void setImg_status(int img_status) {
		this.img_status = img_status;
	}
	public int getImg_type() {
		return img_type;
	}
	public void setImg_type(int img_type) {
		this.img_type = img_type;
	}
	public int getAfter_sale_id() {
		return after_sale_id;
	}
	public void setAfter_sale_id(int after_sale_id) {
		this.after_sale_id = after_sale_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "Img [id=" + id + ", goods_id=" + goods_id + ", img_url=" + img_url + ", img_status=" + img_status
				+ ", img_type=" + img_type + ", after_sale_id=" + after_sale_id + ", user_id=" + user_id + "]";
	}
	
}
