package main;

import java.awt.event.InputEvent;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch();
	}

	int windowWidth = 350;
	int windowHeight = 300;

	@SuppressWarnings("all")
	public void start(Stage window) throws Exception {

		// Create containers ================================================

		VBox root = new VBox();

		// Create scenes ====================================================

		Scene mainScene = new Scene(root, windowWidth, windowHeight);

		// Content for main scene ====================================

		Label clicksLabel = new Label("Enter number of clicks:");
		TextField clicksText = new TextField();

		Label delayLabel = new Label("Enter delay between clicks:");
		TextField delayText = new TextField();

		Button startButton = new Button();

		// Styling ==========================================================

		clicksLabel.setStyle("-fx-font-size: 2em;");
		clicksLabel.setAlignment(Pos.TOP_CENTER);
		clicksLabel.setMinSize(windowWidth, 35);
		clicksLabel.setMaxSize(windowWidth, 35);
		clicksLabel.setTranslateY(20);
		
		clicksText.setMinSize(windowWidth / 2, 30);
		clicksText.setMaxSize(windowWidth / 2, 30);
		clicksText.setTranslateY(25);

		delayLabel.setStyle("-fx-font-size: 2em;");
		delayLabel.setAlignment(Pos.TOP_CENTER);
		delayLabel.setMinSize(windowWidth, 35);
		delayLabel.setMaxSize(windowWidth, 35);
		delayLabel.setTranslateY(35);
		
		delayText.setMinSize(windowWidth / 2, 30);
		delayText.setMaxSize(windowWidth / 2, 30);
		delayText.setTranslateY(40);
		
		startButton.setStyle("-fx-font-size: 1.5em;");
		startButton.setTranslateY(60);
		
		root.setAlignment(Pos.TOP_CENTER);
		
		startButton.setText("Start");

		// Button action ====================================================

		Clicker clicker = new Clicker();

		startButton.setOnMouseClicked(e -> {

			int clicks = Integer.parseInt(clicksText.getText());
			int delay = Integer.parseInt(delayText.getText());

			Clicker.delay = Integer.parseInt(delayText.getText());
			
			root.getChildren().removeAll(clicksLabel, clicksText, delayLabel, delayText, startButton);
			
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("");
			alert.setHeaderText(null);
			alert.setContentText("Clicking will start in 3 seconds");

			alert.showAndWait();
			
			Label timer = new Label("Clicking will start in 3 seconds!");
			
			root.getChildren().addAll(timer);
			
			try {
				Thread.sleep(3000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			root.getChildren().removeAll(timer);
			root.getChildren().removeAll(clicksLabel, clicksText, delayLabel, delayText, startButton);
			
			Label done = new Label("Clicking has finished!");
			Label clicksDone = new Label("Clicked: " + clicks + " times!");

			for (int i = 0; i < clicks; i++) {
				clicker.clickMouse(InputEvent.BUTTON1_MASK);
			}
			
			done.setStyle("-fx-font-size: 2em;");
			done.setTranslateY(80);
			
			clicksDone.setStyle("-fx-font-size: 2em;");
			clicksDone.setTranslateY(110);
			root.getChildren().addAll(done, clicksDone);
		});

		root.getChildren().addAll(clicksLabel, clicksText, delayLabel, delayText, startButton);

		// Window parameters ===============================================

		window.getIcons().add(new Image("/icons/mouse.png"));
		window.setTitle("Auto Mouse Clicker");
		window.setScene(mainScene);
		window.show();
	}
}
