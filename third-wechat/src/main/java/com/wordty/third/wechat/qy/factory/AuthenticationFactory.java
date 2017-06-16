package com.wordty.third.wechat.qy.factory;

import com.wordty.third.wechat.qy.service.IAuthentication;


/**
 * ClassName: AuthenticationFactory <br/> 
 * Function: 认证类实现工厂. <br/> 
 * date: Jun 2, 2017 3:49:07 PM <br/> 
 * 
 * @author jerry 
 * @version  
 * @since JDK 1.7
 */
public class AuthenticationFactory {
	
	private static volatile IAuthentication authentication = null;
	
	public static IAuthentication getInstance(){
		if( null != authentication ){
			return authentication;
		}
		Object lock = new Object();
		synchronized (lock) {
		}
		return authentication;
	}
	
}
