package com.htzhny.controller;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.htzhny.dao.BillDao;
import com.htzhny.dao.OrderDao;
import com.htzhny.dao.UserDao;
import com.htzhny.entity.Bill;
import com.htzhny.entity.Order;
import com.htzhny.entity.User;
import com.htzhny.service.BillService;
import com.htzhny.service.OrderService;
import com.htzhny.service.UserService;
import com.htzhny.util.DateUtil;
import com.htzhny.util.SessionUtil;

import net.sf.json.JSONObject;

/**
 * 用户控制器
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
	Logger log = Logger.getLogger(UserController.class);
	SessionUtil sessionUtil=SessionUtil.getInstance();
	DateUtil dateUtil = DateUtil.getInstance();
	@Autowired(required=true)
    private UserService userService;
	
	@Autowired(required=true)
    private BillService billService;
	@Autowired(required=true)
	private OrderDao orderDao;
	@Autowired(required=true)
	private BillDao billDao;
    @RequestMapping("/view")
    public String view() {
        return "main/login";
    }

    @RequestMapping("/indexview")
    public String index() {
        return "main/index";
    }
    //登录
    @RequestMapping(value="login", method = RequestMethod.POST)
    public @ResponseBody JSONObject login(@RequestBody Map<String,Object> params,HttpServletRequest request) {
    	JSONObject jsonObject = new JSONObject();
    	String user_name=(String) params.get("user_name");
    	String password=(String) params.get("password");
    	User user = userService.findUserByUserName(user_name); 
    	log.info("User = "+user);
    	if(null!=user){
    		if(password.equals(user.getPassword())){
    			HttpSession session = sessionUtil.getSession(request);
    			if(session==null){
    				session = request.getSession();
    			}
    			session.setAttribute("user", user);
    			String result="1";
    			jsonObject.put("result",result);
    			User user2=(User) session.getAttribute("user");
    			String user1=JSON.toJSONString(user);
    			jsonObject.put("user",user1);
    			if(user.getUser_type()==1){
    			Integer user_id=user.getId();
    			Bill bill=billService.selectBillByUserId(user_id,1);
    			Calendar cal=Calendar.getInstance();
    			Date dt =new Date(); 
    			cal.setTime(dt);
    			int month=cal.get(Calendar.MONTH)+1;
    			int year=cal.get(Calendar.YEAR);
    			if(bill!=null){
    				String a=bill.getOnemonth(); //得到账单的年月
    				Integer oldYear=Integer.parseInt(a.substring(0, 4));
    				Integer oldMonth=Integer.parseInt(a.substring(5));
    				if(oldYear==year){//年份是否相等
    					if(oldMonth<month){ //新的月份如果大于数据库中存储的月份，为改用户新增一个月份的账单。
    						String id =UUID.randomUUID().toString();
    						String year_month=year+"-"+month;
    						Bill newBill=new Bill(id,user_id,year_month,0.00,1,"");
    						billService.addBill(newBill);
    						bill.setFlag(2);
    						billDao.updateFlag(bill);
    						orderDao.updateBillStatus(2);
    					}
    				}else if(oldYear<year){
    					String id =UUID.randomUUID().toString();
    					String year_month=year+"-"+month;
    					Bill newBill=new Bill(id,user_id,year_month,0.00,1,"");
    					billService.addBill(newBill);
    					bill.setFlag(2);
    					billDao.updateFlag(bill);
    					orderDao.updateBillStatus(2);
    			}
    			}else {
    				String id =UUID.randomUUID().toString();
					String year_month=year+"-"+month;
					Bill newBill=new Bill(id,user_id,year_month,0.00,1,"");
					billService.addBill(newBill);
    			}
    			}

    	    	String sessionId = session.getId();
    	    	jsonObject.put("sessionId",sessionId);
    			return jsonObject;
    
    			
    			
    		}
    		String result="0";
    		jsonObject.put("result", result);
    		return jsonObject;
    	}
    	String result="0";
		jsonObject.put("result", result);
		return jsonObject;
    }
    //注册
    @RequestMapping(value="register", method = RequestMethod.POST)
    public @ResponseBody JSONObject register(@RequestBody Map<String,Object> params,HttpServletRequest request){
    	JSONObject jsonObject = new JSONObject();
    	Map<String,Object> map=(Map<String, Object>) params.get("user");
    	User user=JSON.parseObject(JSON.toJSONString(map),User.class);
    		jsonObject.put("result", "user has exist!");
    		if(null!=userService.findUserByUserName(user.getUser_name())){
    		return jsonObject;
    	}
    	Date dt =new Date(); 
		String formatDate = "";  
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；  
	    formatDate = dFormat.format(dt); 
	    String create_time = DateFormat.getDateInstance().format(dt);  
	    user.setCreate_time(formatDate);
	    user.setUser_last_login_time(formatDate);
    	Integer result=userService.addUser(user);
		SessionUtil sessionUtil=SessionUtil.getInstance();
		HttpSession session = sessionUtil.getSession(request);
		if(session==null){
			session = request.getSession();
		}
    	if(1==result){
    		user = userService.findUserByUserName(user.getUser_name());
    		session.setAttribute("user", user);
    		String user1=JSON.toJSONString(user);
    		String sessionId = session.getId();
	    	jsonObject.put("sessionId",sessionId);
    		jsonObject.put("user", user1);
    		return jsonObject;
    	}
    	jsonObject.put("result", result);
		return jsonObject;
    }
    //修改密码
    @RequestMapping(value="updatePassword", method = RequestMethod.POST)
    public @ResponseBody JSONObject updatePassword(@RequestBody Map<String,Object> params){
    	JSONObject jsonObject = new JSONObject();
    	String user_name=(String) params.get("user_name");
    	String password=(String) params.get("password");
    	Integer result=userService.updatePassword(user_name, password);
    	jsonObject.put("result", result);
		return jsonObject;
    	
    }
   @RequestMapping(value="updateMessage", method = RequestMethod.POST)
   //修改个人信息
   public @ResponseBody JSONObject updateMessage(@RequestBody Map<String,Object> params){
    	
	   	JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>) params.get("user");
   		User user1=JSON.parseObject(JSON.toJSONString(map),User.class);
   		Integer result=userService.updateMessage(user1);
   		if(1==result){
   			User user=userService.findUserByUserName(user1.getUser_name());
    		String user2=JSON.toJSONString(user);
    		jsonObject.put("user", user2);
    		return jsonObject;
    	}
   		jsonObject.put("result", result);
	return jsonObject;
    	
   }
   /**
    * 微信登录验证，
	* 根据用户openid 判断用户是否存在
    * 存在  获取userId，否则新增用户	存入openid
    * add by wyc 2018/07/18 
    * @param params  openid 
    * @param request
    * @return
    */
   @RequestMapping(value="wxLogin/userId", method = RequestMethod.POST)
   public @ResponseBody JSONObject wxLogingGetUserByUserid(@RequestBody Map<String,Object> params,HttpServletRequest request) {
   	JSONObject jsonObject = new JSONObject();
   	String user_id=(String) params.get("openid");
   	//String phone=(String) params.get("phone");
   	User user = userService.selectUserByUserId(user_id); 
	String result="1";
   	if(user==null){//用户不存在  创建用户  返回userid
   	//用户不存在，新增用户并将openid存到数据库
   		String phone ="";//默认手机号为空
   		User newUser = new User(phone,1,phone,dateUtil.getNow(),1,user_id);
   		
   		int addResult = userService.addOneUser(newUser);
   		if(addResult!=1){
   			result="0";
   		}else{
   			user = userService.selectUserByUserId(user_id);
   		}
   	}
	HttpSession session = sessionUtil.getSession(request);
	if(session==null){
		session = request.getSession();
	}
	session.setAttribute("user", user);
	String sessionId = session.getId();
	jsonObject.put("sessionId",sessionId);
	jsonObject.put("user", user);
	jsonObject.put("result", result);
	return jsonObject;
   }
   /**
    * 微信登录验证，通过手机号查找用户是否存在 
    * add by wyc 2018/06/25 
    * @param params  openid 。phone
    * @param request
    * @return
    */
   @RequestMapping(value="wxLogin/phone", method = RequestMethod.POST)
   public @ResponseBody JSONObject wxLogingGetUserByPhone(@RequestBody Map<String,Object> params,HttpServletRequest request) {
   	JSONObject jsonObject = new JSONObject();
   	String openId=(String) params.get("openid");
   	String phone=(String) params.get("phone");
   	User user = userService.findUserByUserName(phone); 
	String result="1";
   	if(null==user){//用户不存在
   		result = "0";
   	}else {
   		//用户存在，将openid更新到数据库
   		jsonObject.put("user", user);
   		HttpSession session = sessionUtil.getSession(request);
		if(session==null){
			session = request.getSession();
		}
		session.setAttribute("user", user);
		String sessionId = session.getId();
    	jsonObject.put("sessionId",sessionId);
   		int updateResult = userService.updateUserId(phone, openId);
   		if(updateResult!=1){
   			result="0";
   		}
   	}
	jsonObject.put("result", result);
	return jsonObject;
   }
   /**
    * 航天优选商城 用户登录接口：
    * 	
    * 	1、如果不存在，判断手机号是否存在，存在表示该手机号已注册
    * 	2、手机号也不存在，新增用户，存入手机号与openid
    * add by wyc 2018/06/25 
    * @param params  openid 。phone
    * @param request
    * @return
    */
   @RequestMapping(value="wxLogin/login", method = RequestMethod.POST)
   public @ResponseBody JSONObject wxLogingByPhone(@RequestBody Map<String,Object> params,HttpServletRequest request) {
   	JSONObject jsonObject = new JSONObject();
   	String openId=(String) params.get("openid");
   	String phone=(String) params.get("phone");
   	User user = userService.findUserByUserName(phone); 
	String result="1";
   	if(null!=user){//用户存在
   		result = "0";
   	}else {
   		//用户不存在，新增用户并将openid存到数据库
   		user = new User(phone,1,phone,dateUtil.getNow(),1,openId);
   		
   		HttpSession session = sessionUtil.getSession(request);
		if(session==null){
			session = request.getSession();
		}
		session.setAttribute("user", user);
		String sessionId = session.getId();
    	jsonObject.put("sessionId",sessionId);
   		int addResult = userService.addOneUser(user);
   		if(addResult!=1){
   			result="0";
   		}else{
   			user = userService.findUserByUserName(phone);
   			jsonObject.put("user", user);
   		}
   	}
	jsonObject.put("result", result);
	return jsonObject;
   }
}
