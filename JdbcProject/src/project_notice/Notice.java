package project_notice;

import java.util.Date;

public class Notice {

	private String title;
	private String id;
	private String writer_id;
	private Date regdate;
	private String content;
	private int hit;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	
	public Notice(String title, String id, String writer_id, Date regdate, String content, int hit) {
		this.title = title;
		this.id = id;
		this.writer_id = writer_id;
		this.regdate = regdate;
		this.content = content;
		this.hit = hit;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
	
}
