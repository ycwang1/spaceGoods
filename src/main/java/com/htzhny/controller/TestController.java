package com.htzhny.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.htzhny.entity.User;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="/test")
public class TestController {

    @RequestMapping(value = "/user/test", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject CeShi(@RequestBody Map<String,Object> params) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", params.get("name").toString());
        jsonObject.put("pw", params.get("pw").toString());
        return jsonObject;
    }
    @RequestMapping("/user/editItemSubmit_RequestJson")  
    public @ResponseBody JSONObject editItemSubmit_RequestJson(@RequestBody User items) throws Exception {  
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("result", "测试");
    	return jsonObject;  
  
    }  

	//图片上传
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody JSONObject upload(@RequestParam(value = "filename", required = false) MultipartFile filename)
            throws IOException {
		JSONObject jsonObject = new JSONObject();
		String result = "0";
        if (!filename.isEmpty()) {

            String url = "F:\\upload";

            File file = new File(url);

            if (!file.isDirectory() && !file.exists()) {
                file.mkdir();
            }
            String fileName = filename.getOriginalFilename();
            // 获取图片的扩展名
            String extensionName = fileName
                    .substring(fileName.lastIndexOf(".") + 1);
            // 新的图片文件名 = 获取时间戳+"."图片扩展名
            String newFileName = String.valueOf(System.currentTimeMillis())
                    + "." + extensionName;

            InputStream is = filename.getInputStream();

            //String name = filename.getOriginalFilename();

            FileOutputStream fileOutputStream = new FileOutputStream(url + "/" + newFileName);

            byte[] b = new byte[is.available()];

            is.read(b);

            fileOutputStream.write(b);
            result = "1";
            jsonObject.put("url", newFileName);
        } 
        jsonObject.put("result", result);
        return jsonObject;
    }
}
