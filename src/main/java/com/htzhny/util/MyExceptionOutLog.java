package com.htzhny.util;

public class MyExceptionOutLog {
	public static String outLog(Exception e) {
		String error = "";
		StackTraceElement[] st = e.getStackTrace();
		for (StackTraceElement stackTraceElement : st) {
			String exclass = stackTraceElement.getClassName();
			String method = stackTraceElement.getMethodName();
			/*new Date() + ":" + */
			error += "\t[��:" + exclass + "]����"
			+ method + "ʱ�ڵ�" + stackTraceElement.getLineNumber()
			+ "�д��봦�����쳣!\n\t\t\t�쳣����:" + e.getClass().getName()
			+ "\n\t\t�쳣��ϢΪ:"+ e.toString()+"\n";
		}
		
		return error;
	}
}
