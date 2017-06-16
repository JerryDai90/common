package com.wordty.third.wechat.qy.servlet;

import com.wordty.third.wechat.qy.factory.AuthenticationFactory;
import com.wordty.third.wechat.qy.service.IAuthentication;
import com.wordty.third.wechat.qy.service.IUserinfoService;
import com.wordty.third.wechat.qy.service.impl.UserinfoServiceImpl;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * ClassName: WechatLoginServlet <br/> 
 * Function: 微信登陆认证入口. <br/> 
 * date: Jun 2, 2017 3:38:43 PM <br/> 
 * 
 * @author jerry 
 * @version  
 * @since JDK 1.7
 */
@SuppressWarnings("serial")
public class WechatLoginServlet extends HttpServlet {

	private IUserinfoService userinfo = new UserinfoServiceImpl();
	
	//首页url
	private static final String WECHAT_LOGIN_URL = "/wechat/none.jsp"; 
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAuthentication authFactory = AuthenticationFactory.getInstance();
		
		//step1. 先判断是否已经登录过了
		if( authFactory.isExistSession(request) ){
			request.getRequestDispatcher(authFactory.getIndexUrl()).forward(request, response);
			return;
		}
		
		//step2. 没登录就认证
		// 微信需要配置此Servlet作为入口，且可信域也需要配置成这个
		String code = request.getParameter("code"); //微信返回的当前用户信息，只会一次有效
		String state = request.getParameter("state");//防止重复提交和防伪调用登陆，只会一次有效
		
		if ( StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(state) ) {
			
			if( !userinfo.isSecure( state ) ){
				request.getRequestDispatcher(WECHAT_LOGIN_URL).forward(request, response);
				return;
			}
			// 认证用户信息
			String loginId = userinfo.getUserName( code );
//			String loginId = "zzyh";//debug
			request.setAttribute("wechat_debug_loginId", loginId);
			boolean exist = authFactory.isExist(loginId);
			request.setAttribute("wechat_debug_system_exist_user", exist);
			
			if (exist) {
				// 构建session，跳转到首页
				String url = authFactory.buildSession(request, loginId);
				request.getRequestDispatcher(url).forward(request, response); 
				return;
			}
			// redirect to login page
			request.getRequestDispatcher(WECHAT_LOGIN_URL).forward(request, response);
			return;
		}
		else {
			// redirect to wechat oauth, get return code to login system
			String redirectUri = userinfo.getRedirectUri();
			response.sendRedirect(redirectUri);
			return;
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
