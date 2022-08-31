package login;

import java.net.URL;
import java.util.ResourceBundle;

import dao3.DAO;
import dao3.DAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service3.CommonService;
import service3.CommonServiceImpl;

public class LoginController extends InitController implements Initializable{

	private Parent root;
	private CommonService cs;
	private DAO dao;
	private String id5;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cs = new CommonServiceImpl();
		dao = new DAOImpl();
	}

	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void changeAdminProc() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/adminLogin.fxml", id5);
	}

	public void userLoginProc() {

		TextField userLoginId = (TextField) root.lookup("#loginId");
		PasswordField userLoginPw = (PasswordField) root.lookup("#loginPw");
		
		String id = userLoginId.getText();
		String pw = userLoginPw.getText();
		
		if (dao.checkLogin(id, pw)) {
			cs.errorBox("로그인", "성공", "로그인 성공");
			id5 = id;
			Stage s = (Stage) root.getScene().getWindow();
			cs.showWindow(s, "../userMain/MainForm.fxml", id5);
		} else {
			cs.errorBox("로그인", "실패", "로그인 실패");
		}
	}

	public void userJoinProc() {
		Stage s = new Stage();
		cs.showWindow(s, "../login/joinUser.fxml", id5);
	}

	public void exitProc(ActionEvent event) {
		cs.windowClose(event);
	}

	
	public void setUserId(String id5) {
		this.id5 = id5;
		
	}


}
