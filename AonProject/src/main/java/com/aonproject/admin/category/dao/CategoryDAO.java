package com.aonproject.admin.category.dao;

import java.util.List;

import com.aonproject.admin.category.vo.CategoryVO;

public interface CategoryDAO {
	//ī�װ�����
	public List<CategoryVO> categoryList(CategoryVO cvo);
	
}
