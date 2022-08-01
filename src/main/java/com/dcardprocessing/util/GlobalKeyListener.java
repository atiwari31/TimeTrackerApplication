package com.dcardprocessing.util;

import static com.dcardprocessing.controller.LoginController.id;
import static com.dcardprocessing.controller.LoginController.tokenSession;

import java.time.LocalDateTime;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.dcardprocessing.bean.DesktopEntity;
import com.dcardprocessing.model.KeyboardDetail;
import com.dcardprocessing.model.ProjectDetails;
import com.dcardprocessing.service.impl.DesktopService;


public class GlobalKeyListener implements NativeKeyListener {

@Autowired
 private DesktopService desktopService;




	

        public GlobalKeyListener(DesktopService desktopService) {
	// TODO Auto-generated constructor stub
        	this.desktopService=desktopService;
}



		public void nativeKeyPressed(NativeKeyEvent e) {
			LocalDateTime dateTime = LocalDateTime.now();
			 DesktopEntity desktopEntity=new DesktopEntity();
            System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
            if (e.getKeyCode() == NativeKeyEvent.VC_SHIFT_L) {
                System.out.println("SHIFT L");
                desktopEntity.setActivity("SHIFT L");
                desktopEntity.setCreatedDate(dateTime.toString());
            }
            if (e.getKeyCode() == NativeKeyEvent.VC_SHIFT_R) {
                System.out.println("SHIFT R");
                desktopEntity.setActivity("SHIFT R");
                desktopEntity.setCreatedDate(dateTime.toString());
            }
            if (e.getKeyCode() == NativeKeyEvent.VC_ALT_L) {
                System.out.println("ALT L");
                desktopEntity.setActivity("ALT L");
                desktopEntity.setCreatedDate(dateTime.toString());
            }
            if (e.getKeyCode() == NativeKeyEvent.VC_ALT_R) {
                System.out.println("ALT R");
                desktopEntity.setActivity("ALT R");
                desktopEntity.setCreatedDate(dateTime.toString());
            }
            if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL_L) {
                System.out.println("CONTROL L");
                desktopEntity.setActivity("CONTROL L");
                desktopEntity.setCreatedDate(dateTime.toString());
            }
            if (e.getKeyCode() == NativeKeyEvent.VC_CONTROL_R) {
                System.out.println("CONTROL R");
                desktopEntity.setActivity("CONTROL R");
                desktopEntity.setCreatedDate(dateTime.toString());
            }
            if (e.getKeyCode() == NativeKeyEvent.VC_TAB) {
                System.out.println("TAB");
                desktopEntity.setActivity("TAB");
                desktopEntity.setCreatedDate(dateTime.toString());
            }
            if (e.getKeyCode() == NativeKeyEvent.VC_SPACE) {
                System.out.println("SPACE");
                desktopEntity.setActivity("SPACE");
                desktopEntity.setCreatedDate(dateTime.toString());
            }
            if (e.getKeyCode() == NativeKeyEvent.VC_BACKSPACE) {
                System.out.println("BACKSPACE");
                desktopEntity.setActivity("BACKSPACE");
                desktopEntity.setCreatedDate(dateTime.toString());
            }
            if (e.getKeyCode() == NativeKeyEvent.VC_ENTER) {
                System.out.println("ENTER");
                desktopEntity.setActivity("ENTER");
                desktopEntity.setCreatedDate(dateTime.toString());
            }
            if (e.getKeyCode() == NativeKeyEvent.VC_BROWSER_REFRESH) {
                System.out.println("BROWSER REFRESH");
                desktopEntity.setActivity("BROWSER REFRESH");
                desktopEntity.setCreatedDate(dateTime.toString());
            }
            if (e.getKeyCode() == NativeKeyEvent.VC_BROWSER_SEARCH) {
                System.out.println("BROWSER SEARCH");
                desktopEntity.setActivity("BROWSER SEARCH");
                desktopEntity.setCreatedDate(dateTime.toString());
            }
            keyboardSaved(desktopEntity);
            //desktopService.save(desktopEntity);
        }
        
    

        public void nativeKeyReleased(NativeKeyEvent e) {
            System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
            if (e.getKeyCode() == NativeKeyEvent.NATIVE_KEY_PRESSED) {
                
                System.out.println("Hided Capture Window");
            }
        }

        public void nativeKeyTyped(NativeKeyEvent e) {
        	
        	  if (e.getKeyCode() == NativeKeyEvent.NATIVE_KEY_PRESSED) {
        		  
                  System.out.println("Hided Capture Window");
              }
        
            System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        }

    private void keyboardSaved(DesktopEntity desktopEntity) {
    	String url = "http://65.0.2.180/api/freelancer/saveKeyboardDetail";
    	HttpHeaders headers = new HttpHeaders();
    	KeyboardDetail keyboardDetail= new KeyboardDetail();
		RestTemplate restTemplate = new RestTemplate();
		keyboardDetail.setFreelancer_id(id);
		keyboardDetail.setToken(tokenSession);
		keyboardDetail.setDescription(desktopEntity.getActivity());
		keyboardDetail.setKeyCode(desktopEntity.getActivity());
		HttpEntity<KeyboardDetail> entity = new HttpEntity<KeyboardDetail>(keyboardDetail, headers);
		restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }
    
  
}
