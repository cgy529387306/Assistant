package com.android.mb.assistant.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



public class MACHelper {

	// 计算MAC验证的Key（测试Key：12344321。）
//	public static final String WORK_KEY_FOR_BUSINESS = "@Jm.1405";//生产

	public static final String WORK_KEY_FOR_BUSINESS = "12344321";//测试

	// 算法方式
	public static final String KEY_MD5 = "MD5";
	public static final String KEY_MAC = "HmacMD5";
	public static final String KEY_SHA = "SHA";
	public static final String KEY_SPLIT = "~";

	/**
	 * MD5
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5(byte[] data) throws Exception {
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);
		return md5.digest();
	}

	/**
	 * SHA
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA(byte[] data) throws Exception {
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);
		return sha.digest();
	}

	/**
	 * 哈希MD5
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {
		SecretKey sk = new SecretKeySpec(key.getBytes(), KEY_MAC);
		Mac mac = Mac.getInstance(sk.getAlgorithm());
		mac.init(sk);
		return mac.doFinal(data);
	}

	/**
	 * 哈希MD5
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptHMAC(String data, String key) throws Exception {
		return byte2hex(encryptHMAC(data.getBytes(), key)).toLowerCase();
	}

	/**
	 * 密码加密1
	 * 
	 * @param buffer
	 * @param key
	 * @return
	 */
	public static byte[] getKeyedDigest(byte[] buffer, byte[] key) {
		try {
			MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
			md5.update(buffer);
			return md5.digest(key);
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}

	/**
	 * 密码加密2
	 * 
	 * @param data
	 * @param key
	 * @return
	 */
	public static String getKeyedDigest(String data, String key) {
		try {
			MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
			md5.update(data.getBytes("UTF8"));
			byte[] digest = md5.digest(key.getBytes("UTF8"));
			String result = "";
			for (int i = 0; i < digest.length; i++) {
				result += Integer.toHexString(
						(0x000000ff & digest[i]) | 0xffffff00).substring(6);
			}
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 字节码转换成16进制字符串
	 * 
	 * @param b
	 *            输入要转换的字节码
	 * @return 返回转换后的16进制字符串
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	/**
	 * 会员密码加密，加密设置后上传。
	 * 
	 * @param pwd
	 *            密码明文
	 * @return
	 * @throws Exception
	 */
	public static String pwd(String pwd){
		try {
			// 密码加密，第二个参数请填空字符串。
			return getKeyedDigest(pwd, "").toUpperCase();
		}catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 计算MAC。
	 * 
	 * @param data
	 *            数据串
	 * @return
	 * @throws Exception
	 */
	public static String workMac(String data) throws Exception {
		if (data == null) {
			data = "";
		}

		String macblock = java.net.URLEncoder.encode(data, "UTF-8");
		//Log.info("(MobileMACHelper)workMac()macblock=[" + macblock + "]");
		if (macblock.length() > 64) {// 大于64位截取前64位。
			macblock = macblock.substring(0, 64);
		}

		return MACHelper.encryptHMAC(macblock, WORK_KEY_FOR_BUSINESS);
	}

	/**
	 * 计算MAC。
	 * 
	 * @param data
	 *            数据串
	 * @return
	 * @throws Exception
	 */
	public static String workMacForApp(String data){
		try {
			if (data == null) {
				data = "";
			}
			String macblock = java.net.URLEncoder.encode(data, "UTF-8");
			//Log.info("(MobileMACHelper)workMac()macblock=[" + macblock + "]");
			if (macblock.length() > 64) {// 大于64位截取前64位。
				macblock = macblock.substring(0, 64);
			}
			return MACHelper.encryptHMAC(macblock, "12344321");
		}catch (Exception e){
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 
	 * 
	 * @param data
	 *            明文
	 * @return
	 * @throws Exception
	 */
	public static String md5(String data) {
		if (data != null) {
			try {
				String md5block = java.net.URLEncoder.encode(data, "UTF-8");
				// 密码加密，第二个参数请填空字符串。
				String getKeyedDigest = getKeyedDigest(md5block, "")
						.toUpperCase();
				return getKeyedDigest;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "";
	}


	public static void main(String[] args) throws Exception {
		String data = "sfy~C4CA4238A0B923820DCC509A6F75849B";
		MACHelper s= new MACHelper();
        System.out.println( "加密="+s.workMacForApp(data));
	}

	public static String getData(List<String> params){
		StringBuilder sb = new StringBuilder();
		if (Helper.isNotEmpty(params)){
			for (int i=0;i<params.size();i++){
				String data = params.get(i);
				if (i>=params.size()-1){
					sb.append(data);
				}else{
					sb.append(data).append(KEY_SPLIT);
				}
			}
		}
		return sb.toString();
	}

	public static String buildMap(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();
		if (map.size() > 0) {
			for (String key : map.keySet()) {
				sb.append(key + "=");
				if (TextUtils.isEmpty(map.get(key))) {
					sb.append("&");
				} else {
					String value = map.get(key);
					try {
						value = URLEncoder.encode(value, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					sb.append(value + "&");
				}
			}
		}
		return sb.toString();
	}

}
