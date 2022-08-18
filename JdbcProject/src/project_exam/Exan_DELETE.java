package project_exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Exan_DELETE {

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
			String sql = "delete from custmer where num = ?";
			System.out.print("삭제할 번호 > ");
			int num = sc.nextInt();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			int result = pstmt.executeUpdate();
			if(result > 0)
				System.out.println("삭제 성공");
			else
				System.out.println("삭제 실패");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
