package com.parser;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import com.util.ByteArrayUtil;
import com.util.TimeUtil;

import jpcap.NetworkInterface;
import jpcap.packet.Packet;

/**
 * 	数据链路帧解析
 * @author LiuQiankun
 *
 */
public class FrameParser {

	public String parse(Packet packet) {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("--------------------------------------").append("\n");
		buffer.append("数据链路帧解析内容").append("\n");
		buffer.append("--------------------------------------").append("\n");
		
		Map<String, String> frameMap = new LinkedHashMap<String, String>(16);
		frameMap.put("InterFace Name: ", networkInterface.name);
		frameMap.put("InterFace Description: ", networkInterface.description);
		frameMap.put("Encapsulation Type: ", networkInterface.datalink_description);
		frameMap.put("Arrival Time: ", timeUnit.exchang(packet.sec));
		frameMap.put("Epoch Time: ", String.valueOf(packet.sec));
		frameMap.put("Capture Length: ", String.valueOf(packet.caplen)+" Byte");
		frameMap.put("Frame Length: ", String.valueOf(packet.len)+" Byte");
		frameMap.put("All Data: ",DatatypeConverter.printHexBinary(packet.header)+
				DatatypeConverter.printHexBinary(packet.data));
		frameMap.put("Header: ",  DatatypeConverter.printHexBinary(packet.header));
		
		for(Map.Entry<String, String> e : frameMap.entrySet()) {
			buffer.append(e.getKey()+e.getValue()).append("\n");
		}
		
		return buffer.toString();
	}
	
	
	private TimeUtil timeUnit;
	private ByteArrayUtil byteArrayUtil;
	private NetworkInterface networkInterface;
	
	public FrameParser(NetworkInterface networkInterface) {
		super();
		this.timeUnit = new TimeUtil();
		this.byteArrayUtil = new ByteArrayUtil();
		this.networkInterface = networkInterface;
	}
	
}
