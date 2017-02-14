package com.aonproject.admin.product.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aonproject.admin.product.controller.ProductController;
import com.aonproject.admin.product.vo.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired
	private SqlSession session;
	
	//��ǰ ����Ʈ ����
	@Override
	public List<ProductVO> productList(ProductVO pvo) {
		return session.selectList("productList");
	}
	
	//��ǰ ������ ����
	@Override
	public ProductVO productDetail(ProductVO pvo) {
		return (ProductVO)session.selectOne("productDetail", pvo);
	}
	
	//��ǰ �űԵ��
	@Override
	public int productInsert(ProductVO pvo) {
		return session.insert("productInsert", pvo);
	}
	
	//��ǰ ������Ʈ
	@Override
	public int productUpdate(ProductVO pvo) {
		return session.update("productUpdate", pvo);
	}
	
	//��ǰ����
	@Override
	public int productDelete(ProductVO pvo) {
		return session.delete("productDelete", pvo);
	}
	
	//��ǰ��ȣ ����
	@Override
	public String createP_no() {
		return session.selectOne("createP_no");
	}

	@Override
	public List<ProductVO> productForCategory(ProductVO pvo) {
		return session.selectList("productForCategory", pvo);
	}

	@Override
	public ProductVO productDetailSupport(String p_no) {
		return session.selectOne("productDetailSupport", p_no);
	}

	/*@Override
	public int cntList() {
		return session.selectOne("cntList");
	}*/

	

}
