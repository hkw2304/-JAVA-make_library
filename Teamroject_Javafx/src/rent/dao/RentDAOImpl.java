package rent.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import javafx.scene.image.Image;

public class RentDAOImpl implements RentDAO{
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##baek";
	String password = "1075";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String sql = null;
	
	public HashMap<String, Object> backMain() {
		// TODO Auto-generated method stub
		int i=0;
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			sql = "select b.bname, a.bno, b.author, b.publisher, b.b_coment, nvl(img , '/img/no_image.png') as img, a.cnt "
					+ "from ( select count(*) as cnt, bno "
					+ "from rental_book "
					+ "group by bno "
					+ "order by count(*) desc )a "
					+ "join book b on a.bno = b.bno "
					+ "where rownum <= 3";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				i++;
				map.put("bno_"+i, rs.getString("bno"));
				map.put("bname_"+i, rs.getString("bname"));
				map.put("author_"+i, rs.getString("author"));
				map.put("publisher_"+i, rs.getString("publisher"));
				map.put("img_"+i, rs.getString("img"));
				map.put("b_coment_"+i, rs.getString("b_coment"));
			}
			
			return map;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return map;
	}

	@Override
	public int rentBook(int key, String userid) {
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			String sql = "insert into rental_book values(rno_seq.nextval, ?, ?, sysdate, '', sysdate+7)";
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setInt(1, key);
			pstmt.setString(2, userid);
			int res = pstmt.executeUpdate();
			
			if (res>=1) {
				return res;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return -1;
	}


}
