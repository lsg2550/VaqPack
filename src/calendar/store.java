package calendar;

import java.util.ArrayList;
import javafx.application.Application;
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
public class store extends Application {

    ArrayList<commitment> commitments = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        Text prompt = new Text("enter name to store as");
        TextField name = new TextField();
        Button btn = new Button("Store");
        btn.setOnAction(e -> {
            database.store(name.getText(), commitments.toArray(new commitment[0]));
        });

        VBox root = new VBox();
        root.getChildren().addAll(prompt, name, btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Commitments");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void start(Stage s, ArrayList<commitment> c) {
        commitments = c;
        start(s);
    }
}
