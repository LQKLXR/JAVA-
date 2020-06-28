package com.thread;

import java.io.IOException;

import com.receiver.MyPacketReceiver;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;

/**
 *  	控制协议解析的线程
 * @author LiuQiankun
 *
 */
public class ProtocolAnalysisThread extends Thread{
	
	
	@Override
	public void run() {
		
		NetworkInterface[] devices = JpcapCaptor.getDeviceList();//获取网卡列表
		for (NetworkInterface n : devices) {
			System.out.println(n.name + "     |     " + n.description);
		}
		System.out.println();
		JpcapCaptor jpcap = null;
		int caplen = 65532;//每次捕获的最大长度
		boolean promiscCheck = true;
		try {
			jpcap = JpcapCaptor.openDevice(devices[0], caplen, promiscCheck, 50);//绑定该网卡
		} catch (IOException e) {
			e.printStackTrace();
		}
		jpcap.loopPacket(-1, new MyPacketReceiver(devices[0]));//开始循环监听，监听函数在MyPacketReceiver实现
		super.run();
	}
}
