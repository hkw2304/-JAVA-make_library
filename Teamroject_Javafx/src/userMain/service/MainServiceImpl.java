package userMain.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import login.InitController;
import userMain.BookSub;
import userMain.dao.MainDAO;
import userMain.dao.MainDAOImpl;

public class MainServiceImpl implements MainService{
	
	MainDAO dao;
	
	public MainServiceImpl() {
		// TODO Auto-generated method stub
		dao = new MainDAOImpl();
	}

	@Override
	public int clickrent(List<BookSub> rlist, String userid) {	//대여버튼
		// TODO Auto-generated method stub
		int chk = dao.rent(rlist, userid);
		return chk;
	}

	@Override
	public HashMap<String, Object> clickSelect(String bname) {	// 단일 검색 
		// TODO Auto-generated method stub
		HashMap<String, Object> map = dao.clickSelect(bname);
		return map;
	}

	@Override
	public ObservableList<BookSub> clickSelectAll() {	// 테이블 뷰 도서 목록 전체 검색
		ObservableList<BookSub> list = dao.clickSelectAll();
		return list;
		
		
	}
	
	@Override
	public Parent openLentPage(Stage s, String form, String bno, String userid) {	// 이미지 클릭 팝업
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource(form));
		Parent root = null;
		
		try {
			root = loader.load();
			s.setScene(new Scene(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InitController ctl = loader.getController();
		
		ImageView img = (ImageView) root.lookup("#image");
		HashMap<String, Object> map = dao.getInfo(bno);
		img.setImage(new Image(map.get("img").toString()));
		
		Label author = (Label) root.lookup("#author");
		Label publisher = (Label) root.lookup("#publisher");
		TextArea b_coment = (TextArea) root.lookup("#coment");
		Label bname = (Label) root.lookup("#bname");
		Label key = (Label) root.lookup("#bno");
		
		author.setText("저자 : "+map.get("author").toString());
		publisher.setText("출판사 : "+map.get("publisher").toString());
		b_coment.setText(map.get("b_coment").toString());
		bname.setText(map.get("bname").toString());
		key.setText(bno);
		
		ctl.setRoot(root);
		ctl.setUserId(userid);
		
		s.show();
		
		return root;
	}

	@Override
	public HashMap<String, Object> setImg() {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = dao.setting();
		return map;
	}

}
