package login;

import java.sql.Date;
import java.time.LocalDate;

public class Rental {
	private int rno;
	private int bno;
	private String id;
	private String yn;
	public String getYn() {
		return yn;
	}
	public void setYn(String yn) {
		this.yn = yn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private Date out_date;
	private Date Due_date;
	private Date in_date;
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public Date getOut_date() {
		return out_date;
	}
	public void setOut_date(Date out_date) {
		this.out_date = out_date;
	}
	public Date getDue_date() {
		return Due_date;
	}
	public void setDue_date(Date due_date) {
		Due_date = due_date;
	}
	public Date getIn_date() {
		return in_date;
	}
	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}


}
