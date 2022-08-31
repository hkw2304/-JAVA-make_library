package login;

public class Book {
	private String title; // 도서제목
	private int bno; // 도서번호(중복허용x)
	private String author; // 작가
	private String publisher; // 출판사
	private int bcnt; // 책의 재고수량
	private String b_coment;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getB_coment() {
		return b_coment;
	}
	public void setB_coment(String b_coment) {
		this.b_coment = b_coment;
	}
	public int getBcnt() {
		return bcnt;
	}
	public void setBcnt(int bcnt) {
		this.bcnt = bcnt;
	}
	

}
