package com.htzhny.entity;
/**
 * 
 * @author mEssA9e
 *售后事件
 */
public class After_sale {
	private String after_sale_id;
	private int user_id;//所属用户ID
	private String a_order_id;//订单id
	private String after_sale_reason;//申请售后理由
	private int handle_status; //1：发起申请 2：同意申请 3：拒绝申请 
	private String handle_way; // 1:开红字发票 2：补发 
	private String handle_person;//售后处理人
	private String handle_time; //受理时间
	private String prove_img;//证明图片

	public String getAfter_sale_id() {
		return after_sale_id;
	}
	public void setAfter_sale_id(String after_sale_id) {
		this.after_sale_id = after_sale_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getA_order_id() {
		return a_order_id;
	}
	public void setA_order_id(String a_order_id) {
		this.a_order_id = a_order_id;
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
	
	
	
	@Override
	public String toString() {
		return "After_sale [after_sale_id=" + after_sale_id + ", user_id=" + user_id + ", a_order_id=" + a_order_id
				+ ", after_sale_reason=" + after_sale_reason + ", handle_status=" + handle_status + ", handle_way="
				+ handle_way + ", handle_person=" + handle_person + ", handle_time=" + handle_time + ", prove_img="
				+ prove_img + "]";
	}
	public After_sale(String after_sale_id, int user_id, String a_order_id, String after_sale_reason, int handle_status,
			String handle_way, String handle_person, String handle_time, String prove_img) {
		super();
		this.after_sale_id = after_sale_id;
		this.user_id = user_id;
		this.a_order_id = a_order_id;
		this.after_sale_reason = after_sale_reason;
		this.handle_status = handle_status;
		this.handle_way = handle_way;
		this.handle_person = handle_person;
		this.handle_time = handle_time;
		this.prove_img = prove_img;
	}
	public After_sale() {
		super();
	}
	


	
}