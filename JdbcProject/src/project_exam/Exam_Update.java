package project_exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Exam_Update {

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
			String sql = null;
			System.out.print("수정할 번호 입력 > ");
			int selectNum = sc.nextInt();
			System.out.println("수정할 항목 : 1.이메일 / 2.전화번호");
			System.out.print("선택 > ");
			int select  = sc.nextInt();
			switch(select) {
			case 1:
				System.out.print("수정할 이메일 >>");
				String email = sc.next();
				sql = "update custmer set email = ? where num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, email);
				pstmt.setInt(2, selectNum);
				break;
			case 2:
				System.out.print("수정할 전화번호 >>");
				String tel = sc.next();
				sql = "update custmer set tel = ? where num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, tel);
				pstmt.setInt(2, selectNum);
				break;
			default:
				System.out.println("수정할 항목은 << 1~2번까지 >>");
			}
			
			int result = pstmt.executeUpdate();
			
			if(result > 0)
				System.out.println("업데이트 성공");
			else
				System.out.println("업데이트 실패");	
			
			pstmt.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
