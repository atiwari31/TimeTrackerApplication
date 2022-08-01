package com.dcardprocessing.controller;

import static com.dcardprocessing.TimeTrackerApp.stageManager;
import static com.dcardprocessing.controller.LoginController.id;
import static com.dcardprocessing.controller.LoginController.tokenSession;
import static com.dcardprocessing.controller.LoginController.userName;
import static com.dcardprocessing.util.ScreenCaptureTask.moduleScreenId;
import static com.dcardprocessing.util.URLInterface.urlEndTime;
import static com.dcardprocessing.util.URLInterface.urlModule;
import static com.dcardprocessing.util.URLInterface.urlProject;
import static com.dcardprocessing.util.URLInterface.urlStartTime;

import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ScheduledExecutorService;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.dcardprocessing.model.EndTimeDetail;
import com.dcardprocessing.model.Project;
import com.dcardprocessing.model.ProjectDetails;
import com.dcardprocessing.model.StartTimeDetail;
import com.dcardprocessing.service.impl.ImageCaptureServiceImpl;
import com.dcardprocessing.util.ProjectModuleCombo;
import com.dcardprocessing.view.FxmlView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

@Controller
public class DashboardController implements Initializable {

	@FXML
	private ComboBox<Project> project;
	@FXML
	private ComboBox<ProjectModuleCombo> task;

	@FXML
	private Button btnstartButton;
	@FXML
	private Label startsuccess;
	@FXML
	private Label projecterror;
	@FXML
	private Label taskerror;

	@FXML
	private Label welcome;

	@FXML
	private Label successmsg;

	ScheduledExecutorService scheduledExecutorService;

	@Autowired
	private ImageCaptureServiceImpl imageCaptureServiceImpl;
	private ObservableList<Project> projectObjList = FXCollections.observableArrayList();
	private ObservableList<ProjectModuleCombo> moduleObjList = FXCollections.observableArrayList();
	int moduleId = 0;
	static int timeId = 0;
	static int projectLeadIdStatic = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		projectObjList = FXCollections.observableArrayList();
		
		welcome.setText("Welcome To Elina Application :" + userName);

		// TODO Auto-generated method stub
		ProjectDetails projectDetails = new ProjectDetails();

		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		projectDetails.setFreelancer_id(id);
		projectDetails.setToken(tokenSession);
		HttpEntity<ProjectDetails> entity = new HttpEntity<ProjectDetails>(projectDetails, headers);
		String reponse = restTemplate.exchange(urlProject, HttpMethod.POST, entity, String.class).getBody();
		JSONParser parser = new JSONParser();
		String success = null;
		JSONObject user = null;
		JSONObject projectModule = null;
		String projectTittle = null;
		int projectId = 0;

