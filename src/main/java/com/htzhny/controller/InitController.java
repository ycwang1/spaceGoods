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
 * ΢�Ų�����ֱ�ӱ������Ϣ���ں�̨��ʼ����ȡ
 */
@Controller
@RequestMapping(value="/init")
public class InitController {
	Logger log = Logger.getLogger(InitController.class);
	HttpUtil http = HttpUtil.getInstance();
	@Autowired
	private InitParamDao initParamDao;
    //��¼
    @SuppressWarnings("finally")
	@RequestMapping(value="getWxInfo", method = RequestMethod.POST)
    public @ResponseBody JSONObject getWxInfo(@RequestBody Map<String,Object> params,HttpServletRequest request) {
    	JSONObject jsonObject = new JSONObject();
    	List<InitParam> list = initParamDao.selectParam(1);//��ȡС�������
		Map<String,String> map = new HashMap<String,String>();
		if(list.size()>0){
			for(InitParam param:list){
				map.put(param.getName(), param.getValue());
			}
		}
    	String code = (String) params.get("code");

    	String appId=map.get("wxAppId");
    	String secret=map.get("wxSecret");//������ѡ
    	String result="1";
    	try {
	    	String address = map.get("wxGetOpenIdUrl");
	    	 //�������
	        Map<String, String> param = new HashMap<String, String>();
	        param.put("appid", appId);//���Ǹýӿ���Ҫ�Ĳ���
	        param.put("secret", secret);//���Ǹýӿ���Ҫ�Ĳ���
	        param.put("js_code", code);//���Ǹýӿ���Ҫ�Ĳ���
	        
	        param.put("grant_type", "authorization_code");
	        String res = http.get(address, param, null);
	        System.out.println(res);//��ӡ���ز���
//	        log.info("΢������openid ���ؽ��="+res);
	        res = res.substring(res.indexOf("{"));//��ȡ
	        jsonObject.put("message",res);//��ӡ
	        
	        
	        jsonObject.put("appId", appId);
			jsonObject.put("secret", secret);
			jsonObject.put("result", result);
//			return jsonObject;
            
	       
    	} catch (Exception e) {
            // TODO �쳣
            e.printStackTrace();
        }finally{
        	return jsonObject;
        }
		
    }
}
