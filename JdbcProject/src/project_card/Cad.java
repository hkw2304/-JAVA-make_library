package project_card;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Cad {
	Scanner sc = new Scanner(System.in);
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	CADAO dao = new CADAO();
	CADException cad = new CADException();
	// Money money = new Money();
	// Picture pic = new Picture();

	// 1.가계부 소개

	public void CAInformation() {
		System.out.println("가계부 소개");
		System.out.println("[기능안내]");
		System.out.println("- 간편한 카드와 계좌 등록과 삭제");
		System.out.println("- 월간 내역보기");
		System.out.println("- 주간 내역보기");
		System.out.println("- 일간 내역보기");
		System.out.println("- 카테고리별 지출통계");
		// pic.main2();
	}

	// 2.회원가입
	public void insertMember() {

		boolean id = true;
		boolean pw = true;
		boolean name = true;

		System.out.println("____________________________________________________");
		System.out.println("                                           회원가입");
		System.out.println("____________________________________________________");

		CADTO dto = new CADTO();

		while (id) {
			System.out.print("아이디 : ");
			String str = sc.next();
			int excape = cad.idFormat(str);
			if (excape == 0) {
				dto.setId(str);
				id = false;
			}
		}
		while (pw) {
			System.out.print("비밀번호 : ");
			String pw1 = sc.next();
			int excape = cad.pwCheck(pw1);
			if (excape == 0) {
				while (true) {
					System.out.print("비밀번호 확인 : ");
					String pw2 = sc.next();
					if (pw1.equals(pw2)) {
						break;
					} else {
						System.out.println("비밀번호가 다릅니다.");
					}
				}
				dto.setPwd(pw1);
				pw = false;
			}
		}
		System.out.print("이름 : ");
		String str = sc.next();
		dto.setName(str);
		name = false;

		int result = dao.insertMember(dto);
		if (result > 0) {
			System.out.println("회원 가입 완료");
		} else
			System.out.println("회원가입 실패");
	}

	public String login() {
		String id;
		String pwd;
		String checkid = null;

		try {
			System.out.print("아이디 : ");
			id = sc.next();
			System.out.print("비밀번호 : ");
			pwd = sc.next();

			checkid = dao.login(id, pwd);

		} catch (Exception e) {
			System.out.println(e.toString());
			return checkid;
		}
		return checkid;
	}

	public void deleteMember() {
		try {
			System.out.println("삭제할 정보를 입력하세요.");
			System.out.print("id : ");
			String id = sc.next();
			System.out.print("pwd : ");
			String pwd = sc.next();

			int result = dao.deleteMember(id, pwd);

			if (result == 1)
				System.out.println("회원탈퇴 성공");

			else
				System.out.println("회원탈퇴 실패 정보를 다시 확인하세요");

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	public void insertAset(String id) {
		System.out.println("_____________________________");
		System.out.println("카드계좌등록");
		System.out.println("_____________________________");

		try {
			int result = 0;
			int BC = 0;
			String str = null;

			boolean accDiv = true;
			boolean accno = true;
			boolean cardno = true;
			boolean accAmount = true;
			boolean nickname = true;

			CADTO dto = new CADTO();

			while (BC < 1 || BC > 2) {
				System.out.println("1.계좌등록 / 2.카드등록");
				System.out.print("선택 ▶▶▶");
				BC = sc.nextInt();
			}
			switch (BC) {
			case 1:
				System.out.println("등록하시는 계좌의 유형을 입력해주세요.");
				System.out.println("[입출금] / [예/적금]");
				while (accDiv) {
					try {
						System.out.println("▶");
						str = sc.next();
						cad.accdivCheck(str);
						dto.setAccDiv(str);
						accDiv = false;
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
				System.out.print("은행을 입력하세요 : ");
				dto.setBank(sc.next());
				System.out.println();
				System.out.print("예금주를 입력하세요 : ");
				dto.setAccName(sc.next());
				System.out.println();
				System.out.println("계좌번호를 입력하세요(-없이 숫자만 입력");
				while (accno) {
					try {
						System.out.print("▶");
						str = sc.next();
						cad.numberCheck2(str);

						dto.setAccno(str);
						accno = false;
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
				System.out.println();
				while (accAmount) {

					System.out.print("계좌금액을 입력 : ");
					System.out.print("▶");
					int num = sc.nextInt();
					int excape = cad.numberCheck1(num);
					if (excape == 0) {
							dto.setAccAmount(num);
							accAmount = false;
					}
				}
				while (nickname) {
					try {
						System.out.print("계좌이름 : ");
						str = sc.next();
						cad.accNickCheck(str);
						nickname = false;
						dto.setBankNick(str);
					} catch (Exception e) {
						System.out.println(e.toString());
					}
				}
				result = dao.insertAsset(dto, id, BC);
				if (result > 0)
					System.out.println("계좌등록 성공");
				else
					System.out.println("계좌등록 실패");
				break;
			case 2:
				while (cardno) {
					System.out.println("카드번호 입력(-없이 숫자만)");
					System.out.println("▶");
					str = sc.next();
					int excape = cad.numberCheck2(str);
					if (excape == 0) {
						dto.setCardno(str);
						cardno = false;
					}
				}
				System.out.print("카드 은행 입력 : ");
				dto.setCard(sc.next());
				System.out.print("명의주 입력 : ");
				dto.setCardName(sc.next());
				while(nickname) {
					System.out.println("카드 간편이름 : ");
					str = sc.next();
					cad.accNickCheck(str);
					nickname = false;
					dto.setCardNick(str);
				}
				result = dao.insertAsset(dto,  id,  BC);
				if(result > 0) 
					System.out.println("카드등록 성공");
				else
					System.out.println("카드등록 실패");
				break;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	//계좌/카드 삭제
	public void deleteAsset(String id) {
		String CAname;
		String CAno;
		int BC = 0;
		int result = 0;
		
		System.out.print("1.계좌 삭제 / 2.카드 삭제");
		BC = sc.nextInt();
		
		System.out.println("삭제할 정보 입력");
		System.out.print("계좌 : ");
		CAname = sc.next();
		System.out.print("계좌번호 : ");
		CAno = sc.next();
		
		result = dao.deleteAsset(id, CAname, CAno, BC);
		if(result > 0) 
			System.out.println("삭제 성공");
		else
			System.out.println("삭제 실패");
	}

}
