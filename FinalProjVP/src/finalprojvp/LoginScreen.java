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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Joaquin
 */
public class LoginScreen extends Application{
    
    
    private GeneralInfoGUI genInfoGUI;
    
    private Scene startScene, registerScene, mainWindowScene;
    private BorderPane signInWindow;
    private Stage window;
    
    private MenuBar mb = new MenuBar();                                     //Menu Bar
    private Menu mList = new Menu("Menu");                                  //Menu Item
    private MenuItem calendar = new MenuItem("Calendar");                      //Calendar
    private MenuItem weeklyScheduler = new MenuItem("Weekly Scheduler");              //WeeklyScheduler
    private MenuItem genInfo = new MenuItem("General Information");           //General Information
    private MenuItem logout = new MenuItem("Log Out");
   
    
       
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
  
 /*****************************************************************************
                Sign In Window
 ******************************************************************************/
 
        signInWindow = new BorderPane(); //Creates borderPane 
        signInWindow.setCenter(getSignInGrid());
        
        startScene = new Scene(signInWindow);
        
        window.setScene(startScene);
        window.setTitle("UTRGV VaqPack");
        window.show();
        window.setFullScreen(true); //Sets to Full Screen
        
    }

    
    private Scene getMainWindowScene(){
        
        //MenuBar
        mb.getMenus().addAll(mList);
        mList.getItems().addAll(calendar, weeklyScheduler, genInfo, logout);
        
        BorderPane bottomPane = new BorderPane();
       
        VBox vb = new VBox();
        vb.getChildren().addAll(mb, bottomPane);
        
        logout.setOnAction((ActionEvent e) -> {
           window.setScene(startScene);
           window.setTitle("UTRGV VaqPack");
           window.show();
           window.setFullScreen(true); //Sets to Full Screen
               });
        
//        genInfo.setOnAction((ActionEvent e) -> {
//            bottomPane.setCenter(genInfoGUI);
//        });
        
    
        
        mainWindowScene = new Scene(vb, 600, 600);
        
        
        return mainWindowScene;
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
        
        signInGrid.add(getIcon(), 1, 0);
        
        Text scenetitle = new Text("UTRGV VaqPack");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        signInGrid.add(scenetitle, 1, 1);
        
        Label userName = new Label("User Name:");
        signInGrid.add(userName, 0, 2);
        
        TextField userTextField = new TextField();
        signInGrid.add(userTextField, 1, 2);
        
        Label pw = new Label("Password:");
        signInGrid.add(pw, 0, 3);
        
        PasswordField pwBox = new PasswordField();
        signInGrid.add(pwBox, 1, 3);
    
       Button signInBtn = new Button("Login");  //Sign In Button
       signInBtn.setMaxWidth(Double.MAX_VALUE);
       signInBtn.setOnAction((ActionEvent e) -> {
           
           window.setScene(getMainWindowScene());
           window.setFullScreen(true);
               });
       
       
       //New User 
       Button registerBtn = new Button("Register"); //Register Buttton
       registerBtn.setMaxWidth(Double.MAX_VALUE);
       registerBtn.setOnAction((ActionEvent e) -> {
           signInWindow.setCenter(getRegistrationForm());
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
        
        Label password = new Label("Password:");
        regGridPane.add(password, 0, 2);
        TextField passwordField = new TextField();
        regGridPane.add(passwordField, 1, 2);
        
        Label firstName = new Label("First Name:");
        regGridPane.add(firstName, 0, 3);
        TextField firstNameField = new TextField();
        regGridPane.add(firstNameField, 1, 3);
        
        Label lastName = new Label("Last Name:");
        regGridPane.add(lastName, 0, 4);
        TextField lastNameField = new TextField();
        regGridPane.add(lastNameField, 1, 4);
        
        Label email = new Label("Email:");
        regGridPane.add(email, 0, 5);
        TextField emailField = new TextField();
        regGridPane.add(emailField, 1, 5);
        
        Label bday = new Label("Birthday:");
        regGridPane.add(bday, 0, 6);
        TextField bdayField = new TextField();
        regGridPane.add(bdayField, 1, 6);
        
        Label address = new Label("Address:");
        regGridPane.add(address, 0, 7);
        TextField addressField = new TextField();
        regGridPane.add(addressField, 1, 7);
        
        Label city = new Label("City:");
        regGridPane.add(city, 0, 8);
        TextField cityField = new TextField();
        regGridPane.add(cityField, 1, 8);
        
        Label state = new Label("State:");
        regGridPane.add(state, 0, 9);
        TextField stateField = new TextField();
        regGridPane.add(stateField, 1, 9);
        
        Label zip = new Label("Zip:");
        regGridPane.add(zip, 0, 10);
        TextField zipField = new TextField();
        regGridPane.add(zipField, 1, 10);
        
        Label major = new Label("Major:");
        regGridPane.add(major, 0, 11);
        TextField majorField = new TextField();
        regGridPane.add(majorField, 1, 11);
        
        Label classification = new Label("Classification:");
        regGridPane.add(classification, 0, 12);
        TextField classificationField = new TextField();
        regGridPane.add(classificationField, 1, 12);
        
        Button submitBtn = new Button("Submit");
        submitBtn.setAlignment(Pos.CENTER);
        regGridPane.add(submitBtn, 1, 13);
        submitBtn.setOnAction((ActionEvent e) -> {
            signInWindow.setCenter(getSignInGrid());
        });
        
        return regGridPane;
    }
    
    private Circle getIcon(){
        Circle icon = new Circle(50);
        Image image = new Image("UTRGV.png");
        ImagePattern pattern = new ImagePattern(image);
        icon.setFill(pattern);
        
        return icon;
    }
    
//    private VBox 
}
