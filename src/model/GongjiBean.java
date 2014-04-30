package model;

public class GongjiBean {

	/*
	 * 테이블 필드명과 변수명은 같게 한다.
	 */
	private int gongji_no;
	private String gongji_name;
	private String gongji_title;
	private String gongji_pwd;
	private String gongji_cont;
	private int gongji_hit;
	private String gongji_date;
	
	public int getGongji_no() {
		return gongji_no;
	}
	public void setGongji_no(int gongji_no) {
		this.gongji_no = gongji_no;
	}
	public String getGongji_name() {
		return gongji_name;
	}
	public void setGongji_name(String gongji_name) {
		this.gongji_name = gongji_name;
	}
	public String getGongji_title() {
		return gongji_title;
	}
	public void setGongji_title(String gongji_title) {
		this.gongji_title = gongji_title;
	}
	public String getGongji_pwd() {
		return gongji_pwd;
	}
	public void setGongji_pwd(String gongji_pwd) {
		this.gongji_pwd = gongji_pwd;
	}
	public String getGongji_cont() {
		return gongji_cont;
	}
	public void setGongji_cont(String gongji_cont) {
		this.gongji_cont = gongji_cont;
	}
	public int getGongji_hit() {
		return gongji_hit;
	}
	public void setGongji_hit(int gongji_hit) {
		this.gongji_hit = gongji_hit;
	}
	public String getGongji_date() {
		return gongji_date;
	}
	public void setGongji_date(String gongji_date) {
		this.gongji_date = gongji_date.substring(0,10);
	}	
}
