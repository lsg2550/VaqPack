package WeeklyScheduler;
public class Table {
    private int timeIncrement = 15;
    private CourseDetails[] courses;
    
    public Table() {
    } 
    
    public Table(int timeIncrement, CourseDetails[] courses) {
        this.timeIncrement = timeIncrement;
        this.courses = courses;
    }
    
    public String[][] createTable() {
        String[][] table = new String[60 / getTimeIncrement() * 14 + 1][6];
        setTimeFields(table);
        setClassesFields(table, getCourses());
        return table;
    }
    
    public void setTimeFields(String table[][]) {
        int minuteChanges = 60 / getTimeIncrement();
        int hourHand = 7;
        String hh_toString = "";
        int minuteHand;
        String am_pm = " AM";
        
        for (int row = 0; row < table.length; row++) {
            minuteHand = row % minuteChanges * getTimeIncrement();
            if (minuteHand == 0) {
                hourHand++;
                hh_toString = Integer.toString(hourHand);
                if (hourHand >= 10) {
                    if (hourHand == 12) {
                        am_pm = " PM";
                        hourHand -= 12;
                    }
                }
                else
                    hh_toString = "0" + hh_toString;
                table[row][0] = hh_toString + ":" + Integer.toString(minuteHand)
                        + "0" + am_pm;
            }
            else if (minuteHand < 10)
                table[row][0] = hh_toString + ":0" + 
                        Integer.toString(minuteHand) + am_pm;
            else
                table[row][0] = hh_toString + ":" + 
                        Integer.toString(minuteHand) + am_pm;
        }
    }
    
    public void setClassesFields(String[][] table, CourseDetails[] my) {
        String[] rows1 = new String[table.length];
        String[] rows2 = new String[my.length];
        String[] rows3 = new String[my.length];
        for (int i = 0; i < table.length; i++) {
            rows1[i] = table[i][0];
        }
        for (int i = 0; i < my.length; i++) {
            rows2[i] = my[i].getStart();
            rows3[i] = my[i].getEnd();
        }
        int[][] start = stringTimeToInt(rows2);
        int[][] end = stringTimeToInt(rows3);
        int[][] time = stringTimeToInt(rows1);
        
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < my.length; j++) {
                if (start[j][0] == time[i][0] && start[j][2] == time[i][2]) {
                    if (start[j][1] == time[i][1] || (start[j][1] > time[i][1]
                            && start[j][1] < time[i][1] + getTimeIncrement())) {
                        if (end[j][2] == 1 && end[j][0] != 12)
                            end[j][0] += 12;
                        if (start[j][2] == 1 && start[j][0] != 12)
                            start[j][0] += 12;
                        int fields = ((end[j][0] - start[j][0]) * 60 + 
                                (end[j][1] - start[j][1])) / getTimeIncrement() 
                                + 1;
                        for (int k = 0; k < fields; k++) {
                            for (int l = 0; l < 5; l++) {
                                if (my[j].getDay()[l] == true)
                                    table[i + k][l + 1] = " " +
                                            my[j].getCourseP() + " " + 
                                            my[j].getLocation() + " ";
                            }
                        }
                    }
                        
                }
            }
        }
    }
    
    public int[][] stringTimeToInt(String[] x) {
        int[][] y = new int[x.length][3];
        for (int i = 0; i < x.length; i++) {
            String[] z = x[i].split("[:, ]");
            if (z[2].contains("AM"))
                z[2] = "0";
            else
                z[2] = "1";
            for (int j = 0; j < y[i].length; j++) {
                y[i][j] = Integer.parseInt(z[j]);
            }
        }
        return y;
    }

    public int getTimeIncrement() {
        return timeIncrement;
    }

    public void setTimeIncrement(int timeIncrement) {
        this.timeIncrement = timeIncrement;
    }

    public CourseDetails[] getCourses() {
        return courses;
    }

    public void setCourses(CourseDetails[] courses) {
        this.courses = courses;
    }
}

