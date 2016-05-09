package calendar;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author german
 */
public class myCalendarDialog extends Application {

    Button addCommitment;
    Stage primaryStage;
    TextArea input;
    DatePicker dateInput;
    LocalDate date;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Text prompt = new Text("Enter Commitment");
        input = new TextArea();
        addCommitment = new Button("Add Commitment");
        dateInput = new DatePicker();

        dateInput.setOnAction(e -> {
            date = dateInput.getValue();
        });

        VBox root = new VBox();
        root.getChildren().addAll(prompt, dateInput, input, addCommitment);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("myCalendarDialog");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Object[] getData() {
        return new Object[]{date, input.getText()};
    }

    public void setCapture(EventHandler<ActionEvent> e) {
        addCommitment.setOnAction(e);
    }

    public void close() {
        primaryStage.close();
    }
}
