package com.htzhny.entity;

import java.util.Date;
/**
 * 
 * @author mEssA9e
 *�ջ���ַ��
 */
public class InitParam {
	private int id;
	private String name; //��������
	private String value;//����ֵ
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public InitParam(int id, String name, String value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}
	
	public InitParam(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}
	@Override
	public String toString() {
		return "InitParam [id=" + id + ", name=" + name + ", value=" + value + "]";
	}
}
