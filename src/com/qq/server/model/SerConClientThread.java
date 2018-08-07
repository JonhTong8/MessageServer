/**
 * 服务器和客户端的通信线程
 */
package com.qq.server.model;

import java.net.*;
import java.util.*;

import com.qq.common.Message;
import com.qq.common.*;
import java.io.*;

public class SerConClientThread extends Thread{

	Socket s;
	
	public SerConClientThread(Socket s) {
		this.s = s;
		
	}
	//让该线程去通知其他用户
	public void notifyOther(String iam) {
		//得到所有用户的线程
		HashMap hm = ManageClientThread.hm;
		Iterator it = hm.keySet().iterator();
		
		while(it.hasNext()) {
			//取出在线人员的ID
			String onLineUserId = it.next().toString();
			Message ms = new Message();
			ms.setCon(iam);
			ms.setMesType(MessageType.message_ret_onLineFriend);
			
			try {
				ObjectOutputStream oos =new  ObjectOutputStream(ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
				ms.setGetter(onLineUserId);
				oos.writeObject(ms);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void run() {
		while (true) {
			//这里的线程可以接受客户端的信息
			
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message ms = (Message) ois.readObject();
				
				//System.out.println(ms.getSender()+" 给 "+ms.getGetter()+" 说 "+ms.getCon());
				//转发信息
				//取得接收人的通讯线程
				if(ms.getMesType().equals(MessageType.message_comm_mes)) {
					SerConClientThread sc = ManageClientThread.getClientThread(ms.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(ms);
				}else if (ms.getMesType().equals(MessageType.message_get_onLineFriend)) {
					//把服务器上的好友返还给客户端
					String res = ManageClientThread.getAllOnlineUserId();
					Message m = new Message();
					m.setMesType(MessageType.message_ret_onLineFriend);
					m.setCon(res);
					m.setGetter(ms.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m);
				}else if(ms.getMesType().equals(MessageType.message_logout)) {
					//stop the process
					ManageClientThread.removeClientThread(ms.getSender());
					break;
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
