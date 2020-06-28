package com.parser;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.receiver.MyPacketReceiver;
import com.record.FlowRecorder;
import com.swing.MyJFrame;
import com.util.ProtocolUtil;

import jpcap.packet.IPPacket;
import jpcap.packet.Packet;

public class IPParser {

	private ProtocolUtil protocolUtil = new ProtocolUtil();
	
	public String parse(Packet packet) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("--------------------------------------").append("\n");
		buffer.append("IPЭ���������").append("\n");
		buffer.append("--------------------------------------").append("\n");
		
		Map<String, String> ipMap = new LinkedHashMap<String, String>(32);
		IPPacket ipPacket = (IPPacket) packet;
		ipMap.put("Protocol Name: ", "Internet Protocol");
		ipMap.put("Version: ", String.valueOf(ipPacket.version));
		ipMap.put("Source ip: ", String.valueOf(ipPacket.src_ip.getHostAddress()));
		ipMap.put("Destination ip: ", String.valueOf(ipPacket.dst_ip.getHostAddress()));
		short ip_OptionLength = 0;
		if(ipPacket.option == null) {
			ip_OptionLength = 0;
		}
		ipMap.put("Header Length: ", String.valueOf(ip_OptionLength + 20));
		ipMap.put("Priority: ", String.valueOf(ipPacket.priority));
		ipMap.put("Total Length: ", String.valueOf(ipPacket.length));
		ipMap.put("Identification: ", String.valueOf(ipPacket.ident));
		ipMap.put("Flags begin: ", "��");
		ipMap.put("Reserved bit: ", String.valueOf(ipPacket.rsv_frag));
		ipMap.put("Don't fragment: ", String.valueOf(ipPacket.dont_frag));
		ipMap.put("More fragment: ", String.valueOf(ipPacket.more_frag));
		ipMap.put("Flags end: ", "��");
		ipMap.put("Fragment offest: ", String.valueOf(ipPacket.offset));
		ipMap.put("Time to live: ", String.valueOf(ipPacket.hop_limit));
		ipMap.put("Protocol: ", protocolUtil.exchange(ipPacket.protocol));
		for(Map.Entry<String, String> e : ipMap.entrySet()) {
			buffer.append(e.getKey()+e.getValue()).append("\n");
		}
		
		//�������֮�������
		//�ȴӱ�����
		FlowRecorder flowRecorder = FlowRecorder.getRecorderByIp(MyPacketReceiver.ipRecorders,
				ipMap.get("Source ip: "),ipMap.get("Destination ip: "));
		//�����ǰ�����û���������
		if(flowRecorder == null) {
			String ip4 = "192.168.1.103";
			String kind = null;
			if(ipMap.get("Source ip: ").toString().equals(ip4)){
				//������IP���ݰ��Ǳ���������
				kind = "�ϴ�";
				System.out.println(222222);
			}
			else {
				kind = "����";
				
			}
			System.out.println(ipMap.get("Source ip: ").toString());
			MyPacketReceiver.ipCount ++;
			flowRecorder = new FlowRecorder(MyPacketReceiver.ipCount,
					ipMap.get("Source ip: "), ipMap.get("Destination ip: "), 1, kind, ipPacket.length);
			MyJFrame.ipAddData(flowRecorder);
			MyPacketReceiver.ipRecorders.add(flowRecorder);
		}
		//�����ǰ������Ѿ����������
		else {
			flowRecorder = FlowRecorder.getRecorderByIp(MyPacketReceiver.ipRecorders,
					ipMap.get("Source ip: "),ipMap.get("Destination ip: "));
			flowRecorder.setLength(flowRecorder.getLength() + ipPacket.length);
			flowRecorder.setPacketCount(flowRecorder.getPacketCount() + 1);
			MyJFrame.ip_TableModel.setValueAt(flowRecorder.getPacketCount(), flowRecorder.getId() - 1, 4);
			MyJFrame.ip_TableModel.setValueAt(flowRecorder.getLength(), flowRecorder.getId() - 1, 5);
			
		}
		if(flowRecorder.getKind().contains("�ϴ�")) {
			MyPacketReceiver.upload_Flow = MyPacketReceiver.upload_Flow + ipPacket.length;
		}
		else {
			MyPacketReceiver.download_Flow = MyPacketReceiver.download_Flow + ipPacket.length;
		}
		MyJFrame.flow_info.setText("����ͳ��\n�ϴ�������"
									+ String.valueOf(MyPacketReceiver.upload_Flow)
									+" (" + String.valueOf(MyPacketReceiver.upload_Flow/1024) + "K ) "
									+ "\n����������"
									+ String.valueOf(MyPacketReceiver.download_Flow)
									+" (" + String.valueOf(MyPacketReceiver.download_Flow/1024) + "K ) "
									+"\n");
		
		
		return buffer.toString();
	}
}
