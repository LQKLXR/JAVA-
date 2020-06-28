# JAVA-
计算机网络抓包和流量统计
随便做的，比较匆忙，没有设置选择网卡和IP，需要手动写好
需要提前配好WinpCap和JpCap
修改IPParser.java中的IP字符串
//如果当前表格里没有这个数据
		if(flowRecorder == null) {
			String ip4 = "192.168.1.103";//修改此处
			String kind = null;
			if(ipMap.get("Source ip: ").toString().equals(ip4)){
				//如果这个IP数据包是本机发出的
				kind = "上传";
				System.out.println(222222);
			}
			else {
				kind = "下载";
				
			}
修改ProtocolAnalysisThread.java绑定的网卡
try {
			jpcap = JpcapCaptor.openDevice(devices[0], caplen, promiscCheck, 50);//绑定该网卡，修改此处数字
		} catch (IOException e) {
			e.printStackTrace();
		}
