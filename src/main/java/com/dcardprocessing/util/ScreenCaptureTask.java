package com.dcardprocessing.util;

import static com.dcardprocessing.controller.LoginController.id;
import static com.dcardprocessing.controller.LoginController.tokenSession;
import static com.dcardprocessing.controller.DashboardController.hours_use;
import static com.dcardprocessing.controller.DashboardController.hours_bal;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dcardprocessing.controller.DashboardController;
import com.dcardprocessing.model.LoginTimeDetails;
import com.dcardprocessing.model.ScreenShotDetail;
import com.dcardprocessing.service.impl.ImageService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@Component
public class ScreenCaptureTask implements Runnable {
	static int count = 0;
	public static int moduleScreenId = 0;
	public static int time_id = 0;
	public static int lead_id = 0;
	public static Map<String,String> timeMapLocal=new HashMap();
	
	@Autowired
	ImageService imageService;

	public ScreenCaptureTask(ImageService imageService) {
		// TODO Auto-generated constructor stub
		this.imageService = imageService;
	}
	

	@Override
	public void run() {

		// TODO Auto-generated method stub
		count++;
		Robot r;
		try {
			r = new Robot();
			// It saves screenshot to desired path
			String path = "C://sc1/tst" + count + ".jpg";

			// Used to get ScreenSize and capture image
			Rectangle capture = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage Image = r.createScreenCapture(capture);
			// ImageIO.write(Image, "jpg", new File(path));
			// imageEntity.setImage(path);
			// imageEntity.setCreatedDate(dateTime.toString());
			LoginTimeDetails loginTimeDetails=savedScreenShot(Image);
			timeMapLocal.put("Hours_bal", loginTimeDetails.getHours_bal());
			timeMapLocal.put("Hours_use", loginTimeDetails.getHours_use());
			System.out.println("Screenshot saved");
			// imageService.save(imageEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private LoginTimeDetails savedScreenShot(BufferedImage Image) throws ParseException {
		JSONParser parser = new JSONParser();
		LoginTimeDetails loginTimeDetails=null;
		String url = "http://65.0.2.180/api/freelancer/saveScreenshot";
		HttpHeaders headers = new HttpHeaders();
		ScreenShotDetail screenShotDetail = new ScreenShotDetail();
		RestTemplate restTemplate = new RestTemplate();
		screenShotDetail.setFreelancer_id(id);
		screenShotDetail.setToken(tokenSession);
		screenShotDetail.setimage(Base64.encode(Image.toString().getBytes()).getBytes());
		System.out.println(Base64.encode(Image.toString().getBytes()));
		screenShotDetail.setModule_id(moduleScreenId);
		screenShotDetail.setLead_id(lead_id);
		screenShotDetail.setTime_id(time_id);
		HttpEntity<ScreenShotDetail> entity = new HttpEntity<ScreenShotDetail>(screenShotDetail, headers);
		String response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		JSONObject json = (JSONObject) parser.parse(response);
		String success = (String) json.get("success");
		String hours_use_image = (String) json.get("hours_use");
		String hours_bal_image = (String) json.get("hours_bal");
		if (success.equalsIgnoreCase("1")) {
			loginTimeDetails=new LoginTimeDetails();
			//System.out.println("Screenshot saved1" + hours_use_image);
			//System.out.println("Screenshot saved2" + hours_bal_image);
			loginTimeDetails.setHours_bal(hours_bal_image);;
			loginTimeDetails.setHours_use(hours_use_image);
		}
		System.out.println("Screenshot saved1" + hours_use);
		System.out.println("Screenshot saved2" + hours_bal);
		return loginTimeDetails;
	}
}
