package WeeklyScheduler;
import java.io.*;
public class WeeklySchedulerOut {
    private PersonalInfo student = new PersonalInfo();
    private int timeIncrement = 60;
    private CourseDetails[] courses = new CourseDetails[timeIncrement];
    
    public WeeklySchedulerOut() {
    }
    
    public WeeklySchedulerOut(PersonalInfo student, int timeIncrement, 
            CourseDetails[] courses) {
        this.student = student;
        this.timeIncrement = timeIncrement;
        this.courses = courses;
    }
    
    public void print() {
        String[][] table = new Table(getTimeIncrement(), 
                getCourses()).createTable();
        System.out.println("Student Schedule");
        System.out.print(getStudent().getfName() + " ");
        System.out.println(getStudent().getlName());
        System.out.println("ID: " + getStudent().getId());
        System.out.println(getStudent().getSemester() + "\n");
        String checkTimes = new CourseDetails().checkInterference(getCourses());
        if (!checkTimes.isEmpty()) {
            String[] warning = checkTimes.split(";");
            for (String warning1 : warning) {
                System.out.println(warning1);
            }
            System.exit(0);
        }
        System.out.println("\t\tMonday\t\t     Tuesday\t\t  "
                + "Wednesday\t\tThursday\t\tFriday");
        for (String[] table1 : table) {
            for (String table11 : table1) {
                if (table11 == null) {
                    System.out.print("                      ");
                } else {
                    System.out.print(table11);
                }
            }
            System.out.println();
        }
        System.out.println();
        for (CourseDetails course : getCourses()) {
            System.out.println(course.getCourseP() + ": " + 
                    course.getCourseD());
        }
    }
    
    public void outputFile() throws IOException {
        String outputName = student.getlName() + "_" + student.getfName() +
                "-Semester-Schedule.txt";
        FileWriter fileWriter = new FileWriter(outputName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        try (PrintWriter output = new PrintWriter(bufferedWriter)) {
            String[][] table = new Table(getTimeIncrement(), 
                    getCourses()).createTable();
            output.println("Student Schedule");
            output.print(getStudent().getfName() + " ");
            output.println(getStudent().getlName());
            output.println("ID: " + getStudent().getId());
            output.println(getStudent().getSemester() + "\n");
            output.println();
            String checkTimes = 
                    new CourseDetails().checkInterference(getCourses());
            if (!checkTimes.isEmpty()) {
                String[] warning = checkTimes.split(";");
                for (String warning1 : warning) {
                    output.println(warning1);
                }
            }
            else {
                output.println("\t\tMonday\t\t     Tuesday\t\t  "
                        + "Wednesday\t\tThursday\t\tFriday");
                for (String[] table1 : table) {
                    for (String table11 : table1) {
                        if (table11 == null) 
                            output.print("                      ");
                        else
                            output.print(table11);
                    }
                    output.println();
                }
                output.println();
                for (CourseDetails course : getCourses())
                    output.println(course.getCourseP() + ": " + 
                            course.getCourseD());
            }
        }
    }

    public PersonalInfo getStudent() {
        return student;
    }

    public void setStudent(PersonalInfo student) {
        this.student = student;
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
