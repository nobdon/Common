package com.allen.common.comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	// 电子邮件
	private static final String V_EMAIL = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?";
	// 手机
	private static final String V_MOBILE = "(\\+\\d+)?1[3458]\\d{9}$";
	// 固定电话
	private static final String V_PHONE = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
	// 字母
	private static final String V_LETTER = "^[A-Za-z]+$";
	// 数字
	private static String regex = "(\\d+).*";

	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.length() == 0)
			return true;
		else
			return false;
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (isEmpty(email))
			return false;
		return Pattern.matches(V_EMAIL, email);
	}

	/**
	 * 判断是不是字母
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isLetter(String letter) {
		if (isEmpty(letter))
			return false;
		return Pattern.matches(V_LETTER, letter);
	}

	/**
	 * 判断是不是一个合法的手机号 支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港）
	 * 移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
	 * 150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）
	 * 联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）
	 * 电信的号段：133、153、180（未启用）、189
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		if (isEmpty(mobile))
			return false;
		return Pattern.matches(V_MOBILE, mobile);
	}

	/**
	 * 验证固定电话号码
	 * 
	 * @param phone
	 *            电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447 国家（地区）
	 *            代码 ：标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
	 *            数字之后是空格分隔的国家（地区）代码。 区号（城市代码）：这可能包含一个或多个从 0 到 9
	 *            的数字，地区或城市代码放在圆括号—— 对不使用地区或城市代码的国家（地区），则省略该组件。 电话号码：这包含从 0 到 9
	 *            的一个或多个数字
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean isPhone(String phone) {
		if (isEmpty(phone))
			return false;
		return Pattern.matches(V_PHONE, phone);
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 字符串转布尔值
	 * 
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 将一个InputStream流转换成字符串
	 * 
	 * @param is
	 * @return
	 */
	public static String toConvertString(InputStream is) {
		StringBuffer res = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader read = new BufferedReader(isr);
		try {
			String line;
			line = read.readLine();
			while (line != null) {
				res.append(line);
				line = read.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != isr) {
					isr.close();
					isr.close();
				}
				if (null != read) {
					read.close();
					read = null;
				}
				if (null != is) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
			}
		}
		return res.toString();
	}

	public static String getNumber(String str) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if (m.find()) {
			return m.group(1);
		}
		return "";
	}

	/**
	 * 处理空字符串
	 * 
	 * @param str
	 * @return String
	 */
	public static String doEmpty(String str) {
		return doEmpty(str, "");
	}

	/**
	 * 处理空字符串
	 * 
	 * @param str
	 * @param defaultValue
	 * @return String
	 */
	public static String doEmpty(String str, String defaultValue) {
		if (str == null || str.equalsIgnoreCase("null")
				|| str.trim().equals("") || str.trim().equals("－请选择－")) {
			str = defaultValue;
		} else if (str.startsWith("null")) {
			str = str.substring(4, str.length());
		}
		return str.trim();
	}

	/**
	 * 请选择
	 */
	final static String PLEASE_SELECT = "请选择...";

	public static boolean notEmpty(Object o) {
		return o != null && !"".equals(o.toString().trim())
				&& !"null".equalsIgnoreCase(o.toString().trim())
				&& !"undefined".equalsIgnoreCase(o.toString().trim())
				&& !PLEASE_SELECT.equals(o.toString().trim());
	}

	public static boolean empty(Object o) {
		return o == null || "".equals(o.toString().trim())
				|| "null".equalsIgnoreCase(o.toString().trim())
				|| "undefined".equalsIgnoreCase(o.toString().trim())
				|| PLEASE_SELECT.equals(o.toString().trim());
	}

	public static boolean num(Object o) {
		int n = 0;
		try {
			n = Integer.parseInt(o.toString().trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if (n > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean decimal(Object o) {
		double n = 0;
		try {
			n = Double.parseDouble(o.toString().trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		if (n > 0.0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 给JID返回用户名
	 * 
	 * @param Jid
	 * @return
	 */
	public static String getUserNameByJid(String Jid) {
		if (empty(Jid)) {
			return null;
		}
		if (!Jid.contains("@")) {
			return Jid;
		}
		return Jid.split("@")[0];
	}

	/**
	 * 给用户名返回JID
	 * 
	 * @param jidFor
	 *            域名//ysj-pc
	 * @param userName
	 * @return
	 */
	public static String getJidByName(String userName, String jidFor) {
		if (empty(jidFor) || empty(jidFor)) {
			return null;
		}
		return userName + "@" + jidFor;
	}

	/**
	 * 根据给定的时间字符串，返回月 日 时 分 秒
	 * 
	 * @param allDate
	 *            like "yyyy-MM-dd hh:mm:ss SSS"
	 * @return
	 */
	public static String getMonthTomTime(String allDate) {
		return allDate.substring(5, 19);
	}

	/**
	 * 根据给定的时间字符串，返回月 日 时 分 月到分钟
	 * 
	 * @param allDate
	 *            like "yyyy-MM-dd hh:mm:ss SSS"
	 * @return
	 */
	public static String getMonthTime(String allDate) {
		return allDate.substring(5, 16);
	}

	/**
	 * Phone的判断
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNum(String mobiles) {
		String regExp = "^[1]([3][0-9]{1}|([5][0-9]{1})|([8][0-9]{1})|([7][0-9]{1}))[0-9]{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(mobiles);
		return m.find();// boolean
	}

	/**
	 * 得到图片名称
	 * 
	 * @param url
	 * @return
	 */
	public static String getIconName(String url) {
		String str = url.toString();
		int index = str.lastIndexOf("/") + 1;
		String iconName = str.substring(index);
		return iconName;
	}

	/**
	 * 得到图片的路径不包括文件本身
	 * 
	 * @param url
	 * @return
	 */
	public static String getIconUrl(String url) {
		String str = url.toString();
		int end = str.lastIndexOf("/");
		String uri = str.substring(0, end);
		return uri;
	}

	/**
	 * 半角转化为全角的方法
	 * 
	 * @param input
	 * @return
	 */
	public static String ToSBC(String input) {
		// 半角转全角：
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127 && c[i] > 32)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}

	/**
	 * 全角转化为半角的方法
	 * 
	 * @param input
	 * @return
	 */
	public static String ToDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (isChinese(c[i])) {
				if (c[i] == 12288) {
					c[i] = (char) 32;
					continue;
				}
				if (c[i] > 65280 && c[i] < 65375)
					c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);
	}

	/**
	 * 利用编码的方式判断字符是否为汉字的方法
	 * 
	 * @param c
	 * @return
	 */
	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

}
