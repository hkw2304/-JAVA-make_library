package userMain.service;

import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.stage.Stage;
import userMain.BookSub;

public interface MainService {

	public int clickrent(List<BookSub> rlist, String userid);
	public HashMap<String, Object> clickSelect(String banme);
	public ObservableList<BookSub> clickSelectAll();
	public Parent openLentPage(Stage s, String form, String bno, String userid);
	public HashMap<String, Object> setImg();

}
