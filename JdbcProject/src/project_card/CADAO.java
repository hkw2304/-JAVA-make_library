package project_card;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CADAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement st = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "system";
	String password = "oracle";
	
	public int insertMember(CADTO dto) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("jdbc driver 로딩 성공");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");

			String sql = "insert into member(id,pwd,name,inputdate) values(?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(3, dto.getInputdate());

			result = pstmt.executeUpdate();
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
		
		return result;
	}

	public String login(String id, String pwd) {
		int result = 0;
		String login = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("jdbc driver 로딩 성공");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");

			String sql = "select id.pwd from member where id = ? and pwd = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next()) 
				login = id;
			else
				login = "";
			
			rs.close();
			pstmt.close();
			con.close();

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		}
		catch(Exception e) {
			System.out.println(e.toString());
			return login;
		}
		return login;
	}

	public int deleteMember(String id, String pwd) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("jdbc driver 로딩 성공");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 연결 성공");

			String sql = "delete member where id = ? and pwd = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			result = pstmt.executeUpdate();
			
			con.commit(); //컴밋을 하네???
			pstmt.close();

		} catch (ClassNotFoundException e) {
			System.out.println("jdbc driver 로딩 실패");
		} catch (SQLException e) {
			System.out.println("오라클 연결 실패");
		}
		return result;
	}
	//계좌,카드 등록
	public int insertAsset(CADTO dto, String id, int BC) {
		int result = 0;
		int result1 = 0;
		String sql = null;
		if(BC == 1) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("jdbc driver 로딩 성공");
				con = DriverManager.getConnection(url, user, password);
				System.out.println("오라클 연결 성공");

				sql = "select * from account where id = ? and nickname = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, dto.getBankNick());
				result = pstmt.executeUpdate();
				
				if(result == 1) {
					System.out.println("중복입니다 다시 입력하세요.");
				}
				else {
					sql = "insert into account(id,accdiv,bank,accname,accno,accamount,nickname) values (?,?,?,?,?,?,?)";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.setString(2,dto.getAccDiv());
					pstmt.setString(3, dto.getBank());
					pstmt.setString(4, dto.getAccName());
					pstmt.setString(5, dto.getAccno());
					pstmt.setInt(6, dto.getAccAmount());
					pstmt.setString(7, dto.getBankNick());
					
					result1 = pstmt.executeUpdate();
					pstmt.close();
				}

			} catch (ClassNotFoundException e) {
				System.out.println("jdbc driver 로딩 실패");
			} catch (SQLException e) {
				System.out.println("오라클 연결 실패");
			}
		}
		if(BC == 2) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("jdbc driver 로딩 성공");
				con = DriverManager.getConnection(url, user, password);
				System.out.println("오라클 연결 성공");

				sql = "select * from account where id = ? and nickname = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, dto.getBankNick());
				result = pstmt.executeUpdate();
				
				if(result == 1) {
					System.out.println("중복입니다 다시 입력하세요.");
				}
				else {
					sql = "insert into card(id,card,cardname,cardno,nickname,cardamount) values (?,?,?,?,?,0)";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.setString(2, dto.getCard());
					pstmt.setString(3, dto.getCardName());
					pstmt.setString(4, dto.getCardno());
					pstmt.setString(5, dto.getCardNick());
					result1 = pstmt.executeUpdate();
					pstmt.close();
				}

			} catch (ClassNotFoundException e) {
				System.out.println("jdbc driver 로딩 실패");
			} catch (SQLException e) {
				System.out.println("오라클 연결 실패");
			}
		}
		return result1;
	}

	public int deleteAsset(String id, String cAname, String cAno, int bC) {
		
		return 0;
	}
}
