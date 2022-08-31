package service3;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import login.InitController;

public class CommonServiceImpl implements CommonService{
	
	@Override
	public void windowClose(ActionEvent event) {
		Parent root = (Parent) event.getSource();
		Stage s = (Stage) root.getScene().getWindow();
		s.close();
		
	}

	@Override
	public Parent showWindow(Stage s, String fxml, String id5) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
		Parent root = null;
		try {
			root = loader.load();
			s.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		InitController ic = loader.getController();
		ic.setRoot(root);
		ic.setUserId(id5);
	
		
		s.show();
		
		return root;
	}

	@Override
	public void errorBox(String tltle, String header, String content) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle(tltle);
		a.setHeaderText(header);
		a.setContentText(content);
		a.showAndWait();
		
	}

	@Override
	public boolean isEmpty(String str) {
		if (str.isEmpty()) {
			return true;
		}
		return false;
	}

}
