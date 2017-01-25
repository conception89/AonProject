package com.aonproject.admin.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aonproject.admin.product.dao.ProductDAO;
import com.aonproject.admin.product.vo.ProductVO;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	/*��ǰ ����Ʈ ����*/
	@Override
	public List<ProductVO> productList(ProductVO pvo) {
		List<ProductVO> productList = null;
		productList = productDAO.productList(pvo);
		return productList;
	}
	
	/*��ǰ  ������ ����*/
	@Override
	public ProductVO productDetail(ProductVO pvo) {
		ProductVO productDetail = null;
		productDetail = productDAO.productDetail(pvo);
		return productDetail;
	}
	
	/*��ǰ ������Ʈ ����*/
	@Override
	public int productUpdate(ProductVO pvo) {
		int result = 0; 
		result = productDAO.productUpdate(pvo);
		return result;
	}

}
