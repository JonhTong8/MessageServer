/**
 * 这是QQ服务器，监听某个qq客户端，来连接
 */
package com.qq.server.model;
import com.qq.common.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class MyQqServer implements Runnable{
	private ServerSocket ss = null;
	private Thread t = null;
	public MyQqServer()  {
		
		//在10001端口监听
		try {
			ss = new ServerSocket(10001);
			t = new Thread(this);
			t.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void stopMyQqServer() {
		t.interrupt();
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {

			//阻塞，等待连接
			while(!t.isInterrupted()) {
				Socket s = ss.accept();
				System.out.println("accpeted");
				//接收客户端发来的信息
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User)ois.readObject();
				
				Message ms = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				if(u.getPasswd().equals("jonhtong")) {
					ms.setMesType("1");	
					oos.writeObject(ms);
					//如果客户端登录成功，则新启动一个线程 保持监听
					SerConClientThread scct = new SerConClientThread(s);
					ManageClientThread.addClientThread(u.getUserId(), scct);
					//启动与该客户端通讯的线程
					scct.start();
					
					//通知其他在线用户
					scct.notifyOther(u.getUserId());
					
				}else {
					ms.setMesType("2");
					oos.writeObject(ms);
					//密码错误，关闭链接
					s.close();
				}
				
				
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
