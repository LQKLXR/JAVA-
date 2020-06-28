package com.parser;

import java.util.LinkedHashMap;
import java.util.Map;

import com.util.ARPUtil;

import jpcap.packet.ARPPacket;
import jpcap.packet.Packet;

/**
 * 	解析ARP协议
 * @author LiuQiankun
 *
 */
public class ARPParser {
	//用到的工具类
	private ARPUtil arpUtil;
	
	public ARPParser() {
		this.arpUtil = new ARPUtil();
	}
	
	public String parse(Packet packet) {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("--------------------------------------").append("\n");
		buffer.append("ARP协议解析内容").append("\n");
		buffer.append("--------------------------------------").append("\n");
		
		ARPPacket arpPacket = (ARPPacket) packet;
		Map<String, String> arpMap = new LinkedHashMap<>();
		arpMap.put("Protocol Name: ", "Address Resolution Protocol");
		arpMap.put("HardWare Type: ", arpUtil.hardWareTypeExchange(arpPacket.hardtype));
		arpMap.put("Protocol Type", arpUtil.protocolExchange(arpPacket.prototype));
		arpMap.put("HardWare Size: ", String.valueOf(arpPacket.hlen));
		arpMap.put("Protocol Size", String.valueOf(arpPacket.plen));
		arpMap.put("Option: ", arpUtil.optionExchange(arpPacket.operation));
		arpMap.put("Sender Mac address: ", String.valueOf(arpPacket.getSenderHardwareAddress()));
		arpMap.put("Sender IP address:", String.valueOf(arpPacket.getSenderProtocolAddress()));
		arpMap.put("Target Mac address: ", String.valueOf(arpPacket.getTargetHardwareAddress()));
		arpMap.put("Target IP address:", String.valueOf(arpPacket.getTargetProtocolAddress()));
		//
		for(Map.Entry<String, String> e : arpMap.entrySet()) {
        	buffer.append(e.getKey()+e.getValue()).append("\n");
		}
		return buffer.toString();
	}
	
	/**
	 * 	ARP协议的简易信息，显示在表格的最后一列
	 * @param packet
	 * @return
	 */
	public String arpEasyInfo(Packet packet) {
		ARPPacket arpPacket = (ARPPacket) packet;
		return (new StringBuffer("Who has ").append(arpPacket.getTargetProtocolAddress())
				.append(" ? Tell ").append(arpPacket.getSenderProtocolAddress())).toString();
	}
	
	
	
}
