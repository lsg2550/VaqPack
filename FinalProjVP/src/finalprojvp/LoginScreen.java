/*
 *  
 *    
 * 
 */
package finalprojvp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Joaquin
 */
public class LoginScreen extends Application{
    
    
    
    private Scene signInScene, registerScene;
    private BorderPane mainWindow;
       
    @Override
    public void start(Stage primaryStage) throws Exception {
        
  
 /*****************************************************************************
                Sign In Window
 ******************************************************************************/
 
        mainWindow = new BorderPane(); //Creates borderPane 
        mainWindow.setCenter(getSignInGrid());
        
        signInScene = new Scene(mainWindow);
        primaryStage.setScene(signInScene);
        primaryStage.setTitle("UTRGV VaqPack");
        primaryStage.show();
        primaryStage.setFullScreen(true); //Sets to Full Screen
        
    }

    private GridPane getSignInGrid(){
        
         /*****************************************************************************
                   Sign In Widow
        ******************************************************************************/
         
        GridPane signInGrid = new GridPane(); //Create GridPane. Will display in center of borderPane
        signInGrid.setAlignment(Pos.CENTER);
        signInGrid.setHgap(10);
        signInGrid.setVgap(10);
        signInGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        signInGrid.add(scenetitle, 0, 0, 2, 1);
        
        Label userName = new Label("User Name:");
        signInGrid.add(userName, 0, 1);
        
        TextField userTextField = new TextField();
        signInGrid.add(userTextField, 1, 1);
        
        Label pw = new Label("Password:");
        signInGrid.add(pw, 0, 2);
        
        PasswordField pwBox = new PasswordField();
        signInGrid.add(pwBox, 1, 2);
    
       Button signInBtn = new Button("Sign in");  //Sign In Button
       signInBtn.setMaxWidth(Double.MAX_VALUE);
       
       //New User 
       Button registerBtn = new Button("Register"); //Register Buttton
       registerBtn.setOnAction((ActionEvent e) -> {
           mainWindow.setCenter(getRegistrationForm());
               });
       
       //HBox to hold Sign up and Sign In Button 
       HBox hbBtn = new HBox(10);
       hbBtn.setAlignment(Pos.BOTTOM_CENTER);
       hbBtn.getChildren().addAll(registerBtn, signInBtn);
       signInGrid.add(hbBtn, 1, 4);
//       

        return signInGrid;
    }
    
    private GridPane getRegistrationForm(){
        
        /*****************************************************************************
                    Registration Form Window 
        ******************************************************************************/
            
        GridPane regGridPane = new GridPane();
        regGridPane.setAlignment(Pos.CENTER);
        regGridPane.setHgap(10);
        regGridPane.setVgap(10);
        regGridPane.setPadding(new Insets(25,25,25,25));
        
        Text regSceneTitle = new Text("Registration Form");
        regSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        regGridPane.add(regSceneTitle, 0, 0);
        
        //Declare and set Labels
        Label id = new Label("ID:");
        regGridPane.add(id, 0, 1);
        TextField idField = new TextField();
        regGridPane.add(idField, 1, 1);
        
        Label firstName = new Label("First Name:");
        regGridPane.add(firstName, 0, 2);
        TextField firstNameField = new TextField();
        regGridPane.add(firstNameField, 1, 2);
        
        Label lastName = new Label("Last Name:");
        regGridPane.add(lastName, 0, 3);
        TextField lastNameField = new TextField();
        regGridPane.add(lastNameField, 1, 3);
        
        Label email = new Label("Email:");
        regGridPane.add(email, 0, 4);
        TextField emailField = new TextField();
        regGridPane.add(emailField, 1, 4);
        
        Label bday = new Label("Birthday:");
        regGridPane.add(bday, 0, 5);
        TextField bdayField = new TextField();
        regGridPane.add(bdayField, 1, 5);
        
        Label address = new Label("Address:");
        regGridPane.add(address, 0, 6);
        TextField addressField = new TextField();
        regGridPane.add(addressField, 1, 6);
        
        Label major = new Label("Major:");
        regGridPane.add(major, 0, 7);
        TextField majorField = new TextField();
        regGridPane.add(majorField, 1, 7);
        
        Label classification = new Label("Classification:");
        regGridPane.add(classification, 0, 8);
        TextField classificationField = new TextField();
        regGridPane.add(classificationField, 1, 8);
        
        Label password = new Label("Password:");
        regGridPane.add(password, 0, 9);
        TextField passwordField = new TextField();
        regGridPane.add(passwordField, 1, 9);
        
        Button submitBtn = new Button("Submit");
        submitBtn.setAlignment(Pos.CENTER);
        regGridPane.add(submitBtn, 1, 10);
        submitBtn.setOnAction((ActionEvent e) -> {
            mainWindow.setCenter(getSignInGrid());
        });
        
        return regGridPane;
    }
    
//    private VBox 
}
