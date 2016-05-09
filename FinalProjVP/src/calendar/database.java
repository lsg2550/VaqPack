

package calendar;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 *
 * @author german
 */
public class database {

    static final Connection c = dbconnect();
    static final Statement s = getStatement();

    public static void store(String name, commitment[] commitments) {
        try {
            
            ResultSet rs = search(name);
            if (rs.next()) {
                s.executeUpdate("UPDATE data SET commitments = '" + Arrays.toString(commitments) + "' WHERE name = '" + name + "';");
            } else {
                s.executeUpdate("Insert into data values(null, '" + name + "', '" + Arrays.toString(commitments) + "');");
            }
        } catch (Exception e) {

        }
    }

    public static ResultSet search(String name) {
        try {
            ResultSet rs = s.executeQuery("SELECT * FROM data WHERE name = '" + name + "'");
            return rs;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private static Connection dbconnect() {
        try {
            Class.forName("java.sql.DriverManager");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/information", "root", "VegetaSanHer277");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    private static Statement getStatement() {
        try {
            return c.createStatement();
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }
}
