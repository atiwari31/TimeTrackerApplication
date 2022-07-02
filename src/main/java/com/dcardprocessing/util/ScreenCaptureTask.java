package com.dcardprocessing.util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dcardprocessing.bean.ImageEntity;
import com.dcardprocessing.service.impl.ImageService;
import com.dcardprocessing.service.impl.ImageServiceImpl;
@Component
public class ScreenCaptureTask implements  Runnable {
	static int  count=0;
	@Autowired
	ImageService imageService;
	LocalDateTime dateTime = LocalDateTime.now();
	public ScreenCaptureTask(ImageService imageService) {
		// TODO Auto-generated constructor stub
		this.imageService=imageService;
	}


	@Override
	public void run() {
		  
		  ImageEntity imageEntity=new ImageEntity();
		// TODO Auto-generated method stub
		count++;
		Robot r;
		try {
			r = new Robot();
			// It saves screenshot to desired path
			String path = "F://sc1/tst"+count+".jpg";

			// Used to get ScreenSize and capture image
			Rectangle capture =
			new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage Image = r.createScreenCapture(capture);
			ImageIO.write(Image, "jpg", new File(path));
			imageEntity.setImage(path);
			imageEntity.setCreatedDate(dateTime.toString());
			System.out.println("Screenshot saved");
			imageService.save(imageEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
}
