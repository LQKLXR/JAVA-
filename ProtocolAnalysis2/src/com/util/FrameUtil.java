package com.util;

public class FrameUtil {

	public String exchange(short frameType) {
		
		String result = null;
		switch (new Integer(frameType)) {
		case 512:
			result = "RUP";
			break;
		case 2048:
			result = "IP";
			break;
		case 2054:
			result = "ARP";
			break;
		case -32715:
			result = "REVARP";
			break;
		case -32512:
			result = "VLAN";
			break;
		case -31011:
			result = "IPv6";
			break;
		case -28672:
			result = "LOOPBACK";
			break;
		default:
			break;
		}
		return result;
	}
}
