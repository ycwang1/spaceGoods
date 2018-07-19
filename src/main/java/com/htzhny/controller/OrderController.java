package com.htzhny.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.htzhny.dao.BillDao;
import com.htzhny.dao.InitParamDao;
import com.htzhny.dao.OrderDao;
import com.htzhny.entity.Bill;
import com.htzhny.entity.InitParam;
import com.htzhny.entity.Order;
import com.htzhny.entity.OrderLog;
import com.htzhny.entity.OrderQuery;
import com.htzhny.entity.PageBean;
import com.htzhny.entity.TaskJobResult;
import com.htzhny.entity.User;
import com.htzhny.service.BillService;
import com.htzhny.service.OrderService;
import com.htzhny.service.Order_itemService;
import com.htzhny.service.Order_logService;
import com.htzhny.service.UserService;
import com.htzhny.util.HttpUtil;
import com.htzhny.util.OrderUtil;
import com.htzhny.util.PayCommonUtil;
import com.htzhny.util.SessionUtil;
import com.htzhny.util.XMLUtil;

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
	private InitParamDao initParamDao;
	@Autowired
	private BillDao billDao;
	OrderUtil orderUtil = OrderUtil.getInstance();
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
		if(status==13){//如果是支付成功订单 判断 数据库中13订单 是否达到团购达标数量
			List<TaskJobResult> list = orderDao.selectOrder();//查找达到达标数量的商品
			int account = 0;
			for(TaskJobResult item:list){
				account = item.getAccount()+item.getInitNumber();
				//当参团数 达标，将包含改goodsId 的订单状态改为待收货
				if(account>=item.getTotalNumber()){
					orderDao.updateOrderStatusByGoodsId(item.getGoodsId());
//					orderDao.updateOrderStatusByGoodsId(43);  测试
				}
			}
		}
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
	
//	/**
//	 * 新增团购订单
//	 * @param params
//	 * @return
//	 */
//	@RequestMapping(value="addTeamBuyingOrder", method = RequestMethod.POST)
//	public @ResponseBody JSONObject addTeamBuyingOrder(@RequestBody Map<String, Object> params){
//		JSONObject jsonObject = new JSONObject();
//   		Map<String,Object> map=(Map<String, Object>)params.get("order");
//   		Order order=JSON.parseObject(JSON.toJSONString(map),Order.class);
//   		Integer result=orderService.addTeamBuyingOrder(order);
//   		jsonObject.put("result", result);
//   		return jsonObject;
//	}
	/**
	 * 购物车  入库，不走session
	 * @param params
	 * @return
	 */
	@RequestMapping(value="addCurtOrder", method = RequestMethod.POST)
	public @ResponseBody JSONObject addCurtOrder(@RequestBody Map<String, Object> params){
		
		JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("order");
   		Order order=JSON.parseObject(JSON.toJSONString(map),Order.class);
   		String orderId = orderUtil.getOrderId();
   		order.setId(orderId);
   		Integer result=orderService.addCurtOrder(order);
   		jsonObject.put("result", result);
   		return jsonObject;
	}
	/*
	 * 购买 小程序支付 统一下单接口
	 * @param request
	 * @param response
	 * @return
	 * */
	
	@ResponseBody
	@RequestMapping("returnparam")
	public void doOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		List<InitParam> list = initParamDao.selectParam(1);//获取小程序参数
		Map<String,String> mapParam = new HashMap<String,String>();
		if(list.size()>0){
			for(InitParam param:list){
				mapParam.put(param.getName(), param.getValue());
			}
		}
		
		//得到openid（微信用户唯一的openid）
		String openid = request.getParameter("openid");
		//得到价钱（自定义）
		int fee = 0;
		if (null != request.getParameter("price")) {
			fee = Integer.parseInt(request.getParameter("price").toString());
		}
		//得到商品的ID（自定义）
		String goodsid=request.getParameter("goodsId");
		//订单标题（自定义）
		String title = request.getParameter("title");
		//时间戳
		String times = System.currentTimeMillis() + "";
		
		//订单编号（自定义 这里以时间戳+随机数）
		Random random = new Random();
		String did = times+random.nextInt(1000);
 
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", mapParam.get("wxAppId"));//微信小程序ID
		packageParams.put("mch_id", mapParam.get("wxStoreID"));//商户ID
		packageParams.put("nonce_str", times);//随机字符串（32位以内） 这里使用时间戳
		packageParams.put("body", title);//支付主体名称 自定义
		packageParams.put("out_trade_no", did+goodsid);//编号 自定义以时间戳+随机数+商品ID
		packageParams.put("total_fee", fee);//价格 自定义
		packageParams.put("spbill_create_ip", mapParam.get("wxBillCreateIp"));
		//packageParams.put("spbill_create_ip", remoteAddr);
		packageParams.put("notify_url", mapParam.get("wxNotifyUrl"));//支付返回地址要外网访问的到， localhost不行，调用下面buy方法。（订单存入数据库）
