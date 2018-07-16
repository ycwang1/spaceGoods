package com.htzhny.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.htzhny.dao.InitParamDao;
import com.htzhny.entity.InitParam;
import com.htzhny.util.HttpUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 微信不允许直接保存的信息，在后台初始化获取
 */
@Controller
@RequestMapping(value="/init")
public class InitController {
	Logger log = Logger.getLogger(InitController.class);
	HttpUtil http = HttpUtil.getInstance();
	@Autowired
	private InitParamDao initParamDao;
    //登录
    @SuppressWarnings("finally")
	@RequestMapping(value="getWxInfo", method = RequestMethod.POST)
    public @ResponseBody JSONObject getWxInfo(@RequestBody Map<String,Object> params,HttpServletRequest request) {
    	JSONObject jsonObject = new JSONObject();
    	List<InitParam> list = initParamDao.selectParam(1);//获取小程序参数
		Map<String,String> map = new HashMap<String,String>();
		if(list.size()>0){
			for(InitParam param:list){
				map.put(param.getName(), param.getValue());
			}
		}
    	String code = (String) params.get("code");

    	String appId=map.get("wxAppId");
    	String secret=map.get("wxSecret");//航天优选
    	String result="1";
    	try {
	    	String address = map.get("wxGetOpenIdUrl");
	    	 //请求参数
	        Map<String, String> param = new HashMap<String, String>();
	        param.put("appid", appId);//这是该接口需要的参数
	        param.put("secret", secret);//这是该接口需要的参数
	        param.put("js_code", code);//这是该接口需要的参数
	        
	        param.put("grant_type", "authorization_code");
	        String res = http.get(address, param, null);
	        System.out.println(res);//打印返回参数
//	        log.info("微信请求openid 返回结果="+res);
	        res = res.substring(res.indexOf("{"));//截取
	        jsonObject.put("message",res);//打印
	        
	        
	        jsonObject.put("appId", appId);
			jsonObject.put("secret", secret);
			jsonObject.put("result", result);
//			return jsonObject;
            
	       
    	} catch (Exception e) {
            // TODO 异常
            e.printStackTrace();
        }finally{
        	return jsonObject;
        }
		
    }
}
