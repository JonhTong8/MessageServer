package com.qq.common;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Warning {

	public void showInfo(String info) {
		JOptionPane jop = new JOptionPane();
    	JOptionPane.showMessageDialog(new JTextArea(), info, "Warning！", JOptionPane.WARNING_MESSAGE );
	}
}

