package com.util;

import java.text.SimpleDateFormat;

public class TimeUtil {

	public String exchang(long time) {
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //���ø�ʽ
		String timeText=format.format(time*1000L);                                //��ô���ʽ���ַ���
		return timeText;
	}
}
