package com.htzhny.entity;

public class After_saleQuery {
	private String after_sale_id;
	private String user_name;
	private String delivery_time;
	private String order_id;
	private String after_sale_reason;//申请售后理由
	private int handle_status; //1：未受理 2：处理中 3：处理完成
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getDelivery_time() {
		return delivery_time;
	}
	public void setDelivery_time(String delivery_time) {
		this.delivery_time = delivery_time;
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
	public String getAfter_sale_id() {
		return after_sale_id;
	}
	public void setAfter_sale_id(String after_sale_id) {
		this.after_sale_id = after_sale_id;
	}
	
	public After_saleQuery() {
		super();
	}
	public After_saleQuery(String after_sale_id, String user_name, String delivery_time, String order_id,
			String after_sale_reason, int handle_status) {
		super();
		this.after_sale_id = after_sale_id;
		this.user_name = user_name;
		this.delivery_time = delivery_time;
		this.order_id = order_id;
		this.after_sale_reason = after_sale_reason;
		this.handle_status = handle_status;
	}
	@Override
	public String toString() {
		return "After_saleQuery [after_sale_id=" + after_sale_id + ", user_name=" + user_name + ", delivery_time="
				+ delivery_time + ", order_id=" + order_id + ", after_sale_reason=" + after_sale_reason
				+ ", handle_status=" + handle_status + "]";
	}
	

	
	
	
}