//		packageParams.put("notify_url", "https://sx.htzhny.com/spaceGoods/order/buy.action");//支付返回地址要外网访问的到， localhost不行，调用下面buy方法。（订单存入数据库）
		packageParams.put("trade_type", mapParam.get("wxTradeType"));//这个api有，固定的
		packageParams.put("openid", openid);//用户的openid 可以要 可以不要
		//获取sign
		String sign = PayCommonUtil.createSign("UTF-8", packageParams, mapParam.get("wxStoreSecretKey"));//最后这个是自己在微信商户设置的32位密钥
		packageParams.put("sign", sign);
		System.out.println(sign);
		//转成XML
		String requestXML = PayCommonUtil.getRequestXml(packageParams);
		System.out.println(requestXML);
		//得到含有prepay_id的XML
		String resXml = HttpUtil.postData(mapParam.get("wxGetRepayIdUrl"), requestXML);
		System.out.println(resXml);
		//解析XML存入Map
		Map map = XMLUtil.doXMLParse(resXml);
		System.out.println(map);
		// String return_code = (String) map.get("return_code");
		//得到prepay_id
		String prepay_id = (String) map.get("prepay_id");
		SortedMap<Object, Object> packageP = new TreeMap<Object, Object>();
		packageP.put("appId", mapParam.get("wxAppId"));//！！！注意，这里是appId,上面是appid  map中的appId
		packageP.put("nonceStr", times);//时间戳
		packageP.put("package", "prepay_id=" + prepay_id);//必须把package写成 "prepay_id="+prepay_id这种形式
		packageP.put("signType", "MD5");//paySign加密
		packageP.put("timeStamp", (System.currentTimeMillis() / 1000) + "");
		//得到paySign
		String paySign = PayCommonUtil.createSign("UTF-8", packageP, mapParam.get("wxStoreSecretKey"));
		packageP.put("paySign", paySign);
		//将packageP数据返回给小程序
		Gson gson = new Gson();
		String json = gson.toJson(packageP);
		PrintWriter pw = response.getWriter();
		System.out.println(json);
		pw.write(json);
		pw.close();
	}
	//订单存入数据库  上面参数 packageParams.put("notify_url", "http://你的IP地址/order/buy.action");回调的就是这个方法
	@RequestMapping(value="buy")
	@ResponseBody
	public void Buy(HttpServletRequest request,HttpServletResponse response) throws Exception{
 
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));  
		String line = null;  
		StringBuilder sb = new StringBuilder();  
		while((line = br.readLine()) != null){  
			sb.append(line);  
		}  
		br.close();  
		//sb为微信返回的xml  
		String notityXml = sb.toString();  
		String resXml = "";  
		Map map = XMLUtil.doXMLParse(notityXml);
		String returnCode = (String) map.get("return_code");  
 
		if("SUCCESS".equals(returnCode)){  
			String out_trade_no=(String) map.get("out_trade_no");
			String timestamp=(String) map.get("nonce_str");
			String goodsid=out_trade_no.substring(out_trade_no.length()-3, out_trade_no.length());
			String openid=(String) map.get("openid");
			/*
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 自己写存入数据库的逻辑
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * */
			resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
					+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
		}else {
			resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
					+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
		}
		BufferedOutputStream out = new BufferedOutputStream(  
				response.getOutputStream());  
		out.write(resXml.getBytes());  
		out.flush();  
		out.close();  

	}

	/**
	 * 根据订单id更新订单信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="updateOrder", method = RequestMethod.POST)
	public  @ResponseBody JSONObject updateOrder(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		String delivery_time = (String) params.get("delivery_time");
		String address = (String) params.get("address");
		String orderId = (String) params.get("order_id");
		Integer result = orderDao.updateOrder(delivery_time, address, orderId);
		jsonObject.put("result", result);
		return jsonObject;
	}
	
	/**
	 * 根据订单id更新订单信息
	 * @param params
	 * @return
	 */
	@RequestMapping(value="selectOrder", method = RequestMethod.POST)
	public  @ResponseBody JSONObject selectOrder(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		List<TaskJobResult> list = orderDao.selectOrder();
		jsonObject.put("result", list);
		return jsonObject;
	}
	
	
}
