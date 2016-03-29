/*
 *  
 *    
 * 
 */
package finalprojvp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Joaquin
 */
public class LoginScreen extends Application{
    
       
    @Override
    public void start(Stage primaryStage) {
        
        BorderPane borderPane = new BorderPane(); //Creates borderPane 
       
        
        GridPane grid = new GridPane(); //Create GridPane. Will display in center of borderPane
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        borderPane.setCenter(grid);
        
        Scene scene = new Scene(borderPane, 300, 275);
        primaryStage.setScene(scene);
        

        primaryStage.setTitle("UTRGV VaqPack");
        
        primaryStage.show();
        
        primaryStage.setFullScreen(true); //Sets to Full Screen
    
}
}
