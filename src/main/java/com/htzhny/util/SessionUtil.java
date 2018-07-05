package com.htzhny.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.htzhny.session.MySessionContext;

public class SessionUtil {
	private SessionUtil() {}  
    private static SessionUtil single=null;  
    //静态工厂方法   
    public static SessionUtil getInstance() {  
         if (single == null) {    
             single = new SessionUtil();  
         }    
        return single;  
    }  
	//小程序session每次请求session会改变，保持session一致性 wyc
	public HttpSession getSession(HttpServletRequest request ){
		String sessionId = request.getHeader("Cookie");
		if(sessionId==null){
			return null;
		}
		MySessionContext myc= MySessionContext.getInstance();  
		HttpSession session = myc.getSession(sessionId);  
		return session;
	}
}
