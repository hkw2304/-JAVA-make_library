package project_card;

import java.util.Scanner;

public class CadMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean loop = true;
		Cad cad = new Cad();
		
		try {
			while(loop) {
				System.out.println("__________________________________________________________________________");
				System.out.println("                                                   가계부        ");
				System.out.println("1.가계부 소개             2.회원가입             3.로그인             4.회원탈퇴             5.종료");
				System.out.println("__________________________________________________________________________");
				System.out.print("▶▶▶ ");
				int ch = sc.nextInt();
				
				switch(ch) {
				case 1:
					cad.CAInformation();
					break;
				case 2:
					cad.insertMember();
					break;
				case 3:
					//CADLogin.main(args);
					break;
				case 4:
					cad.deleteMember();
					break;
				case 5:
					System.out.println("프로그램 종료 Bye~~");
					loop = false;
					break;
				default:
					System.out.println("<<기능은 1~5번 까지 입니다.>>");
				
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
