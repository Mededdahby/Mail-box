package ServerSide;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    static {
        try {
            //TODO: JNDI methode ...!
        /* Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("com.mysql.cj.jdbc.Driver");
            conn = dataSource.getConnection("jdbc:mysql://localhost/Cabinier","root", ""));*/
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/BoitDialog", "root", "");
            System.out.println("Connected !");
        } catch (SQLException e) {
            System.out.println("SQL problems");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not exist");
        }
    }

    public static Connection getConnection() {
        return conn;
    }
}