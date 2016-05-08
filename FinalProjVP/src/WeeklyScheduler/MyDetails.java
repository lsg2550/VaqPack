package WeeklyScheduler;

import java.util.ArrayList;
import java.util.List;

public class MyDetails {
    public MyDetails() {
    }
    
    public PersonalInfo getMyPersonalInfo() {
        PersonalInfo me = new PersonalInfo("Anton", "Kovalyov", "0431945", 
                          "Spring 2016");
        return me;
    }
        
    public List<CourseDetails> getMyCourseDetails() {
        List<CourseDetails> my = new ArrayList<>();
        boolean[] class_0_1_2 = {false, true, false, true, false};
        boolean[] class_3_4 = {true, false, true, false, false};
        boolean[] class_5 = {false, false, true, false, false};
        my.add(new CourseDetails("CSCI-4335", "Computer Architecture", 
                "Main 1.514", class_0_1_2, "09:25 AM", "10:40 AM"));
        my.add(new CourseDetails("CSCI-3340", "Software Engineering I", 
                "Main 1.514", class_0_1_2, "10:50 AM", "12:05 PM"));
        my.add(new CourseDetails("CSCI-4318", "Cyber Security",  "LHSB 1.402",
                class_0_1_2, "01:40 PM", "02:55 PM"));
        my.add(new CourseDetails("CSCI-3336", "Organization of Program Lang", 
                "SETB 1.506", class_3_4, "10:50 AM", "12:05 AM"));
        my.add(new CourseDetails("CSCI-4310", "Design & Analysis of Algorithm", 
                "Main 1.508", class_3_4, "09:25 AM", "10:40 AM"));
        my.add(new CourseDetails("KINE-1229", "VolleyBall", "Garza Gym", 
                class_5, "07:20 PM", "09:50 PM"));
        return my;
    }
}
