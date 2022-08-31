package service3;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;

public interface CommonService {

	
	public void windowClose(ActionEvent event); // 새로 띄운 창을 닫게 해주는  공통메서드
	public Parent showWindow(Stage s, String fxml, String id5); // 새로 창을 띄우는 공통메서드
	public void errorBox(String tltle, String header, String content); // 경고박스 공통메서드
	public boolean isEmpty(String str); // 텍스트필드에 채워야할 빈칸이 있을 때 쓰는 공통메서드
}
