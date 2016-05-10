/*
 *  
 *    
 * 
 */
package finalprojvp;

import WeeklyScheduler.CourseDetails;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Joaquin
 */
public class UserQueries {

    private ObservableList<CourseDetails> list = FXCollections.observableArrayList();
    private static final String URL = "jdbc:mysql://localhost:3306/log";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "zaq12345";

    private Connection connection = null; //manages conncection 
    private ResultSet myresultset = null;
    private ResultSet myresultset2 = null;
    private PreparedStatement insertNewUser = null;
    private PreparedStatement insertNewCourse = null;
    private PreparedStatement deleteCourses = null;
    private Statement mystatement = null;
    private Statement mystatement2 = null;

    //constructor
    public UserQueries() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           

            //Create insert that adds a new entry into the database
            insertNewUser = connection.prepareStatement(
                    "INSERT INTO userInfo " + "(StudentID, Password, FirstName, LastName, Email, PhoneNumber,"
                    + "Address, City, State, Zip, Major, Classification) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
        }//end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } //end catch
    } //end UserQueries constructor 

    //add an entry
    public void addUser(String id, String password, String fname, String lname, String email,
            String num, String address, String city, String state, String zip,
            String major, String classification) {

        //set parameters, then execute insertNewUser
        try {
            insertNewUser.setString(1, id);
            insertNewUser.setString(2, password);
            insertNewUser.setString(3, fname);
            insertNewUser.setString(4, lname);
            insertNewUser.setString(5, email);
            insertNewUser.setString(6, num);
            insertNewUser.setString(7, address);
            insertNewUser.setString(8, city);
            insertNewUser.setString(9, state);
            insertNewUser.setString(10, zip);
            insertNewUser.setString(11, major);
            insertNewUser.setString(12, classification);

            //inset the new entry; return # of rows updated
            insertNewUser.executeUpdate();
        } //end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } //end catch

    } //end method addUser

    //close the datebase connection
    public void close() {
        try {
            connection.close();
        } //end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } //end catch
    } // end method close 

    /**
     * @return the myresultset
     */
    public ResultSet getMyresultset() {
        return myresultset;
    }

    /**
     * @return the mystatement
     */
    public Statement getMystatement() {
        return mystatement;
    }

    /**
     * @param myresultset the myresultset to set
     */
    public void setMyresultset(ResultSet myresultset) {
        this.myresultset = myresultset;
    }

    /**
     * @param mystatement the mystatement to set
     */
    public void setMystatement(Statement mystatement) {
        this.mystatement = mystatement;
    }

    public int passwordAuthentication(String id, String password) {
        try {
            mystatement = connection.createStatement();
            myresultset = mystatement.executeQuery("SELECT * FROM userInfo");

            mystatement2 = connection.createStatement();
            myresultset2 = mystatement2.executeQuery("SELECT * FROM weeklySchedule WHERE StudentID='" + id + "'");
//                            searchDatabase(id);
//                            System.out.println(list.get(0).getCourseD() + " " + list.get(1).getCourseD());
                                         

            while (myresultset.next()) {
                if (myresultset.getString("StudentID").equals(id)) {

                    if (myresultset.getString("Password").equals(password)) {
                        
                           
                        return 1;
                    }

                }
            }

        } catch (SQLException e) {

            System.err.print(e);
        }

        return -1;
    }

    public void weeklyQueries() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //Create insert that adds a new entry into the database
            insertNewCourse = connection.prepareStatement(
                    "INSERT INTO weeklySchedule " + "(courseP, courseD, location, day, start, end, StudentID) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
        }//end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } //end catch
    } //end UserQueries constructor 

    //add a course
    public void addCourse(String courseP, String courseD, String location, String day, String start, String end, String StudentID) {
        //set parameters, then execute insertNewUser
//        weeklyQueries();
        try {
            insertNewCourse = connection.prepareStatement(
                    "INSERT INTO weeklySchedule " + "(courseP, courseD, location, day, start, end, StudentID) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            insertNewCourse.setString(1, courseP);
            insertNewCourse.setString(2, courseD);
            insertNewCourse.setString(3, location);
            insertNewCourse.setString(4, day);
            insertNewCourse.setString(5, start);
            insertNewCourse.setString(6, end);
            insertNewCourse.setString(7, StudentID);
            insertNewCourse.executeUpdate();
            //inset the new entry; return # of rows updated
//            insertNewUser.executeUpdate();
        } //end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        } //end catch

    } //end class UserQueries
    
    public void deleteCourse(String id) {
        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //Create insert that adds a new entry into the database
            deleteCourses = connection.prepareStatement(
                    "DELETE FROM weeklySchedule WHERE StudentID='" + id + "'");
            deleteCourses.executeUpdate();
        }//end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } //end catch
    }

    /**
     * @return the myresultset2
     */
    public ResultSet getMyresultset2() {
        return myresultset2;
    }

    /**
     * @param myresultset2 the myresultset2 to set
     */
    public void setMyresultset2(ResultSet myresultset2) {
        this.myresultset2 = myresultset2;
    }

    /**
     * @return the mystatement2
     */
    public Statement getMystatement2() {
        return mystatement2;
    }

    /**
     * @param mystatement2 the mystatement2 to set
     */
    public void setMystatement2(Statement mystatement2) {
        this.mystatement2 = mystatement2;
    }

    public boolean[] setDay(String day) {
        boolean[] y = {false, false, false, false, false};
        for (int i = 0; i < y.length; i++) {
            y[i] = day.charAt(i) == '1';
        }
        return y;
    }

    public ObservableList<CourseDetails> searchDatabase() {


        try {

            while (myresultset2.next()) {
                CourseDetails x = new CourseDetails();
                    String day = (myresultset2.getString("day"));
                    x.setCourseP(myresultset2.getString("courseP"));
                    x.setCourseD(myresultset2.getString("courseD"));
                    x.setLocation(myresultset2.getString("location"));
                    x.setDay(setDay(day));
                    x.setStart(myresultset2.getString("start"));
                    x.setEnd(myresultset2.getString("end"));
                    list.add(x);

            }
                
        } catch (SQLException e) {

            System.err.print(e);
        }
         return list;
    }

    /**
     * @return the list
     */
    public ObservableList<CourseDetails> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(ObservableList<CourseDetails> list) {
        this.list = list;
    }

}
