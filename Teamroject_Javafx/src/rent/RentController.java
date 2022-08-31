package rent;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import login.InitController;
import rent.service.RentService;
import rent.service.RentServiceImpl;
import userMain.MainController;

public class RentController extends InitController implements Initializable{
	
	private Parent root;
	RentService rs;
	private String userid;
	@FXML Label bno;
	Alert alert;
	
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
	}
	
	public void setUserId(String userId) {
		// TODO Auto-generated method stub
		this.userid = userId;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		rs = new RentServiceImpl();
	}
	
	public void btnClose() {
		System.out.println("user= "+ userid);
		Stage s = (Stage) root.getScene().getWindow();
		rs.backMain(s, "/userMain/MainForm.fxml", userid);
	}
	
	public void rent() {
//		String bno = Integer.parseInt(bno.getText());
		int key = Integer.parseInt(bno.getText());
		System.out.println(bno.getText());
		if(rs.rentBook(key, userid) > 0) {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("대여 성공");
			alert.setContentText("대여를 성공했습니다.\n반납일은 일주일 후 입니다.");
			alert.show();
		} else {
			alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("대여 실패");
			alert.setContentText("대여를 실패했습니다.");
			alert.show();
		}
	}

}
