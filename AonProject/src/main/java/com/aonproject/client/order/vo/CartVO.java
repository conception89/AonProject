package com.aonproject.client.order.vo;

import java.util.List;

public class CartVO {
	private String p_no = ""; // ��ǰ ��ȣ
	private int o_cnt = 0; // ��ǰ ����
	private String p_name = ""; // ��ǰ �̸�
	private String p_type = ""; // ��ǰ ����
	private int p_price = 0; // ��ǰ ����
	private int p_discount = 0; // ������
	private String p_color = ""; // ��ǰ ����
	private String p_size = ""; // ��ǰ ������
	private String pi_file = ""; // ��ǰ �̹��� �̸�
	private String pi_route = ""; // ��ǰ �̹��� ���� ���
	private String color_code = ""; // ���� �ڵ�
	private String size_code = ""; // �������ڵ�
	
	private List<String> p_nos = null;
	private List<Integer> o_cnts = null;
	
	
	
	public String getColor_code() {
		return color_code;
	}
	public void setColor_code(String color_code) {
		this.color_code = color_code;
	}
	public String getSize_code() {
		return size_code;
	}
	public void setSize_code(String size_code) {
		this.size_code = size_code;
	}
	public String getPi_file() {
		return pi_file;
	}
	public void setPi_file(String pi_file) {
		this.pi_file = pi_file;
	}
	public String getPi_route() {
		return pi_route;
	}
	public void setPi_route(String pi_route) {
		this.pi_route = pi_route;
	}
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
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
	public int getP_discount() {
		return p_discount;
	}
	public void setP_discount(int p_discount) {
		this.p_discount = p_discount;
	}
	public String getP_color() {
		return p_color;
	}
	public void setP_color(String p_color) {
		this.p_color = p_color;
	}
	public String getP_size() {
		return p_size;
	}
	public void setP_size(String p_size) {
		this.p_size = p_size;
	}
	public String getP_no() {
		return p_no;
	}
	public void setP_no(String p_no) {
		this.p_no = p_no;
	}
	public int getO_cnt() {
		return o_cnt;
	}
	public void setO_cnt(int o_cnt) {
		this.o_cnt = o_cnt;
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
	
	
	
}
