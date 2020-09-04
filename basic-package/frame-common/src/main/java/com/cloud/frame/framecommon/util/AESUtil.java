package com.cloud.frame.framecommon.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

	public static final String DEFAULT_SECRET_KEY = "1abcdefgabcdefg2";

	public static String encrypt(String content, String secertkey) throws Exception {
		if(StringUtils.isEmpty(content)) {
			return null;
		}
		if(StringUtils.isEmpty(secertkey)){
			secertkey = DEFAULT_SECRET_KEY;
		}
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE,
				new SecretKeySpec(secertkey.getBytes("utf-8"), "AES"));
		byte[] bytes = cipher.doFinal(content.getBytes("utf-8"));
		String result = Base64.encodeBase64URLSafeString(bytes);
		return result;
	}

	public static String decrypt(String encryptStr, String secertkey) throws Exception {
		if(StringUtils.isEmpty(encryptStr)) {
			return null;
		}
		if(StringUtils.isEmpty(secertkey)){
			secertkey = DEFAULT_SECRET_KEY;
		}
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE,
				new SecretKeySpec(secertkey.getBytes("utf-8"), "AES"));
		byte[] bytes = Base64.decodeBase64(encryptStr);
		bytes = cipher.doFinal(bytes);
		return new String(bytes);
	}

	public static void main(String[] args) throws Exception {
//		String content = "明文内容";
//		String secertkey = "yq46KaWWm*xA@!4&";
//		String encryptStr = AESUtil.encrypt(content, secertkey);
//		String decryptStr = AESUtil.decrypt(encryptStr, secertkey);
//		System.out.println("明文:" + content);
//		System.out.println("密钥:" + secertkey);
//		System.out.println("加密后信息:" + encryptStr);
//		System.out.println("解密后信息:" + decryptStr);
		String secertKey = "yq46KaWWm*xA@!4&";
		JSONObject loginJson = new JSONObject();
		loginJson.put("username","admin");
		loginJson.put("password","123456");
		String encryptStr = AESUtil.encrypt(loginJson.toJSONString(), secertKey);
		String decryptStr = AESUtil.decrypt(encryptStr, secertKey);
		System.out.println("明文:" + loginJson.toJSONString());
		System.out.println("密钥:" + secertKey);
		System.out.println("加密后信息:" + encryptStr);
		System.out.println("解密后信息:" + decryptStr);

	}
}
