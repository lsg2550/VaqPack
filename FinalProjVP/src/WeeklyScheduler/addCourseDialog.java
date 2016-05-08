package WeeklyScheduler;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Anton
 */
public class addCourseDialog extends Dialog<CourseDetails> {
    
    //All textfields
    protected TextField coursePField = new TextField();
    protected TextField courseDField = new TextField();
    protected TextField courseLocField = new TextField();
    
    //CheckBoxes for days
    protected CheckBox m, t, w, th, f;
    
    protected ChoiceBox<String> startHour, startMinutes, startPeriod;
    
    protected ChoiceBox<String> endHour, endMinutes, endPeriod;
    
    protected Text tExceptions = new Text("\n\n\n");
    
    public addCourseDialog(ObservableList<CourseDetails> coursesList) {
        setTitle("UTRGV VaqPack");
        setHeaderText("Enter Course Fields");
        
        String[] hourTicks = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        String[] minuteTicks = {"00","05","10","15","20","25","30","35","40","45","50","55"};
        String[] period = {"AM", "PM"};
        
        // All labels
        Label coursePLabel = new Label("Course Prefix:");
        Label courseDLabel = new Label("Course Description:");
        Label courseLocLabel = new Label("Course Location:");
        Label courseDaysLabel = new Label("Days:");
        Label courseSTLabel = new Label("Start Time:");
        Label courseETLabel = new Label("End Time:");
        
        //Checkboxes for days
        m = new CheckBox("M");
        t = new CheckBox("T");
        w = new CheckBox("W");
        th = new CheckBox("TH");
        f = new CheckBox("F");

        
        //Horizontal box contaning the checkboxes for days
        HBox daysHB = new HBox(20);
        daysHB.getChildren().addAll(m, t, w, th, f);
        
        //ChoiceBoxes for start time:
        startHour = new ChoiceBox<>(FXCollections.observableArrayList(hourTicks));
        startHour.getSelectionModel().select(7);
        startMinutes = new ChoiceBox<>(FXCollections.observableArrayList(minuteTicks));
        startMinutes.getSelectionModel().select(0);
        startPeriod = new ChoiceBox<>(FXCollections.observableArrayList(period));
        startPeriod.getSelectionModel().select(0);
        
        //Horizontal box contaning the start time ChoiceBoxes
        HBox startTimeHB = new HBox(10);
        startTimeHB.getChildren().addAll(startHour, startMinutes, startPeriod);
        
        //ChoiceBoxes for end time
        endHour = new ChoiceBox<>(FXCollections.observableArrayList(hourTicks));
        endHour.getSelectionModel().select(9);
        endMinutes = new ChoiceBox<>(FXCollections.observableArrayList(minuteTicks));
        endMinutes.getSelectionModel().select(0);
        endPeriod = new ChoiceBox<>(FXCollections.observableArrayList(period));
        endPeriod.getSelectionModel().select(1);
        
        //Horizontal box containing the end time ChoiceBoxes
        HBox endTimeHB = new HBox(10);
        endTimeHB.getChildren().addAll(endHour, endMinutes, endPeriod);
        
        // Add everything to a GridPane
        GridPane gp = new GridPane();
        gp.setVgap(10);
        gp.setHgap(10);
        gp.add(coursePLabel, 0, 0);
        gp.add(coursePField, 1, 0);
        gp.add(courseDLabel, 0, 1);
        gp.add(courseDField, 1, 1);
        gp.add(courseLocLabel, 0, 2);
        gp.add(courseLocField, 1, 2);
        gp.add(courseDaysLabel, 0, 3);
        gp.add(daysHB, 1, 3);
        gp.add(courseSTLabel, 0, 4);
        gp.add(startTimeHB, 1, 4);
        gp.add(courseETLabel, 0, 5);
        gp.add(endTimeHB, 1, 5);
        
        VBox vb = new VBox(10);
        vb.getChildren().addAll(gp, tExceptions);
        
        getDialogPane().setContent(vb);
    }
}
