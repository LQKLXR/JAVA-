package com.swing.listener;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.receiver.MyPacketReceiver;
import com.swing.MyJFrame;

/**
 * 	选中表格某行的事件监听器
 * @author LiuQiankun
 *
 */
public class MyListSelectionListener implements ListSelectionListener{
	
	private JTable table;
	
	public MyListSelectionListener(JTable table, JTextArea textArea) {
		super();
		this.table = table;
	}

	

	@Override
	public void valueChanged(ListSelectionEvent listselectionevent) {
		// 获取选中的行
		int selectedRow = table.getSelectedRow();
		//根据选中的行号（表格从0开始，我设置的Map从1开始，故+1），找出详细信息
		String diff_info = MyPacketReceiver.protocolRecordMap.get(new Integer(selectedRow+1)).getDiff_info();
		//显示在面板上
		MyJFrame.diff_info.setText(diff_info);
		
	}
	

}
