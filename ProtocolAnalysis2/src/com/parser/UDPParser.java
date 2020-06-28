package com.parser;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import jpcap.packet.Packet;
import jpcap.packet.UDPPacket;

public class UDPParser {

	public String parse(Packet packet) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("--------------------------------------").append("\n");
		buffer.append("UDP协议解析内容").append("\n");
		buffer.append("--------------------------------------").append("\n");
		
		Map<String, String> udpMap = new LinkedHashMap<String, String>(16);
		
		UDPPacket udpPacket = (UDPPacket) packet;
		udpMap.put("Protocol Name: ", "User Datagram Protocol");
		udpMap.put("Source Port: ", String.valueOf(udpPacket.src_port));
		udpMap.put("Destination Port: ", String.valueOf(udpPacket.dst_port));
		udpMap.put("Length: ", String.valueOf(udpPacket.length));
		udpMap.put("Data Length: ", String.valueOf(packet.data.length));
		udpMap.put("Data: ", DatatypeConverter.printHexBinary(packet.data));
		for(Map.Entry<String, String> e : udpMap.entrySet()) {
			buffer.append(e.getKey()+e.getValue()).append("\n");
		}
		
		return buffer.toString();
	}
	
	/**
	 * UDP简易信息，显示在表格最后一项
	 * @param packet
	 * @return
	 */
	public String udpEasyInfo(Packet packet) {
		UDPPacket udpPacket = (UDPPacket) packet;
		return udpPacket.src_port + " → " + udpPacket.dst_port + "  Length = " + String.valueOf(udpPacket.length); 
	}
	
}
