package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	StackPane pane = new StackPane();
	Scene scene = new Scene(pane, 800,500);
	public void start(Stage stage) {
		try {
			Label name = new Label("Username : ");
			Label pwd = new Label("pwd : ");
			Label message = new Label();
			TextField tname = new TextField();
			PasswordField tpwd = new PasswordField();
			
			Button button = new Button("Login");
			button.prefHeightProperty().bind(tname.heightProperty().add(tpwd.heightProperty()));
			button.setOnAction(e->{
				message.setStyle("-fx-text-fill: red;");
				String name2 = tname.getText();
				String pwd2 = tpwd.getText();
				if(name2.equals("")) {
					message.setText("Please enter your name");
				}
				else if(pwd2.equals("")) {
					pwd.setText("Please enter your pwd");
				}
				else if(!name2.equals("Rin") || !pwd2.equals("pwd")) {
					message.setText("Invalid name or pwd");
				}
				else {
					message.setStyle("-fx-text-fill : green;");
					message.setText("Successfully Login");
				}
			});
			GridPane grid = new GridPane();
			grid.addRow(0, name, tname);
			grid.addRow(1, pwd, tpwd);
			grid.add(button, 2,0,1,2);
			grid.add(message, 0, 2,3,1);
			grid.setAlignment(Pos.CENTER);
			pane.getChildren().add(grid);
			
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
