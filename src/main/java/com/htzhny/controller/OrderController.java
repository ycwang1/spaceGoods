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
	//��ѯĳ������״̬������
	public  @ResponseBody JSONObject selectCountByStatus(@RequestBody Map<String, Object> params){
		int status=1;
		Integer num=orderService.selectCountByStatus(status);
		System.out.println(num);
		return null;
	}
	@RequestMapping(value="selectUserOrderByStatus", method = RequestMethod.POST)
	//ͨ��״̬��ѯĳ���û������ж���
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
	//ͨ��״̬��ѯ�����û������ж���
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
	//ͨ���˵�״̬״̬��ѯĳ���û��������˵�
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
	//ͨ���˵�״̬״̬��ѯ�����û��������˵�
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
	//���ɶ���(���ﳵ����)
	public @ResponseBody JSONObject addOrder(@RequestBody Map<String, Object> params){
		
		
		
		JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("order");
   		Order order=JSON.parseObject(JSON.toJSONString(map),Order.class);
   		Integer result=orderService.addOrder(order);
   		jsonObject.put("result", result);
	return jsonObject;
	}
	@RequestMapping(value="updateStatus", method = RequestMethod.POST)
	//�޸Ķ���״̬
	public @ResponseBody JSONObject updateStatus(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();

		Integer status= (Integer)params.get("status");
		String id= (String)params.get("id");
		Integer result=orderService.updateStatus(status, id);
		if(status==13){//�����֧���ɹ����� �ж� ���ݿ���13���� �Ƿ�ﵽ�Ź��������
			List<TaskJobResult> list = orderDao.selectOrder();//���Ҵﵽ�����������Ʒ
			int account = 0;
			for(TaskJobResult item:list){
				account = item.getAccount()+item.getInitNumber();
				//�������� ��꣬��������goodsId �Ķ���״̬��Ϊ���ջ�
				if(account>=item.getTotalNumber()){
					orderDao.updateOrderStatusByGoodsId(item.getGoodsId());
//					orderDao.updateOrderStatusByGoodsId(43);  ����
				}
			}
		}
		jsonObject.put("result", result);
		return jsonObject;
	}

	@RequestMapping(value="updatePayStatus", method = RequestMethod.POST)
	//�޸Ķ���֧��״̬
	public @ResponseBody JSONObject updatePayStatus(@RequestBody Map<String, Object> params){
		
		JSONObject jsonObject = new JSONObject();
		String order_id= (String)params.get("order_id");
		Integer pay_status= (Integer)params.get("pay_status");
		Integer result=orderService.updatePayStatus(pay_status, order_id);
		jsonObject.put("result", result);
		return jsonObject;
	}
	@RequestMapping(value="updatePayStatusByUser", method = RequestMethod.POST)
	//�޸�ĳ���û���������ɶ���֧��״̬
	public @ResponseBody JSONObject updatePayStatusByUser(@RequestBody Map<String, Object> params){
		
		JSONObject jsonObject = new JSONObject();
		Integer user_id= (Integer)params.get("user_id");
		List<OrderQuery> list=orderService.selectUserOrder(user_id);
		Date dt =new Date(); 
		String formatDate = "";  
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH��ʾ24Сʱ�ƣ�  
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
	 * ���ݶ���״̬(�����Ƕ������״̬)���Ҷ���
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
	 * ͨ���˵�״̬״̬��ѯĳ���û���֧��������������״̬Ϊ�����
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
//	 * �����Ź�����
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
	 * ���ﳵ  ��⣬����session
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
	 * ���� С����֧�� ͳһ�µ��ӿ�
	 * @param request
	 * @param response
	 * @return
	 * */
	
	@ResponseBody
	@RequestMapping("returnparam")
	public void doOrder(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		List<InitParam> list = initParamDao.selectParam(1);//��ȡС�������
		Map<String,String> mapParam = new HashMap<String,String>();
		if(list.size()>0){
			for(InitParam param:list){
				mapParam.put(param.getName(), param.getValue());
			}
		}
		
		//�õ�openid��΢���û�Ψһ��openid��
		String openid = request.getParameter("openid");
		//�õ���Ǯ���Զ��壩
		int fee = 0;
		if (null != request.getParameter("price")) {
			fee = Integer.parseInt(request.getParameter("price").toString());
		}
		//�õ���Ʒ��ID���Զ��壩
		String goodsid=request.getParameter("goodsId");
		//�������⣨�Զ��壩
		String title = request.getParameter("title");
		//ʱ���
		String times = System.currentTimeMillis() + "";
		
		//������ţ��Զ��� ������ʱ���+�������
		Random random = new Random();
		String did = times+random.nextInt(1000);
 
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		packageParams.put("appid", mapParam.get("wxAppId"));//΢��С����ID
		packageParams.put("mch_id", mapParam.get("wxStoreID"));//�̻�ID
		packageParams.put("nonce_str", times);//����ַ�����32λ���ڣ� ����ʹ��ʱ���
		packageParams.put("body", title);//֧���������� �Զ���
		packageParams.put("out_trade_no", did+goodsid);//��� �Զ�����ʱ���+�����+��ƷID
		packageParams.put("total_fee", fee);//�۸� �Զ���
		packageParams.put("spbill_create_ip", mapParam.get("wxBillCreateIp"));
		//packageParams.put("spbill_create_ip", remoteAddr);
		packageParams.put("notify_url", mapParam.get("wxNotifyUrl"));//֧�����ص�ַҪ�������ʵĵ��� localhost���У���������buy�������������������ݿ⣩
//		packageParams.put("notify_url", "https://sx.htzhny.com/spaceGoods/order/buy.action");//֧�����ص�ַҪ�������ʵĵ��� localhost���У���������buy�������������������ݿ⣩
		packageParams.put("trade_type", mapParam.get("wxTradeType"));//���api�У��̶���
		packageParams.put("openid", openid);//�û���openid ����Ҫ ���Բ�Ҫ
		//��ȡsign
		String sign = PayCommonUtil.createSign("UTF-8", packageParams, mapParam.get("wxStoreSecretKey"));//���������Լ���΢���̻����õ�32λ��Կ
		packageParams.put("sign", sign);
		System.out.println(sign);
		//ת��XML
		String requestXML = PayCommonUtil.getRequestXml(packageParams);
		System.out.println(requestXML);
		//�õ�����prepay_id��XML
		String resXml = HttpUtil.postData(mapParam.get("wxGetRepayIdUrl"), requestXML);
		System.out.println(resXml);
		//����XML����Map
		Map map = XMLUtil.doXMLParse(resXml);
		System.out.println(map);
		// String return_code = (String) map.get("return_code");
		//�õ�prepay_id
		String prepay_id = (String) map.get("prepay_id");
		SortedMap<Object, Object> packageP = new TreeMap<Object, Object>();
		packageP.put("appId", mapParam.get("wxAppId"));//������ע�⣬������appId,������appid  map�е�appId
		packageP.put("nonceStr", times);//ʱ���
		packageP.put("package", "prepay_id=" + prepay_id);//�����packageд�� "prepay_id="+prepay_id������ʽ
		packageP.put("signType", "MD5");//paySign����
		packageP.put("timeStamp", (System.currentTimeMillis() / 1000) + "");
		//�õ�paySign
		String paySign = PayCommonUtil.createSign("UTF-8", packageP, mapParam.get("wxStoreSecretKey"));
		packageP.put("paySign", paySign);
		//��packageP���ݷ��ظ�С����
		Gson gson = new Gson();
		String json = gson.toJson(packageP);
		PrintWriter pw = response.getWriter();
		System.out.println(json);
		pw.write(json);
		pw.close();
	}
	//�����������ݿ�  ������� packageParams.put("notify_url", "http://���IP��ַ/order/buy.action");�ص��ľ����������
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
		//sbΪ΢�ŷ��ص�xml  
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
			 * �Լ�д�������ݿ���߼�
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
					+ "<return_msg><![CDATA[����Ϊ��]]></return_msg>" + "</xml> ";  
		}
		BufferedOutputStream out = new BufferedOutputStream(  
				response.getOutputStream());  
		out.write(resXml.getBytes());  
		out.flush();  
		out.close();  

	}

	/**
	 * ���ݶ���id���¶�����Ϣ
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
	 * ���ݶ���id���¶�����Ϣ
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
