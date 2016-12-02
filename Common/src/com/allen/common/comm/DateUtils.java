package com.allen.common.comm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 简单的日期工具类，待补充
 * 
 * @author:   allen
 * @version:  1.0  
 */
public class DateUtils {
	private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		}
	};

	private final static ThreadLocal<SimpleDateFormat> dateFormater3 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy'年'M'月'd'日' HH:mm:ss", Locale.getDefault());
		}
	};


	/**
	 * 将字符串类型日期转位日期类型
	 * @return 返回yyyy'年'M'月'd'日' HH:mm:ss类型的日期
	 */
	public static String getDate(Date sdate) {
		return dateFormater3.get().format(sdate);
	}
    
	/**
	 * 将字符串类型日期转位日期类型
	 * @return 返回"yyyy-MM-dd"类型的日期
	 */
	public static String getDate2(Date sdate) {
		return dateFormater2.get().format(sdate);
	}
	
	/**
	 * 将字符串类型日期转位日期类型
	 * 
	 * @param sdate
	 * @return 转换失败返回null
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater.get().parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将日期类型转换为String类型
	 * 
	 * @param sdate
	 * @return yyyy-MM-dd HH:mm:ss;转换失败返回null
	 */
	public static String toString(Date sdate) {
		return dateFormater.get().format(sdate);
	}

	/**
	 * 比较日期大小
	 * 
	 * @param sdate
	 * @return 转换失败返回null
	 */
	public static boolean compare(String date1, String date2) {
		return toDate(date1).compareTo(toDate(date2)) > 0;
	}

	/**
	 * 将字符串类型日期转位日期类型
	 * 
	 * @param sdate
	 * @return 转换失败返回null
	 */
	public static Date toDate(String sdate, String simpleDateFormat) {
		try {
			return new SimpleDateFormat(simpleDateFormat, Locale.getDefault()).parse(sdate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 判断给定字符串时间是否为今日
	 * 
	 * @param sdate
	 * @return boolean
	 */
	public static boolean isToday(String sdate) {
		boolean b = false;
		Date time = toDate(sdate);
		Date today = new Date();
		if (time != null) {
			String nowDate = dateFormater2.get().format(today);
			String timeDate = dateFormater2.get().format(time);
			if (nowDate.equals(timeDate)) {
				b = true;
			}
		}
		return b;
	}

	/**
	 * 返回long类型的今天的日期
	 * 
	 * @return
	 */
	public static long getToday() {
		Calendar cal = Calendar.getInstance();
		String curDate = dateFormater2.get().format(cal.getTime());
		curDate = curDate.replace("-", "");
		return Long.parseLong(curDate);
	}

	/**
	 * 返回当前时间
	 * 格式:yyyy-MM-dd HH:mm:ss
	 * @return string
	 */
	public static String getNowTime() {
		return dateFormater.get().format(new Date());
	}

	/**
	 *  指定的日期字符，返回当前是星期几
	 * @param date
	 * @return
	 */
	public static String getWeek(String date) {
		Date mDate = null;
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
			mDate = sdf.parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(mDate);
			int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (week_index < 0) {
				week_index = 0;
			}
			return weeks[week_index];
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 *  返回当前是星期几
	 * @return
	 */
	public static String getCurrentWeek() {
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

		Calendar cal = Calendar.getInstance();
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return weeks[week_index];
	}

	/**
	 *  以友好的方式返回日期
	 * @return
	 */
	public static String getFriendlyDate(String date) {
		Calendar now = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		now.setTime(toDate(formatDate(new Date(), "yyyy-MM-dd 00:00:00")));
		calendar.setTime(toDate(date, "yyyy-MM-dd"));
		int between_days = (int) ((calendar.getTimeInMillis() - now.getTimeInMillis()) / (1000 * 3600 * 24));
		String str = formatDateByString(date, "yyyy'年'M'月'd'日'");
		switch (between_days) {
		case -2:
			str = "前天";
			break;
		case -1:
			str = "昨天";
			break;
		case -0:
			str = "今天";
			break;
		case 1:
			str = "明天";
			break;
		case 2:
			str = "后天";
			break;
		}
		return str;
	}
	
	public static boolean isToday2(String date){
		Calendar now = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		now.setTime(toDate(formatDate(new Date(), "yyyy-MM-dd 00:00:00")));
		calendar.setTime(toDate(date, "yyyy-MM-dd"));
		int between_days = (int) ((calendar.getTimeInMillis() - now.getTimeInMillis()) / (1000 * 3600 * 24));
		if(between_days == -0){
			return true;
		}
		return false;
	}

	/**
	 * 格式化时间为指定的格式
	 * @param date 日期格式的字符串
	 * @param formatString 格式化模板("M'月'dd'日' hh':'mm")
	 * @return 格式化后的字符串
	 */
	public static String formatDateByString(String date, String formatString) {
		Date dt = toDate(date);
		// 判断是否格式不对
		if (dt == null) {
			dt = toDate(date + " 00:00:00");
		}
		// 格式不对,返回原日期
		if (dt == null) {
			return date;
		}
		String str = new SimpleDateFormat(formatString, Locale.getDefault()).format(dt).toString();
		return str;
	}

	/**
	 * 格式化时间为指定的格式
	 * @param date 日期对象
	 * @param formatString 格式化模板("M'月'dd'日' hh':'mm")
	 * @return 格式化后的字符串
	 */
	public static String formatDate(Date date, String formatString) {
		String str = new SimpleDateFormat(formatString, Locale.getDefault()).format(date).toString();
		return str;
	}

	/**
	 * 格式化日报的应交时间
	 * @param date
	 * @return
	 */
	public static String formatLatestDate(String date) {
		Date dt = toDate(date);
		String str = new SimpleDateFormat("M'月'd'日'", Locale.getDefault()).format(dt).toString() + "    ";
		String today = new SimpleDateFormat("yyyy'-'M'-'dd", Locale.getDefault()).format(dt).toString();
		str += DateUtils.getWeek(today);
		return str;
	}

	/**
	 * 特殊处理方法 仅针对单一类型 （ /Date(1419681698567)/ ）的参数
	 * @param longString
	 * @return
	 */
	public static String getDateString(String longString) {
		String dateTime = DateUtils.getDate(getDateByString(longString));
		return dateTime;
	}

	public static Date getDateByString(String longString) {
		String dateTemp = longString.substring(6, longString.length() - 2);
		Long lon = Long.parseLong(dateTemp);
		return new Date(lon);
	}

/**
	 * 特殊处理方法 仅针对单一类型 （ /Date(1419681698567)/ ）的参数
	 * @param longString
	 * @return  "yyyy-MM-dd"
	 */
	public static String getDateString2(String longString){
		
		String dateTemp = longString.substring(6, longString.length()-2);
//		System.out.println(dateTemp);
		Long lon = Long.parseLong(dateTemp);
		Date date = new Date(lon);
		String dateTime = DateUtils.getDate2(date);
		return dateTime;
	}
	
	public static void main(String[] args) {
		String dateTime = "1419681698567";

		Long lon = Long.parseLong(dateTime);
		Date date = new Date(lon);

		System.out.println(DateUtils.getDate(date));

		System.out.println("=======================");
	}
	/** 
     * 获取月份起始日期 
     * @param date 
     * @return 
     * @throws ParseException 
     */  
    public static String getMinMonthDate(String date) throws ParseException{  
        Calendar calendar = Calendar.getInstance();  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        calendar.setTime(dateFormat.parse(date));  
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));  
        return dateFormat.format(calendar.getTime());  
    }  
      
    /** 
     * 获取月份最后日期 
     * @param date 
     * @return 
     * @throws ParseException 
     */  
    public static String getMaxMonthDate(String date) throws ParseException{  
        Calendar calendar = Calendar.getInstance();  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        calendar.setTime(dateFormat.parse(date));  
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return dateFormat.format(calendar.getTime());  
    }  
}
