
package calendar;

import java.sql.Blob;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author german
 */
public class retrive extends Application {

    Stage primaryStage;
    Button btn;
    TextField name;

    @Override
    public void start(Stage s) {
        this.primaryStage = s;
        Text prompt = new Text("enter name to store as");
        name = new TextField();
        btn = new Button("Store");

        VBox root = new VBox();
        root.getChildren().addAll(prompt, name, btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setCapture(EventHandler<ActionEvent> e) {
        btn.setOnAction(e);
    }

    public ArrayList<commitment> getData() {
        try {
            ArrayList<commitment> commitments = new ArrayList<>();
            ResultSet rs = database.search(name.getText());
            rs.next();
            return parseText(rs.getBlob(3));
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    public void close() {
        primaryStage.close();
    }

    private ArrayList<commitment> parseText(Blob blob) {
        try {
            String raw = new String(blob.getBytes(1l, (int) blob.length()));
            raw = raw.replaceAll("[|]", "");
            String[] raws = raw.split(", ");
            ArrayList<commitment> commitments = new ArrayList<>();
            int id = 0;
            for (String commitment : raws) {
                String[] temp = commitment.split("\n");
                LocalDate date = LocalDate.parse(temp[0]);
                String text = "";
                for (int i = 1; i < temp.length; i++) {
                    text += temp[i] + "\n";
                }
                commitments.add(new commitment(date, text, id++));
            }
            return commitments;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
