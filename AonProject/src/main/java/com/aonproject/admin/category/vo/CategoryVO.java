package com.aonproject.admin.category.vo;

public class CategoryVO {
	//��ǰ ī�װ� (2017 F/W, 2017 S/S etc)
	private int ca_no;			//ī�װ� ��ȣ
	private String ca_name;		//ī�װ� �̸�
	private String ca_date;		//�����
	private String ca_confirm;	//ī�װ� ���� 
	
	
	public String getCa_confirm() {
		return ca_confirm;
	}
	public void setCa_confirm(String ca_confirm) {
		this.ca_confirm = ca_confirm;
	}
	public int getCa_no() {
		return ca_no;
	}
	public void setCa_no(int ca_no) {
		this.ca_no = ca_no;
	}
	public String getCa_name() {
		return ca_name;
	}
	public void setCa_name(String ca_name) {
		this.ca_name = ca_name;
	}
	public String getCa_date() {
		return ca_date;
	}
	public void setCa_date(String ca_date) {
		this.ca_date = ca_date;
	}
	
	
}
