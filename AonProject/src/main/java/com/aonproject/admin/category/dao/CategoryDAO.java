package com.aonproject.admin.category.dao;

import java.util.List;

import com.aonproject.admin.category.vo.CategoryVO;
import com.aonproject.admin.commoncode.vo.CommonCodeVO;

public interface CategoryDAO {
	//ī�װ�����
	public List<CategoryVO> categoryList(CategoryVO cvo);
	
}
