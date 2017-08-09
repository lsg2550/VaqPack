/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weeklyscheduler;

/**
 *
 * @author Anton
 */
public class TableRows {

    private String timeCell, mondayCell, tuesdayCell, wednesdayCell,
            thursdayCell, fridayCell;

    public TableRows() {
        timeCell = "none";
        mondayCell = "none";
        tuesdayCell = "none";
        wednesdayCell = "none";
        thursdayCell = "none";
        fridayCell = "none";
    }

    public TableRows(String timeCell, String mondayCell, String tuesdayCell,
            String wednesdayCell, String thursdayCell, String fridayCell) {
        this.timeCell = timeCell;
        this.mondayCell = mondayCell;
        this.tuesdayCell = tuesdayCell;
        this.wednesdayCell = wednesdayCell;
        this.thursdayCell = thursdayCell;
        this.fridayCell = fridayCell;
    }

    /**
     * @return the timeCell
     */
    public String getTimeCell() {
        return timeCell;
    }

    /**
     * @param timeCell the timeCell to set
     */
    public void setTimeCell(String timeCell) {
        this.timeCell = timeCell;
    }

    /**
     * @return the mondayCell
     */
    public String getMondayCell() {
        return mondayCell;
    }

    /**
     * @param mondayCell the mondayCell to set
     */
    public void setMondayCell(String mondayCell) {
        this.mondayCell = mondayCell;
    }

    /**
     * @return the tuesdayCell
     */
    public String getTuesdayCell() {
        return tuesdayCell;
    }

    /**
     * @param tuesdayCell the tuesdayCell to set
     */
    public void setTuesdayCell(String tuesdayCell) {
        this.tuesdayCell = tuesdayCell;
    }

    /**
     * @return the wednesdayCell
     */
    public String getWednesdayCell() {
        return wednesdayCell;
    }

    /**
     * @param wednesdayCell the wednesdayCell to set
     */
    public void setWednesdayCell(String wednesdayCell) {
        this.wednesdayCell = wednesdayCell;
    }

    /**
     * @return the thursdayCell
     */
    public String getThursdayCell() {
        return thursdayCell;
    }

    /**
     * @param thursdayCell the thursdayCell to set
     */
    public void setThursdayCell(String thursdayCell) {
        this.thursdayCell = thursdayCell;
    }

    /**
     * @return the fridayCell
     */
    public String getFridayCell() {
        return fridayCell;
    }

    /**
     * @param fridayCell the fridayCell to set
     */
    public void setFridayCell(String fridayCell) {
        this.fridayCell = fridayCell;
    }

}
