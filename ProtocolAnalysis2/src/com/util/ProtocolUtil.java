package com.util;

public class ProtocolUtil {

	public String exchange(short protocol) {
		String protocol_String = null;
		switch (new Integer(protocol)) {
		case 1:
			protocol_String = "ICMP";
			break;
		case 2:
			protocol_String = "IGMP";
			break;
		case 6:
			protocol_String = "TCP";
			break;
		case 8:
			protocol_String = "EGP";
			break;
		case 9:
			protocol_String = "IGP";
			break;
		case 17:
			protocol_String = "UDP";
			break;
		case 41:
			protocol_String = "IPv6";
			break;
		case 89:
			protocol_String = "OSPF";
			break;
		default:
			break;
		}
		return protocol_String;
	}
}
