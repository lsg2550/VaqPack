/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeeklyScheduler;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Anton
 */
public class CourseWindow extends Stage {
    
    // Header text
    private Text header = new Text("Enter a header");
    
    // Input errors text
    private Text error = new Text("");
    
    // Text fields
    private TextField pField = new TextField("");
    private TextField dField = new TextField("");
    private TextField lField = new TextField("");
    
    // All labels
    private Label pLabel, dLabel, lLabel, daysLabel, startTimeLabel, endTimeLabel;
    
    // CheckBoxes for days
    private CheckBox[] daysList = new CheckBox[5];
    
    // Starting times
    private ChoiceBox<String> startHour, startMinutes, startPeriod;
    
    // Ending times
    private ChoiceBox<String> endHour, endMinutes, endPeriod;
    
    // Buttons OK and Cancel
    private Button btOK, btCancel;
    
    public CourseWindow() {
        // Time input
        String[] hourTicks = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        String[] minuteTicks = {"00","05","10","15","20","25","30","35","40","45","50","55"};
        String[] period = {"AM", "PM"};
        
        // All labels
        pLabel = new Label("Course Prefix:");
        dLabel = new Label("Course Description:");
        lLabel = new Label("Course Location:");
        daysLabel = new Label("Days:");
        startTimeLabel = new Label("Start Time:");
        endTimeLabel = new Label("End Time:");
        
        // Populate daysList
        daysList[0] = new CheckBox("M");
        daysList[1] = new CheckBox("T");
        daysList[2] = new CheckBox("W");
        daysList[3] = new CheckBox("TH");
        daysList[4] = new CheckBox("F");
        
        // Horizontal box containing the daysList
        HBox daysHB = new HBox(20);
        daysHB.getChildren().addAll(daysList);
        
        //Create choiceBoxes for start time:
        startHour = new ChoiceBox<>(FXCollections.observableArrayList(hourTicks));
        startMinutes = new ChoiceBox<>(FXCollections.observableArrayList(minuteTicks));
        startPeriod = new ChoiceBox<>(FXCollections.observableArrayList(period));
        
        //Horizontal box contaning the start time ChoiceBoxes
        HBox startTimeHB = new HBox(10);
        startTimeHB.getChildren().addAll(startHour, startMinutes, startPeriod);
        
        //ChoiceBoxes for end time
        endHour = new ChoiceBox<>(FXCollections.observableArrayList(hourTicks));
        endMinutes = new ChoiceBox<>(FXCollections.observableArrayList(minuteTicks));
        endPeriod = new ChoiceBox<>(FXCollections.observableArrayList(period));
        
        //Horizontal box containing the end time ChoiceBoxes
        HBox endTimeHB = new HBox(10);
        endTimeHB.getChildren().addAll(endHour, endMinutes, endPeriod);
        
        // Add everything to a GridPane
        GridPane gp = new GridPane();
        gp.setVgap(10);
        gp.setHgap(10);
        gp.add(pLabel, 0, 0);
        gp.add(pField, 1, 0);
        gp.add(dLabel, 0, 1);
        gp.add(dField, 1, 1);
        gp.add(lLabel, 0, 2);
        gp.add(lField, 1, 2);
        gp.add(daysLabel, 0, 3);
        gp.add(daysHB, 1, 3);
        gp.add(startTimeLabel, 0, 4);
        gp.add(startTimeHB, 1, 4);
        gp.add(endTimeLabel, 0, 5);
        gp.add(endTimeHB, 1, 5);
        
        // Add buttons to a horizontal box
        btOK = new Button("OK");
        btCancel = new Button("Cancel");
        HBox buttonsHB = new HBox(5);
        buttonsHB.setAlignment(Pos.BOTTOM_RIGHT);
        buttonsHB.getChildren().addAll(btOK, btCancel);
        
        // Add gp and error text to a VBox
        error.setFill(Color.RED);
        VBox vb = new VBox(10);
        vb.getChildren().addAll(header, gp, error);
        vb.setAlignment(Pos.TOP_LEFT);
        
        //Add the VBox and the buttons to a border pane
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10,10,10,10));
        bp.setMinHeight(450);
        bp.setTop(vb);
        bp.setBottom(buttonsHB);
        
        // Add the border pane to the scene
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        Scene scene = new Scene(bp);
        setScene(scene);
        setTitle("UTRGV VaqPack");
    }
    
    public void addCourse(List<CourseDetails> list) {
        header.setText("Complete fields to add a course:\n");
        header.setFont(new Font(18));
        pField.setPromptText("e.g.: CSCI-3340");
        dField.setPromptText("e.g.: Software Engineering I");
        lField.setPromptText("e.g.: MAIN 1.514");
        startHour.getSelectionModel().select(7);
        startMinutes.getSelectionModel().select(0);
        startPeriod.getSelectionModel().select(0);
        endHour.getSelectionModel().select(9);
        endMinutes.getSelectionModel().select(0);
        endPeriod.getSelectionModel().select(1);
        
        btOK.setOnAction(e -> { 
            error.setText("");
            if (!checkError()) {
//                error.setText("");
                CourseDetails course = createCourse();
                String s = course.checkInterference(list);
                if (s.length() == 0) {
                    list.add(course);
                    close();
                }
                else
                    interferenceError(s);
            }
        });
        
        btCancel.setOnAction(e -> {
            close();
        });
    }
    
    public void modifyCourse(List<CourseDetails> list, CourseDetails x) {
        String start = x.getStart();
        String end = x.getEnd();
        
        header.setText("Edit fields to modify the course:\n");
        header.setFont(new Font(18));
        pField.setText(x.getCourseP());
        dField.setText(x.getCourseD());
        lField.setText(x.getLocation());
        startHour.getSelectionModel().select(start.substring(0,2));
        startMinutes.getSelectionModel().select(start.substring(3,5));
        startPeriod.getSelectionModel().select(start.substring(6,8));
        endHour.getSelectionModel().select(end.substring(0,2));
        endMinutes.getSelectionModel().select(end.substring(3,5));
        endPeriod.getSelectionModel().select(end.substring(6,8));
        selectDays(x);
        
        btOK.setOnAction(e -> {  
            error.setText("");
            if (!checkError()) {
                CourseDetails course = createCourse();
                List<CourseDetails> copyList = FXCollections.observableArrayList(list);
                copyList.remove(x);
                String s = course.checkInterference(copyList);
                if (s.length() == 0) {
                    list.set(list.indexOf(x), course);
                    close();
                }
                else
                    interferenceError(s);
            }
        });
        
        btCancel.setOnAction(e -> {
            close();
        });
    }
    
    private boolean[] daysListToBoolean() {
        boolean days[] = new boolean[5];
        for (int i = 0; i < days.length; i++) {
            days[i] = daysList[i].isSelected();
        }
        return days;
    }
    
    private boolean isAnyDaySelected(boolean[] days) {
        for (int i = 0; i < days.length; i++)
            if (days[i] == true)
                return true;
        return false;
    }
    
    private void fieldsError(boolean yes, Label x) {
        if (yes) {
            error.setText("Fields in red are required\n");
            x.setTextFill(Color.RED);
        }
        else
            x.setTextFill(Color.GREEN);
    }
    
    private boolean checkError() {
        boolean check = false;
        
        if (pField.getText().trim().length() == 0) {
            fieldsError(true, pLabel);
            check = true;
           }
        else
            fieldsError(false, pLabel);

        if (dField.getText().trim().length() == 0) {
            fieldsError(true, dLabel);
            check = true;
        }
        else
            fieldsError(false, dLabel);

        if (lField.getText().trim().length() == 0) {
            fieldsError(true, lLabel);
            check = true;
        }
        else
            fieldsError(false, lLabel);

        boolean[] days = daysListToBoolean();
        if(!isAnyDaySelected(days)) {
            fieldsError(true, daysLabel);
            check = true;
        }
        else
            fieldsError(false, daysLabel);
        
        check = checkTimeError(check);
        
        return check;
    }
    
    private CourseDetails createCourse() {
        CourseDetails course = new CourseDetails();
        course.setCourseP(pField.getText().trim());
        course.setCourseD(dField.getText().trim());
        course.setLocation(lField.getText().trim());
        course.setDay(daysListToBoolean());
        course.setStart(startHour.getValue() + ":" + startMinutes.getValue() + 
                " " + startPeriod.getValue());
        course.setEnd(endHour.getValue() + ":" + endMinutes.getValue() + " " + 
                endPeriod.getValue());
        
        return course;
    }
    
    private void interferenceError(String s) {
        String[] tokens = s.split("\n");
        if (tokens.length < 6)
            error.setText(s);
        else {
            for (int i = 0; i < 5; i++) {
                error.setText(error.getText().concat(tokens[i] + "\n"));
            }
            error.setText(error.getText().concat("..."));
        }   
    }
    
    private void selectDays(CourseDetails x) {
        boolean[] days = x.getDay();
        for (int i = 0; i < days.length; i++) {
            if (days[i])
                daysList[i].setSelected(true);
        }
    }
    
    private boolean checkTimeError(boolean check) {
        int sh = Integer.parseInt(startHour.getValue());
        int sm = Integer.parseInt(startMinutes.getValue());
        String sp = startPeriod.getValue();
        int eh = Integer.parseInt(endHour.getValue());
        int em = Integer.parseInt(endMinutes.getValue());
        String ep = endPeriod.getValue();
        String timeError = "Possible times are from 08:00 AM to 10:00 PM\n";
        
        // Check bad input for starting time
        if (sp.contains("AM") && (sh < 8 || sh == 12)) {
            error.setText(error.getText().concat(timeError));
            return true;
        }
        
        if (sp.contains("PM") && (sh == 11 || (sh == 10 && sm > 0))) {
            error.setText(error.getText().concat(timeError));
            return true;
        }
        
        // Check bad input for ending time
        if (ep.contains("AM") && (eh < 8 || eh == 12)) {
            error.setText(error.getText().concat(timeError));
            return true;
        }
        
        if (ep.contains("PM") && (eh == 11 || (eh == 10 && em > 0))) {
            error.setText(error.getText().concat(timeError));
            return true;
        }
        
        return checkStartEndError(check, sh, sm, sp, eh, em, ep);
    }
    
    private boolean checkStartEndError(boolean check, int sh, int sm, String sp, 
            int eh, int em, String ep) {
        String s = "Start time cannot be equal or more than end time";
        
        if (sp.contains("PM")) {
            if(ep.contains("AM")) {
                error.setText(error.getText().concat(s));
                return true;
            }
            else if (sh > eh || (sh == eh && sm >= em)) {
                error.setText(error.getText().concat(s));
                return true;
            }
        }
        else {     
            if (ep.contains("AM")) {
                if(sh > eh || (sh == eh && sm >= em)) {
                    error.setText(error.getText().concat(s));
                    return true;
                }
            }
        }
        return check;
    }
}
