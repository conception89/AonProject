package com.aonproject.admin.category.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aonproject.admin.category.controller.CategoryController;
import com.aonproject.admin.category.vo.CategoryVO;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	Logger logger = Logger.getLogger(CategoryController.class);
	
	@Autowired
	private SqlSession session;
	
	//ī�װ� ����Ʈ ����
	@Override
	public List<CategoryVO> categoryList(CategoryVO cvo) {
		return session.selectList("categoryList", cvo);
	}
	

}
