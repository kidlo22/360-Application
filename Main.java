package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



public class Main extends Application {
	
	private static Stage src;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			src = primaryStage;
			primaryStage.setResizable(false);
			Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
			primaryStage.setTitle("Pediatric System");
			primaryStage.setScene(new Scene(root, 700, 600));
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void changeScene(String fxml) {
		try {
			Parent newScene = FXMLLoader.load(getClass().getResource(fxml));
			src.getScene().setRoot(newScene);;	
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

