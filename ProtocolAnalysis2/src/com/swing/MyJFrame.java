package com.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.record.FlowRecorder;
import com.record.ProtocolRecord;
import com.swing.listener.MyListSelectionListener;

/**
 * 	����ʾ����
 * @author LiuQiankun
 *
 */
public class MyJFrame {
	
	//���ݰ���ϸ��Ϣ
	public static JTextArea diff_info = new JTextArea();
	//IP����ͳ��-�ı���ͳ����Ϣ
	public static JTextArea flow_info = new JTextArea();
	//Э�����ײ���Ϣ
	public static String[] protocol_ColumnNames = {"���", "ʱ��", "Source", "Destionation", "Э������", "����", "��Ϣ"};
	//Э����������Ϣ
	public static DefaultTableModel protocol_TableModel = new DefaultTableModel(null, protocol_ColumnNames) {
		private static final long serialVersionUID = 1L;
		@Override
		public boolean isCellEditable(int i, int j) {
			// TODO Auto-generated method stub
			return false;
		}
	};
	//ip����ײ���Ϣ
	public static String[] ip_ColumnNames = {"���", "Source", "Destionation", "����", "����", "�ܳ��ȣ�byte��"};
	//ip���������Ϣ
	public static DefaultTableModel ip_TableModel = new DefaultTableModel(null, ip_ColumnNames) {
		private static final long serialVersionUID = 1L;
		@Override
		public boolean isCellEditable(int i, int j) {
			// TODO Auto-generated method stub
			return false;
		}
	};
	
	
	
