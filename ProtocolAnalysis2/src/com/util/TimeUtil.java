package com.util;

import java.text.SimpleDateFormat;

public class TimeUtil {

	public String exchang(long time) {
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置格式
		String timeText=format.format(time*1000L);                                //获得带格式的字符串
		return timeText;
	}
}
