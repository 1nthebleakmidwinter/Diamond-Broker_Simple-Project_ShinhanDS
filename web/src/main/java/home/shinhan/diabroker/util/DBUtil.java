package home.shinhan.diabroker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    public static Connection dbConnnect() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String userid = "project1";
        String password = "winter";
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, userid, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void dbDisconnect(Connection conn, Statement st, ResultSet rs) {
        try {
            if(conn!=null) conn.close();
            if(st!=null) st.close();
            if(rs!=null) rs.close();
        } catch(SQLException e) {}
    }
}
