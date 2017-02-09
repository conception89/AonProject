package com.aonproject.admin.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aonproject.admin.category.dao.CategoryDAO;
import com.aonproject.admin.category.vo.CategoryVO;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	/*ī�װ� ����Ʈ ����*/
	@Override
	public List<CategoryVO> categoryList(CategoryVO cvo) {
		List<CategoryVO> categoryList = null;
		categoryList = categoryDAO.categoryList(cvo);
		return categoryList;
	}

	@Override
	public int categoryInsert(CategoryVO cvo) {
		int result = 0;
		result = categoryDAO.categoryInsert(cvo);
		return result;
	}

//	@Override
//	public CategoryVO categorySelect(CategoryVO cvo) {
//		CategoryVO categorySelect = null;
//		categorySelect = categoryDAO.categorySelect(cvo);
//		return categorySelect;
//	}
	
	
}
