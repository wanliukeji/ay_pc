package com.example.demo.Utils;
 
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class SendTelMsgUtils {
	//http://www.miaodiyun.com/
	/**
	 * ACCOUNT_SID:开发者主账号ID(注册后自动生成)
	 */
	public static final String ACCOUNT_SID = "填写ACCOUNT SID";

	/**
	 * AUTH_TOKEN:开发者账号认证密匙(注册后自动生成)
	 */
	public static final String AUTH_TOKEN = "填写AUTH TOKEN";

	/**
	 * BASE_URL:请求地址
	 */
	public static final String BASE_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
 
	/**
	 * RESP_DATA_TYPE:数据返回格式为JSON格式
	 */
	public static final String RESP_DATA_TYPE = "json";
 
	/**
	 * 
	 * @Title：sendMsgTo
	 * @Description：发送短信验证码 @param：
	 * @return：String
	 */
	public static String sendMsgTo(String to, String createRandNum) {
 
		/**
		 * randNum:生成的验证码随机数
		 */
		String randNum = createRandNum;
 
		/**
		 * smsContent:短信内容(短信签名+短信内容，注意要和配置的模板一致，否则报错)
		 */
		String smsContent = "【觅客找房】您的验证码为" + randNum + "，请于2分钟内正确输入，如非本人操作，请忽略此短信。";
 
		/**
		 * 获取时间戳
		 */
		String timestamp = getTimestamp();
 
		/**
		 * 获取签名
		 */
		String sig = sig_MD5(ACCOUNT_SID + AUTH_TOKEN + timestamp);
 
		/**
		 * 要提交的post数据
		 */
		String http_post = "accountSid=" + ACCOUNT_SID + "&smsContent=" + smsContent + "&to=" + to + "&timestamp="
				+ timestamp + "&sig=" + sig + "&respDataType=" + RESP_DATA_TYPE;
 
		OutputStreamWriter osw = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try {
 
			/**
			 * 获取连接
			 */
			URL url = new URL(BASE_URL);
 
			/**
			 * 打开连接
			 */
			URLConnection conn = url.openConnection();
 
			/**
			 * 设置连接参数
			 */
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(20000);
 
			/**
			 * 提交数据
			 */
			osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			osw.write(http_post);
			osw.flush();
 
			/**
			 * 读取返回数据
			 */
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
 
	/**
	 * 获取时间戳
	 */
	public static String getTimestamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = sdf.format(new Date());
		return date;
	}
 
	/**
	 * 对"ACCOUNT_SID + AUTH_TOKEN + timestamp"进行MD5加密
	 * 
	 *                            <dependency>
	 *                            <groupId>commons-codec</groupId>
	 *                            <artifactId>commons-codec</artifactId>
	 *                            <version>1.10</version> </dependency>
	 *                            </dependencies>
	 */
	public static String sig_MD5(String str) {
		String sig_md5 = DigestUtils.md5Hex(str);
		return sig_md5;
	}
 
}