package com.wordty.third.wechat.qy.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

public class WechatUtil {
	
	/**
	 * get请求地址. <br/> 
	 * date: May 25, 2017 5:51:23 PM.<br/>
	 * @author jerry 
	 * @param url
	 * @return 
	 * @since JDK 1.7
	 */
	public static String getResource4GET(String url) {

		BufferedReader buffer = null;
		try {
			URL _url = new URL(url);
			HttpURLConnection openConnection = (HttpURLConnection) _url.openConnection();
			
			buffer = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
			StringBuffer bs = new StringBuffer();
			String l = null;
			while ((l = buffer.readLine()) != null) {
				bs.append(l);
			}
			System.out.println(bs.toString());
			return bs.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try { buffer.close(); } catch (IOException e) { }
		}
		return null;
	}
	
	public static <T>T getResource4GET(String url, Class<?> clazz) {
		String resource4get = getResource4GET(url);
		try {
			return (T) JSONObject.toBean(JSONObject.fromObject(resource4get), clazz);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
