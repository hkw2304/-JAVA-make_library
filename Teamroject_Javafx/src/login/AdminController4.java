package login;

import java.net.URL;
import java.util.ResourceBundle;

import dao3.DAO;
import dao3.DAOImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.Stage;
import service3.CommonService;
import service3.CommonServiceImpl;

public class AdminController4 extends InitController implements Initializable{
	
	private Parent root;
	private CommonService cs;
	private DAO dao;
	
	private String id5;
	public void setUserId(String id5) {
		this.id5 = id5;
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cs = new CommonServiceImpl();
		dao = new DAOImpl();
	}
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}
	
	public void logoutProc() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/adminLogin.fxml", id5);
	}
	
	public void admin2() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/admin2.fxml", id5);
	}
	
	public void admin3() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/admin3.fxml", id5);
	}
	
	public void admin1() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/admin1.fxml", id5);
	}
	
	public void admin5() {
		Stage s = (Stage) root.getScene().getWindow();
		cs.showWindow(s, "../login/admin5.fxml", id5);
	}

}
