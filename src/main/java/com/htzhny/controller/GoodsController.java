package com.htzhny.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import com.htzhny.entity.Goods;
import com.htzhny.entity.PageBean;
import com.htzhny.service.GoodsService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	@RequestMapping(value="addGoods", method = RequestMethod.POST)
	//添加商品
	public  @ResponseBody JSONObject addGoods(MultipartFile file,@RequestBody Map<String, Object> params){
        String filePath = "C:\\upload";//保存图片的路径
        //获取原始图片的拓展名
        String originalFilename = file.getOriginalFilename();
        //新的文件名字
        String newFileName = UUID.randomUUID()+originalFilename;
        //封装上传文件位置的全路径
        File targetFile = new File(filePath,newFileName); 
        try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("goods");
   		Goods goods=JSON.parseObject(JSON.toJSONString(map),Goods.class);
   		Integer result=goodsService.addGoods(goods);
   		jsonObject.put("result", result);
	return jsonObject;
	}
	@RequestMapping(value="updateGoods", method = RequestMethod.POST)
	//修改商品
	public @ResponseBody JSONObject updateGoods(@RequestBody Map<String, Object> params){
		
		JSONObject jsonObject = new JSONObject();
   		Map<String,Object> map=(Map<String, Object>)params.get("goods");
   		Goods goods=JSON.parseObject(JSON.toJSONString(map),Goods.class);
   		Integer result=goodsService.updateGoods(goods);
   		jsonObject.put("result", result);
	return jsonObject;
	}
	@RequestMapping(value="selectCount", method = RequestMethod.POST)
	//查询商品总数
	public ModelAndView selectCount(){
		Integer num=goodsService.selectCount();
		System.out.println(num);
		return null;
	}
	@RequestMapping(value="selectCountByType", method = RequestMethod.POST)
	//查询某个类别商品总数
	public ModelAndView selectCountByType(){
		Integer typeId=1;
		Integer num=goodsService.selectCountByType(1);
		System.out.println(num);
		return null;
	}
	@RequestMapping(value="selectGoodsById", method = RequestMethod.POST)
	//根据id查询商品
	public @ResponseBody JSONObject selectGoodsById(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
    	String str= (String) params.get("goodsId");
    	Integer goods_id=Integer.parseInt(str);
    	Goods goods=goodsService.selectGoodsById(goods_id);
    	jsonObject.put("goods", goods);
    	return jsonObject;
	}
	@RequestMapping(value="selectGoodsByType", method = RequestMethod.POST)
	//根据商品类型查询商品
	public @ResponseBody JSONObject selectGoodsByType(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
    	
    	Integer currentPage= (Integer)params.get("currentPage");
    	String str= (String)params.get("goods_type");
    	Integer typeId=Integer.parseInt(str);
    	PageBean<Goods> pageBean=goodsService.selectGoodsByType(currentPage, typeId);
    	List<Goods> list=pageBean.getLists();
    	String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
	}
	@RequestMapping(value="selectAllGoods", method = RequestMethod.POST)
	//查询所有商品
	public @ResponseBody JSONObject selectAllGoods(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		Integer currentPage= (Integer)params.get("currentPage");
		PageBean<Goods> pageBean=goodsService.selectAllGoods(currentPage);
    	List<Goods> list=pageBean.getLists();
    	String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
	}
	@RequestMapping(value="selectRecommendedGoods")
	//查询推荐商品
	public @ResponseBody JSONObject  selectRecommendedGoods(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		
		List<Goods> list=goodsService.selectRecommendedGoods();
		String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
		
	}
	@RequestMapping(value="updateGoodsRecommendedstatus")
	//修改推荐商品
	public @ResponseBody JSONObject  updateGoodsRecommendedstatus(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		String goods_id=(String) params.get("goods_id");
		Integer goods_Recommended=(Integer) params.get("goods_Recommended");
		Integer result=goodsService.updateGoodsRecommendedstatus(goods_id, goods_Recommended);
		jsonObject.put("result", result);
		return jsonObject;
	}
	@RequestMapping(value="selectAll")
	//查询所有商品（不分页）
	public @ResponseBody JSONObject selectAll(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		
		List<Goods> list=goodsService.selectAll();
		String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
	}
	@RequestMapping(value="selectGoodsStatus")
	//查询所有商品（不分页）
	public @ResponseBody JSONObject selectGoodsStatus(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		Integer goods_status=(Integer) params.get("goods_status");
		List<Goods> list=goodsService.selectGoodsStatus(goods_status);
		String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
	}
	@RequestMapping(value="updateGoodsStatus")
	//上下架商品
	public @ResponseBody JSONObject updateGoodsStatus(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		Integer goods_status=(Integer) params.get("goods_status");
		Integer goods_id=(Integer) params.get("goods_id");
		Integer result=goodsService.updateGoodsStatus(goods_status,goods_id);
		jsonObject.put("result", result);
		return jsonObject;
	}
	
	/**
	 * 根据名称查找蔬菜(模糊查找)
	 * 2018/07/02 wyc
	 * @param params(json格式数组，包含currentPage当前页数，goodsName 商品名称)
	 * @return jsonobject类型数据  list1为查找出来的商品列表
	 */
	@RequestMapping(value="selectGoodsByName", method = RequestMethod.POST)
	public @ResponseBody JSONObject selectGoodsByName(@RequestBody Map<String, Object> params){
		JSONObject jsonObject = new JSONObject();
		Integer currentPage= (Integer)params.get("pages");
		String goodsName = (String) params.get("goodsName");
		PageBean<Goods> pageBean=goodsService.selectGoodsByName(currentPage,goodsName);
    	List<Goods> list=pageBean.getLists();
    	String list1=JSON.toJSONString(list);
    	jsonObject.put("list1",list1);
		return jsonObject;
	}
}
