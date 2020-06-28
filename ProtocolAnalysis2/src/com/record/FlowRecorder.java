package com.record;

import java.util.ArrayList;

/**
 * IP流量记录，对应IP流量JTable的一行
 * @author LiuQiankun
 *
 */
public class FlowRecorder {
	
	private Integer id;
	private String source;
	private String destination;
	private int packetCount;
	private String kind;//上传，下载
	private long length;
	
	public FlowRecorder(Integer id, String source, String destination, int packetCount, String kind, long length) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.packetCount = packetCount;
		this.kind = kind;
		this.length = length;
	}

	/**
	 * 	根据前后IP得到具体的对象
	 * @param recorders
	 * @param src
	 * @param dst
	 * @return
	 */
	public static FlowRecorder getRecorderByIp(ArrayList<FlowRecorder> recorders,String src, String dst) {
		for(FlowRecorder e : recorders) {
			if(e.getSource().equals(src) && e.getDestination().equals(dst)) {
				return e;
			}
		}
		return null;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public int getPacketCount() {
		return packetCount;
	}

	public void setPacketCount(int packetCount) {
		this.packetCount = packetCount;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}
	
	
	
}
