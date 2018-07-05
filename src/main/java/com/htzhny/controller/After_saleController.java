package com.htzhny.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.htzhny.entity.Address;
import com.htzhny.entity.After_sale;
import com.htzhny.entity.After_saleQuery;
import com.htzhny.entity.OrderLog;
import com.htzhny.entity.Order_item;
import com.htzhny.entity.Order_itemAndAfter_saleQuery;
import com.htzhny.entity.Order_itemQuery;
import com.htzhny.entity.PageBean;
import com.htzhny.entity.User;
import com.htzhny.service.After_saleService;
import com.htzhny.service.OrderService;
import com.htzhny.service.Order_itemService;
import com.htzhny.service.Order_logService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/after_sale")
public class After_saleController {
	Logger log = Logger.getLogger(After_saleController.class);
	@Autowired
	private After_saleService after_saleService;
	@Autowired
	private Order_itemService order_itemService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private Order_logService logService;
	
	
	//增加售后记录
    @RequestMapping(value="addAfter_sale", method = RequestMethod.POST)
    public @ResponseBody JSONObject addAfter_sale(@RequestBody Map<String, Object> params) {
    
    	JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("after_sale");
   		After_sale after_sale=JSON.parseObject(JSON.toJSONString(map),After_sale.class);
   		String uuid=UUID.randomUUID().toString();
   		Integer user_id=after_sale.getUser_id();
   		String after_sale_id=user_id+uuid;
   		after_sale.setAfter_sale_id(after_sale_id);
   		Integer result=after_saleService.addAfter_sale(after_sale);
   		if(result!=0){
   			String order_id=after_sale.getA_order_id();
   			orderService.updateStatus(5,order_id );
   		}
   		String order_id=after_sale.getA_order_id();
   		Date dt =new Date(); 
		String formatDate = "";  
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；  
	    formatDate = dFormat.format(dt); 
	    
   		OrderLog orderlog=new OrderLog(order_id,formatDate,5);
   		logService.addLog(orderlog);
   		jsonObject.put("result", result);
	return jsonObject;
    }
    //修改售后记录
    @RequestMapping(value="updateAfter_sale", method = RequestMethod.POST)
    public @ResponseBody JSONObject updateAfter_sale(@RequestBody Map<String, Object> params){
    	
    	JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("after_sale");
   		log.info("修改售后记录 params"+params);
   		After_sale after_sale=JSON.parseObject(JSON.toJSONString(map),After_sale.class);
   		Date dt =new Date(); 
		String formatDate = "";  
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；  
	    formatDate = dFormat.format(dt);
	    after_sale.setHandle_time(formatDate);
	    String order_id=after_sale.getA_order_id();
	    log.info("修改售后记录 order_id"+order_id);
   		Integer result=after_saleService.updateAfter_sale(after_sale);
   		log.info("修改售后记录 result"+result);
   		if(result!=0){
   			orderService.updateStatus(6, order_id);
   		}
   		OrderLog orderlog=new OrderLog(order_id,formatDate,6);
   		logService.addLog(orderlog);
   		OrderLog orderlog1=new OrderLog(order_id,formatDate,7);
   		logService.addLog(orderlog1);
   		jsonObject.put("result", result);
	return jsonObject;
    }
    //查询用户记录总数
    @RequestMapping(value="selectCountByUser", method = RequestMethod.POST)
    public @ResponseBody JSONObject selectCountByUser(@RequestBody Map<String, Integer> params){
    	JSONObject jsonObject = new JSONObject();
    	Integer user_id= params.get("user_id");
    	Integer num=after_saleService.selectCountByUser(user_id);
    	jsonObject.put("num", num);
    	return jsonObject;
    }
    @RequestMapping(value="selectCount", method = RequestMethod.POST)
    //查询总记录数
    public@ResponseBody JSONObject  selectCount(@RequestBody Map<String, Integer> params){
    	JSONObject jsonObject = new JSONObject();
    	Integer num=after_saleService.selectCount();
    	jsonObject.put("num", num);
    	return jsonObject;
    }
    //分页查询(用户)
    @RequestMapping(value="findByPage", method = RequestMethod.POST)
    public @ResponseBody JSONObject findByPage(@RequestBody Map<String, Object> params){
    	JSONObject jsonObject = new JSONObject();
    	
    	Integer currentPage= (Integer) params.get("currentPage");
    	Integer user_id= (Integer) params.get("user_id");
    	
    	PageBean<After_saleQuery> pageBean=after_saleService.findByPage(currentPage, user_id);
    	List<After_saleQuery> list=pageBean.getLists();
    	String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
    	
    }
    //管理员查询售后事件
    @RequestMapping(value="findByPageByAdmin", method = RequestMethod.POST)
    public @ResponseBody JSONObject  findByPageByAdmin(@RequestBody Map<String, Object> params){
    	JSONObject jsonObject = new JSONObject();
    	Integer currentPage= (Integer) params.get("currentPage");
    	PageBean<After_saleQuery> pageBean=after_saleService.findByPageByAdmin(currentPage);

    	List<After_saleQuery> list=pageBean.getLists();
    	String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
    }
    //管理员通过时间查询售后事件
    @RequestMapping(value="findByPageByTime", method = RequestMethod.POST)
    public @ResponseBody JSONObject  findByPageByTime(@RequestBody Map<String, Object> params){
    	JSONObject jsonObject = new JSONObject();
    	int currentPage=(int) params.get("currentPage");
    	String time=(String) params.get("delivery_time");
    	
    	PageBean<After_saleQuery> pageBean=after_saleService.findByPageByTime(currentPage, time);
    	List<After_saleQuery> list=pageBean.getLists();
    	String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
    }
    //通过售后事件id和订单id查询一个订单详情及对应的售后事件详情
    @RequestMapping(value="selectOneById", method = RequestMethod.POST)
    public @ResponseBody JSONObject selectOneById(@RequestBody Map<String, Object> params){
    	
    	JSONObject jsonObject = new JSONObject();
    	int currentPage=(int) params.get("currentPage");
    	String order_id=(String) params.get("order_id");
    	PageBean<Order_itemAndAfter_saleQuery> pagebean =after_saleService.selectOneById(currentPage,order_id);
    	List<Order_itemAndAfter_saleQuery> list=pagebean.getLists();
    	List<OrderLog> orderLogList=logService.findOneOrderLog(order_id);
    	if(orderLogList.size()>0){
    		jsonObject.put("orderLogList",orderLogList);
    	}
    	jsonObject.put("list",list);
    	
    	return jsonObject;
    }
    //取消售后申请
    @RequestMapping(value="updateHandleStatus", method = RequestMethod.POST)
    public @ResponseBody JSONObject updateHandleStatus(@RequestBody Map<String, Object> params){
    	JSONObject jsonObject = new JSONObject();
    	String after_sale_id=(String) params.get("after_sale_id");
    	Integer handle_status=(Integer) params.get("handle_status");
    	Integer result=after_saleService.updateHandleStatus(after_sale_id, handle_status);
    	After_sale after_sale=after_saleService.selectById(after_sale_id);
    	orderService.updateStatus(6, after_sale.getA_order_id());
    	jsonObject.put("result", result);
    	return jsonObject;
    }
    
}
