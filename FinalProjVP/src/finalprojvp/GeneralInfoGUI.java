/*
 *  
 *    
 * 
 */
package finalprojvp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author Joaquin
 */
public class GeneralInfoGUI {
    

    //Create UI Components
    BorderPane bp = new BorderPane();
    Scene scene = new Scene(bp);
    VBox cbVB = new VBox();                         //verticalBox; For choicebox
    VBox diVB = new VBox();                         //defaultImage; For default image & text, or maybe for all images
    VBox wcVB = new VBox();                         //welcomeCenter; will have the campus choicebox above the welcTxt text
    VBox maVB = new VBox();                         //Going contain choicebox of majors and text
    VBox weVB = new VBox();                         //Contains web browser for student major department
    HBox hlHB = new HBox();                         //Contains hyperlink in bottom of borderpane

    //Create Children
    ChoiceBox<String> wCB = new ChoiceBox<>();      //Campus choicebox, for welcTxt since nodes can't share children
    ChoiceBox<String> cCB = new ChoiceBox<>();      //Campus choicebox
    ChoiceBox<String> iCB = new ChoiceBox<>();      //Information choicebox
    ChoiceBox<String> mCB = new ChoiceBox<>();      //Major Choicebox
    TextArea bsktrTA = new TextArea();              //Print bookstore schedule 
    TextArea contaTA = new TextArea();              //Emergency Contact info displayed next to respective campus map
    TextArea majorTA = new TextArea();              //Print information about selected major's department
    Text campTxt = new Text();                      //What campus the user is in
    Text infoTxt = new Text();                      //What type of information the user wants
    Text majoTxt = new Text();                      //Please select your major to view information about it
    Text cimgTxt = new Text();                      //Will be above the images
    Text welcTxt = new Text();                      //Text that will welcTxt the user in the middle of the screen

    //Declare Variable
    private Boolean campus;                         //Brownsville = true; Edinburg = false; No Campus Selected = Null;

    public void launch(Stage infoTab) {
        changeListener();
        //UI(infoTab);
    }

    public BorderPane UI(BorderPane bp) {
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

        //SET UI
        cbVB.getChildren().addAll(campTxt, cCB, infoTxt, iCB);
        maVB.getChildren().addAll(majoTxt, mCB);
        wcVB.getChildren().addAll(wCB, welcTxt);
        hlHB.setAlignment(Pos.CENTER);
        diVB.setAlignment(Pos.CENTER);
        wcVB.setAlignment(Pos.CENTER);
        infoTxt.setVisible(false);
        iCB.setVisible(false);

        bp.setStyle("-fx-background-image: url(itemsReq/oBG.gif); -fx-background-size: contain; -fx-background-position: center; -fx-background-repeat: no-repeat;");
        bp.setCenter(wcVB);
        
        
        return bp;
        /*
        infoTab.getIcons().add(new Image("itemsReq/utrgv.png"));
        infoTab.setTitle("Important Information");
        infoTab.setMaximized(true);
        infoTab.setScene(scene);
        infoTab.show();*/
    }

    /* BEGIN LISTENERS FOR UI */
    //Listens to changes within information, student major, campus etc etc...
    private void changeListener() {
        wCB.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            ccBListener();
        });

        cCB.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            try {
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
        });
    }

    //Listens for which information tab is selected
    private void infoListener() {
        //Clear previous cases
        bp.setBottom(null);
        bp.setRight(null);
        bp.setCenter(null);
        bp.setLeft(null);
        switch (iCB.getValue()) {
            case "Bookstore Info":
                //Set TextArea and insert into borderpane
                bsktrTA.setMaxHeight(350);
                bsktrTA.setMaxWidth(500);
                bsktrTA.setEditable(false);

                //bsi.bkstrTADEF(bsktrTA);
                bp.setCenter(bsktrTA);
                BorderPane.setAlignment(bsktrTA, Pos.CENTER);
                //Magic
                break;
            case "Campus Map":
                //Set TextArea and insert into borderpane
                contaTA.setMaxHeight(350);
                contaTA.setMaxWidth(500);
                contaTA.setEditable(false);

                bp.setRight(contaTA);
                bp.setCenter(diVB);
                BorderPane.setAlignment(diVB, Pos.CENTER);
                BorderPane.setAlignment(contaTA, Pos.CENTER);
                //Magic
                //cm.DEF(contaTA);
                break;
            case "Major Information":
                //Set TextArea and insert into borderpane
                majorTA.setMaxHeight(350);
                majorTA.setMaxWidth(500);
                majorTA.setEditable(false);

                bp.setCenter(majorTA);
                bp.setLeft(maVB);
                bp.setBottom(hlHB);
                BorderPane.setAlignment(hlHB, Pos.CENTER);
                BorderPane.setAlignment(maVB, Pos.CENTER);
                BorderPane.setAlignment(majorTA, Pos.CENTER);
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
        //campusListener();
    }
    /*END LISTENERS FOR UI*/
}
