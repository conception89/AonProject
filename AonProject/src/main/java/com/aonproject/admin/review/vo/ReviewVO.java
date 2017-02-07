package com.aonproject.admin.review.vo;

import com.aonproject.common.util.vo.CommonPagingVO;

public class ReviewVO extends CommonPagingVO{ 
	private int re_no;           //��Ϲ�ȣ
	private String re_title;    //���� ����
	private String re_content;	//���� ����
	private String re_date;  	//���� �����
	private String re_pwd;		//���� ��й�ȣ
	private String re_chk;  	//��� ��Ͽ���(Y, N)
	
	public int getRe_no() {
		return re_no;
	}
	public void setRe_no(int re_no) {
		this.re_no = re_no;
	}
	public String getRe_title() {
		return re_title;
	}
	public void setRe_title(String re_title) {
		this.re_title = re_title;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public String getRe_date() {
		return re_date;
	}
	public void setRe_date(String re_date) {
		this.re_date = re_date;
	}
	public String getRe_pwd() {
		return re_pwd;
	}
	public void setRe_pwd(String re_pwd) {
		this.re_pwd = re_pwd;
	}
	public String getRe_chk() {
		return re_chk;
	}
	public void setRe_chk(String re_chk) {
		this.re_chk = re_chk;
	}
	
}
