package com.aonproject.admin.stock.vo;

public class StockVO {
	private String p_no = "";			//��ǰ��ȣ
	private int stock_cnt = 0;			//������
	private String stock_date = "";		//�����
	
	//��ǰ �߰� ����
	private String ca_name;				//��ǰī�װ���
	private String p_type_name;			//��ǰŸ��
	private String p_name;				//��ǰ��
	private int p_price;				//��ǰ����
	private String p_color;				//��ǰ����
	private String size;				//��ǰ������
	
	
	public String getCa_name() {
		return ca_name;
	}
	public void setCa_name(String ca_name) {
		this.ca_name = ca_name;
	}
	public String getP_type_name() {
		return p_type_name;
	}
	public void setP_type_name(String p_type_name) {
		this.p_type_name = p_type_name;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public String getP_color() {
		return p_color;
	}
	public void setP_color(String p_color) {
		this.p_color = p_color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getP_no() {
		return p_no;
	}
	public void setP_no(String p_no) {
		this.p_no = p_no;
	}
	public int getStock_cnt() {
		return stock_cnt;
	}
	public void setStock_cnt(int stock_cnt) {
		this.stock_cnt = stock_cnt;
	}
	public String getStock_date() {
		return stock_date;
	}
	public void setStock_date(String stock_date) {
		this.stock_date = stock_date;
	}
	
	
}
