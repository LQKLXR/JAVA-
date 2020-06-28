package com.thread;

import java.io.IOException;

import com.receiver.MyPacketReceiver;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

/**
 *  	����Э��������߳�
 * @author LiuQiankun
 *
 */
public class ProtocolAnalysisThread extends Thread{
	
	
	@Override
	public void run() {
		
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();//��ȡ�����б�
		for (NetworkInterface n : devices) {
			System.out.println(n.name + "     |     " + n.description);
		}
		System.out.println();
		JpcapCaptor jpcap = null;
		int caplen = 65532;//ÿ�β������󳤶�
		boolean promiscCheck = true;
		try {
			jpcap = JpcapCaptor.openDevice(devices[0], caplen, promiscCheck, 50);//�󶨸�����
		} catch (IOException e) {
			e.printStackTrace();
		}
		jpcap.loopPacket(-1, new MyPacketReceiver(devices[0]));//��ʼѭ������������������MyPacketReceiverʵ��
		super.run();
	}
}
