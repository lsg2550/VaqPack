package infoTab;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class GUI {

    //Instantiating
    StudentMajorDept smd = new StudentMajorDept();
    BookstoreInfo bsi = new BookstoreInfo();
    CampusMap cm = new CampusMap();
    WebBrowser wb = new WebBrowser();

    //Create UI Components
    private BorderPane bp = new BorderPane();
    Scene scene = new Scene(bp);
    VBox cbVB = new VBox();                         //verticalBox; For choicebox
    VBox diVB = new VBox();                         //defaultImage; For default image & text, or maybe for all images
    VBox wcVB = new VBox();                         //welcomeCenter; will have the campus choicebox above the welcTxt text
    VBox weVB = new VBox();                         //Contains web browser for student major department
    VBox maVB = new VBox();                         //Will contain text and choicebox for major, will be inside Hbox
    HBox hlHB = new HBox();                         //Contains hyperlink with textarea in middle of borderpane for Major selection

    //Create Children
    ChoiceBox<String> wCB = new ChoiceBox<>();      //Campus choicebox, for welcTxt since nodes can't share children
    ChoiceBox<String> cCB = new ChoiceBox<>();      //Campus choicebox
    ChoiceBox<String> iCB = new ChoiceBox<>();      //Information choicebox
    ChoiceBox<String> mCB = new ChoiceBox<>();      //Major Choicebox
    TextArea bsktrTA = new TextArea();              //Print bookstore schedule 
    TextArea contaTA = new TextArea();              //Emergency Contact info displayed next to respective campus map
    //TextArea majorTA = new TextArea();              //Print information about selected major's department
    Text campTxt = new Text();                      //What campus the user is in
    Text infoTxt = new Text();                      //What type of information the user wants
    Text majoTxt = new Text();                      //Please select your major to view information about it
    Text cimgTxt = new Text();                      //Will be above the images
    Text welcTxt = new Text();                      //Text that will welcTxt the user in the middle of the screen

    //Grab images, default, brownsville, edinburg
    Image imgD = new Image("itemsReq/droid.png");
    Image imgB = new Image("itemsReq/utbMAP.PNG");
    Image imgE = new Image("itemsReq/utpaMAP.PNG");
    ImageView iV = new ImageView(imgD);

    //Hyperlink to major's department
    WebView webView = new WebView();
    WebEngine engine = webView.getEngine();
    Hyperlink majLink = new Hyperlink("http://www.utrgv.edu/en-us/");

    //Declare Variable
    private Boolean campus;                         //Brownsville = true; Edinburg = false; No Campus Selected = Null;

    public void launch() {
        changeListener();
        UI();
    }

    private void UI() {
        //CAMPUS TEXT
        campTxt.setText("Pick your campus:");
        campTxt.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 16));
        campTxt.setTextAlignment(TextAlignment.CENTER);
        campTxt.setStroke(Color.BLACK);
        campTxt.setFill(Color.WHITE);
        campTxt.setStrokeWidth(1);

        cCB.getItems().addAll("Brownsville", "Edinburg");
        wCB.getItems().addAll("Brownsville", "Edinburg");

        //INFORMATION TEXT
        infoTxt.setText("Select which type of information you'd like from the choicebox:");
        infoTxt.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 16));
        infoTxt.setTextAlignment(TextAlignment.CENTER);
        infoTxt.setStroke(Color.BLACK);
        infoTxt.setFill(Color.WHITE);
        infoTxt.setStrokeWidth(1);

        iCB.getItems().addAll("Bookstore Info", "Campus Map", "Major Information");

        //MAJOR TEXT
        majoTxt.setText("Select your major from the choicebox:");
        majoTxt.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 16));
        majoTxt.setTextAlignment(TextAlignment.CENTER);
        majoTxt.setStroke(Color.BLACK);
        majoTxt.setFill(Color.WHITE);
        majoTxt.setStrokeWidth(1);

        smd.majorList(mCB);

        //WELCOME TEXT
        welcTxt.setText("Welcome! To get started please select a campus.\n (You can change campus' after your choice.)");
        welcTxt.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 30));
        welcTxt.setTextAlignment(TextAlignment.CENTER);
        welcTxt.setStroke(Color.BLACK);
        welcTxt.setFill(Color.WHITE);
        welcTxt.setStrokeWidth(2);

        //IMAGE TEXT, DEFAULT-BROWNSVILLE-EDINBURG
        cimgTxt.setText("These are not the droids you're looking for. Pick a campus to see its map!");
        cimgTxt.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 24));
        cimgTxt.setTextAlignment(TextAlignment.CENTER);
        cimgTxt.setStroke(Color.BLACK);
        cimgTxt.setFill(Color.WHITE);
        cimgTxt.setStrokeWidth(2);

        //Hyperlink Text
        majLink.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 20));

        //Fixing TextAreas
        bsktrTA.setMaxHeight(400);
        //majorTA.setMaxHeight(400);
        contaTA.setMaxHeight(400);
        bsktrTA.setMaxWidth(500);
        contaTA.setMaxWidth(500);
        //majorTA.setMaxWidth(500);
        bsktrTA.setEditable(false);
        contaTA.setEditable(false);
        //majorTA.setEditable(false);

        //SET UI
        maVB.getChildren().addAll(majoTxt, mCB);
        hlHB.getChildren().addAll(maVB, majLink);
        //majoTxt.setVisible(false);
        hlHB.setVisible(false);
        //mCB.setVisible(false);
        cbVB.getChildren().addAll(campTxt, cCB, infoTxt, iCB, hlHB);
        wcVB.getChildren().addAll(wCB, welcTxt);
        diVB.getChildren().addAll(cimgTxt, iV);
        diVB.setAlignment(Pos.CENTER);
        wcVB.setAlignment(Pos.CENTER);
        infoTxt.setVisible(false);
        iCB.setVisible(false);

        bp.setStyle("-fx-background-image: url(itemsReq/oBG.gif); -fx-background-size: contain; -fx-background-position: center; -fx-background-repeat: no-repeat;");
        bp.setCenter(wcVB);
        /*
        infoTab.getIcons().add(new Image("itemsReq/utrgv.png"));
        infoTab.setTitle("Important Information");
        infoTab.setMaximized(true);
        infoTab.setScene(scene);
        infoTab.show();
         */
    }

    /* BEGIN LISTENERS FOR UI */
    //Listens to changes within information, student major, campus etc etc...
    private void changeListener() {
        wCB.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            ccBListener();
            //iCB.setValue("Campus Map");
            //infoListener();
        });

        cCB.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            try {
                bsi.bkstrCAMP(cCB, bsktrTA);
                cm.mapCAMP(cCB, cimgTxt, iV, contaTA);
            } catch (Exception ex) {
                System.err.print(ex);
                //No reason to have this reached, once a campus is selected the user is limited to Brownsville or Edinburg
            }
        });
        iCB.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            try {
                infoListener();
            } catch (Exception ex) {
                System.err.print(ex);               //Shouldn't be reached anymore
            }
        });
        mCB.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            smd.smD(mCB, majLink, engine);
        });

        majLink.setOnAction(e -> {
            engine.load(majLink.getText());
            //wb.webBrowser(webView);
        });
    }

    //Listens for which information tab is selected
    private void infoListener() {
        //Clear previous cases
        hlHB.setVisible(false);
        //majoTxt.setVisible(false);
        //mCB.setVisible(false);
        bp.setBottom(null);
        bp.setRight(null);
        bp.setCenter(null);
        bp.setLeft(null);
        switch (iCB.getValue()) {
            case "Bookstore Info":
                bp.setCenter(bsktrTA);
                BorderPane.setAlignment(bsktrTA, Pos.CENTER);
                //Magic
                bsi.bkstrCAMP(cCB, bsktrTA);
                break;
            case "Campus Map":
                bp.setRight(contaTA);
                bp.setCenter(diVB);
                BorderPane.setAlignment(contaTA, Pos.CENTER);
                BorderPane.setAlignment(diVB, Pos.CENTER);
                //Magic
                cm.mapCAMP(cCB, cimgTxt, iV, contaTA);
                break;
            case "Major Information":
                //majoTxt.setVisible(true);
                //mCB.setVisible(true);
                hlHB.setVisible(true);
                bp.setCenter(webView);
                //bp.setBottom(hlVB);
                //BorderPane.setAlignment(majorTA, Pos.TOP_CENTER);
                //BorderPane.setAlignment(hlVB, Pos.TOP_CENTER);
                break;
            default://Won't be reached
                break;
        }
    }

    //Listens for which Campus is selected at welcTxt screen
    private void ccBListener() {
        bp.setCenter(null);
        bp.setTop(cbVB);
        infoTxt.setVisible(true);
        iCB.setVisible(true);
        cCB.setValue(wCB.getValue());
    }
    /*END LISTENERS FOR UI*/

    public BorderPane getBp() {
        return bp;
    }
}
