package login;

import javafx.application.Application;
import javafx.stage.Stage;
import service3.CommonService;
import service3.CommonServiceImpl;


public class LoginMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		CommonService cs = new CommonServiceImpl();
		cs.showWindow(primaryStage, "/login/userLogin.fxml", "");
		
	}
	
	
	public static void main(String[] args) {
		launch();
	}
}
