package finalprojvp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FinalProjVP extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
       
        LoginScreen loginScreen = new LoginScreen();
        loginScreen.start(primaryStage);
       
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
