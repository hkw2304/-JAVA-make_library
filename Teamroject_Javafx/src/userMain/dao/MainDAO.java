package userMain.dao;

import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import userMain.BookSub;

public interface MainDAO {
	HashMap<String, Object> clickSelect(String bname);
	ObservableList<BookSub> clickSelectAll();
	int rent(List<BookSub> rlist, String userid);
	HashMap<String, Object> setting();
	HashMap<String, Object> getInfo(String bno);
}
