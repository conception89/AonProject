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
	
	
}
