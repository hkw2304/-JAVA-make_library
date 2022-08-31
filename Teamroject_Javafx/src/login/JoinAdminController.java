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
import service3.JoinService;
import service3.JoinServiceImpl;

public class JoinAdminController extends InitController implements Initializable{
	
	private Parent root;
	private CommonService cs;
	private DAO dao;
	private JoinService js;
	
	private String id5;
	public void setUserId(String id5) {
		this.id5 = id5;
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cs = new CommonServiceImpl();
		dao = new DAOImpl();
		js = new JoinServiceImpl();
	}
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}
	
	public void exitProc(ActionEvent event) {
		cs.windowClose(event);
	}
	
}
