package rent.service;

import javafx.scene.Parent;
import javafx.stage.Stage;

public interface RentService {
	Parent backMain(Stage primaryStage, String str, String userid);
	int rentBook(int key, String userid);
}
