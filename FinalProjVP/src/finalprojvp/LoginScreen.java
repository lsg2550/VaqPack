/*
 *  
 *    
 * 
 */
package finalprojvp;

import WeeklyScheduler.CourseDetails;
import WeeklyScheduler.WeeklySchedulerTab;
import calendar.MyCalendar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import infoTab.GUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Joaquin
 */
public class LoginScreen extends Application {

   

    private UserQueries userQueries = new UserQueries();
    
    private Label studentID;
    private TextField studentIDField;
    private Label password;
    private PasswordField passwordField;
    private Label firstName;
    private TextField firstNameField;
    private Label lastName;
    private TextField lastNameField;
    private Label email;
    private TextField emailField;
    private Label phoneNumber;
    private TextField phoneNumberField;
    private Label address;
    private TextField addressField;
    private Label city;
    private TextField cityField;
    private Label state;
    private TextField stateField;
    private Label zip;
    private TextField zipField;
    private Label major;
    private TextField majorField;
    private Label classification;
    private TextField classificationField;
    private Button submitBtn;
    private Button registerBtn;
    private Button cancelBtn;
    private final Label errorMessage = new Label();
    
    private MyCalendar mycalendar = new MyCalendar(); 

    private Scene startScene, registerScene, mainWindowScene;
    private BorderPane signInWindow;
    private Stage window;

    private BorderPane holderPane;
    private BorderPane changingPane;
    private GridPane signInGrid;
    GridPane regGridPane;
   
    private TabPane tabPane = new TabPane();
    private Tab genInfo = new Tab("General Information");
    private Tab weeklyScheduler = new Tab("Weekly Scheduler");
    private Tab calendar = new Tab("Calendar");
    private StackPane header = new StackPane();

    private WeeklySchedulerTab ws;
   
    private Button logoutBtn = new Button("Logout");
 
    private GUI gui = new GUI();
    private String id;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        
        /**
         * ***************************************************************************
         * Sign In Window
 *****************************************************************************
         */
        
       
        
        //Tab generator 
        tabPane.getTabs().addAll(genInfo, weeklyScheduler, calendar);
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        gui.launch();
        genInfo.setContent(gui.getBp());
        mycalendar.launch();
        calendar.setContent(mycalendar.getRoot());
        
        //Header generator
        
        logoutBtn.setStyle("-fx-font: 21 arial; -fx-base: #b6e7c9;");
        header.getChildren().addAll(tabPane, logoutBtn);
        StackPane.setAlignment(logoutBtn, Pos.TOP_RIGHT);
        
        signInWindow = new BorderPane(); //Creates borderPane 
        signInWindow.setCenter(getSignInGrid());

        startScene = new Scene(signInWindow);

        window.getIcons().add(new Image("itemsReq/utrgv.png"));
        window.setScene(startScene);
        window.setTitle("UTRGV VaqPack");
        window.show();
        window.setFullScreen(true); //Sets to Full Screen
        
       
        
        logoutBtn.setOnAction((ActionEvent e) -> {
//            mb.getMenus().clear();
//            mList.getItems().clear();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("UTRGV VaqPack");
            alert.setHeaderText("Logout");
            alert.setContentText("Are you sure you want to logout? (Changes will be saved)");
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("itemsReq/utrgv.png")); 
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        ObservableList<CourseDetails> list = FXCollections.observableArrayList();
                        list.addAll(ws.getCoursesList());
                        userQueries.deleteCourse(id);
                        for (int i = 0; i < list.size(); i++) {
                           userQueries.addCourse(list.get(i).getCourseP(), list.get(i).getCourseD(),
                                    list.get(i).getLocation(), booleanDayToString(list.get(i).getDay()),
                                    list.get(i).getStart(), list.get(i).getEnd(), id);
                        }
                        System.exit(1);
                    }
                });

