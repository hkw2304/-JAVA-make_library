package project_book;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Service {
	Scanner sc = new Scanner(System.in);
	Dao dao = new Dao();
	Sub sub = new Sub();
	public void select() throws ClassNotFoundException, SQLException {
		System.out.print("원하시는 도서를 검색 하세요 >>");
		String bookName = sc.nextLine();
		dao.select(bookName);
		
	}
	public void insert() throws ClassNotFoundException, SQLException {
		String pricenum = "^[0-9]+$";
		int num;
		String strPirce = "";
		System.out.println("__________________________");
		System.out.println("         [추가 도서]");
		System.out.print("책 제목  >>  ");
		sub.setTitle(sc.nextLine());
		System.out.print("책 저자  >>  ");
		sub.setAuthor(sc.next());
		while(true) {
		System.out.print("책 가격  >>  ");
		num = sc. nextInt();
		strPirce = Integer.toString(num);
		boolean check = Pattern.matches(pricenum, strPirce);
		if(check) {
			sub.setPrice(num);
			break;
			}
		else
			System.out.println("책의 가격은 정수로 입력하세요");
		}
		System.out.print("책 대여가능여부 >>  ");
		sub.setState(sc.next());
		
		dao.insert(sub);
	}
	public void update() throws ClassNotFoundException, SQLException {
		System.out.print("대여할 도서 : ");
		String book = sc.nextLine();
		dao.update(book);
		
		
	}
	public void input() throws ClassNotFoundException, SQLException {
		System.out.print("반납할 도서 : ");
		String book = sc.nextLine();
		dao.input(book);
		
	}



}
