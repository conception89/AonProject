package com.aonproject.client.order.vo;

import java.util.List;

public class Product_orderVO {
	private int 	o_no = 0;			//�ֹ���ȣ		
	private int 	o_cnt = 0;			//�ֹ�����
	private int 	o_price = 0;		//�ֹ�����
	private String 	o_mode = "";		//������
	private String 	o_confirm = "";		//��������
	private String 	o_date = "";		//�����
	private int 	m_no = 0;			//ȸ����ȣ
	private String 	p_no = "";			//��ǰ��ȣ
	private String  o_num = "";
	
	private List<String> p_nos = null;	//��ǰ��ȣ ����Ʈ
	private List<Integer> o_cnts = null;//��ǰ �ֹ����� ����Ʈ
	
	
	
	
	@Override
	public String toString() {
		return "Product_orderVO [o_no=" + o_no + ", o_cnt=" + o_cnt + ", o_price=" + o_price + ", o_mode=" + o_mode
				+ ", o_confirm=" + o_confirm + ", o_date=" + o_date + ", m_no=" + m_no + ", p_no=" + p_no + ", o_num="
				+ o_num + ", p_nos=" + p_nos + ", o_cnts=" + o_cnts + "]";
	}
	public String getO_num() {
		return o_num;
	}
	public void setO_num(String o_num) {
		this.o_num = o_num;
	}
	public List<String> getP_nos() {
		return p_nos;
	}
	public void setP_nos(List<String> p_nos) {
		this.p_nos = p_nos;
	}
	public List<Integer> getO_cnts() {
		return o_cnts;
	}
	public void setO_cnts(List<Integer> o_cnts) {
		this.o_cnts = o_cnts;
	}
	public int getO_no() {
		return o_no;
	}
	public void setO_no(int o_no) {
		this.o_no = o_no;
	}
	public int getO_cnt() {
		return o_cnt;
	}
	public void setO_cnt(int o_cnt) {
		this.o_cnt = o_cnt;
	}
	public int getO_price() {
		return o_price;
	}
	public void setO_price(int o_price) {
		this.o_price = o_price;
	}
	public String getO_mode() {
		return o_mode;
	}
	public void setO_mode(String o_mode) {
		this.o_mode = o_mode;
	}
	public String getO_confirm() {
		return o_confirm;
	}
	public void setO_confirm(String o_confirm) {
		this.o_confirm = o_confirm;
	}
	public String getO_date() {
		return o_date;
	}
	public void setO_date(String o_date) {
		this.o_date = o_date;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getP_no() {
		return p_no;
	}
	public void setP_no(String p_no) {
		this.p_no = p_no;
	}
	
	
}