//            errorMessage.setText("");
//            window.setScene(startScene);
//            window.setTitle("UTRGV VaqPack");
//            window.show();
//            window.setFullScreen(true); //Sets to Full Screen
        });
            window.setOnCloseRequest(ev -> {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("UTRGV VaqPack");
            alert2.setHeaderText("Exit");
            alert2.setContentText("Are you sure you want to exit? (Changes will not be saved)");
            ((Stage)alert2.getDialogPane().getScene().getWindow()).getIcons().add(new Image("itemsReq/utrgv.png")); 
                alert2.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        System.exit(1);
                    }
            });
            
        });
        
       
    }
    
    private String booleanDayToString(boolean[] day) {
        String s = "";
        for (int i = 0; i < day.length; i++) {
            if (day[i])
                s += "1";
            else
                s += "0";
        }
        return s;
    }

    private Scene getMainWindowScene() {

       

        holderPane = new BorderPane();
        changingPane = new BorderPane();

        VBox vb = new VBox(56);
        vb.getChildren().addAll(header, holderPane);

        //Set tabPane hegiht 
        tabPane.minHeightProperty().bind(window.heightProperty());
        
        mainWindowScene = new Scene(vb);

        return mainWindowScene;
    }

    private GridPane getSignInGrid() {

        /**
         * ***************************************************************************
         * Sign In Widow
        *****************************************************************************
         */
        signInGrid = new GridPane(); //Create GridPane. Will display in center of borderPane
        signInGrid.setAlignment(Pos.CENTER);
        signInGrid.setHgap(10);
        signInGrid.setVgap(10);
        signInGrid.setPadding(new Insets(25, 25, 25, 25));

        signInGrid.add(getIcon(), 1, 0);

        Text scenetitle = new Text("UTRGV VaqPack");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        signInGrid.add(scenetitle, 1, 1);

        studentID = new Label("Student ID:");
        signInGrid.add(studentID, 0, 2);

        studentIDField = new TextField();
        signInGrid.add(studentIDField, 1, 2);

        password = new Label("Password:");
        signInGrid.add(password, 0, 3);

        passwordField = new PasswordField();
        signInGrid.add(passwordField, 1, 3);

        Button signInBtn = new Button("Login");  //Sign In Button
        signInBtn.setMaxWidth(Double.MAX_VALUE);
        signInBtn.setOnAction((ActionEvent e) -> {
                
                if (userQueries.passwordAuthentication(studentIDField.getText(), passwordField.getText() ) == 1)
                {   
                    id = studentIDField.getText();
                     window.setScene(getMainWindowScene());
                          window.setFullScreen(true);
                          // Get database to the Weekly Scheduler as an observable list
                          ws = new WeeklySchedulerTab(userQueries.searchDatabase());
                          weeklyScheduler.setContent(ws);
                }
                else
                {
                    errorMessage.setText("Incorrect Student ID or Password!");
                    errorMessage.setTextFill(Color.RED);
                }
                 
                studentIDField.clear();
                passwordField.clear();

        });

        //New User 
        registerBtn = new Button("Register"); //Register Buttton
        registerBtn.setMaxWidth(Double.MAX_VALUE);
        registerBtn.setOnAction((ActionEvent e) -> {
            signInWindow.setCenter(getRegistrationForm());
   
        });

        //HBox to hold register btn and  login Button 
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.CENTER_RIGHT);
        hbBtn.getChildren().addAll(registerBtn, signInBtn);
        signInGrid.add(hbBtn, 1, 4);
        
        signInGrid.add(errorMessage, 1, 5);

        return signInGrid;
    }

    private GridPane getRegistrationForm() {

        /**
         * ***************************************************************************
         * Registration Form Window 
        *****************************************************************************
         */
        regGridPane = new GridPane();
        regGridPane.setAlignment(Pos.CENTER);
        regGridPane.setHgap(10);
        regGridPane.setVgap(10);
        regGridPane.setPadding(new Insets(25, 25, 25, 25));

        Text regSceneTitle = new Text("Registration Form");
        regSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        regGridPane.add(regSceneTitle, 0, 0);

        //Declare and set Labels
        studentID = new Label("Student ID:");
        regGridPane.add(studentID, 0, 1);
        studentIDField = new TextField();
        regGridPane.add(studentIDField, 1, 1);

        password = new Label("Password:");
        regGridPane.add(password, 0, 2);
        passwordField = new PasswordField();
        regGridPane.add(passwordField, 1, 2);

        firstName = new Label("First Name:");
        regGridPane.add(firstName, 0, 3);
        firstNameField = new TextField();
        regGridPane.add(firstNameField, 1, 3);

        lastName = new Label("Last Name:");
        regGridPane.add(lastName, 0, 4);
        lastNameField = new TextField();
        regGridPane.add(lastNameField, 1, 4);

        email = new Label("Email:");
        regGridPane.add(email, 0, 5);
        emailField = new TextField();
        regGridPane.add(emailField, 1, 5);

        phoneNumber = new Label("Phone Number:");
        regGridPane.add(phoneNumber, 0, 6);
        phoneNumberField = new TextField();
        regGridPane.add(phoneNumberField, 1, 6);

        address = new Label("Address:");
        regGridPane.add(address, 0, 7);
        addressField = new TextField();
        regGridPane.add(addressField, 1, 7);

        city = new Label("City:");
        regGridPane.add(city, 0, 8);
        cityField = new TextField();
        regGridPane.add(cityField, 1, 8);

        state = new Label("State:");
        regGridPane.add(state, 0, 9);
        stateField = new TextField();
        regGridPane.add(stateField, 1, 9);

        zip = new Label("Zip:");
        regGridPane.add(zip, 0, 10);
        zipField = new TextField();
        regGridPane.add(zipField, 1, 10);

        major = new Label("Major:");
        regGridPane.add(major, 0, 11);
        majorField = new TextField();
        regGridPane.add(majorField, 1, 11);

        classification = new Label("Classification:");
        regGridPane.add(classification, 0, 12);
        classificationField = new TextField();
        regGridPane.add(classificationField, 1, 12);
     
        submitBtn = new Button("Submit");
        submitBtn.setOnAction((ActionEvent ew) -> {
          submitButtonActionPerformed(ew);
          errorMessage.setText("");
        });
       
        //cancel button 
        cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction((ActionEvent e) -> {
            signInWindow.setCenter(getSignInGrid());
          errorMessage.setText("");
        });
        
        HBox canSubBox = new HBox(10);
        canSubBox.setAlignment(Pos.CENTER_RIGHT);
        canSubBox.getChildren().addAll(cancelBtn, submitBtn);
        regGridPane.add(canSubBox, 1, 13);

        return regGridPane;
    }

    private Circle getIcon() {
        Circle icon = new Circle(50);
        Image image = new Image("UTRGV.png");
        ImagePattern pattern = new ImagePattern(image);
        icon.setFill(pattern);

        return icon;
    }
    
    private void submitButtonActionPerformed (ActionEvent e) { 
        
        userQueries.addUser(studentIDField.getText(), passwordField.getText(),
                firstNameField.getText(), lastNameField.getText(), emailField.getText(), 
                phoneNumberField.getText(), addressField.getText(), cityField.getText(), 
                stateField.getText(), zipField.getText(), majorField.getText(), classificationField.getText());
        
        signInWindow.setCenter(getSignInGrid());
        
    }
    
    private void logoutButtonActionPerformed (ActionEvent e) { 
        
//        userQueries.addCourse(studentIDField.getText());
        
        
        signInWindow.setCenter(getSignInGrid());
        
    }
    
    

    /**
     * @return the studentIDField
     */
    public TextField getStudentIDField() {
        return studentIDField;
    }

    /**
     * @return the passwordField
     */
    public PasswordField getPasswordField() {
        return passwordField;
    }
}



