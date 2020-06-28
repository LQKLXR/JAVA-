package com.record;

/**
 * 	捕获到的每个数据包的记录，对应抓包JTable中的一行
 * @author LiuQiankun
 *
 */
public class ProtocolRecord {
	//除了ID全是显示在JTable的表格的String
	private Integer id;
	private String time;
	private String source;
	private String destination;
	private String protocolName;
	private String length;
	private String easy_info;
	private String diff_info;
	
	public ProtocolRecord(Integer id, String time, String source, String destination, String protocolName, String length,
			String easy_info, String diff_info) {
		super();
		this.id = id;
		this.time = time;
		this.source = source;
		this.destination = destination;
		this.protocolName = protocolName;
		this.length = length;
		this.easy_info = easy_info;
		this.diff_info = diff_info;
	}
	
	public ProtocolRecord() {
		super();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getProtocolName() {
		return protocolName;
	}
	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getEasy_info() {
		return easy_info;
	}
	public void setEasy_info(String easy_info) {
		this.easy_info = easy_info;
	}
	public String getDiff_info() {
		return diff_info;
	}
	public void setDiff_info(String diff_info) {
		this.diff_info = diff_info;
	}
	
	
	
	
}
