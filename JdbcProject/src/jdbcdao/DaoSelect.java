package jdbcdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoSelect {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Statement st = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "oracle";
		 
		try {
			Class.forName(driver);
			System.out.println("jdbc driver 로딩 성공");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");
			
			String sql = "SELECT * FROM NOTICE WHERE HIT > 9";
			st = con.createStatement();
			//출력시 ResultSet 을 사용
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(rs.getString("TITLE"));
				System.out.println(rs.getInt("ID"));
				System.out.println(rs.getString("WRITER_ID"));
				System.out.println(rs.getDate("REGDATE"));
				System.out.println(rs.getString("CONTENT"));
				System.out.println(rs.getInt("HIT"));
				System.out.println("======================");
			}
			st.close();
			rs.close();
			con.close();

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		}

	}

}
