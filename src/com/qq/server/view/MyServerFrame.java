/**
 * 这是服务器端的控制界面，可以完成启动服务器，关闭服务器，可以管理和监控用户
 */
package com.qq.server.view;

import javax.swing.*;

import com.qq.server.model.*;

import java.awt.*;
import java.awt.event.*;

public class MyServerFrame extends JFrame implements ActionListener{

	JPanel jp1;
	JButton jb1,jb2;
	MyQqServer myQqServer =  null;
	
	public static void main(String[] args) {
		MyServerFrame msf = new MyServerFrame();
	}
	
	public MyServerFrame () {
		jp1 = new JPanel();
		jb1 = new JButton ("启动服务器");
		jb1.addActionListener(this);
		jb2 = new JButton ("关闭服务器");
		jb2.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		
		
		this.add(jp1);
		this.setSize(600,450);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jb1) {
			try {
				System.out.println("启动服务器");
				 myQqServer = new MyQqServer();
				
			} finally{
				
			}
		}else if(e.getSource() == jb2) {
			System.out.println("shutdown the server!");
			if (myQqServer != null) {
				myQqServer.stopMyQqServer();
				this.dispose();
			}
		}
	}
}
