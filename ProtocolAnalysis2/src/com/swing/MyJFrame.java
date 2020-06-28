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
 * 	主显示窗口
 * @author LiuQiankun
 *
 */
public class MyJFrame {
	
	//数据包详细信息
	public static JTextArea diff_info = new JTextArea();
	//IP流量统计-文本总统计信息
	public static JTextArea flow_info = new JTextArea();
	//协议表格首部信息
	public static String[] protocol_ColumnNames = {"序号", "时间", "Source", "Destionation", "协议名称", "长度", "信息"};
	//协议表格数据信息
	public static DefaultTableModel protocol_TableModel = new DefaultTableModel(null, protocol_ColumnNames) {
		private static final long serialVersionUID = 1L;
		@Override
		public boolean isCellEditable(int i, int j) {
			// TODO Auto-generated method stub
			return false;
		}
	};
	//ip表格首部信息
	public static String[] ip_ColumnNames = {"序号", "Source", "Destionation", "类型", "次数", "总长度（byte）"};
	//ip表格数据信息
	public static DefaultTableModel ip_TableModel = new DefaultTableModel(null, ip_ColumnNames) {
		private static final long serialVersionUID = 1L;
		@Override
		public boolean isCellEditable(int i, int j) {
			// TODO Auto-generated method stub
			return false;
		}
	};
	
	
	
	/**
	 * 窗口
	 * @return
	 */
	public static JFrame getFrame() {
		// TODO Auto-generated method stub
		JFrame jf = new JFrame("流量统计 软工1701刘乾坤");
        jf.setSize(1500, 960);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        //第一个选项卡
        JPanel left_Panel = new JPanel(new GridLayout(2, 1));
        diff_info.setText("详细信息");
        diff_info.setEditable(false);
        diff_info.setForeground(Color.BLACK);                   // 字体颜色
        diff_info.setFont(new Font(null, Font.PLAIN, 20));      // 字体样式
        
        JTable protocol_Table = protocolJTable();
        //第一个选项卡的两个滚动窗口
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
        //每次只能选一行
		ListSelectionModel selectionModel = protocol_Table.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// 添加选择模型监听器（选中时，显示协议具体信息
		selectionModel.addListSelectionListener(new MyListSelectionListener(protocol_Table, diff_info));
        left_Panel.add(upPanel);
        left_Panel.add(downPanel);
        //第二个选项卡------------------------------------------------------------------------------
        JPanel right_Panel = new JPanel(new BorderLayout());
        flow_info.setText("流量统计\n上传流量：\n下载流量：");
        flow_info.setEditable(false);
        flow_info.setForeground(Color.BLACK);                   // 字体颜色
        flow_info.setFont(new Font(null, Font.PLAIN, 20));      // 字体样式
        //文本Panel
        JScrollPane right_Up_Panel = new JScrollPane(
        		flow_info,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        
        //表格Panel
        JTable ip_Table = ipJTable();
        JScrollPane ipPanel = new JScrollPane(
        		ip_Table,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );
        //每次只能选一行
      	ListSelectionModel selectionModel2 = ip_Table.getSelectionModel();
      	selectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      	right_Panel.add(right_Up_Panel, BorderLayout.NORTH);
      	right_Panel.add(ipPanel, BorderLayout.CENTER);
      	//------------------------------------------------------------------------------------------
      	//选项面板建立
        JTabbedPane tabbedPane = new JTabbedPane();
        //加入两个选项卡
        tabbedPane.addTab("抓包数据", left_Panel);
        tabbedPane.addTab("流量统计", right_Panel);
        
        jf.setContentPane(tabbedPane);
        jf.setVisible(true);        // PS: 最后再设置为可显示(绘制), 所有添加的组件才会显示
        return jf;
	}
	
	
	/**
	 * 	协议表格
	 * @return
	 */
	public static JTable protocolJTable() {
		
		// 创建一个表格，指定 表头 和 所有行数据
        JTable table = new JTable(protocol_TableModel);
        
        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色

        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        // 设置行高
        table.setRowHeight(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getTableHeader().setResizingAllowed(true);
        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
       // table.setPreferredScrollableViewportSize(new Dimension(400, 300));
		return table;
        
	}
	
	
	/**
	 * 	给协议表格增加一个数据
	 * @param protocolRecord
	 */
	public static void protocolAddData(ProtocolRecord protocolRecord) {
		//把解析结果放入Table数据中显示
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
	 * 	协议表格
	 * @return
	 */
	public static JTable ipJTable() {
		
		// 创建一个表格，指定 表头 和 所有行数据
        JTable table = new JTable(ip_TableModel);
        
        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色

        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        // 设置行高
        table.setRowHeight(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getTableHeader().setResizingAllowed(true);
        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
       // table.setPreferredScrollableViewportSize(new Dimension(400, 300));
		return table;
        
	}
	
	
	/**
	 * 	给协议表格增加一个数据
	 * @param protocolRecord
	 */
	public static void ipAddData(FlowRecorder flowRecorder) {
		//把解析结果放入Table数据中显示
		Object[] objects = {flowRecorder.getId(),
							flowRecorder.getSource(),
							flowRecorder.getDestination(),
							flowRecorder.getKind(),
							flowRecorder.getPacketCount(),
							flowRecorder.getLength()};
		MyJFrame.ip_TableModel.addRow(objects);
	}
}
