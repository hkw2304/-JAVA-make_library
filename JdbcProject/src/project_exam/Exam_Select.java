package project_exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Exam_Select {

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "oracle";
		String driver = "oracle.jdbc.driver.OracleDriver";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			System.out.println("오라클 연결 성공");
			String sql = "select * from custmer";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("_____________개인정보______________");
			while(rs.next()) {
				System.out.println("번호 > " + rs.getInt("num"));
				System.out.println("이름 > " + rs.getString("name"));
				System.out.println("이메일 > " + rs.getString("email"));
				System.out.println("전화번호 > " + rs.getString("tel"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
}
