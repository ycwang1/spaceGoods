package com.htzhny.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.fastjson.JSON;
import com.htzhny.dao.BillDao;
import com.htzhny.dao.OrderDao;
import com.htzhny.entity.Bill;
import com.htzhny.entity.Goods;
import com.htzhny.entity.Order;
import com.htzhny.entity.OrderLog;
import com.htzhny.entity.OrderQuery;
import com.htzhny.entity.Order_item;
import com.htzhny.entity.PageBean;
import com.htzhny.entity.User;
import com.htzhny.service.BillService;
import com.htzhny.service.OrderService;
import com.htzhny.service.Order_itemService;
import com.htzhny.service.Order_logService;
import com.htzhny.service.UserService;
import com.htzhny.util.SessionUtil;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value="order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private Order_itemService order_itemService;
	@Autowired
	private BillService billService;
	@Autowired
	private UserService userService;
	@Autowired
	private Order_logService logService;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private BillDao billDao;
	@RequestMapping(value="selectCountByStatus", method = RequestMethod.POST)
	//查询某个订单状态的总数
	public  @ResponseBody JSONObject selectCountByStatus(@RequestBody Map<String, Object> params){
		int status=1;
		Integer num=orderService.selectCountByStatus(status);
		System.out.println(num);
		return null;
	}
	@RequestMapping(value="selectUserOrderByStatus", method = RequestMethod.POST)
	//通过状态查询某个用户的所有订单
	public @ResponseBody JSONObject selectUserOrderByStatus(@RequestBody Map<String, Object> params,HttpServletRequest request){
		SessionUtil sessionUtil=SessionUtil.getInstance();
		HttpSession session = sessionUtil.getSession(request);
		if(session==null){
			session = request.getSession();
		}
		JSONObject jsonObject = new JSONObject();
		Integer currentPage= (Integer)params.get("currentPage");
    	Integer user_id= (Integer)params.get("user_id");
    	if(user_id==0){
    		User user=(User) session.getAttribute("user");
			user_id=user.getId();
    	}
    	String str= (String)params.get("status");
    	Integer status=Integer.parseInt(str);
    	
		PageBean<OrderQuery> pageBean =orderService.selectUserOrderByStatus(currentPage, status,user_id);
		List<OrderQuery> list=pageBean.getLists();
		String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
	}
	
	@RequestMapping(value="selectAllOrderByStatus", method = RequestMethod.POST)
	//通过状态查询所有用户的所有订单
	public @ResponseBody JSONObject selectAllOrderByStatus(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		
		Integer currentPage= (Integer)params.get("currentPage");
		List<User> userList =userService.selectAllUser();
		jsonObject.put("userList",userList);
		String str= (String)params.get("status");
    	Integer status=Integer.parseInt(str);
		PageBean<OrderQuery> pageBean =orderService.selectAllOrderByStatus(currentPage, status);
		List<OrderQuery> list=pageBean.getLists();
		String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
	}
	@RequestMapping(value="selectUserOrderByBillStatus", method = RequestMethod.POST)
	//通过账单状态状态查询某个用户的所有账单
	public @ResponseBody JSONObject selectUserOrderByBillStatus(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		
		String str1= (String)params.get("currentPage");
		Integer currentPage=Integer.parseInt(str1);
		String str=(String) params.get("user_id");
		Integer user_id=Integer.parseInt(str);
		String str2= (String)params.get("bill_status");
		Integer bill_status=Integer.parseInt(str2);
    	
		PageBean<OrderQuery> pageBean =orderService.selectUserOrderByBillStatus(currentPage, bill_status, user_id);
		List<OrderQuery> list=pageBean.getLists();
		String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
    	Bill bill=billService.selectBillByUserId(user_id,bill_status);
    	if(bill!=null){
    		double month_pay_money=bill.getMonth_pay_money();
    		jsonObject.put("month_pay_money",month_pay_money);
    	}
    	
    	
		return jsonObject;
	}
	@RequestMapping(value="selectAllOrderByPayStatus", method = RequestMethod.POST)
	//通过账单状态状态查询所有用户的所有账单
		public @ResponseBody JSONObject selectAllOrderByBillStatus(@RequestBody Map<String, Object> params){
			JSONObject jsonObject = new JSONObject();
			String str1= (String)params.get("currentPage");
			Integer currentPage=Integer.parseInt(str1);
			String str= (String)params.get("bill_status");
			Integer bill_status=Integer.parseInt(str);
			
			List<User> userList =userService.selectAllUser();
			jsonObject.put("userList",userList);
			PageBean<OrderQuery> pageBean =orderService.selectAllOrderByBillStatus(currentPage, bill_status);
			List<OrderQuery> list=pageBean.getLists();
			String list1=JSON.toJSONString(list);
	    	jsonObject.put("list1",list1);
	    	List<Bill> billList=billService.selectAllBill(bill_status);
	    	double seller_month_total_money=0.00;
	    	for(Bill bill:billList){
	    		seller_month_total_money = seller_month_total_money+bill.getMonth_pay_money();
	    		
	    		
	    	}
	    	jsonObject.put("seller_month_total_money",seller_month_total_money);
	    	return jsonObject;
		}
	@RequestMapping(value="addOrder", method = RequestMethod.POST)
	//生成订单(购物车结算)
	public @ResponseBody JSONObject addOrder(@RequestBody Map<String, Object> params){
		
		
		
		JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("order");
   		Order order=JSON.parseObject(JSON.toJSONString(map),Order.class);
   		Integer result=orderService.addOrder(order);
   		jsonObject.put("result", result);
	return jsonObject;
	}
	@RequestMapping(value="updateStatus", method = RequestMethod.POST)
	//修改订单状态
	public @ResponseBody JSONObject updateStatus(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();

		Integer status= (Integer)params.get("status");
		String id= (String)params.get("id");
		Integer result=orderService.updateStatus(status, id);
		jsonObject.put("result", result);
		return jsonObject;
	}

	@RequestMapping(value="updatePayStatus", method = RequestMethod.POST)
	//修改订单支付状态
	public @ResponseBody JSONObject updatePayStatus(@RequestBody Map<String, Object> params){
		
		JSONObject jsonObject = new JSONObject();
		String order_id= (String)params.get("order_id");
		Integer pay_status= (Integer)params.get("pay_status");
		Integer result=orderService.updatePayStatus(pay_status, order_id);
		jsonObject.put("result", result);
		return jsonObject;
	}
	@RequestMapping(value="updatePayStatusByUser", method = RequestMethod.POST)
	//修改某个用户所有已完成订单支付状态
	public @ResponseBody JSONObject updatePayStatusByUser(@RequestBody Map<String, Object> params){
		
		JSONObject jsonObject = new JSONObject();
		Integer user_id= (Integer)params.get("user_id");
		List<OrderQuery> list=orderService.selectUserOrder(user_id);
		Date dt =new Date(); 
		String formatDate = "";  
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；  
	    formatDate = dFormat.format(dt);
		for(OrderQuery order:list){
			String id=order.getId();
			orderDao.updateBillStatus(3);
			OrderLog orderlog=new OrderLog(id,formatDate,3);
   			logService.addLog(orderlog);
		}
		
		Integer result=orderService.updatePayStatusByUser(user_id);
		Bill bill=billService.selectBillByUserId(user_id,2);
		if(result!=0 && bill!=null){
			bill.setFlag(3);
			billDao.updateFlag(bill);
			
		}
		jsonObject.put("result", result);
			
		
		
		
		return jsonObject;
	}
	/**
	 * 根据订单状态(可以是多个订单状态)查找订单
	 * @param params  
	 * @param request
	 * @return
	 */
	@RequestMapping(value="selectUserOrderByOrderStatus", method = RequestMethod.POST)
	public @ResponseBody JSONObject selectUserOrderByOrderStatus(@RequestBody Map<String, Object> params,HttpServletRequest request){
		SessionUtil sessionUtil=SessionUtil.getInstance();
		HttpSession session = sessionUtil.getSession(request);
		if(session==null){
			session = request.getSession();
		}
		JSONObject jsonObject = new JSONObject();
		Integer currentPage= (Integer)params.get("currentPage");
    	Integer user_id= (Integer)params.get("user_id");
    	if(user_id==0){
    		User user=(User) session.getAttribute("user");
			user_id=user.getId();
    	}
    	List str= (List)params.get("status");
    	//Integer status=Integer.parseInt(str);
    	
		PageBean<OrderQuery> pageBean =orderService.selectUserOrderByOrderStatus(currentPage, str,user_id);
		List<OrderQuery> list=pageBean.getLists();
		String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
	}
	/**
	 * 通过账单状态状态查询某个用户待支付订单，，订单状态为已完成
	 * @param params
	 * @return
	 */
	@RequestMapping(value="selectUserUnPayOrder", method = RequestMethod.POST)
	public @ResponseBody JSONObject selectUserUnPayOrder(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		
		Integer currentPage= (Integer)params.get("currentPage");
		Integer user_id= (Integer)params.get("user_id");
 	
		PageBean<OrderQuery> pageBean =orderService.selectUserUnPayOrder(currentPage, user_id);
		List<OrderQuery> list=pageBean.getLists();
		String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
    		
		return jsonObject;
	}
}
