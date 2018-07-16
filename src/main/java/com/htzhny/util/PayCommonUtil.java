package com.htzhny.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class PayCommonUtil {
	/** 
	 * �Ƿ�ǩ����ȷ,������:����������a-z����,������ֵ�Ĳ������μ�ǩ���� 
	 * @return boolean 
	 */  
	public static boolean isTenpaySign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {  
		StringBuffer sb = new StringBuffer();  
		Set es = packageParams.entrySet();  
		Iterator it = es.iterator();  
		while(it.hasNext()) {  
			Map.Entry entry = (Map.Entry)it.next();  
			String k = (String)entry.getKey();  
			String v = (String)entry.getValue();  
			if(!"sign".equals(k) && null != v && !"".equals(v)) {  
				sb.append(k + "=" + v + "&");  
			}  
		}  
 
		sb.append("key=" + API_KEY);  
 
		//���ժҪ  
		String mysign = MD5.MD5Encode(sb.toString(), characterEncoding).toLowerCase();  
		String tenpaySign = ((String)packageParams.get("sign")).toLowerCase();  
 
		//System.out.println(tenpaySign + "    " + mysign);  
		return tenpaySign.equals(mysign);  
	}  
 
	/** 
	 * @author 
	 * @Description��signǩ�� 
	 * @param characterEncoding 
	 *            �����ʽ 
	 * @param parameters 
	 *            ������� 
	 * @return 
	 */  
	public static String createSign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {  
		StringBuffer sb = new StringBuffer();  
		Set es = packageParams.entrySet();  
		Iterator it = es.iterator();  
		while (it.hasNext()) {  
			Map.Entry entry = (Map.Entry) it.next();  
			String k = entry.getKey().toString();  
			String v = entry.getValue().toString();  
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {  
				sb.append(k + "=" + v + "&");  
			}  
		}  
		sb.append("key=" + API_KEY);  
		String sign = MD5.MD5Encode(sb.toString(), characterEncoding).toUpperCase();  
		return sign;  
	}  
	public static String createLinkString(Map<String, String> params) {     
		List<String> keys = new ArrayList<String>(params.keySet());     
		Collections.sort(keys);     
		String prestr = "";     
		for (int i = 0; i < keys.size(); i++) {     
			String key = keys.get(i);     
			String value = params.get(key);     
			if (i == keys.size() - 1) {// ƴ��ʱ�����������һ��&�ַ�     
				prestr = prestr + key + "=" + value;     
			} else {     
				prestr = prestr + key + "=" + value + "&";     
			}     
		}     
		return prestr;     
	}     
	/** 
	 * @author 
	 * @Description�����������ת��Ϊxml��ʽ��string 
	 * @param parameters 
	 *            ������� 
	 * @return 
	 */  
	public static String getRequestXml(SortedMap<Object, Object> parameters) {  
		StringBuffer sb = new StringBuffer();  
		sb.append("<xml>");  
		Set es = parameters.entrySet();  
		Iterator it = es.iterator();  
		while (it.hasNext()) {  
			Map.Entry entry = (Map.Entry) it.next();  
			String k = entry.getKey().toString();  
			String v = entry.getValue().toString();   
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {  
				sb.append("<" + k + ">"  + v + "</" + k + ">");  
			} else {  
				sb.append("<" + k + ">" + v + "</" + k + ">");  
			}  
		}  
		sb.append("</xml>");  
		return sb.toString();  
	}  
 
	/** 
	 * ȡ��һ��ָ�����ȴ�С�����������. 
	 *  
	 * @param length 
	 *            int �趨��ȡ��������ĳ��ȡ�lengthС��11 
	 * @return int �������ɵ�������� 
	 */  
	public static int buildRandom(int length) {  
		int num = 1;  
		double random = Math.random();  
		if (random < 0.1) {  
			random = random + 0.1;  
		}  
		for (int i = 0; i < length; i++) {  
			num = num * 10;  
		}  
		return (int) ((random * num));  
	}  
 
	/** 
	 * ��ȡ��ǰʱ�� yyyyMMddHHmmss 
	 *  
	 * @return String 
	 */  
	public static String getCurrTime() {  
		Date now = new Date();  
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
		String s = outFormat.format(now);  
		return s;  
	}
 
	public static boolean verify(String text, String sign, String key, String input_charset) {
		text = text + key;     
		String mysign =MD5.MD5Encode(text, input_charset).toUpperCase();  
		System.out.println(mysign);	System.out.println(mysign);	System.out.println(mysign);	System.out.println(mysign);
		if (mysign.equals(sign)) {     
			return true;     
		} else {     
			return false;     
		}     
	} 
}
