package WeeklyScheduler;

import java.util.List;

public class CourseDetails {
    private String courseP;
    private String courseD;
    private String location;
    private boolean[] day = {false, false, false, false, false};
    private String start;
    private String end;
        
    public CourseDetails() {
    }
        
    public CourseDetails(String courseP, String courseD, String location, 
            boolean[] day, String start, String end) {
        this.courseP = courseP;
        this.courseD = courseD;
        this.location = location;
        this.day = day;
        this.start = start;
        this.end = end;
    }
    
    public int[][] convertTimeToInt(List<CourseDetails> x) {
        int[][] y = new int[x.size()][2];
        for (int i = 0; i < x.size(); i++) {
            String[] tempStart = x.get(i).getStart().split("[:, ]");
            String[] tempEnd = x.get(i).getEnd().split("[:, ]");
            y[i][0] = Integer.parseInt(tempStart[0] + tempStart[1]);
            y[i][1] = Integer.parseInt(tempEnd[0] + tempEnd[1]);
            if (!tempStart[0].contains("12") && tempStart[2].contains("PM"))
                y[i][0] += 1200;
            if (!tempEnd[0].contains("12") && tempEnd[2].contains("PM"))
                y[i][1] += 1200;
        }
        return y;
    }
    
    public String checkInterference(List<CourseDetails> x) {
        String warning = "";
        int[][] y = convertTimeToInt(x);
        for (int i = x.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (x.get(i) != null) {
                    if (sameDay(x.get(i).getDay(), x.get(j).getDay()) && 
                            twoClassesInterfere(y[i], y[j]))
                        warning += "The classes " + x.get(i).getCourseP() + " and " 
                                + x.get(j).getCourseP() + " interfere.;";
                }
            }
        }
        return warning;
    }
    
    private boolean twoClassesInterfere(int[] x, int[] y) {
        return (x[0] >= y[0] && x[0] <= y[1]) || (y[0] >= x[0] && y[0] <= x[1]);
    }
    
    private boolean sameDay(boolean[] x, boolean[] y) {
        for (int i = 0; i < 5; i++)
            if (x[i] == true && x[i] == y[i])
                return true;
        return false;
    }

    public String getCourseP() {
        return courseP;
    }

    public void setCourseP(String courseP) {
        this.courseP = courseP;
    }

    public String getCourseD() {
        return courseD;
    }

    public void setCourseD(String courseD) {
        this.courseD = courseD;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean[] getDay() {
        return day;
    }

    public void setDay(boolean[] day) {
        this.day = day;
    }
    
    public void setSpecificDays(String days) {
        for (int i = 0; i < days.length(); i++)
            this.day[Integer.parseInt(days.substring(i, i + 1))] = true;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

}

