package login;

import java.net.URL;

import java.util.ResourceBundle;

import dao3.DAO;
import dao3.DAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service3.CommonService;
import service3.CommonServiceImpl;
import service3.JoinService;
import service3.JoinServiceImpl;

public class JoinController extends InitController implements Initializable{

	private Parent root;
	private CommonService cs;
	private JoinService js;
	private DAO dao;
	@FXML TextField chkId;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cs = new CommonServiceImpl();
		js = new JoinServiceImpl();
		dao = new DAOImpl();
	}
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
		
	}
	
	public void exitProc(ActionEvent event) {
		cs.windowClose(event);
	}
	
	private String id5;
	public void setUserId(String id5) {
		this.id5 = id5;
		
	}
	
	public void OpenIdCheckForm() { // 회원가입 아이디 중복확인 창 열림
		
//		Stage s = new Stage();
//		cs.showWindow(s, "../login/IdCheck.fxml","");
		chkId = (TextField) root.lookup("#userId");
		String id = chkId.getText();
		if(id.equals("") || id.equals(null)) {
			cs.errorBox("아이디중복확인", "사용 불가능","다른 아이디를 사용해주세요");
		} else if (dao.idOk(id)) {
			cs.errorBox("아이디중복확인", "사용 불가능","다른 아이디를 사용해주세요");
			chkId.clear();
			chkId.focusedProperty();
		} else {
			cs.errorBox("아이디중복확인", "사용 가능","사용가능한 아이디입니다");
		}
		
	}	
	
	public void IdCheckProc(ActionEvent event) {
		// TODO Auto-generated method stub

		TextField idDouble = (TextField) root.lookup("#idCheck");
		String id = idDouble.getText();

		if (cs.isEmpty(idDouble.getText())) {
			cs.errorBox("오류", "오류발견", idDouble.getText()+"창이 비어있습니다.");
			return;
		}

		if(dao.idOk(id)) {
			cs.errorBox("아이디중복확인", "사용 불가능","다른 아이디를 사용해주세요");
		} else {
			cs.errorBox("아이디중복확인", "사용 가능","사용가능한 아이디입니다");
		}
		Stage s = (Stage) root.getScene().getWindow();
		s.close();
	}
	
	public void cancelProc(ActionEvent event) {
		cs.windowClose(event);
	}
	
	public void joinUserProc() {
		// 조인유저창에서 내용을 입력하고 빈창이 있거나 비밀번호가 다르면 다시 입력하게 하고 알맞게 입력하면 내용을 저장하는 메서드 그후 창을 끈다
		
		User u = new User();
		TextField tfName = (TextField) root.lookup("#userName");
		TextField tfId = (TextField) root.lookup("#userId");
		PasswordField pfPw = (PasswordField) root.lookup("#userPw");
		PasswordField pfPwOk = (PasswordField) root.lookup("#userPwOk");
		TextField tfBirth = (TextField) root.lookup("#userBirth");
		TextField tfEmail = (TextField) root.lookup("#userEmail");


		String[] txtEmpty = {tfName.getText(), tfId.getText(), pfPw.getText(), tfBirth.getText(),tfEmail.getText()};
		String[] txtEmptyName = {"이름", "아이디", "비밀번호","생일","이메일"};

		for (int i=0;i<txtEmpty.length;i++) {
			if (cs.isEmpty(txtEmpty[i])) {
				cs.errorBox("오류", "오류발견", txtEmptyName[i]+" 입력창이 비어있습니다.");
				return;
			}
		}

		String pw = pfPw.getText();
		String pwOk = pfPwOk.getText();
		
		System.out.println("pw="+pw);
		System.out.println("pwok="+pwOk);

		if (!js.comparePw(pw, pwOk)) {
			cs.errorBox("오류", "오류발생", "암호가 다릅니다.");
			return;
		}

		u.setName(tfName.getText());
		u.setId(tfId.getText());
		u.setPw(pfPw.getText());
		u.setBirth(tfBirth.getText());
		u.setEmail(tfEmail.getText());

		if (dao.insertUser(u, 0)) {
			cs.errorBox("회원가입", "성공", "정상적으로 처리되었습니다.");
		} else {
			cs.errorBox("회원가입", "db문제발생", "db입력중 문제가 발생했습니다.");
			return;
		}

		Stage s = (Stage) root.getScene().getWindow();
		s.close();
	}
	
	public void joinAdminProc() {
		// 조인어드민창에서 내용을 입력하고 빈창이 있거나 비밀번호가 다르면 다시 입력하게 하고 알맞게 입력하면 내용을 저장하는 메서드 그후 창을 끈다
		User u = new User();
		TextField tfName = (TextField) root.lookup("#name");
		TextField tfId = (TextField) root.lookup("#userId");
		PasswordField pfPw = (PasswordField) root.lookup("#pw");
		PasswordField pfPwOk = (PasswordField) root.lookup("#pwOk");
		TextField tfBirth = (TextField) root.lookup("#birth");
		TextField tfEmail = (TextField) root.lookup("#email");

		String[] txtEmpty = {tfName.getText(), tfId.getText(), pfPw.getText(),tfBirth.getText(),tfEmail.getText()};
		String[] txtEmptyName = {"이름", "아이디", "비밀번호","생일","이메일"};

		for (int i=0;i<txtEmpty.length;i++) {
			if (cs.isEmpty(txtEmpty[i])) {
				cs.errorBox("오류", "오류발견", txtEmptyName[i]+" 입력창이 비있습니다.");
				return;
			}
		}

		String pw = pfPw.getText();
		String pwOk = pfPwOk.getText();

		if (!js.comparePw(pw, pwOk)) {
			cs.errorBox("오류", "오류발생", "암호가 다릅니다.");
			return;
		}

		u.setName(tfName.getText());
		u.setId(tfId.getText());
		u.setPw(pfPw.getText());
		u.setBirth(tfBirth.getText());
		u.setEmail(tfEmail.getText());



		if (dao.insertUser(u, 1)) {
			cs.errorBox("회원가입", "성공", "정상적으로 처리되었습니다.");
		} else {
			cs.errorBox("회원가입", "db문제발생", "db입력중 문제가 발생했습니다.");
			return;
		}

		Stage s = (Stage) root.getScene().getWindow();
		s.close();


	}
	
}
