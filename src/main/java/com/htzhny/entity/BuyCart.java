package com.htzhny.entity;

import java.util.ArrayList;
import java.util.List;



public class BuyCart {
	List<BuyItem> items = new ArrayList<BuyItem>();
	//添加购物项
	public void addItem(BuyItem item){
		if(items.contains(item)){
			for(BuyItem it:items){
				if(it.equals(item)){
					Integer result=it.getAmount()+item.getAmount();
					it.setAmount(result);
				}
			}
		}else{
			items.add(item);
		}
		
	}
	//删除一个
	public void deleteItem(BuyItem item){
		items.remove(item);
	}
	//商品总数
	public Double getGoodsAmount(){
		double result=0;
		for(BuyItem item:items){
			result+=item.getAmount();
		}
		return result;
	}
	//得到总共商品的最低价
	public Double getGoodsTotalLowPrice(){
		double totalLowPrice=0.00;
		for(BuyItem item:items){
			totalLowPrice+=item.getGoods().getGoods_low_price()*item.getAmount();
		}
		return totalLowPrice;
	}
	//得到总共商品的最高价
	public Double getGoodsTotalHighPrice(){
		double totalHighPrice=0.00;
		for(BuyItem item:items){
			totalHighPrice+=item.getGoods().getGoods_high_price()*item.getAmount();
		}
		return totalHighPrice;
	}
	//清空购物车
	public void clearCart(){
		items.clear();
	}
	public List<BuyItem> getItems() {
		return items;
	}
	public void setItems(List<BuyItem> items) {
		this.items = items;
	}
}
