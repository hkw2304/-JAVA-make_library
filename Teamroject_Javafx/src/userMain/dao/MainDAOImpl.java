package userMain.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import userMain.BookSub;

public class MainDAOImpl implements MainDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##baek";
	String password = "1075";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String sql = null;
	Alert alert;
	HashMap<String, Object> map = new HashMap<String, Object>();
	
	@FXML private ImageView img1, img2, img3, bookImage, bookImage2;
	@FXML private VBox vbox1, vbox2, vbox3;
	@FXML private Label txtid1, txtid2, txtid3, txtid4, hideid1, hideid2, hideid3;
	@FXML private TextField txtmsg;
	@FXML private TableView<BookSub> tableView;
	@FXML private TableColumn<BookSub, Integer> table_No;
	@FXML private TableColumn<BookSub, String> table_Title, table_Author, table_Publisher, table_Coment;
	@FXML private TextField bookAuthor, bookComent, bookNo, bookPublisher, bookTitle, bookState;
	
	
	Image img = null;
	int cnt = 0;
	
	public void close() {
		System.exit(0);
	}
	
	//전체 검색 버튼 누를시 이벤트
	//db에 저장된 내용이 한번에 출력 버튼을 계속 누르면 기존에 있는 내용을 초기화 후 출력
	@Override
	public ObservableList<BookSub> clickSelectAll() {
		// TODO Auto-generated method stub
		Integer bno;
	    String bname;
	    String author;
	    String publisher;
	    String b_coment;
	    String img;
	    //테이블 뷰를 이용하기위한 배열 생성
		ObservableList<BookSub> list = FXCollections.observableArrayList();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("전체 검색 쿼리");
			sql = "select bno, bname, author, publisher, b_coment, nvl(img , '/img/no_image.png') as img from book";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bno = rs.getInt("bno");
				bname = rs.getString("bname");
				author = rs.getString("author");
				publisher = rs.getString("publisher");
				b_coment = rs.getString("b_coment");
				img = rs.getString("img");
				list.add(new BookSub(bno,bname,author,publisher,b_coment, img));
			}
			
			//cnt를 통해 다시 클릭시 기존에 있는 내용을 지우고 내용을 다시 받고 출력
			return list;
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	//기본검색기능
	public HashMap<String, Object> clickSelect(String bname) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println("단일 검색");
			System.out.println("bname = "+bname);
			sql = "select * from book where bname = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bname);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				map.put("bookNo", rs.getString("BNO"));
				map.put("bookTitle", rs.getString("BNAME"));
				map.put("bookAuthor", rs.getString("AUTHOR"));
				map.put("bookPublisher", rs.getString("PUBLISHER"));
//				map.put("bookState", rs.getString("STATE"));
				map.put("bookImage", rs.getString("IMG"));
				map.put("bookImage2", rs.getString("IMG"));
				map.put("txtmsg", "");
			} else {
				// Alert : 버튼 클릭시 새로운 메시지 창이 나온다.
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("도서 정보");
				alert.setContentText("도서 정보가 없습니다.");
				alert.show();
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
//		cServ = new CommServiceImpl();
//		System.out.println("연결");
//		map = setting();
//		vbox1.setOnMouseClicked(event -> imgClick(map.get("bno_1").toString() , event));
//		vbox2.setOnMouseClicked(event -> imgClick(map.get("bno_2").toString() , event));
//		vbox3.setOnMouseClicked(event -> imgClick(map.get("bno_3").toString() , event));
//	}
	
	public HashMap<String, Object> setting() { // 메인 화면에서 이미지 출력 시 책 빌린 횟수가 많은 책부터 출력
		// TODO Auto-generated method stub
		// 라벨을 안보이게 숨김
		// 숨긴 라벨의 id값을 이용하여 DB에서 받아온 값을 사용
		int i=0;
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
	
	public void clickrent(ActionEvent event) {
		Parent root;
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("bookrent.fxml"));
		try {
			root = loader.load();
			stage.setScene(new Scene(root));
			stage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String, Object> getInfo() {	// 메인 화면에서 이미지 출력 시 책 빌린 횟수가 많은 책부터 출력
		int i=0;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
//			sql = "select b.bname, a.bno, b.author, b.publisher, b.b_coment, nvl(img , '/img/no_image.png') as img, a.cnt "
//					+ "from ( select count(*) as cnt, bno "
//					+ "from rental_book "
//					+ "group by bno "
//					+ "order by count(*) desc )a "
//					+ "join book b on a.bno = b.bno "
//					+ "where rownum <= 3";
			sql = "select * from book where bno = ?";
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
	public int rent(List<BookSub> rlist, String userid) {
		// TODO Auto-generated method stub
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			sql = "insert into rental_book values (rno_seq.nextval, ?, ?, sysdate, '', sysdate+7)";
			// insert into rental_book values (rno, bno, id, out_date, in_date, due_date );
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rlist.get(0).getBno());
			pstmt.setString(2, userid);
			
			int res = pstmt.executeUpdate();
			return res;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public HashMap<String, Object> getInfo(String bno) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String sql = "select bno, bname, author, publisher, NVL(img , '/img/no_image.png') as img, b_coment from book where bno = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bno);
			
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			map.put("bno", rs.getString("bno"));
			map.put("bname", rs.getString("bname"));
			map.put("author", rs.getString("author"));
			map.put("publisher", rs.getString("publisher"));
			map.put("img", rs.getString("img"));
			map.put("b_coment", rs.getString("b_coment"));
			System.out.println(map.get("img"));
			return map;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return map;
	}

}
