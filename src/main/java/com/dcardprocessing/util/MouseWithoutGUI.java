package com.dcardprocessing.util;

import java.awt.MouseInfo;

public class MouseWithoutGUI {

	 public static void main(String[] args) throws Exception {
	        while (true) {
	            System.out.println(MouseInfo.getPointerInfo().getLocation().x);
	            try {
	                Thread.sleep(10);
	                break;
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
