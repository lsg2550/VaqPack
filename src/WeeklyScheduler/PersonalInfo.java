package WeeklyScheduler;
public class PersonalInfo {
    private String fName;
    private String lName;
    private String id;
    private String semester;
        
    public PersonalInfo() {
    }
        
    public PersonalInfo(String fName, String lName, String id, 
        String semester) {
        this.fName = fName;
        this.lName = lName;
        this.id = id;
        this.semester = semester;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
