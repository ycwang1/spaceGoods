package com.htzhny.controller;

import java.util.Iterator;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.htzhny.entity.Address;
import com.htzhny.entity.User;
import com.htzhny.service.AddressService;
import com.htzhny.util.SessionUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/address")
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	
	@RequestMapping(value="addAddress", method = RequestMethod.POST)
	//添加地址
	public @ResponseBody JSONObject addAddress(@RequestBody Map<String, Object> params){
		
		JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>) params.get("address");
   		Address address=JSON.parseObject(JSON.toJSONString(map),Address.class);
   		Integer result=addressService.addAddress(address);
   		jsonObject.put("result", result);
	return jsonObject;
	}
	@RequestMapping(value="updateAddressbyId", method = RequestMethod.POST)
	//修改地址
	public  @ResponseBody JSONObject updateAddressbyId(@RequestBody Map<String, Object> params){
		
		
	 	JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>) params.get("address");
   		Address address=JSON.parseObject(JSON.toJSONString(map),Address.class);
   		Integer result=addressService.updateAddressbyId(address);
   		jsonObject.put("result", result);
	return jsonObject;
	}
	@RequestMapping(value="findAddressByUserId", method = RequestMethod.POST)
	//查询所有地址
	public  @ResponseBody JSONObject findAddressByUserId(@RequestBody Map<String, Object> params,HttpServletRequest request){
		SessionUtil sessionUtil=SessionUtil.getInstance();
		HttpSession session = sessionUtil.getSession(request);
		if(session==null){
			session = request.getSession();
		}
		
		User user=(User) session.getAttribute("user");
		Integer user_id=user.getId();
		JSONObject jsonObject = new JSONObject();
		List<Address> list=addressService.findAddressByUserId(user_id);
		jsonObject.put("addressList", list);
	
		return jsonObject;
		}
	@RequestMapping(value="findAddressById", method = RequestMethod.POST)
	//查询一个地址
	public  @ResponseBody JSONObject findAddressById(@RequestBody Map<String, Object> params,HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		Integer id=(Integer) params.get("id");
		Address address=addressService.findAddressById(id);
		jsonObject.put("address", address);
		
		
		return jsonObject;
	}
	
}
