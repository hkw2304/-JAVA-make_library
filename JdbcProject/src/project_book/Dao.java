package project_book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "system";
	String password = "oracle";
	String driver = "oracle.jdbc.driver.OracleDriver";

	public void selectAll() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, password);
		System.out.println("오라클 연결 성공");
		String sql = "select * from book";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			System.out.println("─────────────────────────────");
			System.out.println("             제목 : " + rs.getString("title"));
			System.out.println("             저자 : " + rs.getString("author"));
			System.out.println("             가격 : " + rs.getInt("price"));
			System.out.println("             상태 : " + rs.getString("state"));
			System.out.println("─────────────────────────────");
		}
		rs.close();
		pstmt.close();
		con.close();

	}
	public void select(String bookName) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, password);
		System.out.println("오라클 연결 성공");
		String sql = "select * from book where title = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bookName);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			System.out.println("─────────────────────────────");
			System.out.println("             제목 : " + rs.getString("title"));
			System.out.println("             저자 : " + rs.getString("author"));
			System.out.println("             가격 : " + rs.getInt("price"));
			System.out.println("             상태 : " + rs.getString("state"));
			System.out.println("─────────────────────────────");
		}
		else {
			System.out.println("찾으시는 도서가 없습니다.");
		}
		rs.close();
		pstmt.close();
		con.close();

	}

	public void insert(Sub sub) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, password);
		System.out.println("오라클 연결 성공");
		String sql = "insert into book values(?,?,?,?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, sub.getTitle());
		pstmt.setString(2, sub.getAuthor());
		pstmt.setInt(3, sub.getPrice());
		pstmt.setString(4, sub.getState());

		int result = pstmt.executeUpdate();
		if (result > 0)
			System.out.println("추가 성공");
	}

	public void update(String book) throws SQLException, ClassNotFoundException {
		String sql = null;
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, password);
		System.out.println("오라클 연결 성공");
		sql = "select state from book where title = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			if (rs.getString("state").equals("대여불가능")) {
				System.out.println("이미 대여중입니다.");
			} else {
				sql = "update book set state = '대여불가능' where title = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, book);
				int result = pstmt.executeUpdate();
				if (result > 0)
					System.out.println("대여완료");
			}
		}
		else
			System.out.println("찾으시는 도서가 없습니다.");
	}

	public void input(String book) throws ClassNotFoundException, SQLException {
		String sql = null;
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, password);
		System.out.println("오라클 연결 성공");
		sql = "select state from book where title = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, book);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			if (rs.getString("state").equals("대여가능")) {
				System.out.println("잘못된 도서 입니다.");
			} else {
				sql = "update book set state = '대여가능' where title = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, book);
				int result = pstmt.executeUpdate();
				if (result > 0) {
					System.out.println("반납완료");
				}
			}
		}
		else
			System.out.println("잘못된 도서입니다.");
	}
	public void rental() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		con = DriverManager.getConnection(url,user,password);
		String sql = "select * from book where state = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, "대여가능");
		rs = pstmt.executeQuery();
		System.out.println("__________________________");
		System.out.println("        <<대여 가능 목록>>");
		while(rs.next()) {
			System.out.println(rs.getString("title"));
		}
		System.out.println("__________________________");
	}

}
