package com.dcardprocessing.controller;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.dcardprocessing.config.StageManager;
import com.dcardprocessing.model.Login;
import com.dcardprocessing.service.impl.ImageCaptureServiceImpl;

import com.dcardprocessing.view.FxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

@Controller
public class LoginController implements Initializable {

	@FXML
	private Button btnLogin;

	@FXML
	private PasswordField password;

	@FXML
	private TextField username;

	@FXML
	private Label lblLogin = new Label();

	@Lazy
	@Autowired
	private StageManager stageManager;

	

	public static String tokenSession;
	public static Long id;
	public static String userName;
	@FXML
	private void login(ActionEvent event) throws IOException, InterruptedException, AWTException {
		Login login = new Login();
		
		
		String url = "http://65.0.2.180/api/freelancer/login";
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		login.setEmail(username.getText());
		login.setPassword(password.getText());
		HttpEntity<Login> entity = new HttpEntity<Login>(login, headers);
		String reponse = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		JSONParser parser = new JSONParser();
		String success = null;
		JSONObject user=null;
		try {
			JSONObject json = (JSONObject) parser.parse(reponse);
			success = (String) json.get("success");
			user = (JSONObject) json.get("user");
			
			System.out.println(id);
			if (success.equalsIgnoreCase("1")) {
				tokenSession = (String) json.get("token");
				id=(Long)user.get("id");
				userName=(String)user.get("first_name");
				stageManager.switchScene(FxmlView.ADMIN_LOG_DETAIL);
			
			} else {
				stageManager.switchScene(FxmlView.LOGIN);
				lblLogin.setText("Email or password is incorrect."); 
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tokenSession);

	}

	private boolean emptyValidation(String field, boolean empty) {
		if (!empty) {
			return true;
		} else {
			validationAlert(field, true);
			return false;
		}
	}

	private boolean validate(String field, String value, String pattern) {
		if (!value.isEmpty()) {
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(value);
			if (m.find() && m.group().equals(value)) {
				return true;
			} else {
				validationAlert(field, false);
				return false;
			}
		} else {
			validationAlert(field, true);
			return false;
		}
	}

	private void validationAlert(String field, boolean empty) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Validation Error");
		alert.setHeaderText(null);
		if (field.equals("Role"))
			alert.setContentText("Please Select " + field);
		else {
			if (empty)
				alert.setContentText("Please Enter " + field);
			else
				alert.setContentText("Please Enter Valid " + field);
		}
		alert.showAndWait();
	}

	public String getPassword() {
		return password.getText();
	}

	public String getUsername() {
		return username.getText();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	public void cancel() {
		username.clear();
		password.clear();

	}
}
