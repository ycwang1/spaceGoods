package com.htzhny.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.htzhny.entity.Order;
import com.htzhny.entity.OrderLog;
import com.htzhny.entity.Order_item;
import com.htzhny.entity.Order_itemQuery;
import com.htzhny.entity.PageBean;
import com.htzhny.service.BillService;
import com.htzhny.service.OrderService;
import com.htzhny.service.Order_itemService;
import com.htzhny.service.Order_logService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="order_item")
public class Order_itemController {
	@Autowired
	private Order_itemService order_itemService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private Order_logService logService;
	
	@RequestMapping(value="addOrder_item")
	//生成订单项
	public @ResponseBody JSONObject addOrder_item(@RequestBody Map<String, Object> params){
		
		
		
		JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("order_item");
   		Order_item order_item=JSON.parseObject(JSON.toJSONString(map),Order_item.class);
   		Integer result=order_itemService.addOrder_item(order_item);
   		jsonObject.put("result", result);
	return jsonObject;
	}
	@RequestMapping(value="updateRealPrice")
	//报价
	public @ResponseBody JSONObject updateRealPrice(@RequestBody Map<String, Object> params){
		
		
		
		JSONObject jsonObject = new JSONObject();

 		ArrayList<Order_item> list= (ArrayList<Order_item>) params.get("itemList");
		String order_id1="";
 		double orderRealPrice=0.00;
		for(int i = 0 ; i < list.size() ; i++) {
			 	
				Order_item order_item=JSON.parseObject(JSON.toJSONString(list.get(i)),Order_item.class);
				String order_id=order_item.getOrder_id();
				order_id1=order_id;
				Integer result =order_itemService.updateRealPrice(order_item);
				
				double itemPrice=order_item.getGoods_real_price()*order_item.getGoods_amount();
				orderRealPrice=itemPrice+orderRealPrice;
				
				
				if(result!=0 && i==0){
				
//				orderService.updateStatus(3, order_id);}
				orderService.updateStatus(4, order_id);}
				
					
				
			 
			}
		Integer result=orderService.updateRealPrice(orderRealPrice, order_id1);
		
		
		jsonObject.put("result", result);
	

		
	
		return jsonObject;
	}
	@RequestMapping(value="selectCount")
	//查询某个订单的订单项总数
	public ModelAndView selectCount(){
		String id="045465";
		Integer num=order_itemService.selectCount(id);
		System.out.println(num);
		return null;
	}
	@RequestMapping(value="selectAllByOrderId")
	//查询某个订单的所有订单项
	public @ResponseBody JSONObject  selectAllByOrderId(@RequestBody Map<String, Object> params){
		
		String order_id= (String)params.get("id");
		Integer currentPage= (Integer)params.get("currentPage");
		JSONObject jsonObject = new JSONObject();
		PageBean<Order_itemQuery> pageBean =order_itemService.selectAllByOrderId(currentPage, order_id);
		List<Order_itemQuery> list=pageBean.getLists();
		String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
    	List<OrderLog> orderLogList=logService.findOneOrderLog(order_id);
    	if(orderLogList.size()>0){
    		jsonObject.put("orderLogList",orderLogList);
    	}
		return jsonObject;
	}
	
	@RequestMapping(value="deleteOrderItem")
	//删除指定订单项
	public @ResponseBody JSONObject  deleteOrderItem(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		String id= (String)params.get("id");
		String order_id=(String) params.get("order_id");
		Double orderRealPrice=Double.parseDouble((String)params.get("orderRealPrice"));
		Integer amount=(Integer) params.get("amount");
		Double goods_real_price=Double.parseDouble((String)params.get("goods_real_price"));
		Integer result=order_itemService.deleteOrderItem(id);
		 orderRealPrice= orderRealPrice-amount*goods_real_price;
		 
		 orderService.updateRealPrice(orderRealPrice, order_id);
		jsonObject.put("result",result);
		jsonObject.put("orderRealPrice",orderRealPrice);
		return jsonObject;
	}
	@RequestMapping(value="updateOrderItem")
	//给申请售后的商品的价格加负号
	public @ResponseBody JSONObject  updateOrderItem(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		ArrayList<Order_item> list= (ArrayList<Order_item>) params.get("itemList");
		Integer result=0;
		for(int i = 0 ; i < list.size() ; i++) {
		 	
			Order_item order_item=JSON.parseObject(JSON.toJSONString(list.get(i)),Order_item.class);
			order_itemService.updateAfterSale(1, order_item.getId());
			String id=UUID.randomUUID().toString();
			order_item.setId(id);
			order_item.setGoods_real_price(-order_item.getGoods_real_price());
			order_item.setIs_after_sale(2);
			order_itemService.updateAfterSale(1, id);
			result+=order_itemService.addCompleteOrderItem(order_item);
			
		}
		jsonObject.put("result",result);
		return jsonObject;
	}
	
	/**
	 * 查询当天已支付订单，指定商品id的数量
	 * 用于团购数量的配置
	 * @param params
	 * @return
	 */
	@RequestMapping(value="selectCountByGoodsId")
	public @ResponseBody JSONObject  selectCountByGoodsId(@RequestBody Map<String, Object> params){
		Integer order_id= (Integer)params.get("id");
		JSONObject jsonObject = new JSONObject();
		Integer account = 0;
		account = order_itemService.selectCountByGoodsId(order_id);//获取已参加团购数量
    	jsonObject.put("account",account);
		return jsonObject;
	}
}









