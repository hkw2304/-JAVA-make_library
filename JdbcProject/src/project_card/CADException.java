package project_card;

import java.util.regex.Pattern;

public class CADException {

	public int idFormat(String str) {
		int cnt1 = 0;
		int cnt2 = 0;
		int cnt3 = 0;

			if (str.length() > 2 && str.length() < 11) {
				cnt1++;
			}
			// 아이디 영문,숫자 혼합 여부
			for (int i = 0; i < str.length(); i++) {
				char ch = str.charAt(i);
				if ((ch >= 'a' && ch <= 'z') || ch >= 'A' && ch <= 'Z')
					cnt2++;
				if (ch >= '0' && ch <= '9')
					cnt3++;
			}
			if (cnt1 == 0 || cnt2 == 0 || cnt3 == 0) {
				System.out.println("아이디는 영문 숫자 횬용해서 3~10자리 까지입니다.");
				return 1;
			}
			else
				return 0;
	}
	public int pwCheck(String pw1) {
		int cnt1 = 0;
		int cnt2 = 0;
		int cnt3 = 0;

			if (pw1.length() > 2 && pw1.length() < 11) {
				cnt1++;
			}
			// 아이디 영문,숫자 혼합 여부
			for (int i = 0; i < pw1.length(); i++) {
				char ch = pw1.charAt(i);
				if ((ch >= 'a' && ch <= 'z') || ch >= 'A' && ch <= 'Z')
					cnt2++;
				if (ch >= '0' && ch <= '9')
					cnt3++;
			}
			if (cnt1 == 0 || cnt2 == 0 || cnt3 == 0) {
				System.out.println("비밀번호는 영문 숫자 횬용해서 3~10자리 까지입니다.");
				return 1;
			}
			else
				return 0;
	}
	public void accdivCheck(String str) {
		if (!str.equals("입출금") && !str.equals("예/적금"))
			System.out.println("입력은 입출금 , 예/적금 으로만 가능합니다.");
	}

	public int numberCheck2(String str) {
		boolean check = str.matches("^[0-9]*$");
		if (!check) {
			System.out.println("숫자로만 입력하세요");
			return 1;
		}
		else
			return 0;
	}

	public int numberCheck1(int num) {

		if(num > 0)
			return 0;
		else {
			System.out.println("잘못된 금액입니다.");
			return 1;
		}

	}

	public void accNickCheck(String str) {
		if (str.length() < 1 || str.length() > 10) {
			System.out.println("1~10이내의 글자를 입력하세요");
		}
		boolean check = Pattern.matches("ㄱ-ㅎ", str);
		if (!check) {
			System.out.println("한글로만 입력");
		}
	}

}
