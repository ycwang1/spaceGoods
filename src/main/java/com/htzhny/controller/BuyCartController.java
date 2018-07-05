package com.htzhny.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.htzhny.entity.Address;
import com.htzhny.entity.BuyCart;
import com.htzhny.entity.BuyItem;
import com.htzhny.entity.Goods;
import com.htzhny.entity.Order;
import com.htzhny.entity.OrderLog;
import com.htzhny.entity.Order_item;
import com.htzhny.entity.User;
import com.htzhny.service.AddressService;
import com.htzhny.service.GoodsService;
import com.htzhny.service.OrderService;
import com.htzhny.service.Order_itemService;
import com.htzhny.service.Order_logService;
import com.htzhny.service.UserService;
import com.htzhny.util.CartItemUtil;
import com.htzhny.util.OrderUtil;
import com.htzhny.util.SessionUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="buyCart")
public class BuyCartController {
	
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private Order_itemService order_itemService;
	@Autowired
	private Order_logService logService;
	OrderUtil orderUtil = OrderUtil.getInstance();
	SessionUtil sessionUtil=SessionUtil.getInstance();
	//加入购物车
	@RequestMapping(value="addGoods")
	public @ResponseBody JSONObject addGoods(@RequestBody Map<String, Object> params,HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		HttpSession session = sessionUtil.getSession(request);
		if(session==null){
			session = request.getSession();
		}
		String str= (String)params.get("goodsId");
		Integer goods_id=Integer.parseInt(str);
		Integer amount= (Integer)params.get("amount");
		
		BuyCart buyCart=null;
		
		if(null!=session.getAttribute("buyCart")){
			buyCart=(BuyCart) session.getAttribute("buyCart");
		}
		if(null==buyCart){
			buyCart=new BuyCart();
		}
		BuyItem buyItem=new BuyItem();
		buyItem.setAmount(amount);
		Goods goods=goodsService.selectGoodsById(goods_id);
		buyItem.setGoods(goods);
		buyCart.addItem(buyItem);
		
		
		session.setAttribute("buyCart", buyCart);
		jsonObject.put("buyCart",buyCart);
		return jsonObject;
	}
	//清空购物车
	@RequestMapping(value="clearBuyCart")
	public  @ResponseBody JSONObject clearBuyCart(HttpServletRequest request){
//		SessionUtil sessionUtil=SessionUtil.getInstance();
		HttpSession session = sessionUtil.getSession(request);
		if(session==null){
			session = request.getSession();
		}
		JSONObject jsonObject = new JSONObject();
		BuyCart buyCart=(BuyCart) session.getAttribute("buyCart");
		
		if(null!=buyCart){
			buyCart.getItems().clear();
			
			jsonObject.put("result","清空购物车成功！");
			return jsonObject;
		}
		
		return null;
	}
	//删除一个购物项
	@RequestMapping(value="deleteItem")
	public @ResponseBody JSONObject deleteItem(@RequestBody Map<String, Object> params,HttpServletRequest request ){
//		SessionUtil sessionUtil=SessionUtil.getInstance();
		HttpSession session = sessionUtil.getSession(request);
		if(session==null){
			session = request.getSession();
		}
		JSONObject jsonObject = new JSONObject();
		Integer goods_id= (Integer)params.get("goods_id");
		BuyCart buyCart=(BuyCart) session.getAttribute("buyCart");
		if(null!=buyCart){
			Goods goods=new Goods();
			goods.setId(goods_id);
			BuyItem buyItem=new BuyItem();
			buyItem.setGoods(goods);
			buyCart.deleteItem(buyItem);
			
			jsonObject.put("result","删除成功！");
			return jsonObject;
		}
		return null;
	}
	//查询购物车中的所有商品
	@RequestMapping(value="selectBuyCart")
	public @ResponseBody JSONObject selectBuyCart(HttpServletRequest request ){
		JSONObject jsonObject = new JSONObject();
//		SessionUtil sessionUtil=SessionUtil.getInstance();
		HttpSession session = sessionUtil.getSession(request);
		if(session==null){
			session = request.getSession();
		}
		BuyCart buyCart=(BuyCart) session.getAttribute("buyCart");
		if(null!=buyCart){
			jsonObject.put("buyCart",buyCart);
			jsonObject.put("result","1");
			return jsonObject;
		}else{
			jsonObject.put("result","0");
			return jsonObject;
		}
	}
	//结算（生成订单）
		@RequestMapping(value="createOrder")
		public @ResponseBody JSONObject createOrder(@RequestBody Map<String, Object> params,HttpServletRequest request ){
//			SessionUtil sessionUtil=SessionUtil.getInstance();
			HttpSession session = sessionUtil.getSession(request);
			if(session==null){
				session = request.getSession();
			}
			JSONObject jsonObject = new JSONObject();
			String orderId = orderUtil.getOrderId();
			User user=(User) session.getAttribute("user");
			Integer user_id=user.getId();
			jsonObject.put("order_id",orderId);
			List<Address> list=addressService.findAddressByUserId(user_id);
			jsonObject.put("Address",list);
			List<CartItemUtil> list1=(List<CartItemUtil>)params.get("list");
			BuyCart buyCart=(BuyCart) session.getAttribute("buyCart");
			List<BuyItem> items=buyCart.getItems();
			for(int i=0;i<list1.size();i++){
				CartItemUtil cartItem=JSON.parseObject(JSON.toJSONString(list1.get(i)),CartItemUtil.class);
				for(BuyItem item:items){
					if(item.getGoods().getId()==cartItem.getGoods_id()){
						item.setAmount(cartItem.getAmount());
					}
				}
			}
			session.setAttribute("buyCart", buyCart);
			return jsonObject;
		}
		//提交订单、、、、、、、、
		@RequestMapping(value="submitOrder")
		public @ResponseBody JSONObject submitOrder(@RequestBody Map<String, Object> params,HttpServletRequest request ){
//			SessionUtil sessionUtil=SessionUtil.getInstance();
			HttpSession session = sessionUtil.getSession(request);
			if(session==null){
				session = request.getSession();
			}
			JSONObject jsonObject = new JSONObject();
			BuyCart buyCart=(BuyCart) session.getAttribute("buyCart");
			if(null==buyCart){
					return null;
							}
			Date dt =new Date(); 
			
			DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；  
		    String create_time = dFormat.format(dt); 
		    
			String order_id= (String) params.get("order_id");
			String delivery_time=(String) params.get("delivery_time");
			String address=(String) params.get("address");
			User user=(User) session.getAttribute("user");
			Integer user_id=user.getId();
			Double order_low_price=buyCart.getGoodsTotalLowPrice();
			Double order_High_price=buyCart.getGoodsTotalHighPrice();
			Integer order_status=1;
			Integer pay_status=0;
			Order order=new Order(order_id,address,delivery_time,order_low_price,order_High_price,0.00,order_status,create_time,user_id,pay_status,1);
//			Order order=new Order(order_id,address,delivery_time,order_low_price,order_High_price,order_low_price,order_status,create_time,user_id,pay_status,3);

			orderService.addOrder(order);
			
			List<BuyItem>list=buyCart.getItems();
			for(BuyItem item:list){
			String id=UUID.randomUUID().toString();
			Goods goods=item.getGoods();
			Integer goods_id=goods.getId();
			Integer goods_amount=item.getAmount();
			
			Order_item order_item=new Order_item(id,goods_id,order_id,goods_amount,0.00);
			order_itemService.addOrder_item(order_item);

			}
			OrderLog orderlog=new OrderLog(order_id,create_time,1);
   	   		logService.addLog(orderlog);
			buyCart.clearCart();
			jsonObject.put("result","提交订单成功");
			return jsonObject;
				}
}
