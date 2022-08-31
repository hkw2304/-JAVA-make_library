package dao3;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import login.Admin;
import login.Book;
import login.Rental;
import login.User;

public class DAOImpl implements DAO{
	
	Connection con;
	
	public DAOImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String user = "c##baek";
			String pwd = "1075";
			
			con = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insertUser(User u, int level) {
		try {
			String sql = "insert into buser values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getId());
			ps.setString(2, u.getPw());
			ps.setString(3, u.getName());
			ps.setString(4, u.getEmail());
			ps.setString(5, u.getBirth());
			ps.setInt(6, level);
			
			int res = ps.executeUpdate();
			
			if (res>=1) {
				return true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkLogin(String id, String pw) {
		try {
			String sql = "select count(*) from buser where id=? and pw=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			int res = rs.getInt(1);
			if (res==1) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkAdminLogin(String id, String pw) {
		try {
			String sql = "select count(*) from buser where id=? and pw=? and ulevel = 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			int res = rs.getInt(1);
			if (res==1) {
				return true;
			}
			
		} catch (Exception e) {
			return false;
//			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<User> allUser() {
		List<User> list = new ArrayList<User>();
		try {
			String sql = "select * from buser";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				User u = new User();
				u.setName(rs.getString("name"));
				u.setId(rs.getString("id"));
				u.setPw(rs.getString("pw"));
				u.setEmail(rs.getString("email"));
				u.setBirth(rs.getString("birth"));
				list.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insertBook(Book b) {
		try {
			String sql = "insert into book values(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, b.getBno());
			ps.setString(2, b.getTitle());
			ps.setString(3, b.getAuthor());
			ps.setString(4, b.getPublisher());
			ps.setString(5, "");
			ps.setString(6, b.getB_coment());//코맨트
			ps.setInt(7, b.getBcnt());
			
			int res = ps.executeUpdate();
			
			if (res>=1) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifyBook(Book b) {
		try {
			String sql = "update book set bcnt=? where bno=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, b.getBcnt());
			ps.setInt(2, b.getBno());
			
			int res = ps.executeUpdate();
			
			if (res>=1) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteBook(Book b) {
		try {
			String sql = "delete from book where bno=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, b.getBno());
			
			int res = ps.executeUpdate();
			
			if (res>=1) {
				return true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Book> allBook() {
		List<Book> list = new ArrayList<Book>();
		try {
			String sql = "select * from book";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Book b = new Book();
				b.setBno(rs.getInt("bno"));
				b.setTitle(rs.getString("bname"));
				b.setAuthor(rs.getString("author"));
				b.setPublisher(rs.getString("publisher"));
				b.setBcnt(rs.getInt("bcnt"));
				b.setB_coment(rs.getString("b_coment"));
				
				list.add(b);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Book eachBook(int num) {
		try {
			String sql = "select * from book where bno=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Book book = new Book();
				book.setTitle(rs.getString("bname"));
				book.setBno(rs.getInt("bno"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setBcnt(rs.getInt("bcnt"));
				
				return book;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	

	

	@Override
	public List<Rental> myRental(String id5) {
		List<Rental> list = new ArrayList<Rental>();
		
		
		try {
			String sql = "select rno, bno, id, out_date, in_date, due_date, case when in_date is null then '미반납' else '반납' end from rental_book where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id5);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Rental r = new Rental();
				r.setRno(rs.getInt(1));
				r.setBno(rs.getInt(2));
				r.setId(id5);
				r.setOut_date(rs.getDate(4));
				r.setIn_date(rs.getDate(5));
				r.setDue_date(rs.getDate(6));
				r.setYn(rs.getString(7));
				
				list.add(r);
				
			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean idOk(String id) {	//관리자,사용자 회원가입시 id 중복체크
		// TODO Auto-generated method stub

		try {
			String sql = "select count(*) from buser where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			rs.next();
			int result = rs.getInt(1);
			if(result == 1) {
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean modifyBannap(int rno, String id5) {
		try {
			String sql = "update rental_book set in_date=sysdate where rno=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, rno);
			
			int res = ps.executeUpdate();
			
			if (res>=1) {
				return true;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User mypageLogin(String id, String pw) {
		try {
			String sql = "select * from buser where id=? and pw=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				User u = new User();
				u.setName(rs.getString("name"));
				u.setId(rs.getString("id"));
				u.setPw(rs.getString("pw"));
				u.setBirth(rs.getString("birth"));
				u.setEmail(rs.getString("email"));
				
				return u;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean mypageModify(User u, String id5) {
		try {
			String sql = "update buser set name=?, pw=?, birth=?, email=? where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getName());
			ps.setString(2, u.getPw());
			ps.setString(3, u.getBirth());
			ps.setString(4, u.getEmail());
			ps.setString(5, id5);
			
			int res = ps.executeUpdate();
			
			if (res>=1) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUser(String id, String pw) {
		try {
			String sql = "delete from buser where id=? and pw=?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, id);
			ps.setString(2, pw);
			
			int res = ps.executeUpdate();
			
			if (res>=1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
