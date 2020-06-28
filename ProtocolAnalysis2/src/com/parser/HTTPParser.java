package com.parser;

import javax.xml.bind.DatatypeConverter;

import com.util.ByteArrayUtil;

import jpcap.packet.Packet;

/**
 * HTTP请求解析
 * @author LiuQiankun
 *
 */
public class HTTPParser {
	
	public String parse(Packet packet) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("--------------------------------------").append("\n");
		buffer.append("HTTP协议解析内容").append("\n");
		buffer.append("--------------------------------------").append("\n");
		String data_String = DatatypeConverter.printHexBinary(packet.data);
		String httpString = byteArrayUtil.hexStr2Str(data_String);
		buffer.append(httpString).append("\n");
		return buffer.toString();
	}

	
	
	private ByteArrayUtil byteArrayUtil;
	public HTTPParser() {
		this.byteArrayUtil = new ByteArrayUtil();
	}
}