	/**
	 * ����
	 * @return
	 */
	public static JFrame getFrame() {
		// TODO Auto-generated method stub
		JFrame jf = new JFrame("����ͳ�� ��1701��Ǭ��");
        jf.setSize(1500, 960);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        //��һ��ѡ�
        JPanel left_Panel = new JPanel(new GridLayout(2, 1));
        diff_info.setText("��ϸ��Ϣ");
        diff_info.setEditable(false);
        diff_info.setForeground(Color.BLACK);                   // ������ɫ
        diff_info.setFont(new Font(null, Font.PLAIN, 20));      // ������ʽ
        
        JTable protocol_Table = protocolJTable();
        //��һ��ѡ���������������
        JScrollPane upPanel = new JScrollPane(
        		protocol_Table,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        JScrollPane downPanel = new JScrollPane(
        		diff_info,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        //ÿ��ֻ��ѡһ��
		ListSelectionModel selectionModel = protocol_Table.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// ���ѡ��ģ�ͼ�������ѡ��ʱ����ʾЭ�������Ϣ
		selectionModel.addListSelectionListener(new MyListSelectionListener(protocol_Table, diff_info));
        left_Panel.add(upPanel);
        left_Panel.add(downPanel);
        //�ڶ���ѡ�------------------------------------------------------------------------------
        JPanel right_Panel = new JPanel(new BorderLayout());
        flow_info.setText("����ͳ��\n�ϴ�������\n����������");
        flow_info.setEditable(false);
        flow_info.setForeground(Color.BLACK);                   // ������ɫ
        flow_info.setFont(new Font(null, Font.PLAIN, 20));      // ������ʽ
        //�ı�Panel
        JScrollPane right_Up_Panel = new JScrollPane(
        		flow_info,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        
        //���Panel
        JTable ip_Table = ipJTable();
        JScrollPane ipPanel = new JScrollPane(
        		ip_Table,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        //ÿ��ֻ��ѡһ��
      	ListSelectionModel selectionModel2 = ip_Table.getSelectionModel();
      	selectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      	right_Panel.add(right_Up_Panel, BorderLayout.NORTH);
      	right_Panel.add(ipPanel, BorderLayout.CENTER);
      	//------------------------------------------------------------------------------------------
      	//ѡ����彨��
        JTabbedPane tabbedPane = new JTabbedPane();
        //��������ѡ�
        tabbedPane.addTab("ץ������", left_Panel);
        tabbedPane.addTab("����ͳ��", right_Panel);
        
        jf.setContentPane(tabbedPane);
        jf.setVisible(true);        // PS: ���������Ϊ����ʾ(����), ������ӵ�����Ż���ʾ
        return jf;
	}
	
	
	/**
	 * 	Э����
	 * @return
	 */
	public static JTable protocolJTable() {
		
		// ����һ�����ָ�� ��ͷ �� ����������
        JTable table = new JTable(protocol_TableModel);
        
        // ���ñ��������ɫ
        table.setForeground(Color.BLACK);                   // ������ɫ
        table.setFont(new Font(null, Font.PLAIN, 14));      // ������ʽ
        table.setSelectionForeground(Color.DARK_GRAY);      // ѡ�к�������ɫ
        table.setSelectionBackground(Color.LIGHT_GRAY);     // ѡ�к����屳��
        table.setGridColor(Color.GRAY);                     // ������ɫ

        // ���ñ�ͷ
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // ���ñ�ͷ����������ʽ
        table.getTableHeader().setForeground(Color.RED);                // ���ñ�ͷ����������ɫ
        table.getTableHeader().setResizingAllowed(false);               // ���ò������ֶ��ı��п�
        table.getTableHeader().setReorderingAllowed(false);             // ���ò������϶������������

        // �����и�
        table.setRowHeight(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getTableHeader().setResizingAllowed(true);
        // ���ù�������ӿڴ�С�������ô�С�������ݣ���Ҫ�϶����������ܿ�����
       // table.setPreferredScrollableViewportSize(new Dimension(400, 300));
		return table;
        
	}
	
	
	/**
	 * 	��Э��������һ������
	 * @param protocolRecord
	 */
	public static void protocolAddData(ProtocolRecord protocolRecord) {
		//�ѽ����������Table��������ʾ
		Object[] objects = {protocolRecord.getId(),
							protocolRecord.getTime(),
							protocolRecord.getSource(),
							protocolRecord.getDestination(),
							protocolRecord.getProtocolName(),
							protocolRecord.getLength(),
							protocolRecord.getEasy_info()};
		MyJFrame.protocol_TableModel.addRow(objects);
	}
	
	/**
	 * 	Э����
	 * @return
	 */
	public static JTable ipJTable() {
		
		// ����һ�����ָ�� ��ͷ �� ����������
        JTable table = new JTable(ip_TableModel);
        
        // ���ñ��������ɫ
        table.setForeground(Color.BLACK);                   // ������ɫ
        table.setFont(new Font(null, Font.PLAIN, 14));      // ������ʽ
        table.setSelectionForeground(Color.DARK_GRAY);      // ѡ�к�������ɫ
        table.setSelectionBackground(Color.LIGHT_GRAY);     // ѡ�к����屳��
        table.setGridColor(Color.GRAY);                     // ������ɫ

        // ���ñ�ͷ
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // ���ñ�ͷ����������ʽ
        table.getTableHeader().setForeground(Color.RED);                // ���ñ�ͷ����������ɫ
        table.getTableHeader().setResizingAllowed(false);               // ���ò������ֶ��ı��п�
        table.getTableHeader().setReorderingAllowed(false);             // ���ò������϶������������

        // �����и�
        table.setRowHeight(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getTableHeader().setResizingAllowed(true);
        // ���ù�������ӿڴ�С�������ô�С�������ݣ���Ҫ�϶����������ܿ�����
       // table.setPreferredScrollableViewportSize(new Dimension(400, 300));
		return table;
        
	}
	
	
	/**
	 * 	��Э��������һ������
	 * @param protocolRecord
	 */
	public static void ipAddData(FlowRecorder flowRecorder) {
		//�ѽ����������Table��������ʾ
		Object[] objects = {flowRecorder.getId(),
							flowRecorder.getSource(),
							flowRecorder.getDestination(),
							flowRecorder.getKind(),
							flowRecorder.getPacketCount(),
							flowRecorder.getLength()};
		MyJFrame.ip_TableModel.addRow(objects);
	}
}
