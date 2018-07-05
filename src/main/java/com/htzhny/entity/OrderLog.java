package com.htzhny.entity;

import java.sql.Date;
/**
 * 
 * @author mEssA9e
 *订单日志类
 */
public class OrderLog {
	private Long log_id;
	private String order_id;//订单id
	private String handle_time;//处理时间
	private Integer event;//处理事件 1：提交提单 2：报价 3：确认订单 4：发货  5:提交售后申请 6:售后处理完成  7： 确认收货 8：付款  9:取消售后申请

	public Long getLog_id() {
		return log_id;
	}
	public void setLog_id(Long log_id) {
		this.log_id = log_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getHandle_time() {
		return handle_time;
	}
	public void setHandle_time(String handle_time) {
		this.handle_time = handle_time;
	}
	public Integer getEvent() {
		return event;
	}
	public void setEvent(Integer event) {
		this.event = event;
	}
	public OrderLog(Long log_id, String order_id, String handle_time, Integer event) {
		super();
		this.log_id = log_id;
		this.order_id = order_id;
		this.handle_time = handle_time;
		this.event = event;
	}
	
	public OrderLog(String order_id, String handle_time, Integer event) {
		super();
		this.order_id = order_id;
		this.handle_time = handle_time;
		this.event = event;
	}
	public OrderLog() {
		super();
	}
	@Override
	public String toString() {
		return "OrderLog [log_id=" + log_id + ", order_id=" + order_id + ", handle_time=" + handle_time + ", event="
				+ event + "]";
	}
	
	
	
}
