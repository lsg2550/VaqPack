package information;

import static javafx.application.Application.launch;
import javafx.scene.layout.BorderPane;

public class InformationLauncher {

    GUI gui = new GUI();

    public void start(BorderPane bp) {
//        gui.launch(bp);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
