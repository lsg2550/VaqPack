/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeeklyScheduler;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Anton
 */
public class WeeklySchedulerTab {
    
    
    private final TableView<TableRows> table = new TableView<>();
    private final IntegerProperty timeIncrement = new SimpleIntegerProperty();
    private final VBox coursesBox = new VBox();
    private final MenuBar menuBar = new MenuBar();
    private Table coursesTable;
    private String[][] fields;
    
    public void create(BorderPane BP) {
        
        createMenuBar();
        createTableView();
        createCoursesBox();
        
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.getChildren().addAll(table, coursesBox);
        VBox.setMargin(table, new Insets(30, 30, 10, 40));
        
        ScrollPane scrollPane = new ScrollPane(vBox);
        vBox.minWidthProperty().bind(menuBar.widthProperty().add(-20));
        vBox.maxWidthProperty().bind(menuBar.widthProperty().add(-20));
        
        BorderPane bp = new BorderPane();
        bp.setCenter(scrollPane);
        bp.setTop(menuBar);
        
        BP.getChildren().clear();
        BP.setCenter(bp);
    }

    public void createTableView() {
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
        
        table.getColumns().addAll(timeColumn, mondayColumn, tuesdayColumn, 
                wednesdayColumn, thursdayColumn, fridayColumn);
        table.setStyle("-fx-alignment: CENTER;");
        
        timeIncrement.addListener((Observable ov) -> {
            coursesTable = new Table(timeIncrement.get(), new MyDetails().getMyCourseDetails());
            fields = coursesTable.createTable();
            table.setItems(getCells(fields));
            table.setFixedCellSize(30);
            table.setMinHeight(fields.length * 30 + 32);
            table.setMaxHeight(fields.length * 30 + 32);
            table.setMinWidth(962);
            table.setMaxWidth(962);
        });
        timeIncrement.set(60);
    }
    
    public ObservableList<TableRows> getCells(String[][] fields) {
        ObservableList<TableRows> cells = FXCollections.observableArrayList();
        for (String[] field : fields) {
            cells.add(new TableRows(field[0], field[1], field[2], field[3], 
                    field[4], field[5]));
        }
        return cells;
    }
    
    public void createCoursesBox() {
        int numberOfCourses = coursesTable.getCourses().size();
        Text tNumberOfCourses = new Text(Integer.toString(numberOfCourses) + 
                " Course(s):");
        coursesBox.getChildren().add(tNumberOfCourses);
        for (int i = 0; i < numberOfCourses; i++) {
            coursesBox.getChildren().add(new Text(
                    coursesTable.getCourses().get(i).getCourseP() + " " + 
                            coursesTable.getCourses().get(i).getCourseD()));
        }
        coursesBox.setAlignment(Pos.CENTER);
    }
    
    public void createMenuBar() {
        // Courses menu
        Menu courses = new Menu("Courses");
        
        // Courses menu items
        MenuItem add = new MenuItem("Add");
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(e -> {
            Alert alert = new Alert(AlertType.WARNING);
            alert.showAndWait();
        });
        
        MenuItem modify = new MenuItem("Modify");
        
        // Add items to courses menu
        courses.getItems().addAll(add, delete, modify);
        
        // Interval menu
        Menu interval = new Menu("Interval");
        
        // Interval menu items
        MenuItem sixty = new MenuItem("60");
        sixty.setOnAction(e -> timeIncrement.set(60));
        
        MenuItem thirty = new MenuItem("30");
        thirty.setOnAction(e -> timeIncrement.set(30));
        
        MenuItem fifteen = new MenuItem("15");
        fifteen.setOnAction(e -> timeIncrement.set(15));
        
        // Add items to interval menu
        interval.getItems().addAll(sixty, thirty, fifteen);
        
        // View menu
        Menu view = new Menu("View");
        
        // View menu items
        Menu color = new Menu("Color");
        Menu font = new Menu("Font");
        Menu background = new Menu("Background");
        
        // color subMenu items
        MenuItem color1 = new MenuItem("color1");
        MenuItem color2 = new MenuItem("color2");
        MenuItem color3 = new MenuItem("color3");
        
        // Add items to color subMenu
        color.getItems().addAll(color1, color2, color3);
        
        // font subMenu items
        MenuItem font1 = new MenuItem("font1");
        MenuItem font2 = new MenuItem("font2");
        MenuItem font3 = new MenuItem("font3");
        
        // Add items to font subMenu
        font.getItems().addAll(font1, font2, font3);
        
        // background subMenu items
        MenuItem background1 = new MenuItem("background1");
        MenuItem background2 = new MenuItem("background2");
        MenuItem background3 = new MenuItem("background3");
        
        // Add items to background subMenu
        background.getItems().addAll(background1, background2, background3);
        
        // Add items to view menu
        view.getItems().addAll(color, font, background);
        
        menuBar.getMenus().addAll(courses, interval, view);
    }
}