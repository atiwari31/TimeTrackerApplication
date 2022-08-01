package com.dcardprocessing.service.impl;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dcardprocessing.util.GlobalKeyListener;
import com.dcardprocessing.util.KeyboardActivityTask;
import com.dcardprocessing.util.ScreenCaptureTask;

@Component
public class ImageCaptureServiceImpl extends Thread{
	 private Thread thread;
     private String threadName;
	
	static int  count=0;
	
	@Autowired
	private DesktopService desktopService;
	
	@Autowired
	private ImageService imageService;
	

	public ScheduledExecutorService  callScreen() throws InterruptedException, AWTException, IOException {
		
		
		 ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		 ScreenCaptureTask task1 = new ScreenCaptureTask(imageService);
		     
		    System.out.println("The time is : " + new Date());
		    ScheduledFuture<?> result = executor.scheduleAtFixedRate(task1, 5, 5, TimeUnit.SECONDS);
		    //executor.schedule(task1, 5 , TimeUnit.SECONDS);
		     
		    try {
		    	TimeUnit.MILLISECONDS.sleep(2000);
		    } catch (InterruptedException e) {
		          e.printStackTrace();
		    }
		 return executor;
	}

	public void  keyboardActivity() throws InterruptedException, AWTException, IOException {
		
		
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
}
