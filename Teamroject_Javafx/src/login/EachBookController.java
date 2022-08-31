package login;

import java.net.URL;
import java.util.ResourceBundle;

import dao3.DAO;
import dao3.DAOImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class EachBookController extends InitController implements Initializable{
	
	private Parent root;
	private DAO dao;
	
	private String id5;
	public void setUserId(String id5) {
		this.id5 = id5;
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = new DAOImpl();
		
	}
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}
	
	public void exitProc() {
		Stage s = (Stage) root.getScene().getWindow();
		s.close();
	}

}
