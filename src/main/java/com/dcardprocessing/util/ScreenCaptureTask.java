package com.dcardprocessing.util;

import static com.dcardprocessing.controller.LoginController.id;
import static com.dcardprocessing.controller.LoginController.tokenSession;

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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dcardprocessing.bean.ImageEntity;
import com.dcardprocessing.model.KeyboardDetail;
import com.dcardprocessing.model.ScreenShotDetail;
import com.dcardprocessing.service.impl.ImageService;
import com.dcardprocessing.service.impl.ImageServiceImpl;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
@Component
public class ScreenCaptureTask implements  Runnable {
	static int  count=0;
	public static int  moduleScreenId=0;
	@Autowired
	ImageService imageService;
	
	public ScreenCaptureTask(ImageService imageService) {
		// TODO Auto-generated constructor stub
		this.imageService=imageService;
	}


	@Override
	public void run() {
		LocalDateTime dateTime = LocalDateTime.now();
		  ImageEntity imageEntity=new ImageEntity();
		// TODO Auto-generated method stub
		count++;
		Robot r;
		try {
			r = new Robot();
			// It saves screenshot to desired path
			String path = "C://sc1/tst"+count+".jpg";

			// Used to get ScreenSize and capture image
			Rectangle capture =	new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage Image = r.createScreenCapture(capture);
			//ImageIO.write(Image, "jpg", new File(path));
			//imageEntity.setImage(path);
			//imageEntity.setCreatedDate(dateTime.toString());
			savedScreenShot(Image);
			System.out.println("Screenshot saved");
			//imageService.save(imageEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void savedScreenShot(BufferedImage Image) {
		String url = "http://65.0.2.180/api/freelancer/saveScreenshot";
    	HttpHeaders headers = new HttpHeaders();
    	ScreenShotDetail screenShotDetail= new ScreenShotDetail();
		RestTemplate restTemplate = new RestTemplate();
		screenShotDetail.setFreelancer_id(id);
		screenShotDetail.setToken(tokenSession);
		screenShotDetail.setimage(Base64.encode(Image.toString().getBytes()).getBytes());
		System.out.println(Base64.encode(Image.toString().getBytes()));
		screenShotDetail.setModule_id(moduleScreenId);
		HttpEntity<ScreenShotDetail> entity = new HttpEntity<ScreenShotDetail>(screenShotDetail, headers);
		String response=restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		System.out.println("Screenshot saved"+response);
	}
}
