package com.util;

public class ARPUtil {

	/**
	 * 	操作数转换
	 * @param operation
	 * @return
	 */
	public String optionExchange(short operation) {
		String result = null;
		
		switch(operation)
        {
        case 1: // '\001'
        	result = "ARP REQUEST (1)";
            break;

        case 2: // '\002'
        	result = "ARP REPLY (2)";
            break;

        case 3: // '\003'
        	result = "RARP REQUEST (3)";
            break;

        case 4: // '\004'
        	result = "RARP REPLY (4)";
            break;

        case 8: // '\b'
        	result = "INV REQUEST (8)";
            break;

        case 9: // '\t'
        	result = "INV REPLY (9)";
            break;

        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        default:
        	result = "UNKNOWN ";
            break;
        }	
		return result;
		
	}
	
	/**
	 * 硬件类型转换
	 * @param hardWareType
	 * @return
	 */
	public String hardWareTypeExchange(short hardWareType) {
		String result = null;
		switch (hardWareType) {
		case 1:
			result = "Ethernet";
			break;
		case 6:
			result = "IEEE802";
			break;
		case 15:
			result = "FrameRelay";
			break;
		default:
			break;
		}
		return result;
	}
	
	public String protocolExchange(short protocolType) {
		String result = null;
		switch (protocolType) {
		case 2048:
			result = "IP";
			break;
		default:
			result = "Unknown";
			break;
		}
		return result;
	}
}
