

package calendar;

import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 *
 * @author german
 */
public class commitment {

    LocalDate date;
    String text; //info about the event
    int id;

    public commitment() {
        date = null;
        text = null;
        id = -1;
    }

    public void getCommitment() {
        Stage stage = new Stage();
        myCalendarDialog dialog = new myCalendarDialog();
        dialog.start(stage);
        dialog.setCapture(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setData(dialog.getData());
                dialog.close();
            }
        });
    }

    private void setData(Object[] data) {
        LocalDate date = (LocalDate) data[0];
        String text = (String) data[1];
        this.date = date;
        this.text = text;
    }

    public commitment(LocalDate date, String text, int id) {
        this.date = date;
        this.text = text;
        this.id = id;
    }

    @Override
    public String toString() {
        return date + "\n" + text;
    }
}