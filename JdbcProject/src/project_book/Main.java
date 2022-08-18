package project_book;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		Service service = new Service();
		Dao dao = new Dao();
		boolean loop = true;
		while(loop) {
		System.out.println("__________________________");
		System.out.println("           도서 프로그램");
		System.out.println("           1.도서 전체조회");
		System.out.println("           2.도서 검색");
		System.out.println("           3.도서 추가");
		System.out.println("           4.도서 대여");
		System.out.println("           5.도서 반납");
		System.out.println("           6.대여 가능 도서");
		System.out.println("__________________________");
		System.out.print("선택 >> ");
		int select = sc.nextInt();
		
		switch(select) {
		case 1:
			dao.selectAll();
			break;
		case 2:
			service.select();
			break;
		case 3:
			service.insert();
			break;
		case 4:
			service.update();
			break;
		case 5:
			service.input();
			break;
		case 6:
			dao.rental();
			break;
		default:
			System.out.println("<<1~3번 까지 입니다.>>");
		}
		}

	}

}
