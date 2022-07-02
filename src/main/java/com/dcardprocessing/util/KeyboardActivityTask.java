package com.dcardprocessing.util;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.python.core.Py;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;

import com.dcardprocessing.service.impl.DesktopService;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

public class KeyboardActivityTask implements Runnable{
	
	
	@Autowired
	private DesktopService desktopService;
	
	public KeyboardActivityTask(DesktopService desktopService) {
		this.desktopService=desktopService;
		// TODO Auto-generated constructor stub
	}
	
	



	@Override
	public void run() {
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}
          
		
		GlobalScreen.addNativeKeyListener(new GlobalKeyListener(desktopService));
	
	}

	
	
//	
}
