package infoTab;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    private final BorderPane bp = new BorderPane();
    private final Scene scene = new Scene(getBp());
    private final VBox cbVB = new VBox();                         //verticalBox; For choicebox
    private final VBox diVB = new VBox();                         //defaultImage; For default image & text, or maybe for all images
    private final VBox wcVB = new VBox();                         //welcomeCenter; will have the campus choicebox above the welcTxt text
    private final VBox weVB = new VBox();                         //Contains web browser for student major department
    private final VBox maVB = new VBox();                         //Will contain text and choicebox for major, will be inside Hbox
    private final HBox hlHB = new HBox();                         //Contains hyperlink with textarea in middle of borderpane for Major selection
    private final GridPane gp = new GridPane();                   //Contain website browser

    //Create Children
    private final ChoiceBox<String> wCB = new ChoiceBox<>();      //Campus choicebox, for welcTxt since nodes can't share children
    private final ChoiceBox<String> cCB = new ChoiceBox<>();      //Campus choicebox
    private final ChoiceBox<String> iCB = new ChoiceBox<>();      //Information choicebox
    private final ChoiceBox<String> mCB = new ChoiceBox<>();      //Major Choicebox
    private final TextArea bsktrTA = new TextArea();              //Print bookstore schedule 
    private final TextArea contaTA = new TextArea();              //Emergency Contact info displayed next to respective campus map
    private final Text campTxt = new Text();                      //What campus the user is in
    private final Text infoTxt = new Text();                      //What type of information the user wants
    private final Text majoTxt = new Text();                      //Please select your major to view information about it
    private final Text cimgTxt = new Text();                      //Will be above the images
    private final Text welcTxt = new Text();                      //Text that will welcTxt the user in the middle of the screen
    private final Button btn = new Button("Go To Site");
    private final TextField tf = new TextField("https://www.");

    //Grab images, default, brownsville, edinburg
    private final Image imgD = new Image("itemsReq/droid.png");
    private final Image imgB = new Image("itemsReq/utbMAP.PNG");
    private final Image imgE = new Image("itemsReq/utpaMAP.PNG");
    private final ImageView iV = new ImageView(imgD);

    //Hyperlink to major's department
    private final WebView webView = new WebView();
    private final WebEngine engine = webView.getEngine();
    private final Hyperlink majLink = new Hyperlink("http://www.utrgv.edu/en-us/");
    
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
        
        mCB.getItems().addAll("Accounting", "Anthropology", "Applied Arts and Sciences",
                "Art", "Biology", "Biomedical Sciences", "Chemistry", "Civil Engineering",
                "Clinical Laboratory Sciences", "Communication Sciences and Disorders",
                "Communication Studies", "Computational Science", "Computer Engineering",
                "Computer Information Systems Technology", "Computer Science", "Criminal Justice",
                "Criminology and Criminal Justice", "Dance", "Dietetics", "Early Care and Early Childhood",
                "Economics", "Electrical Engineering", "Engineering Physics",
                "Engineering Technology", "English", "Environmental Sciences",
                "Exercise Science", "Finance", "Health", "Health Services Technology",
                "History", "Information Systems", "Interdisciplinary Studies",
                "International Business", "Kinesiology", "Management", "Manufacturing Engineering",
                "Management", "Marketing", "Mass Communication", "Materials Management and Logistics",
                "Mathematics", "Mechnical Engineering", "Mexican American Studies",
                "Multidisciplinary Studies", "Music", "Nursing", "Performance",
                "Philosophy", "Physical Science", "Physics", "Political Science",
                "Psychology", "Rehabiliation Services - Deaf Studies", "Rehabiliation Services",
                "Social Studies Composite", "Social Work", "Sociology", "Spanish",
                "Spanish Translation and Interpreting", "Theater");

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

        //Fixing TextAreas
        bsktrTA.setMaxHeight(400);
        contaTA.setMaxHeight(400);
        bsktrTA.setMaxWidth(500);
        contaTA.setMaxWidth(500);
        bsktrTA.setEditable(true);
        contaTA.setEditable(true);

        //SET UI
        hlHB.setVisible(false);
        cbVB.getChildren().addAll(campTxt, cCB, infoTxt, iCB, hlHB);
        wcVB.getChildren().addAll(wCB, welcTxt);
        maVB.getChildren().addAll(majoTxt, mCB);
        diVB.getChildren().addAll(cimgTxt, iV);
        hlHB.getChildren().add(maVB);
        diVB.setAlignment(Pos.CENTER);
        wcVB.setAlignment(Pos.CENTER);
        infoTxt.setVisible(false);
        iCB.setVisible(false);
        
        bp.setStyle("-fx-background-image: url(itemsReq/oBG.gif); "
                + "-fx-background-size: contain; "
                + "-fx-background-position: center; "
                + "-fx-background-repeat: no-repeat;"
        );
        
        bp.setCenter(wcVB);
    }
    
    public BorderPane getBp() {
        return bp;
    }

    /* BEGIN LISTENERS FOR UI */
    //Listens to changes within information, student major, campus etc etc...
    private void changeListener() {
        wCB.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            ccBListener();
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
            smd.smD(mCB, majLink);
            engine.load(majLink.getText());
        });
        btn.setOnAction(e -> {
            majLink.setText(tf.getText());
            engine.load(majLink.getText());
        });
    }

    //Listens for which information tab is selected
    private void infoListener() {
        //Clear previous cases
        hlHB.setVisible(false);
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
                gp.add(tf, 0, 0);
                gp.add(btn, 1, 0);
                gp.setAlignment(Pos.TOP_CENTER);
                bp.setBottom(gp);
                
                hlHB.setVisible(true);
                bp.setCenter(webView);
                engine.load(majLink.getText());
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
    }/*END LISTENERS FOR UI*/
}
