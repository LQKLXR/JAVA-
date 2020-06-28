package com.main;

import com.thread.JavaSwingThread;
import com.thread.ProtocolAnalysisThread;

public class Main {

	public static void main(String[] args) {
		
		//后端协议分析线程
		Thread protocolAnalysisThread = new ProtocolAnalysisThread();
		//前端显示线程
		Thread javaSwingThread = new JavaSwingThread();
		protocolAnalysisThread.start();
		javaSwingThread.start();
		
		
	}

}
