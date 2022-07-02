package com.dcardprocessing;

import java.time.LocalDate;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.dcardprocessing.config.StageManager;
import com.dcardprocessing.view.FxmlView;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

@SpringBootApplication

public class TimeTrackerApp extends Application {

	protected ConfigurableApplicationContext springContext;
	protected StageManager stageManager;

	public static void main(final String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() throws Exception {
		springContext = springBootApplicationContext();
		System.setProperty("java.awt.headless", "false");	

	}

	@Override
	public void start(Stage stage) throws Exception {

		stageManager = springContext.getBean(StageManager.class, stage);
		displayInitialScene();
	}

	@Override
	public void stop() throws Exception {
		
		springContext.close();
		System.exit(1);
	}

	/**
	 * Useful to override this method by sub-classes wishing to change the first
	 * Scene to be displayed on startup. Example: Functional tests on main window.
	 */
	protected void displayInitialScene() {
	
		stageManager.switchScene(FxmlView.LOGIN);

	}

	private ConfigurableApplicationContext springBootApplicationContext() {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(TimeTrackerApp.class);
		String[] args = getParameters().getRaw().stream().toArray(String[]::new);
		return builder.run(args);
	}

}
