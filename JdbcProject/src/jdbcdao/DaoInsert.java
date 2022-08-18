package jdbcdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoInsert {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Statement st = null;
		 
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("jdbc driver 로딩 성공");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");
			
			String title = "Test8";
			String writer_id = "KIEIGHT";
			String content = "Test8";
			int hit = 14;
			String sql = "INSERT INTO notice ("
					+ "    title,"
					+ "    writer_id,"
					+ "    content,"
					+ "    hit,"
					+ "    id"
					+ ") VALUES (?,?,?,?, ACCOUNT_SEQ.NEXTVAL)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer_id);
			pstmt.setString(3, content);
			pstmt.setInt(4, hit);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("입력 완료");
			}

			pstmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		}

	}

}
