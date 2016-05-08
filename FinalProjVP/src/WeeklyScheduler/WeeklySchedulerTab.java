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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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

/**
 *
 * @author Anton
 */
public class WeeklySchedulerTab extends BorderPane {
    
    
    private TableView<TableRows> table;
    private final IntegerProperty timeIncrement = new SimpleIntegerProperty();
    private final Text coursesText = new Text();
    private final MenuBar menuBar = new MenuBar();
    private final Menu delete = new Menu("Delete");
    private final Menu modify = new Menu("Modify");
    private final VBox vBox = new VBox(10);
    private final ObservableList<CourseDetails> coursesList = FXCollections.observableArrayList();
    
    public WeeklySchedulerTab() {
        
        createMenuBar();
        createTableView();   
        
        vBox.setPadding(new Insets(30,30,30,30));
//        vBox.getChildren().addAll(table, coursesText);
        vBox.setAlignment(Pos.CENTER);
        vBox.prefWidthProperty().bind(menuBar.widthProperty());
        
        ScrollPane scrollPane = new ScrollPane(vBox);
        
        this.setCenter(scrollPane);
        this.setTop(menuBar);
    }

    private void createTableView() {
        timeIncrement.addListener((Observable ov) -> {
            
            table = new TableView<>();
            
            //Time column
            TableColumn<TableRows, String> timeColumn = new TableColumn<>("");
            timeColumn.setMinWidth(85);
            timeColumn.setMaxWidth(85);
            timeColumn.setSortable(false);
            timeColumn.setStyle("-fx-alignment: CENTER;");
            timeColumn.setCellValueFactory(new PropertyValueFactory<>("timeCell"));

            //Monday Column
            TableColumn<TableRows, String> mondayColumn = new TableColumn<>("Monday");
            mondayColumn.setMinWidth(175);
            mondayColumn.setMaxWidth(175);
            mondayColumn.setSortable(false);
            mondayColumn.setStyle("-fx-alignment: CENTER;");
            mondayColumn.setCellValueFactory(new PropertyValueFactory<>("mondayCell"));

            //Tuesday Column
            TableColumn<TableRows, String> tuesdayColumn = new TableColumn<>("Tuesday");
            tuesdayColumn.setMinWidth(175);
            tuesdayColumn.setMaxWidth(175);
            tuesdayColumn.setSortable(false);
            tuesdayColumn.setStyle("-fx-alignment: CENTER;");
            tuesdayColumn.setCellValueFactory(new PropertyValueFactory<>("tuesdayCell"));

            //Wednesday Column
            TableColumn<TableRows, String> wednesdayColumn = new TableColumn<>("Wednesday");
            wednesdayColumn.setMinWidth(175);
            wednesdayColumn.setMaxWidth(175);
            wednesdayColumn.setSortable(false);
            wednesdayColumn.setStyle("-fx-alignment: CENTER;");
            wednesdayColumn.setCellValueFactory(new PropertyValueFactory<>("wednesdayCell"));

            //Thursday Column
            TableColumn<TableRows, String> thursdayColumn = new TableColumn<>("Thursday");
            thursdayColumn.setMinWidth(175);
            thursdayColumn.setMaxWidth(175);
            thursdayColumn.setSortable(false);
            thursdayColumn.setStyle("-fx-alignment: CENTER;");
            thursdayColumn.setCellValueFactory(new PropertyValueFactory<>("thursdayCell"));

            //Friday Column
            TableColumn<TableRows, String> fridayColumn = new TableColumn<>("Friday");
            fridayColumn.setMinWidth(175);
            fridayColumn.setMaxWidth(175);
            fridayColumn.setSortable(false);
            fridayColumn.setStyle("-fx-alignment: CENTER;");
            fridayColumn.setCellValueFactory(new PropertyValueFactory<>("fridayCell"));

            table.getColumns().addAll(timeColumn, mondayColumn, tuesdayColumn, 
                    wednesdayColumn, thursdayColumn, fridayColumn);
            table.setStyle("-fx-alignment: CENTER;");

            String[][] fields = new Table(timeIncrement.get(), coursesList).createTable();
            table.setItems(getCells(fields));
            table.setFixedCellSize(30);
            table.setMinHeight(fields.length * 30 + 32);
            table.setMaxHeight(fields.length * 30 + 32);
            table.setMinWidth(962);
            table.setMaxWidth(962);
            vBox.getChildren().clear();
            vBox.getChildren().addAll(table, coursesText);
        });
        timeIncrement.set(60);
        
        coursesList.addListener((Observable ov) ->{
            String[][] fields = new Table(timeIncrement.get(), coursesList).createTable();
            table.setItems(getCells(fields));
            createCoursesText();
            delete.getItems().clear();
            modify.getItems().clear();
            editMenus(delete, modify);
        });
        coursesList.addAll(new MyDetails().getMyCourseDetails());
    }
    
    private ObservableList<TableRows> getCells(String[][] fields) {
        ObservableList<TableRows> cells = FXCollections.observableArrayList();
        for (String[] field : fields) {
            cells.add(new TableRows(field[0], field[1], field[2], field[3], 
                    field[4], field[5]));
        }
        return cells;
    }
    
    private void createCoursesText() {
        String s = Integer.toString(coursesList.size()) + " Course(s):\n";
        for (int i = 0; i < coursesList.size(); i++) {
            s += coursesList.get(i).toString() + "\n";
        }
        coursesText.setText(s + "\n\n");
    }
    
    private void createMenuBar() {
        // Courses menu
        Menu courses = new Menu("Courses");
        
        // Courses menu items
        MenuItem add = new MenuItem("Add");
        
        // Add courses to Delete and Modify menus
        editMenus(delete, modify);
        
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
        
        // Add actions to the courses' menu items
        
        add.setOnAction(e -> {
            CourseWindow stage = new CourseWindow();
            stage.addCourse(coursesList);
            stage.showAndWait();
        });
        
        // Add items to background subMenu
        background.getItems().addAll(background1, background2, background3);
        
        // Add items to view menu
        view.getItems().addAll(color, font, background);
        
        menuBar.getMenus().addAll(courses, interval, view);
    }
    
    private void editMenus(Menu delete, Menu modify) {
        for (int i = 0; i < coursesList.size(); i++) {
            CourseDetails course = coursesList.get(i);
            String s = course.getCourseP() + " " + course.getCourseD(); 
            
            MenuItem deleteCourse = new MenuItem(s);
            MenuItem modifyCourse = new MenuItem(s);
            
            delete.getItems().add(deleteCourse);
            modify.getItems().add(modifyCourse);
            
            deleteCourse.setOnAction(e -> { 
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("UTRGV VaqPack");
                alert.setHeaderText("Remove Course");
                alert.setContentText("Are you sure you want to remove " + s + "?");
                alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK)
                    coursesList.remove(course);
                });
            });
            
            modifyCourse.setOnAction(e -> { 
                CourseWindow stage = new CourseWindow();
                stage.modifyCourse(coursesList, course);
                stage.showAndWait();
            });
        }
    }
}