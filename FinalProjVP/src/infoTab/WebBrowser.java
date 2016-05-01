package infoTab;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebBrowser {

    private final Pane pane = new Pane();
    private final Stage stage = new Stage();
    private final Scene scene = new Scene(pane);

    public void webBrowser(WebView webView) {
        try {
            webView.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            pane.getChildren().add(webView);
            stage.setTitle("Student Assistant Organizer Explorer");
            stage.getIcons().add(new Image("itemsReq/utrgv.png"));
            stage.setResizable(true);
            stage.toFront();
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            stage.show();
        }
    }

}
