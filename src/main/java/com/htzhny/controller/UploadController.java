package com.htzhny.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by majl on 2017/9/1.
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @RequestMapping("/picture")
    public void uploadPicture(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //��ȡ�ļ���Ҫ�ϴ�����·��
        String path = request.getRealPath("/upload") + "/";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        logger.debug("path=" + path);

        request.setCharacterEncoding("utf-8");  //���ñ���
        //��ô����ļ���Ŀ����
        DiskFileItemFactory factory = new DiskFileItemFactory();

        //���û�����������õĻ�,�ϴ�����ļ���ռ�úܶ��ڴ棬
        //������ʱ��ŵĴ洢��,����洢�ҿ��Ժ����մ洢�ļ���Ŀ¼��ͬ
        /**
         * ԭ��: �����ȴ浽��ʱ�洢�ң�Ȼ��������д����ӦĿ¼��Ӳ���ϣ�
         * ������˵���ϴ�һ���ļ�ʱ����ʵ���ϴ������ݣ���һ������ .tem ��ʽ��
         * Ȼ���ٽ�������д����ӦĿ¼��Ӳ����
         */
        factory.setRepository(dir);
        //���û���Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ���ʱ�洢��
        factory.setSizeThreshold(1024 * 1024);
        //��ˮƽ��API�ļ��ϴ�����
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> list = upload.parseRequest(request);
            FileItem picture = null;
            for (FileItem item : list) {
                //��ȡ������������
                String name = item.getFieldName();
                //�����ȡ�ı���Ϣ����ͨ�� �ı� ��Ϣ
                if (item.isFormField()) {
                    //��ȡ�û�����������ַ���
                    String value = item.getString();
                    request.setAttribute(name, value);
                    logger.debug("name=" + name + ",value=" + value);
                } else {
                    picture = item;
                }
            }

            //�Զ����ϴ�ͼƬ������ΪuserId.jpg
            String fileName = request.getAttribute("userId") + ".jpg";
            String destPath = path + fileName;
            logger.debug("destPath=" + destPath);

            //����д��������
            File file = new File(destPath);
            OutputStream out = new FileOutputStream(file);
            InputStream in = picture.getInputStream();
            int length = 0;
            byte[] buf = new byte[1024];
            // in.read(buf) ÿ�ζ��������ݴ����buf ������
            while ((length = in.read(buf)) != -1) {
                //��buf������ȡ������д�����������������
                out.write(buf, 0, length);
            }
            in.close();
            out.close();
        } catch (FileUploadException e1) {
            logger.error("", e1);
        } catch (Exception e) {
            logger.error("", e);
        }


        PrintWriter printWriter = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        HashMap<String, Object> res = new HashMap<String, Object>();
        res.put("success", true);
        printWriter.write(JSON.toJSONString(res));
        printWriter.flush();
    }
}