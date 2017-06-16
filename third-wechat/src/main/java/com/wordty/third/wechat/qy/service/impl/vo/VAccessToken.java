package com.wordty.third.wechat.qy.service.impl.vo;

/**
 * 
 * ClassName: VAccessToken <br/> 
 * Function: 微信认证信息. <br/> 
 * date: May 25, 2017 5:27:35 PM <br/> 
 * 
 * @author jerry 
 * @version  
 * @since JDK 1.7
 */
public class VAccessToken {
	
	/**
	 * 许可证
	 */
	public String accessToken;
	/**
	 *  有效时间
	 */
	public Long expiresIn;
	
	/**
	 * 生成许可的时间
	 */
	public Long currentTimeMillis;

	public VAccessToken() {
	}
	
	public VAccessToken(String accessToken, Long expiresIn) {
		super();
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.currentTimeMillis = System.currentTimeMillis();
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Long getCurrentTimeMillis() {
		return currentTimeMillis;
	}

	public void setCurrentTimeMillis(Long currentTimeMillis) {
		this.currentTimeMillis = currentTimeMillis;
	}
}
