package userMain;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import login.InitController;
import service3.CommonService;
import service3.CommonServiceImpl;
import userMain.service.MainService;
import userMain.service.MainServiceImpl;

public class MainController extends InitController implements Initializable{
	
	private Parent root;
	private CommonService cs;
	@FXML ImageView img1, img2, img3, img4, bookImage, bookImage2;
	@FXML VBox vbox1, vbox2, vbox3, vbox4;
	//			책 이름
	@FXML Label txtid1, txtid2, txtid3, hideid1, hideid2, hideid3, hidesrc;
	@FXML private TextField txtmsg;
	@FXML private TableView<BookSub> tableView;
	@FXML private TableColumn<BookSub, Integer> table_No;
	@FXML private TableColumn<BookSub, String> table_Title, table_Author, table_Publisher, table_Coment, table_Img;
	@FXML private TextField bookAuthor, bookComent, bookNo, bookPublisher, bookTitle, bookState;
	Alert alert;
	ObservableList<BookSub> list = FXCollections.observableArrayList();
	public HashMap<String, Object> map;
	private MainService ms;
	private String userid;
	
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}
	
	@Override
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.userid = userId;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		cs = new CommonServiceImpl();
		ms = new MainServiceImpl();
		
		HashMap<String, Object> map = setting();
		vbox1.setOnMouseClicked(event -> click(map.get("bno_1").toString() , event));
		vbox2.setOnMouseClicked(event -> click(map.get("bno_2").toString() , event));
		vbox3.setOnMouseClicked(event -> click(map.get("bno_3").toString() , event));
		
		tableView.setOnMouseClicked(e -> {
			if (e.getClickCount()>1) {
				bookNo.setText(tableView.getSelectionModel().getSelectedItem().getBno().toString());
				bookTitle.setText(tableView.getSelectionModel().getSelectedItem().getBname());
				bookAuthor.setText(tableView.getSelectionModel().getSelectedItem().getAuthor());
				bookPublisher.setText(tableView.getSelectionModel().getSelectedItem().getPublisher());
				bookImage.setImage(new Image(tableView.getSelectionModel().getSelectedItem().getImg()));
				bookImage2.setImage(new Image(tableView.getSelectionModel().getSelectedItem().getImg()));
				
			}
		});
	}
	
	private HashMap<String, Object> setting() {
		System.out.println("maincontroller");
		// TODO Auto-generated method stub
		// 라벨을 안보이게 숨김
		// 숨긴 라벨의 id값을 이용하여 DB에서 받아온 값을 사용
		hideid1.setVisible(false);
		hideid1.setManaged(false);
		hideid2.setVisible(false);
		hideid2.setManaged(false);
		hideid3.setVisible(false);
		hideid3.setManaged(false);
		
		map = ms.setImg();
		//이미지
		img1.setImage(new Image(map.get("img_1").toString()));
		img2.setImage(new Image(map.get("img_2").toString()));
		img3.setImage(new Image(map.get("img_3").toString()));
		
		//라벨
		txtid1.setText(map.get("bname_1").toString());
		txtid2.setText(map.get("bname_2").toString());
		txtid3.setText(map.get("bname_3").toString());
		
		//hide값 label에 값을 숨겨 기본키를 숨겨두어 사용
		hideid1.setText(map.get("bno_1").toString());
		hideid2.setText(map.get("bno_2").toString());
		hideid3.setText(map.get("bno_3").toString());
		
		return map;
	}

	private void click(String bno, MouseEvent event) {	//클릭한 이미지의 기본키 값을 전달
		// TODO Auto-generated method stub
		Stage s = (Stage) hideid1.getScene().getWindow();
		ms.openLentPage(s, "/rent/RentForm.fxml", bno, userid);
	}
	
	public void clickrent() {
		List<BookSub> rlist = new ArrayList<BookSub>();
		
		int bno = Integer.parseInt(bookNo.getText());
		String bname = bookTitle.getText();
		String author = bookAuthor.getText();
		String publisher = bookPublisher.getText();
		String b_coment = "";
		String img = "";
		
		rlist.add(new BookSub(bno,bname,author,publisher,b_coment, img));
		if(ms.clickrent(rlist, userid)>0) {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("대여 성공");
			alert.setContentText("대여를 성공했습니다.\n반납일은 일주일 후 입니다.");
			alert.show();
		} else {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("등록 실패");
			alert.setContentText("대여를 실패했습니다.");
			alert.show();
		}
	}
	
	public void close() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/userLogin.fxml", userid);
	}
	
	public void clickSelect() {
		HashMap<String, Object> clk_map = new HashMap<String, Object>();
		clk_map = ms.clickSelect(txtmsg.getText());
		if(!clk_map.isEmpty()) {
			bookNo.setText(clk_map.get("bookNo").toString());
			bookTitle.setText(clk_map.get("bookTitle").toString());
			bookAuthor.setText(clk_map.get("bookAuthor").toString());
			bookPublisher.setText(clk_map.get("bookPublisher").toString());
			bookImage.setImage(new Image(clk_map.get("bookImage").toString()));
			bookImage2.setImage(new Image(clk_map.get("bookImage2").toString()));
			txtmsg.setText("");
		}
		
		
	}
	
	public void clickSelectAll() {
		
		ObservableList<BookSub> list = ms.clickSelectAll();
		
		tableView.getItems().clear();
		//테이블 열에 입력을 위한 코드
		table_No.setCellValueFactory(new PropertyValueFactory<BookSub, Integer>("bno"));
		table_Title.setCellValueFactory(new PropertyValueFactory<BookSub, String>("bname"));
		table_Author.setCellValueFactory(new PropertyValueFactory<BookSub, String>("author"));
		table_Publisher.setCellValueFactory(new PropertyValueFactory<BookSub, String>("publisher"));
		table_Coment.setCellValueFactory(new PropertyValueFactory<BookSub, String>("b_coment"));
		table_Img.setCellValueFactory(new PropertyValueFactory<BookSub, String>("img")); // 이미지 숨김
		tableView.setItems(list);
	}
	
	public void goMy() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/myPage.fxml", userid);
	}
}
