package com.wordty.third.wechat.qy.service;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: IAuthentication <br/> 
 * Function: 用户认证信息，用于微信自动登陆的时候判断. <br/> 
 * date: Jun 1, 2017 5:52:12 PM <br/> 
 * 
 * @author jerry 
 * @version  
 * @since JDK 1.7
 */
public interface IAuthentication {
	
	/**
	 * 是否存在用户. <br/> 
	 * date: Jun 1, 2017 5:52:44 PM.<br/>
	 * @author jerry 
	 * @param loginId 用户名（登陆id）
	 * @return 
	 * @since JDK 1.7
	 */
	boolean isExist(String loginId);
	
	/**
	 * 首页url. <br/> 
	 * date: Jun 6, 2017 10:02:54 AM.<br/>
	 * @author jerry 
	 * @return 
	 * @since JDK 1.7
	 */
	String getIndexUrl();
	
	/**
	 * 是否存在于session，如果存在就不做验证了. <br/> 
	 * date: Jun 6, 2017 10:03:26 AM.<br/>
	 * @author jerry 
	 * @param req
	 * @return 
	 * @since JDK 1.7
	 */
	boolean isExistSession(HttpServletRequest req);
	
	
	/**
	 * 构建需要的session信息，需要返回重定向的url. <br/> 
	 * date: Jun 2, 2017 2:51:11 PM.<br/>
	 * @author jerry 
	 * @param req
	 * @param loginId 
	 * @since JDK 1.7
	 */
	String buildSession(HttpServletRequest req, String loginId);
	
	
}
