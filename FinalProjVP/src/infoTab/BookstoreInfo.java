package infoTab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class BookstoreInfo {

    /*For editing TextArea when making changes to Campus*/
    public void bkstrCAMP(ChoiceBox<String> cb, TextArea ta) {
        switch (cb.getValue()) {
            case "Brownsville":
                bkstrBROW(ta);
                break;
            case "Edinburg":
                bkstrEDIN(ta);
                break;
            default://Tested this, won't be reached for some reason
                break;
        }
    }

    //Display Brownsville Message in TextArea
    private void bkstrBROW(TextArea ta) {
        ta.clear();
        try {
            Scanner s = new Scanner(new File("itemsReq/bkstrBROW.txt"));
            while (s.hasNext()) {
                ta.appendText(s.nextLine() + "\n");
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }

    //Display Edinburg Message in TextArea
    private void bkstrEDIN(TextArea ta) {
        ta.clear();
        try {
            Scanner s = new Scanner(new File("itemsReq/bkstrEDIN.txt"));
            while (s.hasNext()) {
                ta.appendText(s.nextLine() + "\n");
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
    }
}
