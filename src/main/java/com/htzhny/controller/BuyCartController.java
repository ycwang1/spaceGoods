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
import com.alibaba.fastjson.JSONObject;
import com.htzhny.entity.Address;
import com.htzhny.entity.BuyCart;
import com.htzhny.entity.BuyItem;
import com.htzhny.entity.Goods;
import com.htzhny.entity.Order;
import com.htzhny.entity.OrderLog;
import com.htzhny.entity.Order_item;
import com.htzhny.entity.Order_itemQuery;
import com.htzhny.entity.PageBean;
import com.htzhny.entity.User;
import com.htzhny.service.AddressService;
import com.htzhny.service.GoodsService;
import com.htzhny.service.OrderService;
import com.htzhny.service.Order_itemService;
import com.htzhny.service.Order_logService;
import com.htzhny.service.UserService;
import com.htzhny.util.CartItemUtil;
import com.htzhny.util.DateUtil;
import com.htzhny.util.OrderUtil;
import com.htzhny.util.SessionUtil;


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
	DateUtil dateUtil = DateUtil.getInstance();
	SessionUtil sessionUtil=SessionUtil.getInstance();
	//���빺�ﳵ
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
	//��չ��ﳵ
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
			
			jsonObject.put("result","��չ��ﳵ�ɹ���");
			return jsonObject;
		}
		
		return null;
	}
	//ɾ��һ��������
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
			
			jsonObject.put("result","ɾ���ɹ���");
			return jsonObject;
		}
		return null;
	}
	//��ѯ���ﳵ�е�������Ʒ
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
	//���㣨���ɶ�����
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
		//�ύ��������������������
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
			
			DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH��ʾ24Сʱ�ƣ�  
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
			jsonObject.put("result","�ύ�����ɹ�");
			return jsonObject;
		}
		/**
		 * ���빺�ﳵ  ��1�ж����û�����ڹ��ﳵ���� ���������򴴽�������addCurtOrder
		 * 				2 ���ڣ��������Ʒid�붩���ж϶������Ƿ���ڣ��������� ����
		 * 				3 ���ڣ������������
		 * 			������user_id,goodsId,goodsAmount,price
		 * @param params
		 * @param request
		 * @return
		 */
		@RequestMapping(value="addCurtGoods")
		public @ResponseBody JSONObject addCurtGoods(@RequestBody Map<String, Object> params,HttpServletRequest request){
			JSONObject jsonObject = new JSONObject();
			Integer userId = (Integer)params.get("userId");
			Integer goodsId = (Integer)params.get("goodsId");
			Integer goodsAmount = (Integer)params.get("goodsAmount");
			String  price = (String) params.get("price");
			Double goodsPrice = Double.parseDouble(price);
			
			//�����û����ﳵ������״̬Ϊ10
			Order order = orderService.selectUserCurtOrder(userId);
			String orderId = null;
			boolean exist = false;//���ﳵ
			if(order== null){//��������
				Order newOrder = new Order();
				newOrder.setId(orderUtil.getOrderId());
				newOrder.setUser_id(userId);
				newOrder.setCreate_time(dateUtil.getNow());
				newOrder.setOrder_real_price(goodsAmount*goodsPrice);
				newOrder.setPay_status(0);
				newOrder.setOrder_status(10);
				Integer result = orderService.addCurtOrder(newOrder);//�������
				if(result==1){
					orderId = newOrder.getId();
				}else{
					jsonObject.put("result", 0);
					return jsonObject;
				}
			}else{//���ݶ���id���ң��û�id����order_item ��
				orderId = order.getId();
				List<Order_item> list = order_itemService.selectAllItem(order.getId());//����id
				if(list.size()>0){
					for(Order_item item:list){
						if(item.getGoods_id()==goodsId){//���������Ѿ��ڣ���������
							order_itemService.updateAmountById(item.getGoods_amount()+goodsAmount, item.getId());
							exist = true;
						}
					}
				}			
				//���¶����۸�
				orderService.updateRealPrice(order.getOrder_real_price()+goodsAmount*goodsPrice, order.getId());
			}
			if(!exist){//����������
				Order_item item = new Order_item();
				item.setId(UUID.randomUUID().toString());//id
				item.setGoods_amount(goodsAmount);//����
				item.setGoods_id(goodsId);//��Ʒid
				item.setIs_after_sale(0);
				item.setGoods_real_price(goodsPrice);//�۸�
				item.setOrder_id(orderId);
				order_itemService.addCompleteOrderItem(item);
			}
			return jsonObject;
		}
		
		/**
		 * ���ҹ��ﳵ�е�����
		 * ͨ��userId ���� ����״̬Ϊ10�Ķ���
		 * ���������   ���ﳵΪ��
		 * ����  ����һ��������
		 * @param params   userId
		 * @param request
		 * @return   list<Order_Item>,orderId,result=0
		 */
		@RequestMapping(value="selectCurtGoods")
		public @ResponseBody JSONObject selectCurtGoods(@RequestBody Map<String, Object> params,HttpServletRequest request){
			JSONObject jsonObject = new JSONObject();
			Integer userId = (Integer)params.get("userId");
			Order order = orderService.selectUserCurtOrder(userId);
			if(order==null){
				jsonObject.put("result", '0');
			}else{
				String orderId  = order.getId();
				PageBean<Order_itemQuery> pageBean = order_itemService.selectAllByOrderId(1,orderId);
				List list = pageBean.getLists();
				jsonObject.put("list", list);
				jsonObject.put("result", '1');
			}
			return jsonObject;
		}
		
		/**
		 * ���¶����������������۸�
		 * @param params   list 
		 * @param request
		 * @return   list<Order_Item>,orderId,result=0
		 */
		@RequestMapping(value="updateOrderItem")
		public @ResponseBody JSONObject updateOrderItem(@RequestBody Map<String, Object> params,HttpServletRequest request){
			JSONObject jsonObject = new JSONObject();
			String orderId = (String)params.get("orderId");
			Integer user_id = (Integer)params.get("userId");
			List<CartItemUtil> list1=(List<CartItemUtil>)params.get("list");
			double orderPrice = 0.0;
			for(int i=0;i<list1.size();i++){
				CartItemUtil cartItem=JSON.parseObject(JSON.toJSONString(list1.get(i)),CartItemUtil.class);
				orderPrice+=cartItem.getAmount()*cartItem.getPrice();
				order_itemService.updateAmountById(cartItem.getAmount(), cartItem.getId());
			}
			Integer result = orderService.updateRealPrice(orderPrice, orderId);//���¶����۸�
			jsonObject.put("result", result);
			jsonObject.put("orderId", orderId);
			List<Address> list=addressService.findAddressByUserId(user_id);
			jsonObject.put("Address",list);
			jsonObject.put("orderPrice", orderPrice);
			return jsonObject;
		}
}
