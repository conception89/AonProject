package com.aonproject.common.util.vo;

public class CommonPagingVO {
	protected String pageNum = "0"; // ������ ��ȣ
	protected int totalCount = 0; // �� ������ ��
	protected int countList = 0; // �� �������� ������ ������ ����
	protected int countPage = 0; // �� �������� ������ ������ ��ȣ ��
	protected int totalPage = 0; // ������ ������ ��ȣ�� ������ ��ȣ
	protected int[] pageTotal = null; // ������ ������ ��ȣ��
	protected int start_data = 0; // �� �������� ������ ������ ���� ��ȣ
	protected int end_data = 0; // // �� �������� ������ ������ �� ��ȣ
	
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCountList() {
		return countList;
	}
	public void setCountList(int countList) {
		this.countList = countList;
	}
	public int getCountPage() {
		return countPage;
	}
	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int[] getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(int[] pageTotal) {
		this.pageTotal = pageTotal;
	}
	public int getStart_data() {
		return start_data;
	}
	public void setStart_data(int start_data) {
		this.start_data = start_data;
	}
	public int getEnd_data() {
		return end_data;
	}
	public void setEnd_data(int end_data) {
		this.end_data = end_data;
	}
	
	
}
