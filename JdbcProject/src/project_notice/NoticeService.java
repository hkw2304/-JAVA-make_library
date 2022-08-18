package project_notice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class NoticeService {
	Scanner sc = new Scanner(System.in);
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "system";
	private String password = "oracle";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	public void insert() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver 로딩 성공");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");
			
			System.out.print("TITLE : ");
			String title = sc.next();
			System.out.print("WRITER_ID : ");
			String writer_id =sc.next();
			System.out.print("CONTENT : ");
			String content = sc.next();
			System.out.print("HIT : ");
			int hit = sc.nextInt();
			
			String sql = "INSERT INTO NOTICE(TITLE, WRITER_ID,CONTENT,HIT,ID)"
					+ " VALUES(?,?,?,?,ACCOUNT_SEQ.NEXTVAL)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer_id);
			pstmt.setString(3, content);
			pstmt.setInt(4, hit);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("입력 완료");
			}
			con.close();
			pstmt.close();

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		}
	}
	
	public void select() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver 로딩 성공");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");
			
			String sql = "SELECT * FROM NOTICE WHERE HIT > 9";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("TITLE"));
				System.out.println(rs.getInt("ID"));
				System.out.println(rs.getString("WRITER_ID"));
				System.out.println(rs.getDate("REGDATE"));
				System.out.println(rs.getString("CONTENT"));
				System.out.println(rs.getInt("HIT"));
				System.out.println("======================");
			}

			pstmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		}
	}

	public void update() {
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("jdbc driver 로딩 성공");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");
			
			System.out.print("UpDate : ");
			String title = sc.next();
			System.out.print("Update Where : ");
			String uptitle = sc.next();
			
			String sql = "UPDATE notice"
					+ " SET"
					+ " TITLE = ?"
					+ " WHERE"
					+ " TITLE = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, uptitle);

			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("수정 완료");
			}

			pstmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		}
		
	}

	public void delete() {
		try {
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("jdbc driver 로딩 성공");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");
			
			System.out.print("Delete Where : ");
			String title = sc.next();
			
			String sql = "DELETE FROM NOTICE WHERE TITLE = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
	
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("삭제 완료");
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