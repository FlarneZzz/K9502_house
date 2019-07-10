package com.team.house.sms;

import java.util.HashMap;
import java.util.Map;

/**  
 * @Title: http://www.smschinese.cn/api.shtml
 * @date 2011-3-22
 * @version V1.2  
 */
public class SmsUtil {
	
	//用户名
	private static String Uid = "Flarne";
	
	//接口安全秘钥
	private static String Key = "d41d8cd98f00b204e980";
	
	//手机号码，多个号码如13800000000,13800000001,13800000002
	//private static String smsMob = "15527656720";
	
	//短信内容
	//private static String smsText = "我想你啦！";

	/**
	 * 发送短信的方法
	 * @param sendPhone  发送的手机号、多个手机号用户逗号隔开
	 * @param sendMsg  发送的信息普通短信限70个字，还可输入63字。
	 * @return  成功发送的条数  大于0成功
	 */
	public static int sendMsg(String sendPhone,String sendMsg) {
		
		HttpClientUtil client = HttpClientUtil.getInstance();
		
		//UTF发送
		int result = client.sendMsgUtf8(Uid, Key, sendMsg, sendPhone);
		return result;
	}
}
