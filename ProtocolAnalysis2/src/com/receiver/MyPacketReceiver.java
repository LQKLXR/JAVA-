package com.receiver;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import com.parser.ARPParser;
import com.parser.EthernetParser;
import com.parser.FrameParser;
import com.parser.HTTPParser;
import com.parser.IPParser;
import com.parser.TCPParser;
import com.parser.UDPParser;
import com.record.FlowRecorder;
import com.record.ProtocolRecord;
import com.swing.MyJFrame;
import com.util.ByteArrayUtil;
import com.util.TimeUtil;

import jpcap.NetworkInterface;
import jpcap.PacketReceiver;
import jpcap.packet.ARPPacket;
import jpcap.packet.ICMPPacket;
import jpcap.packet.IPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

/**
 * 	数据包接收器
 * @author LiuQiankun
 *
 */
public class MyPacketReceiver implements PacketReceiver{

	
	//全局维护的一个协议记录Map
	public static Map<Integer, ProtocolRecord> protocolRecordMap = new LinkedHashMap<>(512);
	//全局维护的一个协议行数计数器
	public static int protocolCount =  0;
	//全局维护的一个IP流量记录列表
	public static ArrayList<FlowRecorder> ipRecorders = new ArrayList<>(512);
	//全局维护的一个IP流量行数计数器
	public static int ipCount = 0;
	//总上传-下载流量
	public static long download_Flow = 0L;
	public static long upload_Flow = 0L;
	// Parser
	private IPParser ipParser;
	private EthernetParser ethernetParser;
	private FrameParser frameParser;
	private TCPParser tcpParser;
	private UDPParser udpParser;
	private ARPParser arpParser;
	private HTTPParser httpParser;
	// Util
	private ByteArrayUtil byteArrayUtil;
	private TimeUtil timeUtil;
	
	public MyPacketReceiver(NetworkInterface networkInterface) {
		super();
		this.tcpParser = new TCPParser();
		this.ipParser = new IPParser();
		this.ethernetParser = new EthernetParser();
		this.udpParser = new UDPParser();
		this.arpParser = new ARPParser();
		this.httpParser = new HTTPParser();
		this.frameParser = new FrameParser(networkInterface);
		this.byteArrayUtil = new ByteArrayUtil();
		this.timeUtil = new TimeUtil();
	}
	
	@Override
	public void receivePacket(Packet packet) {
		if (packet.getClass().equals(IPPacket.class)){//如果是IP协议
			
			protocolCount++;
			StringBuffer buffer = new StringBuffer();
    		//数据链路帧解析
			buffer.append(frameParser.parse(packet));
    		//以太网解析
			buffer.append(ethernetParser.parse(packet));
    		//IP解析
			buffer.append(ipParser.parse(packet));
			//存储解析结果
			ProtocolRecord protocolRecord = new ProtocolRecord();
			protocolRecord.setProtocolName("IP");
			protocolRecord.setId(protocolCount);
			protocolRecord.setTime(timeUtil.exchang(packet.sec));
			protocolRecord.setLength(String.valueOf(packet.len));
			protocolRecord.setSource(((IPPacket)packet).src_ip.getHostAddress());
			protocolRecord.setDestination(((IPPacket)packet).dst_ip.getHostAddress());
			protocolRecord.setEasy_info(((IPPacket)packet).toString());
			protocolRecord.setDiff_info(buffer.toString());
			protocolRecordMap.put(protocolCount, protocolRecord);
			//加入表格
			MyJFrame.protocolAddData(protocolRecord);
        }  
		else if (packet.getClass().equals(ARPPacket.class)){  //如果是ARP协议
			protocolCount++;
			StringBuffer buffer = new StringBuffer();
			//数据链路帧解析
			buffer.append(frameParser.parse(packet));
    		//以太网解析
			buffer.append(ethernetParser.parse(packet));
			//ARP协议解析
			buffer.append(arpParser.parse(packet));
			//存储解析结果
			ProtocolRecord protocolRecord = new ProtocolRecord();
			protocolRecord.setProtocolName("ARP");
			protocolRecord.setId(protocolCount);
			protocolRecord.setTime(timeUtil.exchang(packet.sec));
			protocolRecord.setLength(String.valueOf(packet.len));
			protocolRecord.setSource(((ARPPacket)packet).getSenderProtocolAddress().toString());
			protocolRecord.setDestination(((ARPPacket)packet).getTargetProtocolAddress().toString());
			protocolRecord.setEasy_info(arpParser.arpEasyInfo(packet));
			protocolRecord.setDiff_info(buffer.toString());
			protocolRecordMap.put(protocolCount, protocolRecord);
			//加入表格
			MyJFrame.protocolAddData(protocolRecord);
        }
        else if (packet.getClass().equals(UDPPacket.class)){
        	protocolCount++;
        	StringBuffer buffer = new StringBuffer();
			//数据链路帧解析
			buffer.append(frameParser.parse(packet));
    		//以太网解析
			buffer.append(ethernetParser.parse(packet));
    		//IP解析
			buffer.append(ipParser.parse(packet));
			//UDP解析
			buffer.append(udpParser.parse(packet));
			//存储解析结果
			ProtocolRecord protocolRecord = new ProtocolRecord();
			protocolRecord.setProtocolName("UDP");
			protocolRecord.setId(protocolCount);
			protocolRecord.setTime(timeUtil.exchang(packet.sec));
			protocolRecord.setLength(String.valueOf(packet.len));
			protocolRecord.setSource(((IPPacket)packet).src_ip.getHostAddress());
			protocolRecord.setDestination(((IPPacket)packet).dst_ip.getHostAddress());
			protocolRecord.setEasy_info(udpParser.udpEasyInfo(packet));
			protocolRecord.setDiff_info(buffer.toString());
			protocolRecordMap.put(protocolCount, protocolRecord);
			//加入表格
			MyJFrame.protocolAddData(protocolRecord);
		}
        else if (packet.getClass().equals(TCPPacket.class)){
        	protocolCount++;
        	StringBuffer buffer = new StringBuffer();
    		//数据链路帧解析
			buffer.append(frameParser.parse(packet));
    		//以太网解析
			buffer.append(ethernetParser.parse(packet));
    		//IP解析
			buffer.append(ipParser.parse(packet));
    		//TCP解析
			buffer.append(tcpParser.parser(packet));
			//存储解析结果
			ProtocolRecord protocolRecord = new ProtocolRecord();
			protocolRecord.setProtocolName("TCP");
			protocolRecord.setDiff_info(buffer.toString());
			protocolRecord.setId(protocolCount);
			protocolRecord.setTime(timeUtil.exchang(packet.sec));
			protocolRecord.setLength(String.valueOf(packet.len));
			protocolRecord.setSource(((IPPacket)packet).src_ip.getHostAddress());
			protocolRecord.setDestination(((IPPacket)packet).dst_ip.getHostAddress());
			protocolRecord.setEasy_info(tcpParser.tcpEasyInfo(packet));
			//如果是HTTP协议，解析HTTP协议
			if(byteArrayUtil.hexStr2Str(DatatypeConverter.printHexBinary(packet.data)).contains("HTTP")) {
				//buffer.append();
				protocolRecord.setDiff_info(buffer.toString() + httpParser.parse(packet));
				protocolRecord.setProtocolName("HTTP");
			}
			//加入Map
			protocolRecordMap.put(protocolCount, protocolRecord);
			//加入表格
			MyJFrame.protocolAddData(protocolRecord);
			
		}
        else if (packet.getClass().equals(ICMPPacket.class)){
        	
		}
		
	}
	
	
	
}
