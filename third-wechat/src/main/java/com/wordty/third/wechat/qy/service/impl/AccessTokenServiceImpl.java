package com.wordty.third.wechat.qy.service.impl;

import com.wordty.third.wechat.qy.service.IAccessTokenService;
import com.wordty.third.wechat.qy.service.WechatParams;
import com.wordty.third.wechat.qy.service.impl.vo.VAccessToken;
import com.wordty.third.wechat.qy.utils.WechatUtil;

import java.util.Map;


public class AccessTokenServiceImpl implements IAccessTokenService {

	//缓存访问微信的权限信息
	private volatile static VAccessToken ACCESS_TOKEN;

	public synchronized String getAccessToken() {
		if (null == ACCESS_TOKEN) {
			return _getAccessToken().getAccessToken();
		}
		long currentTimeMillis = System.currentTimeMillis();
		if (currentTimeMillis - ACCESS_TOKEN.getCurrentTimeMillis() < ACCESS_TOKEN.expiresIn) {
			return ACCESS_TOKEN.getAccessToken();
		}
		return _getAccessToken().getAccessToken();
	}

	private VAccessToken _getAccessToken() {
		String tempURL = WechatParams.getTOKEN_URL().replaceAll("\\$CORPID", WechatParams.getCorpID()).replaceAll("\\$CORPSECRET", WechatParams.getSecret());
		Map<String, Object> resource4get = WechatUtil.getResource4GET(tempURL, Map.class);
		if( !resource4get.containsKey("access_token") ){
			throw new RuntimeException("调用微信失败：");
		}
		return new VAccessToken(resource4get.get("access_token").toString(), Long.valueOf( resource4get.get("expires_in").toString()));
	}

	
	public static void main(String[] args) {
		AccessTokenServiceImpl i = new AccessTokenServiceImpl();
		String accessToken = i.getAccessToken();
		System.out.println(accessToken);
		
	}
	
}
