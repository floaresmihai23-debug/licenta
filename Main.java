package me.mihaif.licenta;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.mihaif.licenta.ui.GameWindow;

public class Main extends Application {
    public static GameWindow gameWindow;
	
	@Override
    public void start(Stage primaryStage) {
    	primaryStage.getIcons().add(
    			new Image(getClass().getResourceAsStream("/assets/logo.png"))
    	);
        gameWindow = new GameWindow(primaryStage, 600, 400, "Sketch.me");
        gameWindow.show();
        
        
 //parallel thread
    }

    public static void main(String[] args) {
        launch(args);

    }
}
