package project_notice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NoticeMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		NoticeService ns = new NoticeService();
		while (true) {
			System.out.println("____________________1");
			System.out.println("1.조회");
			System.out.println("2.입력");
			System.out.println("3.수정");
			System.out.println("4.삭제");
			System.out.print("선택 >> ");
			int select = sc.nextInt();

			switch (select) {
			case 1:
				ns.select();
				break;
			case 2:
				ns.insert();
				break;
			case 3:
				ns.update();
				break;
			case 4:
				ns.delete();
				break;
			default:
				System.out.println("<<기능은 1~4번까지>>");
			}
		}

	}

}
