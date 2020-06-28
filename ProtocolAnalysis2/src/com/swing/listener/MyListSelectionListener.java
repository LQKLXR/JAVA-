package com.swing.listener;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.receiver.MyPacketReceiver;
import com.swing.MyJFrame;

/**
 * 	ѡ�б��ĳ�е��¼�������
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
		// ��ȡѡ�е���
		int selectedRow = table.getSelectedRow();
		//����ѡ�е��кţ�����0��ʼ�������õ�Map��1��ʼ����+1�����ҳ���ϸ��Ϣ
		String diff_info = MyPacketReceiver.protocolRecordMap.get(new Integer(selectedRow+1)).getDiff_info();
		//��ʾ�������
		MyJFrame.diff_info.setText(diff_info);
		
	}
	

}
