package userMain;

public class BookSub {
	Integer bno;
    String bname;
    String author;
    String publisher;
    String b_coment;
    String img;

	public BookSub(Integer bno, String bname, String author, String publisher, String b_coment, String img) {
		
		this.bno = bno;
		this.bname = bname;
		this.author = author;
		this.publisher = publisher;
		this.b_coment = b_coment;
		this.img = img;
	}

	public Integer getBno() {
		return bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
