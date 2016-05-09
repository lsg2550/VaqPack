package calendar;

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author german
 */
public class MyCalendar{

    LocalDate date = LocalDate.now();
    commitment c = new commitment();
    ArrayList<commitment> commitments = new ArrayList<>();
    VBox buttons = new VBox();
    private HBox root = new HBox();

    public void launch() {
        Button asd = new Button(String.format("%20s", "Add Commiment"));
        Button delete = new Button(String.format("%20s", "Delete Commiment"));
        Button store = new Button(String.format("%20s", "Store data"));
        Button retrive = new Button(String.format("%20s", "Retrive Data"));    
        
        asd.setOnAction(e -> {
            addCommitment();
        });
        delete.setOnAction(e -> {
            delete d = new delete();
            Stage stage = new Stage();
            d.start(stage);

        });
        store.setOnAction(e -> {
            store s = new store();
            Stage stage = new Stage();
            s.start(stage, commitments);

        });
        retrive.setOnAction(e -> {
            retrive r = new retrive();
            r.setCapture(e2 -> {
                commitments = r.getData();
            });
            Stage stage = new Stage();
            r.start(stage);

        });
        
        date = date.minusDays(date.getDayOfMonth() - 1);
        int month = date.getMonthValue();

        int week = 1;
        System.out.println(date);
        GridPane gp = new GridPane();
        gp.addRow(0, getWeekDays());
        while (date.getMonthValue() == month) {
            if (date.getDayOfWeek().getValue() == 7) {
                week++;
            }
            gp.add(displayDay(date.getDayOfMonth()), date.getDayOfWeek().getValue() % 7, week);
            System.out.println(week + "   " + date.getDayOfWeek().getValue());
            date = date.plusDays(1);
        }
        buttons.getChildren().addAll(asd, delete, store, retrive);
        root.setSpacing(5);
        root.getChildren().addAll(buttons, gp);
    }

    public void addCommitment() {
        c.getCommitment();
        commitments.add(c);
    }

    public StackPane displayDay(int i) {
        StackPane out = new StackPane();
        out.getChildren().add(new Text(String.format(" %01d ", i)));
        return out;
    }

    private Node[] getWeekDays() {
        String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        Text[] out = new Text[7];
        for (int i = 0; i < 7; i++) {
            out[i] = new Text(days[i]);
        }
        return out;
    }

    public HBox getRoot() {
        return root;
    }
}