		try {

			String json = (String) parser.parse(reponse).toString();
			user = new JSONObject(json);
			success = (String) user.get("success");

			if (success.equalsIgnoreCase("1")) {
				Project projectRes = null;

				JSONArray jsonArray = user.getJSONArray("leads");
				for (int i = 0; i < jsonArray.length(); i++) {
					projectRes = new Project();
					JSONObject explrObject = jsonArray.getJSONObject(i);
					int projectLeadId = (int) explrObject.get("project_leads_id");

					JSONObject projectObject = (JSONObject) explrObject.get("projectdetail");
					projectId = (int) projectObject.get("project_id");
					projectTittle = (String) projectObject.get("project_title");
					projectRes.setProjectId(projectId);
					projectRes.setProjectTitle(projectTittle);
					projectRes.setProjectLeadId(projectLeadId);
					projectObjList.add(projectRes);

				}
				project.setItems(projectObjList);
				project.setPromptText("Select Project");
				project.setConverter(new StringConverter<Project>() {

					@Override
					public String toString(Project object) {
						return object.getProjectTitle();
					}

					@Override
					public Project fromString(String string) {
						return project.getItems().stream().filter(ap -> ap.getProjectTitle().equals(string)).findFirst()
								.orElse(null);
					}
				});

				project.valueProperty().addListener((obs, oldval, newval) -> {
					if (newval != null)
						System.out.println("Selected airport: " + newval.getProjectTitle() + ". ID: "
								+ newval.getProjectId() + "Lead ID :" + newval.getProjectLeadId());
					projecterror.setText("");
					try {
						projectMethod(newval.getProjectLeadId());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				/************* Start ****************/

//				HttpEntity<ProjectDetails> entityModule = new HttpEntity<ProjectDetails>(projectDetails, headers);
//				ProjectModuleCombo projectModuleCombo = null;
//
//				for (int i = 0; i < jsonArray.length(); i++) {
//					JSONObject explrObject = jsonArray.getJSONObject(i);
//					projectModuleCombo = new ProjectModuleCombo();
//					//projectDetails.setProject_leads_id((int) explrObject.get("project_leads_id"));
//					projectDetails.setProject_leads_id(6);
//					String reponseModule = null;
//					reponseModule = restTemplate.exchange(urlModule, HttpMethod.POST, entityModule, String.class)
//							.getBody();
//
//					String jsonModule = (String) parser.parse(reponseModule).toString();
//
//					projectModule = new JSONObject(jsonModule);
//					if (success.equalsIgnoreCase("1")) {
//						JSONArray jsonLead = projectModule.getJSONArray("leads");
//						if (!jsonLead.isEmpty()) {
//							JSONObject projectObject = (JSONObject) jsonLead.get(0);
//							projectModuleCombo.setModuleId(projectObject.get("project_schedule_module_id").toString());
//							projectModuleCombo.setModuleName(projectObject.get("module_scope").toString());
//							moduleObjList.add(projectModuleCombo);
//						}
//					}
//					break;
//				}
//				task.setItems(moduleObjList);
//				task.setPromptText("Select  Module");
//				task.setConverter(new StringConverter<ProjectModuleCombo>() {
//
//					@Override
//					public String toString(ProjectModuleCombo object) {
//						return object.getModuleName();
//					}
//
//					@Override
//					public ProjectModuleCombo fromString(String string) {
//						return task.getItems().stream().filter(ap -> ap.getModuleName().equals(string)).findFirst()
//								.orElse(null);
//					}
//				});
//
//				task.valueProperty().addListener((obs, oldval, newval) -> {
//					if (newval != null)
//						System.out.println(
//								"Selected airport: " + newval.getModuleName() + ". ID: " + newval.getModuleId());
//					taskerror.setText("");
//					moduleMethod(newval.getModuleId());
//				});
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	public void startScan(ActionEvent event) throws InterruptedException, AWTException, IOException, ParseException {

		if (project == null || project.getValue() == null || project.getValue().getProjectTitle().isEmpty()) {
			System.out.println("#####################################");
			projecterror.setText("Please Select Project");
			return;
		} else if (task == null || task.getValue() == null || task.getValue().getModuleName().isEmpty()) {
			taskerror.setText("Please Select Task");
			return;
		} else {

			StartTimeDetail startTimeDetail = new StartTimeDetail();
			HttpHeaders headers = new HttpHeaders();
			RestTemplate restTemplate = new RestTemplate();
			startTimeDetail.setFreelancer_id(id);
			startTimeDetail.setToken(tokenSession);
			startTimeDetail.setModule_id(moduleId);
			HttpEntity<StartTimeDetail> entity = new HttpEntity<StartTimeDetail>(startTimeDetail, headers);
			String reponse = restTemplate.exchange(urlStartTime, HttpMethod.POST, entity, String.class).getBody();
			JSONParser parser = new JSONParser();
			String json = (String) parser.parse(reponse).toString();
			JSONObject timeRepone = new JSONObject(json);
			String success = (String) timeRepone.get("success");
			if (success.equalsIgnoreCase("1")) {
				timeId = (int) timeRepone.get("time_id");
			}
			successmsg.setText("Task Started Successfully");
			scheduledExecutorService = imageCaptureServiceImpl.callScreen();
			imageCaptureServiceImpl.keyboardActivity();

		}
	}

	/**
	 * @return the kk
	 */
	public Button getBtnstartButton() {
		return btnstartButton;
	}

	/**
	 * @param btnstartButton the btnstartButton to set
	 */
	public void setBtnstartButton(Button btnstartButton) {
		this.btnstartButton = btnstartButton;
	}

	/**
	 * @return the startsuccess
	 */
	public Label getStartsuccess() {
		return startsuccess;
	}

	/**
	 * @param startsuccess the startsuccess to set
	 */
	public void setStartsuccess(Label startsuccess) {
		this.startsuccess = startsuccess;
	}

	/**
	 * @return the projecterror
	 */
	public Label getProjecterror() {
		return projecterror;
	}

	/**
	 * @param projecterror the projecterror to set
	 */
	public void setProjecterror(Label projecterror) {
		this.projecterror = projecterror;
	}

	/**
	 * @return the project
	 */
	public ComboBox<Project> getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(ComboBox<Project> project) {
		this.project = project;
	}

	private void moduleMethod(String ModuleId) {
		moduleId = Integer.parseInt(ModuleId);
		moduleScreenId = moduleId;
	}

	private void projectMethod(int projectLeadId) throws ParseException {
		projectLeadIdStatic = projectLeadId;
		moduleObjList = FXCollections.observableArrayList();
		JSONObject projectModule = null;
		JSONParser parser = new JSONParser();
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		ProjectDetails projectDetails = new ProjectDetails();
		HttpEntity<ProjectDetails> entityModule = new HttpEntity<ProjectDetails>(projectDetails, headers);
		ProjectModuleCombo projectModuleCombo = null;
		projectModuleCombo = new ProjectModuleCombo();
		//projectDetails.setProject_leads_id((int) explrObject.get("project_leads_id"));
		projectDetails.setFreelancer_id(id);
		projectDetails.setToken(tokenSession);
		projectDetails.setProject_leads_id(projectLeadId);
		String reponseModule = null;
		reponseModule = restTemplate.exchange(urlModule, HttpMethod.POST, entityModule, String.class)
				.getBody();

		String jsonModule = (String) parser.parse(reponseModule).toString();

		projectModule = new JSONObject(jsonModule);
		String success = (String) projectModule.get("success");
		if (success.equalsIgnoreCase("1")) {
			JSONArray jsonLead = projectModule.getJSONArray("leads");
			if (!jsonLead.isEmpty()) {
				JSONObject projectObject = (JSONObject) jsonLead.get(0);
				projectModuleCombo.setModuleId(projectObject.get("project_schedule_module_id").toString());
				projectModuleCombo.setModuleName(projectObject.get("module_scope").toString());
				moduleObjList.add(projectModuleCombo);
			}
			task.setItems(moduleObjList);
			task.setPromptText("Select  Module");
			task.setConverter(new StringConverter<ProjectModuleCombo>() {

				@Override
				public String toString(ProjectModuleCombo object) {
					return object.getModuleName();
				}

				@Override
				public ProjectModuleCombo fromString(String string) {
					return task.getItems().stream().filter(ap -> ap.getModuleName().equals(string)).findFirst()
							.orElse(null);
				}
			});

			task.valueProperty().addListener((obs, oldval, newval) -> {
				if (newval != null)
					System.out.println(
							"Selected airport: " + newval.getModuleName() + ". ID: " + newval.getModuleId());
				taskerror.setText("");
				moduleMethod(newval.getModuleId());
			});
		}
	}

	public static void endTime() {

		if (timeId != 0) {
			EndTimeDetail endTimeDetail = new EndTimeDetail();
			HttpHeaders headers = new HttpHeaders();
			RestTemplate restTemplate = new RestTemplate();
			endTimeDetail.setFreelancer_id(id);
			endTimeDetail.setToken(tokenSession);
			endTimeDetail.setTime_id(timeId);
			endTimeDetail.setProject_lead_id(projectLeadIdStatic);
			HttpEntity<EndTimeDetail> entity = new HttpEntity<EndTimeDetail>(endTimeDetail, headers);
			String reponse = restTemplate.exchange(urlEndTime, HttpMethod.POST, entity, String.class).getBody();
			System.out.println(reponse);
			id = 0l;
			tokenSession = null;
			timeId = 0;
			
		}

	}

	@FXML
	private void logout(ActionEvent event) throws IOException, InterruptedException, AWTException, NativeHookException {
		endTime();
		if (scheduledExecutorService != null && !scheduledExecutorService.isShutdown())
			scheduledExecutorService.shutdownNow();
		GlobalScreen.unregisterNativeHook();
		id = 0l;
		tokenSession = null;
		projectObjList=null;
		moduleObjList=null;
		stageManager.switchScene(FxmlView.LOGIN);
	}

	/**
	 * @return the taskerror
	 */
	public Label getTaskerror() {
		return taskerror;
	}

	/**
	 * @param taskerror the taskerror to set
	 */
	public void setTaskerror(Label taskerror) {
		this.taskerror = taskerror;
	}

	/**
	 * @return the welcome
	 */
	public Label getWelcome() {
		return welcome;
	}

	/**
	 * @param welcome the welcome to set
	 */
	public void setWelcome(Label welcome) {
		this.welcome = welcome;
	}

}
