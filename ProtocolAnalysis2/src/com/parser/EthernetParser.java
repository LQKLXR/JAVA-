package com.parser;

import java.util.LinkedHashMap;
import java.util.Map;

import com.util.ByteArrayUtil;
import com.util.FrameUtil;

import jpcap.packet.EthernetPacket;
import jpcap.packet.Packet;

/**
 * 	��̫������
 * @author LiuQiankun
 *
 */
public class EthernetParser {

	public String parse(Packet packet) {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("--------------------------------------").append("\n");
		buffer.append("��̫����������").append("\n");
		buffer.append("--------------------------------------").append("\n");
		
		EthernetPacket ethernetPacket = (EthernetPacket) packet.datalink;
		Map<String, String> ethernetMap = new LinkedHashMap<>();
        ethernetMap.put("Source Mac: ", byteArrayUtil.macAddressExchange(ethernetPacket.src_mac));  //Դmac��ַ
        ethernetMap.put("Destination Mac: ", byteArrayUtil.macAddressExchange(ethernetPacket.dst_mac)); //Ŀ��Mac��ַ
        ethernetMap.put("Frame Type: ", frameUtil.exchange(ethernetPacket.frametype));
        ethernetMap.put("To Sum Up: ", packet.datalink.toString().split("@")[1].split(" ")[1]);
        for(Map.Entry<String, String> e : ethernetMap.entrySet()) {
        	buffer.append(e.getKey()+e.getValue()).append("\n");
		}
        
        return buffer.toString();
	}
	
	
	private FrameUtil frameUtil;
	private ByteArrayUtil byteArrayUtil;

	public EthernetParser() {
		super();
		this.frameUtil = new FrameUtil();
		this.byteArrayUtil = new ByteArrayUtil();
		
	}
	
}
