package com.wordty.third.wechat.qy.service;

public interface IUserinfoService {

	/**
	 * 获取用户的用户名称. <br/> 
	 * date: May 26, 2017 9:24:27 AM.<br/>
	 * @author jerry 
	 * @param code
	 * @return 
	 * @since JDK 1.7
	 */
	String getUserName(String code);

	/**
	 * 微信客户端认证第一步用户code获取重定向url. <br/> 
	 * date: May 26, 2017 9:31:32 AM.<br/>
	 * @author jerry 
	 * @return 
	 * @since JDK 1.7
	 */
	String getRedirectUri();
	
	/**
	 * 是否是安全的. <br/> 
	 * date: Jun 2, 2017 3:04:02 PM.<br/>
	 * @author jerry 
	 * @param key
	 * @return 
	 * @since JDK 1.7
	 */
	boolean isSecure(String key);
	
	
}
