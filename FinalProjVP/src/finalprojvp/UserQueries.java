/*
 *  
 *    
 * 
 */
package finalprojvp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Joaquin
 */
public class UserQueries {
    
   
    
    private static final String URL = "jdbc:mysql://localhost:3306/log";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    
    private Connection connection = null; //manages conncection 
    private ResultSet myresultset = null;
    private PreparedStatement insertNewUser = null;
    private Statement mystatement = null;
    
    //constructor
    public UserQueries(){
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            

            //Create insert that adds a new entry into the database
            insertNewUser = connection.prepareStatement(
                "INSERT INTO userInfo " + "(StudentID, Password, FirstName, LastName, Email, PhoneNumber,"
                        + "Address, City, State, Zip, Major, Classification) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );
        }//end try
        catch (SQLException sqlException){
            sqlException.printStackTrace();
            System.exit(1);
        } //end catch
    } //end UserQueries constructor 
    

    
    //add an entry
    public void addUser(String id, String password, String fname, String lname, String email,
            String num, String address, String city, String state, String zip, 
            String major, String classification)
    {
      
        
        //set parameters, then execute insertNewUser
        try
        {
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
        catch ( SQLException sqlException )
        {
            sqlException.printStackTrace();
            close();
        } //end catch
        
       
    } //end method addUser
    
    //close the datebase connection
    public void close()
    {
        try
        {
            connection.close();
        } //end try
        catch ( SQLException sqlException )
        {
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
    
     public int passwordAuthentication(String id, String password)
    {
        try {
            mystatement = connection.createStatement();
            myresultset = mystatement.executeQuery("SELECT * FROM userInfo"); 

            while (myresultset.next()) {
                if (myresultset.getString("StudentID").equals(id))
                {
                    
                     if (myresultset.getString("Password").equals(password))
                     {
                         
                         return 1;
                }
                     
                }
        }
         
    }
        catch (SQLException e)
                {
                   
            System.err.print(e);
                } 
        
        return -1;
   } 
   
} //end class UserQueries
