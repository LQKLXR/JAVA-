package com.util;

import javax.xml.bind.DatatypeConverter;

public class ByteArrayUtil {
	/**
	 * Mac��ַ��16����ת��
	 * @param array
	 * @return
	 */
	public String macAddressExchange(byte[] array) {
		StringBuffer buffer = new StringBuffer(); 
		int count = 1;
        for (byte b : array) {  
        	buffer.append(Integer.toHexString(b & 0xff));
            if(count++ != array.length) 
            	buffer.append(":");
        }
        return buffer.toString();
	}
	
	
	/**
	 * ���ݵ�16����ת��
	 * @param array
	 * @return
	 */
	public String dataExchange(byte[] array) {
		
		String first = DatatypeConverter.printHexBinary(array);
		StringBuffer buffer = new StringBuffer("\n");
		int temp = first.length() / 16;
		for(int i = 0; i < temp; i++) {
			if((i + 1) * 16 < first.length()) {
				buffer.append(first.substring(i*16, (i+1)*16)).append("\n");
			}
			else {
				buffer.append(first.substring(i*16, first.length())).append("\n");
			}
		}
        return buffer.toString();
	}

	/**
	 *  16�����ַ���ת�ı��ַ���
	 * @param hexStr
	 * @return
	 */
	public String hexStr2Str(String hexStr) {
		String str = "0123456789ABCDEF";
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;
		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}
}
