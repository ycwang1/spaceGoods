package com.htzhny.entity;

import java.util.Date;
/**
 * 
 * @author mEssA9e
 *商品类型
 */
public class Goods_type {
	private Integer id;
	private String goods_type_name; //商品类型名
	private String create_time;//创建时间
	private Integer status;//1:使用中 2：已失效
	private String icon;//种类图标
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGoods_type_name() {
		return goods_type_name;
	}
	public void setGoods_type_name(String goods_type_name) {
		this.goods_type_name = goods_type_name;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Goods_type(Integer id, String goods_type_name, String create_time, Integer status) {
		super();
		this.id = id;
		this.goods_type_name = goods_type_name;
		this.create_time = create_time;
		this.status = status;
	}
	
	public Goods_type(Integer id, String goods_type_name, String create_time, Integer status, String icon) {
		super();
		this.id = id;
		this.goods_type_name = goods_type_name;
		this.create_time = create_time;
		this.status = status;
		this.icon = icon;
	}
	public Goods_type() {
		super();
	}
	public Goods_type(Integer id, String goods_type_name) {
		super();
		this.id = id;
		this.goods_type_name = goods_type_name;
	}
	public Goods_type(Integer id, Integer status) {
		super();
		this.id = id;
		this.status = status;
	}

	
}
