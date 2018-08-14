/**
 * 定义信息包的种类
 */
package com.qq.common;

public interface MessageType {

	String message_login_succeed="1";//表明登陆成功
	String message_login_failed="2";//表明登陆失败
	String message_comm_mes="3";//普通信息包
	String message_get_onLineFriend="4";//请求获得在线好友信息包
	String message_ret_onLineFriend="5";//返回在线好友信息包
	String message_logout="6";//退出登录信息包
	String message_send_file="7";//发送文件
	String message_get_file="8";//同意获取文件
	String message_image="9";//发送图片
}
