package rent.service;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.InitController;
import rent.dao.RentDAO;
import rent.dao.RentDAOImpl;

public class RentServiceImpl implements RentService{
	
	public RentDAO dao = new RentDAOImpl();
	
	@Override
	public Parent backMain(Stage primaryStage, String str, String userid) {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource(str));
		Parent root = null;
//		HashMap<String, Object> map = info.backMain();
		
		try {
			root = loader.load();
			primaryStage.setScene(new Scene(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InitController ctl = loader.getController();
		
		ctl.setRoot(root);
		ctl.setUserId(userid);
		
		
		primaryStage.show();
		
		return root;
	}

	@Override
	public int rentBook(int key, String userid) {
		// TODO Auto-generated method stub
		int chk = dao.rentBook(key, userid);
		return chk;
	}

}
