package com.htzhny.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.htzhny.dao.Goods_typeDao;
import com.htzhny.entity.Goods;
import com.htzhny.entity.Goods_type;
import com.htzhny.service.Good_typeService;
import com.htzhny.service.GoodsService;

import net.sf.json.JSONObject;





@Controller
@RequestMapping(value="goods_type")
public class Goods_typeController {
	@Autowired
	private Good_typeService goodTypeService;
	@RequestMapping(value="addGoods_type")
	//添加商品种类
	public @ResponseBody JSONObject addGoods_type(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("goods_type");
   		Goods_type goods_type=JSON.parseObject(JSON.toJSONString(map),Goods_type.class);
   		Integer result=goodTypeService.addGoods_type(goods_type);
   		
   		jsonObject.put("result", result);
   		return jsonObject;
	}
	@RequestMapping(value="updateGoods_type_name")
	//通过id修改商品种类名称
	public @ResponseBody JSONObject updateGoods_type_name(@RequestBody Map<String, Object> params){
		
		JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("goods_type");
   		Goods_type goods_type=JSON.parseObject(JSON.toJSONString(map),Goods_type.class);
   		Integer result=goodTypeService.updateGoods_type_name(goods_type);
   		
   		jsonObject.put("result", result);
   		return jsonObject;
	}
	@RequestMapping(value="updateGoods_type_status")
	//通过id修改商品种类状态（上下架）
	public @ResponseBody JSONObject updateGoods_type_status(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("goods_type");
   		Goods_type goods_type=JSON.parseObject(JSON.toJSONString(map),Goods_type.class);
   		Integer result=goodTypeService.updateGoods_type_status(goods_type);
   		
   		jsonObject.put("result", result);
   		return jsonObject;
	}
	@RequestMapping(value="showAllType")
	//展示所有商品种类
	public @ResponseBody JSONObject showAllType(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		List<Goods_type> list=goodTypeService.showAllType();
		
		jsonObject.put("list",list);
   		return jsonObject;
	}

}
