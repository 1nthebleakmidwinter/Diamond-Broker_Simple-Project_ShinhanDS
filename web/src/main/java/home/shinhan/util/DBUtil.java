package home.shinhan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    public static Connection dbConnect() {
		// 1. JDBC Driver load
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid="hr";
		String password = "hr";
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. JDBC Driver load 성공");
			conn = DriverManager.getConnection(url, userid, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
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
		System.out.println("DB 연결 해제");
	}
}
