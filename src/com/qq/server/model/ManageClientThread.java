/**
 * 管理客户端线程
 */
package com.qq.server.model;
import java.util.*;

public class ManageClientThread {

	public static HashMap hm = new HashMap<String,SerConClientThread>();
	
	public static void  addClientThread(String uid,SerConClientThread ct) {
		//向hm中添加一个客户端通讯线程
		hm.put(uid, ct);
	}
	public static SerConClientThread removeClientThread(String uid) {
		//remove process from hm
		return (SerConClientThread)hm.remove(uid);
	}
	
	public static SerConClientThread getClientThread(String uid) {
		return (SerConClientThread)hm.get(uid);
	}
	
	//返回在线好友信息
	public static String getAllOnlineUserId() {
		//使用迭代器完成
		Iterator it = hm.keySet().iterator();
		String res="";
		while(it.hasNext()) {
			res+=it.next().toString()+" ";
		}
		return res;
	}
}
