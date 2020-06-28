package com.parser;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
/**
 * 	TCP协议解析
 * @author LiuQiankun
 *
 */
public class TCPParser {
	
	/**
	 * 	TCP协议数据的解析
	 * @param packet
	 * @return
	 */
	public String parser(Packet packet) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("--------------------------------------").append("\n");
		buffer.append("TCP协议解析内容").append("\n");
		buffer.append("--------------------------------------").append("\n");
		//TCP解析
		//存储结构
		//System.out.println(DatatypeConverter.printHexBinary(packet.data));
		Map<String, String> tcpMap = new LinkedHashMap<String, String>(64);
		TCPPacket tcpPacket = (TCPPacket) packet;
		tcpMap.put("Protocol Name: ", "Transmission Control Protocol");
		//源端口
		tcpMap.put("Source Port: ", String.valueOf(tcpPacket.src_port));
		//目的端口
		tcpMap.put("Destination Port: ", String.valueOf(tcpPacket.dst_port));
		tcpMap.put("Sequence number: ", String.valueOf(tcpPacket.sequence));
		tcpMap.put("Ack number: ", String.valueOf(tcpPacket.ack_num));
		short tcp_OptionLength = 0;
		if(tcpPacket.option == null) {
			tcp_OptionLength = 0;
		}
		tcpMap.put("Header Length: ", String.valueOf(tcp_OptionLength + 20));
		tcpMap.put("Flags begin: ", "↓");
		tcpMap.put("Reserver1: ", String.valueOf(tcpPacket.rsv1));
		tcpMap.put("Reserver2: ", String.valueOf(tcpPacket.rsv2));
		tcpMap.put("Fin: ", String.valueOf(tcpPacket.fin));
		tcpMap.put("Syn: ", String.valueOf(tcpPacket.syn));
		tcpMap.put("Urgent: ", String.valueOf(tcpPacket.urg));
		tcpMap.put("Push: ", String.valueOf(tcpPacket.psh));
		tcpMap.put("Reset: ", String.valueOf(tcpPacket.rst));
		tcpMap.put("Acknowledgment: ", String.valueOf(tcpPacket.ack));
		tcpMap.put("Flags end: ", "↑");
		tcpMap.put("Window Size Value: ",String.valueOf(tcpPacket.window));
		tcpMap.put("Urgent pointer: ",String.valueOf(tcpPacket.urgent_pointer));
		if(tcpPacket.option!=null)
			tcpMap.put("Option: ", DatatypeConverter.printHexBinary(tcpPacket.option));
		else
			tcpMap.put("Option: ", "null");
		tcpMap.put("Data: ", DatatypeConverter.printHexBinary(packet.data));
		for(Map.Entry<String, String> e : tcpMap.entrySet()) {
			buffer.append(e.getKey()+e.getValue()).append("\n");
		}
		return buffer.toString();
	}
	
	
	public String tcpEasyInfo(Packet packet) {
		TCPPacket tcpPacket = (TCPPacket) packet;
		
		return new StringBuilder().append(tcpPacket.src_port).append(" → ").append(tcpPacket.dst_port).append(" seq(").append(tcpPacket.sequence).append(") win(").append(tcpPacket.window).append(")")
				.append(tcpPacket.ack ? (new StringBuilder(" ACK ")).append(tcpPacket.ack_num).toString() : "").append(" ")
				.append(tcpPacket.syn ? " SYN" : "").append(tcpPacket.fin ? " FIN" : "").append(tcpPacket.psh ? " PSH" : "")
				.append(tcpPacket.rst ? " RSU" : "").append(tcpPacket.urg ? " URG" : "").toString();
	    
		
	}

}
