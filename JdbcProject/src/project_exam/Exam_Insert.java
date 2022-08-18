package project_exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Exam_Insert {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "oracle";
		String driver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,user,password);
			System.out.println("오라클 연결 성공");
			
			System.out.print("번호 입력 > ");
			int num = sc.nextInt();
			System.out.print("이름 입력 > ");
			String name = sc.next();
			System.out.print("이메일 입력 > ");
			String email = sc.next();
			System.out.print("저장할 핸드폰번호 > ");
			String tel = sc.next();
			
			
			
			String sql = "insert into custmer values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, tel);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("입력성공");
			}
			else
				System.out.println("입력실패");
			
			pstmt.close();
			con.close();
						
		}
		catch(Exception e) {
			e.printStackTrace();
		}
				

	}

}
