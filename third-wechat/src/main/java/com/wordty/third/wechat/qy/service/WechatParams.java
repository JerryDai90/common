package com.wordty.third.wechat.qy.service;


import org.apache.log4j.Logger;

/**
 * ClassName: WechatParams <br/> 
 * Function: 系统运行时需要的微信参数（可以从配置文件来，也可以读取数据库）. <br/> 
 * date: May 26, 2017 9:32:56 AM <br/> 
 * 
 * @author jerry 
 * @version  
 * @since JDK 1.7
 */
public class WechatParams {

	private static Logger logger = Logger.getLogger(WechatParams.class);

	private static String corpID = "";//CorpID是企业号的标识，每个企业号拥有一个唯一的CorpID。
	private static String secret = "";//secret是管理组凭证密钥，系统管理员在企业号管理后台创建管理组时，企业号后台为该管理组分配一个唯一的secret。通过该secret能够确定管理组，及管理组所拥有的对应用、通讯录、接口的访问权限。
	private static String redirect_uri = "";//重定向url，在微信又叫安全域名，需要微信的安全域名一致
	
	//accessToken获取
	private static final String TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=$CORPID&corpsecret=$CORPSECRET";
	//在微信客户端重定向获取当前登陆用userid
	private static final String AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=$APPID&redirect_uri=$REDIRECT_URI&response_type=code&scope=SCOPE&agentid=AGENTID&state=$STATE#wechat_redirect";
	//获取用户信息，使用userid去换
	private static final String USERINFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=$ACCESS_TOKEN&code=$CODE";
	
	
	public static String getCorpID() {
		return getDictVal("QY_WECHAT_CORPID", null, "企业微信corpID不能为空，填写例如["+corpID+"]", true);
	}
	public static String getSecret() {
		return getDictVal("QY_WECHAT_SECRET", null, "企业微信secret不能为空，填写例如["+secret+"]", true);
	}
	public static String getRedirect_uri() {
		return getDictVal("QY_WECHAT_REDIRECT_URI", null, "企业微信跳转uri不能为空，填写例如["+redirect_uri+"]，跳转uri在微信里面叫安全域名", true);
	}
	public static String getTOKEN_URL() {
		return TOKEN_URL;
	}
	public static String getAUTHORIZE_URL() {
		return AUTHORIZE_URL;
	}
	public static String getUSERINFO_URL() {
		return USERINFO_URL;
	}
	
	public static String getDictVal(String dictKey, String defVal, String isNullTip, boolean isRequired){

//		UmDict dict =URMSConfig.getUmDict("QY_WECHAT");
//		UmDictitem[] dictitems2 = dict.getDictitems();
//		String dictval = null;
//		for( UmDictitem _item : dictitems2 ){
//			if( _item.getItemcode().equals(dictKey) ){
//				dictval = _item.getItemvalue();
//			}
//		}
//		if( StringUtils.isBlank(dictval) ){
//			if( isRequired ){
//				logger.error(isNullTip+" : key="+dictKey);
//				throw new RuntimeException(isNullTip+" : key="+dictKey);
//			}
//			if( StringUtils.isNotBlank(defVal) ){
//				return defVal;
//			}
//		}
//		return dictval;
		return null;
	}
	
}
