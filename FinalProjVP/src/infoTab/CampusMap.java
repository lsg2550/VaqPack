package infoTab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class CampusMap {

    /*For editing map displayed when making changes to Campus*/
    public void mapCAMP(ChoiceBox<String> cb, Text txt, ImageView iv, TextArea ta) {
        Image img;
        switch (cb.getValue()) {
            case "Brownsville":
                img = new Image("itemsReq/utbMAP.PNG");
                mapBROW(txt, img, iv);
                contactInfB(ta);
                break;
            case "Edinburg":
                img = new Image("itemsReq/utpaMAP.PNG");
                mapEDIN(txt, img, iv);
                contactInfE(ta);
                break;
            default://Tested this, won't be reached for some reason
                break;
        }
    }

    //Brownsville is selected
    private void mapBROW(Text txt, Image img, ImageView iv) {
        txt.setText("Brownsville Map");
        iv.setImage(img);
    }

    //Edinburg is selected
    private void mapEDIN(Text txt, Image img, ImageView iv) {
        txt.setText("Edinburg Map");
        iv.setImage(img);
    }

    //Emergency Contact Information
    private void contactInfB(TextArea ta) {
        ta.clear();
        try {
            Scanner s = new Scanner(new File("itemsReq/campEMERb.txt"));
            while (s.hasNext()) {
                ta.appendText(s.nextLine() + "\n");
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }

    //Emergency Contact Information
    private void contactInfE(TextArea ta) {
        ta.clear();
        try {
            Scanner s = new Scanner(new File("itemsReq/campEMERe.txt"));
            while (s.hasNext()) {
                ta.appendText(s.nextLine() + "\n");
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }
}
