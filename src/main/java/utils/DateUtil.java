package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static Date getDate() {
		Date date = new Date();
		return date;
	}
	public static Date get30AfterDate(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 30);//计算30天后的时间
		return c.getTime();
	}
	//计算两个date差值（分钟）
	public static long MinuteNum(Date date1,Date date2){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        format.setLenient(false);
        long minutes=(date2.getTime()-date1.getTime())/(1000*60);
        return minutes;
	}
	public static void main(String[] args) throws ParseException {
		Date date1 = getDate();
		Date date2 = get30AfterDate();
		System.out.println(date1);
		System.out.println(date2);
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		System.out.println(now);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String str = format2.format(date1);
		System.out.println(str);
		System.out.println(format2.parse(str));
	}
}
