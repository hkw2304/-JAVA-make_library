package login;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao3.DAO;
import dao3.DAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service3.CommonService;
import service3.CommonServiceImpl;

public class MyPageController extends InitController implements Initializable{
	
	private Parent root;
	private CommonService cs;
	private DAO dao;
	private String id5;
	
	public void setUserId(String id5) {
		this.id5 = id5;
		
	}
	
	@FXML TableColumn<Rental, String> myRno;
	@FXML TableColumn<Rental, String> myBno;
	@FXML TableColumn<Rental, String> myOut_date;
	@FXML TableColumn<Rental, String> myDue_date;
	@FXML TableColumn<Rental, String> myIn_date;
	@FXML TableColumn<Rental, String> myYn;
	@FXML Button bannap;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cs = new CommonServiceImpl();
		dao = new DAOImpl();
	}
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}

	public void goModifyMyInfo() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/myPage1.fxml", id5);
	}
	
	public void logoutProc(ActionEvent event) {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/myPage.fxml", id5);
	}

	public void cancelProc(ActionEvent event) {
		cs.windowClose(event);
	}

	public void mypage1() {
		// mypage0 -> 회원정보확인 전 id pw 확인
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/mypage1.fxml", id5);
	}

	public void mypage2() {
		// mypage1 -> 회원정보확인
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/mypage2.fxml", id5);
	}

	public void mypage3() {
		// mypage2 -> 회원정보수정
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/mypage3.fxml", id5);
	}

	public void mypage4() {
		// mypage3-> 회원정보 id pw 입력후 탈퇴
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/mypage4.fxml", id5);
	}

	
	
	
	public void mypageLogin() {
		TextField idCheck = (TextField) root.lookup("#idCheck");
		PasswordField pwCheck = (PasswordField) root.lookup("#pwCheck");

		String id = idCheck.getText();
		String pw = pwCheck.getText();
		
		User u = dao.mypageLogin(id,pw);
		
		
		if (id.equals(id5) && u.getPw().equals(pw)) {
			Stage s = (Stage) root.getScene().getWindow();
			root = cs.showWindow(s, "../login/myPage2.fxml", id5);
			mypageState(id, pw, root);
		} else {
			cs.errorBox("", "로그인한 계정과 일치하지 않음", "");
			
		}
		
	}


	public void mypageState(String id, String pw, Parent root) {

		User u = dao.mypageLogin(id,pw);
		System.out.println(id);
		System.out.println("test");
	
		Label uName = (Label) root.lookup("#myname");
		Label uId = (Label) root.lookup("#myid");
		Label uPw = (Label) root.lookup("#mypw");
		Label uBirth = (Label) root.lookup("#mybirth");
		Label uEmail = (Label) root.lookup("#myemail");

		uName.setText(u.getName());
		uId.setText(u.getId());
		uPw.setText(u.getPw());
		uBirth.setText(u.getBirth());
		uEmail.setText(u.getEmail());
		
		
	}


	public void modifyUser() {
		User u = new User();
		
		TextField modName = (TextField) root.lookup("#modName");
		PasswordField modPw = (PasswordField) root.lookup("#modPw");
		PasswordField modPwok = (PasswordField) root.lookup("#modPwok");
		TextField modBirth = (TextField) root.lookup("#modBirth");
		TextField modEmail = (TextField) root.lookup("#modEmail");

		String[] txtEmpty = {modName.getText(), modPw.getText(), modPwok.getText(),
				modBirth.getText(), modEmail.getText()};

		String[] txtEmptyName = {"이름", "비밀번호", "비밀번호확인", "생년월일", "이메일"};

		for (int i=0;i<txtEmpty.length;i++) {
			if (cs.isEmpty(txtEmpty[i])) {
				cs.errorBox("오류", "오류발견", txtEmptyName[i]+"창이 비었음");
				return;
			}
		}

		if (modPw.getText().equals(modPwok.getText())) {
			cs.errorBox("비밀번호수정", "비밀번호확인과 일치", "비밀번호 수정 가능합니다.");
		} else {
			cs.errorBox("비밀번호수정", "비밀번호확인과 불일치", "비밀번호 수정 불가능합니다.");
		}

		
		u.setName(modName.getText());
		u.setPw(modPw.getText());
		u.setBirth(modBirth.getText());
		u.setEmail(modEmail.getText());

		if (dao.mypageModify(u, id5)) {
			cs.errorBox("수정", "수정성공", "회원정보가 수정되었습니다.");

		} else {
			cs.errorBox("수정", "수정실패", "db 문제 발생");
			return;
		}

	}

	public void deleteUser() {

		TextField delId = (TextField) root.lookup("#delId");
		TextField delPw = (PasswordField) root.lookup("#delPw");

		String id = delId.getText();
		String pw = delPw.getText();

		if (id.equals(id5)) {
			if (dao.deleteUser(id, pw)) {
				cs.errorBox("탈퇴", "탈퇴성공", "탈퇴되었습니다.");
				Stage s = (Stage) root.getScene().getWindow();
				cs.showWindow(s, "../login/userLogin.fxml", id5);
			} else {
				cs.errorBox("탈퇴", "탈퇴실패","다시 시도해주세요.");
			}
		} else {
			cs.errorBox("", "로그인한 계정이 아님", "");
		}
		
		

	}
	
	
	
	
	
	
	
	public void goUserMain() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../userMain/Mainform.fxml", id5);
	}
	
	public void myRentalSearchProc() {
		
		List<Rental> list = dao.myRental(id5);
		
		TableView<Rental> myList = (TableView<Rental>) root.lookup("#myPageList");
		
		myRno.setCellValueFactory(new PropertyValueFactory<Rental, String>("rno"));
		myBno.setCellValueFactory(new PropertyValueFactory<Rental, String>("bno"));
		myOut_date.setCellValueFactory(new PropertyValueFactory<Rental, String>("out_date"));
		myIn_date.setCellValueFactory(new PropertyValueFactory<Rental, String>("in_date"));
		myDue_date.setCellValueFactory(new PropertyValueFactory<Rental, String>("due_date"));
		myYn.setCellValueFactory(new PropertyValueFactory<Rental, String>("Yn"));
		
		myList.getColumns().setAll(myRno, myBno, myOut_date, myIn_date, myDue_date, myYn);
		
		ObservableList<Rental> oList = FXCollections.observableArrayList(list);
		
		myList.setItems(oList);
		
		bannap.setOnAction(e -> {
			if (myList.getSelectionModel().isEmpty()) {
				cs.errorBox("", "반납할 도서가 선택되지 않음", "");
				return;
			} else {
				
				
				int selectedBno = myList.getSelectionModel().getSelectedItem().getBno();
				int selectedRno = myList.getSelectionModel().getSelectedItem().getRno();
				
				if (dao.modifyBannap(selectedRno, id5)) {
					cs.errorBox("", "반납이 정상적으로 이루어짐", "");
					myList.getItems().clear();
					
					List<Rental> bannapList = dao.myRental(id5);
					
					myRno.setCellValueFactory(new PropertyValueFactory<Rental, String>("rno"));
					myBno.setCellValueFactory(new PropertyValueFactory<Rental, String>("bno"));
					myOut_date.setCellValueFactory(new PropertyValueFactory<Rental, String>("out_date"));
					myIn_date.setCellValueFactory(new PropertyValueFactory<Rental, String>("in_date"));
					myDue_date.setCellValueFactory(new PropertyValueFactory<Rental, String>("due_date"));
					myYn.setCellValueFactory(new PropertyValueFactory<Rental, String>("Yn"));
					
					myList.getColumns().setAll(myRno, myBno, myOut_date, myIn_date, myDue_date, myYn);
					
					ObservableList<Rental> bannapoList = FXCollections.observableArrayList(bannapList);
					
					myList.setItems(bannapoList);
				} else {
					cs.errorBox("", "db수정중 문제발생", "");
				}
				
				
				
				
				
			}
		});
		
		
		
	}
}
