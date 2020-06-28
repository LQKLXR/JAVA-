package com.thread;

import com.swing.MyJFrame;

public class JavaSwingThread extends Thread{

	@Override
	public void run() {
		MyJFrame.getFrame();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.run();
	}
}
