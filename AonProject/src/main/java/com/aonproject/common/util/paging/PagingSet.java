package com.aonproject.common.util.paging;

import com.aonproject.admin.aInfo.vo.AdminVO;
import com.aonproject.common.util.vo.CommonPagingVO;

public class PagingSet {
	public static void setPageing(CommonPagingVO vo, int cnt){
		int pageNum;
		vo.setTotalCount(cnt);
		
		if(vo.getCountList() == 0) vo.setCountList(10);
		if(vo.getCountPage() == 0) vo.setCountPage(5);
		
		vo.setTotalPage(vo.getTotalCount() / vo.getCountList());
		
		if(vo.getTotalCount() % vo.getCountList() != 0){
			int total = vo.getTotalPage();
			total++;
			vo.setTotalPage(total);
		}
		
		if(vo.getPageNum().equals("0") || vo.getPageNum() == null){
			vo.setPageNum("1");
			pageNum = Integer.parseInt(vo.getPageNum());
		}
		else{
			try{
				pageNum = Integer.parseInt(vo.getPageNum());
				if(pageNum < 1) pageNum = 1;
			}
			catch(NumberFormatException e){
				pageNum = 1;
			}
		}
		if(pageNum > vo.getTotalPage()){ // ���� ������ ��ȣ�� �� �������� ��ȣ���� ũ�ٸ�, ������ ������ ��ȣ�� ����
			pageNum = vo.getTotalPage();
		}
		
		if(vo.getTotalCount() != 0){
			int[] result = null; // ��ȯ�� ������ ��ȣ��
			
			int startPage = ((pageNum - 1) / vo.getCountPage()) * vo.getCountPage() + 1; // ������ ������ ��ȣ�� ������ ���� ��ȣ
			int lastPage = startPage + vo.getCountPage() - 1; 
			
			if(lastPage > vo.getTotalPage()){ // ������ ������ ��ȣ�� ������ ������ ��ȣ�� �� ������ �������� ũ��, �� ������ ������ ����
				lastPage = vo.getTotalPage();
			}
			
			result = new int[lastPage - startPage + 1];
			int resultSize = result.length;
			for(int i = 0; i < resultSize; i++){ // ������ ������ ��ȣ���� result�� ����
				result[i] = startPage + i;
			}
			
			vo.setPageTotal(result);
		}
		
		vo.setStart_data((pageNum -1) * vo.getCountList() + 1);
		vo.setEnd_data(pageNum * vo.getCountList());
	}
	
	public static void setPageing(AdminVO vo, int cnt){
		int pageNum;
		vo.setTotalCount(cnt);
		
		if(vo.getCountList() == 0) vo.setCountList(10);
		if(vo.getCountPage() == 0) vo.setCountPage(5);
		
		vo.setTotalPage(vo.getTotalCount() / vo.getCountList());
		
		if(vo.getTotalCount() % vo.getCountList() != 0){
			int total = vo.getTotalPage();
			total++;
			vo.setTotalPage(total);
		}
		
		if(vo.getPageNum().equals("0") || vo.getPageNum() == null){
			vo.setPageNum("1");
			pageNum = Integer.parseInt(vo.getPageNum());
		}
		else{
			try{
				pageNum = Integer.parseInt(vo.getPageNum());
				if(pageNum < 1) pageNum = 1;
			}
			catch(NumberFormatException e){
				pageNum = 1;
			}
		}
		if(pageNum > vo.getTotalPage()){ // ���� ������ ��ȣ�� �� �������� ��ȣ���� ũ�ٸ�, ������ ������ ��ȣ�� ����
			pageNum = vo.getTotalPage();
		}
		
		if(vo.getTotalCount() != 0){
			int[] result = null; // ��ȯ�� ������ ��ȣ��
			
			int startPage = ((pageNum - 1) / vo.getCountPage()) * vo.getCountPage() + 1; // ������ ������ ��ȣ�� ������ ���� ��ȣ
			int lastPage = startPage + vo.getCountPage() - 1; 
			
			if(lastPage > vo.getTotalPage()){ // ������ ������ ��ȣ�� ������ ������ ��ȣ�� �� ������ �������� ũ��, �� ������ ������ ����
				lastPage = vo.getTotalPage();
			}
			
			result = new int[lastPage - startPage + 1];
			int resultSize = result.length;
			for(int i = 0; i < resultSize; i++){ // ������ ������ ��ȣ���� result�� ����
				result[i] = startPage + i;
			}
			
			vo.setPageTotal(result);
		}
		
		vo.setStart_data((pageNum -1) * vo.getCountList() + 1);
		vo.setEnd_data(pageNum * vo.getCountList());
	}
}
