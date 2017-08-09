/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weeklyscheduler;

import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private ObservableList<CourseDetails> coursesList = FXCollections.observableArrayList();
    private final ObservableList<PieChart.Data> times = FXCollections.observableArrayList();
    private StringProperty color = new SimpleStringProperty();
    private StringProperty font = new SimpleStringProperty();
    private StringProperty background = new SimpleStringProperty();
    private PieChart chart = new PieChart();
    private HBox hb = new HBox();
//    UserQueries querie = new UserQueries();
//    ResultSet y =  querie.getMyresultset2();

    public WeeklySchedulerTab(ObservableList<CourseDetails> list) {
        createStyling();
        createMenuBar();
        createTableView();

        if (list.isEmpty()) {
            createCoursesText();
            times.clear();
            times.addAll(new PieChart.Data("Available Time", 4200), new PieChart.Data("Busy Time", 0));
        }

        coursesList.addAll(list);

        vBox.setPadding(new Insets(50, 50, 50, 50));
        vBox.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(vBox);

        setCenter(scrollPane);
        setTop(menuBar);
        vBox.prefHeightProperty().bind(heightProperty().add(-menuBar.getHeight()));
        vBox.prefWidthProperty().bind(widthProperty().add(-20));
    }

    private void createTableView() {
        times.addListener((Observable ov) -> {
            chart.setData(times);
        });

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

            String[][] fields = new Table(timeIncrement.get(), coursesList).createTable();
            table.setItems(getCells(fields));
            table.setFixedCellSize(30);
            table.setMinHeight(fields.length * 30 + 32);
            table.setMaxHeight(fields.length * 30 + 32);
            table.setMinWidth(962);
            table.setMaxWidth(962);
            hb.setAlignment(Pos.CENTER);
            hb.getChildren().clear();
            hb.getChildren().add(table);
            CheckMenuItem tempVisual = (CheckMenuItem) menuBar.getMenus().get(2).getItems().get(4);
            if (tempVisual.isSelected()) {
                hb.getChildren().add(chart);
            }
            vBox.getChildren().clear();
            vBox.getChildren().addAll(hb, coursesText);
        });
        timeIncrement.set(60);

        coursesList.addListener((Observable ov) -> {
            String[][] fields = new Table(timeIncrement.get(), coursesList).createTable();
            table.setItems(getCells(fields));
            createCoursesText();
            delete.getItems().clear();
            modify.getItems().clear();
            editMenus(delete, modify);
            times.clear();
            times.addAll(new PieChart.Data("Available Time", 4200 - busyMinutes()),
                    new PieChart.Data("Busy Time", busyMinutes()));
        });
    }

    private void coursesListListener() {
        String[][] fields = new Table(timeIncrement.get(), coursesList).createTable();
        table.setItems(getCells(fields));
        createCoursesText();
        delete.getItems().clear();
        modify.getItems().clear();
        editMenus(delete, modify);
        times.clear();
        times.addAll(new PieChart.Data("Available Time", 4200 - busyMinutes()),
                new PieChart.Data("Busy Time", busyMinutes()));
    }

    public boolean[] setDay(String day) {
        boolean[] y = {false, false, false, false, false};
        for (int i = 0; i < y.length; i++) {
            y[i] = day.charAt(i) == '1';
        }
        return y;
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
        if (coursesList.isEmpty()) {
            coursesText.setText("0 Course(s):\n\n\n\n");
        } else {
            String s = Integer.toString(coursesList.size()) + " Course(s):\n";
            for (int i = 0; i < coursesList.size(); i++) {
                s += coursesList.get(i).toString() + "\n";
            }
            coursesText.setText(s + "\n\n");
        }
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
        Menu colorMenu = new Menu("Color");
        Menu fontMenu = new Menu("Font");
        Menu backgroundMenu = new Menu("Background");
        MenuItem defaultMenu = new MenuItem("Default");
        CheckMenuItem visual = new CheckMenuItem("Visualization");

        // color subMenu items
        MenuItem color1 = new MenuItem("Tan");
        MenuItem color2 = new MenuItem("Light Blue");
        MenuItem color3 = new MenuItem("Metal");

        // Add items to color subMenu
        colorMenu.getItems().addAll(color1, color2, color3);

        // font subMenu items
        MenuItem font1 = new MenuItem("Regular");
        MenuItem font2 = new MenuItem("Serif");
        MenuItem font3 = new MenuItem("Tahoma");

        // Add items to font subMenu
        fontMenu.getItems().addAll(font1, font2, font3);

        // background subMenu items
        MenuItem background1 = new MenuItem("Grey Shade");
        MenuItem background2 = new MenuItem("Beige");
        MenuItem background3 = new MenuItem("Blue");

        // Add actions to the courses' menu items
        add.setOnAction(e -> {
            CourseWindow stage = new CourseWindow();
            stage.addCourse(coursesList);
            stage.showAndWait();
        });

        color1.setOnAction(e
                -> color.set("-fx-color: #d2b48c;")
        );

        color2.setOnAction(e
                -> color.set("-fx-color: #87CEEB;")
        );

        color3.setOnAction(e
                -> color.set("-fx-color: #D3D3D3;")
        );

        // Add actions to the view menu items
        font1.setOnAction(e
                -> font.set("-fx-font-family: 'Regular';")
        );

        font2.setOnAction(e
                -> font.set("-fx-font-family: 'Serif';")
        );

        font3.setOnAction(e
                -> font.set("-fx-font-family: 'Tahoma';")
        );

        background1.setOnAction(e
                -> background.set("-fx-background-image: url(background1.png); "
                        + "-fx-background-size: contain; -fx-background-position: "
                        + "center; -fx-background-repeat: round")
        );

        background2.setOnAction(e
                -> background.set("-fx-background-image: url(background2.jpg); "
                        + "-fx-background-size: contain; -fx-background-position: "
                        + "center; -fx-background-repeat: round")
        );

        background3.setOnAction(e
                -> background.set("-fx-background-image: url(background3.jpg); "
                        + "-fx-background-size: contain; -fx-background-position: "
                        + "center; -fx-background-repeat: round")
        );

        defaultMenu.setOnAction(e -> {
            color.set("-fx-color: #d2b48c;");
            font.set("-fx-font-family: 'Tahoma';");
            background.set("-fx-background-image: url(background2.jpg); "
                    + "-fx-background-size: contain; -fx-background-position: "
                    + "center; -fx-background-repeat: round");
        });

        visual.setOnAction(e -> {
            if (visual.isSelected()) {
                hb.getChildren().add(chart);
            } else {
                hb.getChildren().remove(chart);
            }
        });

        // Add items to background subMenu
        backgroundMenu.getItems().addAll(background1, background2, background3);

        // Add items to view menu
        view.getItems().addAll(colorMenu, fontMenu, backgroundMenu, defaultMenu,
                visual);

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
                ((Stage) alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("itemsReq/utrgv.png"));
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        coursesList.remove(course);
                    }
                });
            });

            modifyCourse.setOnAction(e -> {
                CourseWindow stage = new CourseWindow();
                stage.modifyCourse(coursesList, course);
                stage.showAndWait();
            });
        }
    }

    private int busyMinutes() {
        int result = 0;
        int startT;
        int endT;
        for (int i = 0; i < coursesList.size(); i++) {
            String[] tempStart = coursesList.get(i).getStart().split("[:, ]");
            String[] tempEnd = coursesList.get(i).getEnd().split("[:, ]");
            int sh = Integer.parseInt(tempStart[0]);
            int sm = Integer.parseInt(tempStart[1]);
            int eh = Integer.parseInt(tempEnd[0]);
            int em = Integer.parseInt(tempEnd[1]);

            if (!tempStart[0].contains("12") && tempStart[2].contains("PM")) {
                startT = (sh + 12) * 60 + sm;
            } else {
                startT = sh * 60 + sm;
            }

            if (!tempEnd[0].contains("12") && tempEnd[2].contains("PM")) {
                endT = (eh + 12) * 60 + em;
            } else {
                endT = eh * 60 + em;
            }

            result += countDays(coursesList.get(i)) * (endT - startT);

        }
        return result;
    }

    private int countDays(CourseDetails x) {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (x.getDay()[i]) {
                count++;
            }
        }
        return count;
    }

    private void createStyling() {
        color.addListener((Observable ov)
                -> setStyle(color.getValue() + font.get())
        );
        color.set("-fx-color: #d2b48c;");

        font.addListener((Observable ov)
                -> setStyle(color.getValue() + font.get())
        );
        font.set("-fx-font-family: 'Tahoma';");

        background.addListener((Observable ov)
                -> vBox.setStyle(background.get())
        );
        background.set("-fx-background-image: url(background2.jpg); "
                + "-fx-background-size: contain; -fx-background-position: "
                + "center; -fx-background-repeat: round");
    }

    /**
     * @return the coursesList
     */
    public ObservableList<CourseDetails> getCoursesList() {
        return coursesList;
    }

    /**
     * @param coursesList the coursesList to set
     */
    public void setCoursesList(ObservableList<CourseDetails> coursesList) {
        this.coursesList = FXCollections.observableArrayList(coursesList);
    }
}
