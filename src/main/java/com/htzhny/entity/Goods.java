package com.htzhny.entity;

import java.util.Date;

/**
 * 
 * @author mEssA9e
 *商品类
 */
public class Goods {
	private Integer id;
	private String goods_name;
	private double goods_low_price; //最低价
	private double goods_high_price;//最高价
	private int goods_type; //商品类型
	private String goods_detail; //商品详情
	private int goods_status; //1:上架 2：下架
	private int goods_Recommended; //是否推荐
	private String goods_introduce; //商品介绍
	private String base_id;//基地id
	private int goods_resource_id;//溯源报告id
	private String goods_create_time; //商品添加时间
	private String goods_quality_report_id;//商品质检报告id
	private String goods_img; //商品图片
//	

	public Goods(Integer id, String goods_name, double d, double e, int goods_type,
			String goods_detail, int goods_status, String goods_create_time,String goods_img) {
		super();
		this.id = id;
		this.goods_name = goods_name;
		this.goods_low_price = d;
		this.goods_high_price = e;
		this.goods_type = goods_type;
		this.goods_detail = goods_detail;
		this.goods_status = goods_status;
		this.goods_create_time = goods_create_time;
		this.goods_img=goods_img;
	}
	public Goods() {
		super();
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", goods_name=" + goods_name + ", goods_low_price=" + goods_low_price
				+ ", goods_high_price=" + goods_high_price + ", goods_type=" + goods_type + ", goods_detail="
				+ goods_detail + ", goods_status=" + goods_status + ", goods_Recommended=" + goods_Recommended
				+ ", goods_introduce=" + goods_introduce + ", base_id=" + base_id + ", goods_resource_id="
				+ goods_resource_id + ", goods_create_time=" + goods_create_time + ", goods_quality_report_id="
				+ goods_quality_report_id + ", goods_img=" + goods_img + "]";
	}
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
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
	public int getGoods_type() {
		return goods_type;
	}
	public void setGoods_type(int goods_type) {
		this.goods_type = goods_type;
	}
	public String getGoods_detail() {
		return goods_detail;
	}
	public void setGoods_detail(String goods_detail) {
		this.goods_detail = goods_detail;
	}
	
	public String getGoods_img() {
		return goods_img;
	}
	public void setGoods_img(String goods_img) {
		this.goods_img = goods_img;
	}
	public int getGoods_status() {
		return goods_status;
	}
	public void setGoods_status(int goods_status) {
		this.goods_status = goods_status;
	}
	public int getGoods_Recommended() {
		return goods_Recommended;
	}
	public void setGoods_Recommended(int goods_Recommended) {
		this.goods_Recommended = goods_Recommended;
	}
	public String getGoods_introduce() {
		return goods_introduce;
	}
	public void setGoods_introduce(String goods_introduce) {
		this.goods_introduce = goods_introduce;
	}
	public String getBase_id() {
		return base_id;
	}
	public void setBase_id(String base_id) {
		this.base_id = base_id;
	}
	public int getGoods_resource_id() {
		return goods_resource_id;
	}
	public void setGoods_resource_id(int goods_resource_id) {
		this.goods_resource_id = goods_resource_id;
	}
	public String getGoods_create_time() {
		return goods_create_time;
	}
	public void setGoods_create_time(String goods_create_time) {
		this.goods_create_time = goods_create_time;
	}
	public String getGoods_quality_report_id() {
		return goods_quality_report_id;
	}
	public void setGoods_quality_report_id(String goods_quality_report_id) {
		this.goods_quality_report_id = goods_quality_report_id;
	}
	public Goods(String goods_name, double goods_low_price, double goods_high_price, int goods_type,
			String goods_detail, int goods_status, int goods_Recommended, String goods_create_time, String goods_img) {
		super();
		this.goods_name = goods_name;
		this.goods_low_price = goods_low_price;
		this.goods_high_price = goods_high_price;
		this.goods_type = goods_type;
		this.goods_detail = goods_detail;
		this.goods_status = goods_status;
		this.goods_Recommended = goods_Recommended;
		this.goods_create_time = goods_create_time;
		this.goods_img = goods_img;
	}
	public Goods(Integer id, String goods_name, double goods_low_price, double goods_high_price, int goods_type,
			String goods_detail, int goods_status, int goods_Recommended, String goods_create_time, String goods_img) {
		super();
		this.id = id;
		this.goods_name = goods_name;
		this.goods_low_price = goods_low_price;
		this.goods_high_price = goods_high_price;
		this.goods_type = goods_type;
		this.goods_detail = goods_detail;
		this.goods_status = goods_status;
		this.goods_Recommended = goods_Recommended;
		this.goods_create_time = goods_create_time;
		this.goods_img = goods_img;
	}
	public Goods(Integer id, String goods_name, double goods_low_price, double goods_high_price, int goods_type,
			String goods_detail, int goods_status, int goods_Recommended, String goods_introduce, String base_id,
			int goods_resource_id, String goods_create_time, String goods_quality_report_id, String goods_img) {
		super();
		this.id = id;
		this.goods_name = goods_name;
		this.goods_low_price = goods_low_price;
		this.goods_high_price = goods_high_price;
		this.goods_type = goods_type;
		this.goods_detail = goods_detail;
		this.goods_status = goods_status;
		this.goods_Recommended = goods_Recommended;
		this.goods_introduce = goods_introduce;
		this.base_id = base_id;
		this.goods_resource_id = goods_resource_id;
		this.goods_create_time = goods_create_time;
		this.goods_quality_report_id = goods_quality_report_id;
		this.goods_img = goods_img;
	}
	
	
}
