package com.main;

import com.thread.JavaSwingThread;
import com.thread.ProtocolAnalysisThread;

public class Main {

	public static void main(String[] args) {
		
		//���Э������߳�
		Thread protocolAnalysisThread = new ProtocolAnalysisThread();
		//ǰ����ʾ�߳�
		Thread javaSwingThread = new JavaSwingThread();
		protocolAnalysisThread.start();
		javaSwingThread.start();
		
		
	}

}
