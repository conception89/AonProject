package com.aonproject.admin.product.vo;

import org.springframework.web.multipart.MultipartFile;

import com.aonproject.client.order.vo.Product_orderVO;

public class ProductVO extends Product_orderVO {
	private String p_no;		//��ǰ �ڵ�
	private String p_name;		//��ǰ��
	private String p_info;		//��ǰ����
	private int	p_price;		//��ǰ ����
	private int p_discount;		//��ǰ ������
	private String color_code;	//���� �ڵ�
	private String size_code;	//������ �ڵ�
	private String p_fabric;	//��ǰ ����
	private String p_caution;	//���ǻ���
	private String p_date;		//�����
	private int ca_no;			//ī�װ� ��ȣ
	private String p_type;		//��ǰŸ��
	private String p_del;		//��ǰ��������
	
	private String createP_no;	
	
	//ī�װ� ���� �ʵ� ����
	private String ca_name;		//ī�װ� �̸�
	
	//�����ڵ� �ʵ� ����
	private String p_type_name;	//��ǰ����
	private String color;		//����
	private String size;		//������
	
	//���� ���ε� ���� �Ӽ�
	private MultipartFile file;	//÷������
	private String pi_file =""; 	//���� ������ ������ ���ϸ�
	
	
	
	public String getCreateP_no() {
		return createP_no;
	}
	public void setCreateP_no(String createP_no) {
		this.createP_no = createP_no;
	}
	public String getP_info() {
		return p_info;
	}
	public void setP_info(String p_info) {
		this.p_info = p_info;
	}
	public String getP_del() {
		return p_del;
	}
	public void setP_del(String p_del) {
		this.p_del = p_del;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getPi_file() {
		return pi_file;
	}
	public void setPi_file(String pi_file) {
		this.pi_file = pi_file;
	}
	public String getP_type_name() {
		return p_type_name;
	}
	public void setP_type_name(String p_type_name) {
		this.p_type_name = p_type_name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getCa_name() {
		return ca_name;
	}
	public void setCa_name(String ca_name) {
		this.ca_name = ca_name;
	}
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	public String getP_no() {
		return p_no;
	}
	public void setP_no(String p_no) {
		this.p_no = p_no;
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
	public String getP_fabric() {
		return p_fabric;
	}
	public void setP_fabric(String p_fabric) {
		this.p_fabric = p_fabric;
	}
	public String getP_caution() {
		return p_caution;
	}
	public void setP_caution(String p_caution) {
		this.p_caution = p_caution;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_date(String p_date) {
		this.p_date = p_date;
	}
	public int getCa_no() {
		return ca_no;
	}
	public void setCa_no(int ca_no) {
		this.ca_no = ca_no;
	}

	
	
}
