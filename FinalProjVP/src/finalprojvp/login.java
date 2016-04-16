/*
 *  
 *    
 * 
 */
package finalprojvp;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author Joaquin
 */
public class login 
{
    public static void main(String[] args) {
        Connection conn = null;
        
        
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/log","root","password");
            if(conn != null)
            {
                System.out.println("connected to database successfully");
            }
        }catch(Exception e)
        {
            System.err.print(e);
            System.out.println("not connected to database");
        }
        
    }
    
}
