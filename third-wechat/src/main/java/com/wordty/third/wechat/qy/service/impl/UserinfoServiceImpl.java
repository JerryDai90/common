package com.wordty.third.wechat.qy.service.impl;

import com.wordty.third.wechat.qy.service.IAccessTokenService;
import com.wordty.third.wechat.qy.service.IUserinfoService;
import com.wordty.third.wechat.qy.service.WechatParams;
import com.wordty.third.wechat.qy.utils.WechatUtil;

import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserinfoServiceImpl implements IUserinfoService {

	private static Map<String, VSecureInfo>	cacheSecureinfo		= new ConcurrentHashMap<String, VSecureInfo>();
	static {
		// 定时器清楚缓存
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Iterator<VSecureInfo> iterator = cacheSecureinfo.values().iterator();
				long currentTimeMillis = System.currentTimeMillis();
				while (iterator.hasNext()) {
					VSecureInfo next = iterator.next();
					// 微信的code在1分钟之内都是有效的
					if (currentTimeMillis - next.currentTimeMillis >= (1000 * 60)) {
						iterator.remove();
					}
				}
			}
		}, 1000 * 60 * 10l);
	}

	static IAccessTokenService accessTokenService	= new AccessTokenServiceImpl();

	public String getUserName(String code) {
		String accessToken = accessTokenService.getAccessToken();

		String tempURL = WechatParams.getUSERINFO_URL().replaceAll("\\$ACCESS_TOKEN", accessToken).replaceAll("\\$CODE", code);
		Map<String, String> resource = WechatUtil.getResource4GET(tempURL, Map.class);
		return resource.get("UserId");
	}

	public String getRedirectUri() {
		String tempURL = WechatParams.getAUTHORIZE_URL().replaceAll("\\$APPID", WechatParams.getCorpID()).replaceAll("\\$REDIRECT_URI", WechatParams.getRedirect_uri()).replaceAll("\\$STATE", buildSecureKey());
		return tempURL;
	}

	@Override
	public boolean isSecure(String key) {
		VSecureInfo cacheCode = cacheSecureinfo.get(key);
		if (null == cacheCode) {
			return false;
		}
		// 微信的code在10分钟之内都是有效的
		if (System.currentTimeMillis() - cacheCode.currentTimeMillis <= (1000 * 60)) {
			cacheSecureinfo.remove(key);
			return true;
		}
		return false;
	}

	private String buildSecureKey() {
		VSecureInfo vs = new VSecureInfo(UUID.randomUUID().toString());
		cacheSecureinfo.put(vs.key, vs);
		return vs.key;
	}

	private static class VSecureInfo {
		public String	key;
		public Long		currentTimeMillis;

		public VSecureInfo(String key) {
			this.key = key;
			this.currentTimeMillis = System.currentTimeMillis();
		}
	}

	public static void main(String[] args) {
		UserinfoServiceImpl i = new UserinfoServiceImpl();
		String userName = i.getUserName("");
		System.out.println(userName);

	}

}
