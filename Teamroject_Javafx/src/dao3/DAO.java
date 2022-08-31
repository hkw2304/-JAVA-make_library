package dao3;

import java.sql.ResultSet;
import java.util.List;

import login.Admin;
import login.Book;
import login.Rental;
import login.User;

public interface DAO {

	public boolean insertUser(User u, int level); // 회원을 등록하는 메서드 level 1이면 관리자 0이면 회원
	// level값은 db에서 설정함
	public boolean checkLogin(String id, String pw); // 유저 로그인하는 메서드
	
	public boolean checkAdminLogin(String id, String pw); // 관리자 로그인하는 메서드
	
	public boolean insertBook(Book b); // 관리자 페이지에서 도서를 등록하는 메서드
	public boolean modifyBook(Book b); // 관리자 페이지에서 도서정보를 수정하는 메서드
	public boolean deleteBook(Book b); // 관리자 페이지에서 도서를 삭제하는 메서드
	
	
	
	
	// 지혜님이 만들어주신 메서드가 로그인한 계정이 로그인안한 다른 계정의 정보를 수정하거나 탈퇴가 가능하게 되어있어서
	// DAOImpl의 쿼리문을 일부 수정해서 로그인한 계정과 관련한 수정과 탈퇴와 정보띄우기만 가능하게 수정했습니다.
	public User mypageLogin(String id, String pw); // 마이페이지에서 회원정보 띄우는 메서드
	public boolean mypageModify(User u, String id5); // 마이페이지에서 회원정보 수정하는 메서드
	public boolean deleteUser(String id, String pw); // 마이페이지에서 회원탈퇴하는 메서드
	
	
	
	
	
	public List<User> allUser(); // 관리자 페이지에서 전체회원조회하는 메서드 
	public List<Book> allBook(); // 관리자 페이지에서 전체도서조회하는 메서드
	
	
	public Book eachBook(int num); // 관리자 페이지에서 조회한 도서 테이블뷰 클릭시 정보 띄우는 메서드
	
	 
	
	public List<Rental> myRental(String id5); // 마이페이지에서 자신이 대여한 목록을 확인하는 메서드
	
	public boolean idOk(String id);// 회원목록 회원가입의 DB에서 아이디 중복확인 메서드
	
	public boolean modifyBannap(int rno, String id5); // 마이페이지에서 자신이 대여한 미반납된 도서를 클릭시
	// 반납해주는 메서드
}
