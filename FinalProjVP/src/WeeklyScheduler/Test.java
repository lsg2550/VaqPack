/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeeklyScheduler;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Anton
 */
public class Test extends Application {
    
    BorderPane ws = new WeeklySchedulerTab();
    Button btn = new Button();
    StackPane root = new StackPane();
    
    @Override
    public void start(Stage primaryStage) {
        root.getChildren().add(btn);
        btn.setText("Click me man!");
        btn.setOnAction(e -> {
                root.getChildren().clear();
                root.getChildren().add(ws);
        });
        
        Scene scene = new Scene(root, 1000, 700);
        
        primaryStage.setTitle("Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
