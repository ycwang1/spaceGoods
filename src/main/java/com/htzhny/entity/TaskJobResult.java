package com.htzhny.entity;

/**
 * 批量查询拼团成功与否  结果
 * @author Administrator
 *
 */
public class TaskJobResult {
	private int account;//合计数量
	private int goodsId;//商品id
	private int initNumber;//初始团购数量
	private int totalNumber;//团购达标数量
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getInitNumber() {
		return initNumber;
	}
	public void setInitNumber(int initNumber) {
		this.initNumber = initNumber;
	}
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public TaskJobResult(int account, int goodsId, int initNumber, int totalNumber) {
		super();
		this.account = account;
		this.goodsId = goodsId;
		this.initNumber = initNumber;
		this.totalNumber = totalNumber;
	}
	@Override
	public String toString() {
		return "TaskJobResult [account=" + account + ", goodsId=" + goodsId + ", initNumber=" + initNumber
				+ ", totalNumber=" + totalNumber + "]";
	}
	public TaskJobResult() {
		super();
	}
}
