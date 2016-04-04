/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeeklyScheduler;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Anton
 */
public class WeeklySchedulerTab extends Application {
    
    private TableView<TableRows> table;
    private int timeIncrement = 60;
    private Button btUser, btCourse, btTime, btBackground, btGridLines, 
            btFullScreen;
    private Table coursesTable = new Table(timeIncrement, 
            new MyDetails().getMyCourseDetails());
    private String[][] fields = coursesTable.createTable();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //Time column
        TableColumn<TableRows, String> timeColumn = new TableColumn<>("");
        timeColumn.setMinWidth(85);
        timeColumn.setStyle("-fx-alignment: CENTER;");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("timeCell"));
        
        //Monday Column
        TableColumn<TableRows, String> mondayColumn = new TableColumn<>("Monday");
        mondayColumn.setMinWidth(175);
        mondayColumn.setStyle("-fx-alignment: CENTER;");
        mondayColumn.setCellValueFactory(new PropertyValueFactory<>("mondayCell"));
        
        //Tuesday Column
        TableColumn<TableRows, String> tuesdayColumn = new TableColumn<>("Tuesday");
        tuesdayColumn.setMinWidth(175);
        tuesdayColumn.setStyle("-fx-alignment: CENTER;");
        tuesdayColumn.setCellValueFactory(new PropertyValueFactory<>("tuesdayCell"));
        
        //Wednesday Column
        TableColumn<TableRows, String> wednesdayColumn = new TableColumn<>("Wednesday");
        wednesdayColumn.setMinWidth(175);
        wednesdayColumn.setStyle("-fx-alignment: CENTER;");
        wednesdayColumn.setCellValueFactory(new PropertyValueFactory<>("wednesdayCell"));
        
        //Thursday Column
        TableColumn<TableRows, String> thursdayColumn = new TableColumn<>("Thursday");
        thursdayColumn.setMinWidth(175);
        thursdayColumn.setStyle("-fx-alignment: CENTER;");
        thursdayColumn.setCellValueFactory(new PropertyValueFactory<>("thursdayCell"));
        
        //Friday Column
        TableColumn<TableRows, String> fridayColumn = new TableColumn<>("Friday");
        fridayColumn.setMinWidth(175);
        fridayColumn.setStyle("-fx-alignment: CENTER;");
        fridayColumn.setCellValueFactory(new PropertyValueFactory<>("fridayCell"));
        
        table = new TableView<>();
        table.setItems(getCells());
        table.getColumns().addAll(timeColumn, mondayColumn, tuesdayColumn, 
                wednesdayColumn, thursdayColumn, fridayColumn);
        table.setFixedCellSize(30);
        double tableHeight = fields.length * 30 + 32;
        table.setPrefHeight(tableHeight);
        
        btUser = new Button();
        btUser.setText("Add/Change new user");
        btUser.setMinWidth(200);
        
        btCourse = new Button();
        btCourse.setText("Add new course");
        btCourse.setMinWidth(200);
        
        btTime = new Button();
        btTime.setText("Change time interval");
        btTime.setMinWidth(200);
        
        btBackground = new Button();
        btBackground.setText("Change Background Color");
        btBackground.setMinWidth(200);
        
        btGridLines = new Button();
        btGridLines.setText("Show Grid Lines");
        btGridLines.setMinWidth(200);
        
        btFullScreen = new Button();
        btFullScreen.setText("Switch to Full Screen");
        btFullScreen.setMinWidth(200);
        
        VBox vBoxLeft = new VBox();
        vBoxLeft.setPadding(new Insets(20,20,20,20));
        vBoxLeft.getChildren().addAll(btUser, btCourse, btTime);
        
        VBox vBoxRight = new VBox();
        vBoxRight.setPadding(new Insets(20,20,20,20));
        vBoxRight.getChildren().addAll(btBackground, btGridLines, btFullScreen);
        
        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBoxLeft, table, vBoxRight);
        
        int increment = 20;
        int numberOfCourses = coursesTable.getCourses().length;
        Text tNumberOfCourses = new Text(600, tableHeight + increment, 
                Integer.toString(numberOfCourses) + " Course(s):");
        
        Pane pane = new Pane();
        pane.getChildren().addAll(hBox, tNumberOfCourses);
        for (int i = 0; i < numberOfCourses; i++) {
            increment += 20;
            pane.getChildren().add(new Text(600, tableHeight + increment, 
                    coursesTable.getCourses()[i].getCourseP() + " " + 
                            coursesTable.getCourses()[i].getCourseD()));
        }

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("VaqPack Weekly Schedule!");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public ObservableList<TableRows> getCells() {
        ObservableList<TableRows> cells = FXCollections.observableArrayList();
        for (String[] field : fields) {
            cells.add(new TableRows(field[0], field[1], field[2], field[3], 
                    field[4], field[5]));
        }
        return cells;
    }
}
