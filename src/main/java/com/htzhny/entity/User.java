package com.htzhny.entity;

import java.util.Date;
/**
 * 
 * @author mEssA9e
 *
 *用户类
 */
public class User {
	private int id;
	private String user_name;//用户名
	private String password;//密码
	private int user_type; // 1:管理员 2：客户
	private String user_phone;//电话
	private int user_id_type; //1:身份证 2：其他
	private String user_id_card;//证件号码
	private String user_birthday;//生日
	private String user_company;//所属单位
	private String create_time;//创建时间
	private String user_last_login_time;//上次登录时间
	private int status; //1:正常 2：异常
	private String user_img;//用户头像
	private String openId;//微信openid

	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public int getUser_id_type() {
		return user_id_type;
	}
	public void setUser_id_type(int user_id_type) {
		this.user_id_type = user_id_type;
	}
	public String getUser_id_card() {
		return user_id_card;
	}
	public void setUser_id_card(String user_id_card) {
		this.user_id_card = user_id_card;
	}
	public String getUser_birthday() {
		return user_birthday;
	}
	public void setUser_birthday(String user_birthday) {
		this.user_birthday = user_birthday;
	}
	public String getUser_company() {
		return user_company;
	}
	public void setUser_company(String user_company) {
		this.user_company = user_company;
	}

	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUser_last_login_time() {
		return user_last_login_time;
	}
	public void setUser_last_login_time(String user_last_login_time) {
		this.user_last_login_time = user_last_login_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public User(String user_name, String password) {
		super();
		
		this.user_name = user_name;
		this.password = password;
	}
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	
	public User(int id, String user_name, String password, int user_type, String user_phone, int user_id_type,
			String user_id_card, String user_birthday, String user_company, String create_time,
			String user_last_login_time, int status, String user_img) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.password = password;
		this.user_type = user_type;
		this.user_phone = user_phone;
		this.user_id_type = user_id_type;
		this.user_id_card = user_id_card;
		this.user_birthday = user_birthday;
		this.user_company = user_company;
		this.create_time = create_time;
		this.user_last_login_time = user_last_login_time;
		this.status = status;
		this.user_img = user_img;
	}
	public User(String user_name, String user_phone, int user_id_type, String user_id_card, String user_birthday,
			String user_company, String user_img) {
		super();
		this.user_name = user_name;
		this.user_phone = user_phone;
		this.user_id_type = user_id_type;
		this.user_id_card = user_id_card;
		this.user_birthday = user_birthday;
		this.user_company = user_company;
		this.user_img = user_img;
	}
	public User(String user_name, String password, int user_type, String user_phone, int user_id_type,
			String user_id_card, int status) {
		super();
		this.user_name = user_name;
		this.password = password;
		this.user_type = user_type;
		this.user_phone = user_phone;
		this.user_id_type = user_id_type;
		this.user_id_card = user_id_card;
		this.status = status;
	}
	public User() {
		super();
	}
	public User(int id, String user_name, String user_company) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.user_company = user_company;
	}
	public User(int id, String user_name, String password, int user_type, String user_phone, int user_id_type,
			String user_id_card, String user_birthday, String user_company, String create_time,
			String user_last_login_time, int status, String user_img,String openId) {
		super();
		this.id = id;
		this.user_name = user_name;
		this.password = password;
		this.user_type = user_type;
		this.user_phone = user_phone;
		this.user_id_type = user_id_type;
		this.user_id_card = user_id_card;
		this.user_birthday = user_birthday;
		this.user_company = user_company;
		this.create_time = create_time;
		this.user_last_login_time = user_last_login_time;
		this.status = status;
		this.user_img = user_img;
		this.openId = openId;
	}
	
	public User(String user_name, int user_type, String user_phone, String create_time, int status, String openId) {
		super();
		this.user_name = user_name;
		this.user_type = user_type;
		this.user_phone = user_phone;
		this.create_time = create_time;
		this.status = status;
		this.openId = openId;
	}
	
	public User(int user_type, String create_time, int status, String openId) {
		super();
		this.user_type = user_type;
		this.create_time = create_time;
		this.status = status;
		this.openId = openId;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + user_name + ", password=" + password + ", user_type=" + user_type
				+ ", user_phone=" + user_phone + ", user_id_type=" + user_id_type + ", user_id_card=" + user_id_card
				+ ", user_birthday=" + user_birthday + ", user_company=" + user_company + ", create_time=" + create_time
				+ ", user_last_login_time=" + user_last_login_time + ", status=" + status + ", user_img=" + user_img
				+ ", openId=" + openId + "]";
	}
}
