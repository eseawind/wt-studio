package com.wt.studio.plugin.tools.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CodeDigest {
	public static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public enum CodeType {
		MD5, BASE64, AES, DES, RC4, Rabbit, TripleDes, SHA1, SHA224, SHA256, SHA384, SHA512, HmacSHA1, HmacSHA224, HmacSHA256, HmacSHA384, HmacSHA512, HmacMD5
	}

	/**
	 * MD5 加密
	 * 
	 * @param s
	 * @return
	 */
	public static String getMD5(String s) {
		if (s == null)
			return null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(s.getBytes());
			byte[] bs = md5.digest();
			int j = bs.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = bs[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * BASE64加密
	 * 
	 * @param s
	 * @return
	 */
	public static String getBASE64(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	/**
	 * BASE64解密
	 * 
	 * @param s
	 * @return
	 */
	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}

	public static String encodeByType(CodeType type, String source,
			String secrect) {
		String result = "";
		switch (type) {
		case MD5:
			result = getMD5(source);
			break;
		case BASE64:
			result = getBASE64(source);
			break;
		case SHA1:
			result = getSHA1(source);
			break;
		case SHA224:
			result = getSHA224(source);
			break;
		case SHA256:
			result = getSHA256(source);
			break;
		case SHA384:
			result = getSHA384(source);
			break;
		case SHA512:
			result = getSHA512(source);
			break;
		
		}
		return result;
	}

	public static String decodeByType(CodeType type, String source,
			String secrect) {
		String result = null;
		switch (type) {
		case MD5:
			break;
		case BASE64:
			result = getFromBASE64(source);
			break;
		}
		return result;
	}

	public static String getSHA1(String str) {
		try {
			return new sun.misc.BASE64Encoder().encode(MessageDigest
					.getInstance("SHA1").digest(str.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static String getSHA224(String str) {
		try {
			return MessageDigest
					.getInstance("SHA224").digest(str.getBytes()).toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static String getSHA256(String str) {
		try {
			return new sun.misc.BASE64Encoder().encode(MessageDigest
					.getInstance("SHA256").digest(str.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static String getSHA384(String str) {
		try {
			return new sun.misc.BASE64Encoder().encode(MessageDigest
					.getInstance("SHA384").digest(str.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static String getSHA512(String str) {
		try {
			return new sun.misc.BASE64Encoder().encode(MessageDigest
					.getInstance("SHA512").digest(str.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
