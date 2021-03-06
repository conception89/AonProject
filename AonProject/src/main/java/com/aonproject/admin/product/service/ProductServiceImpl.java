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
	
	/*카테고리 리스트 구현*/
	
	
	/*상품 리스트 구현*/
	@Override
	public List<ProductVO> productList(ProductVO pvo) {
		List<ProductVO> productList = null;
		productList = productDAO.productList(pvo);
		return productList;
	}
	
	/*상품  디테일 구현*/
	@Override
	public ProductVO productDetail(ProductVO pvo) {
		ProductVO productDetail = null;
		productDetail = productDAO.productDetail(pvo);
		return productDetail;
	}
	
	/*상품 신규등록 구현*/
	@Override
	public int productInsert(ProductVO pvo) {
		int result = 0;
		result = productDAO.productInsert(pvo);
		return result;
	}
	
	/*상품 업데이트 구현*/
	@Override
	public int productUpdate(ProductVO pvo) {
		int result = 0; 
		result = productDAO.productUpdate(pvo);
		return result;
	}
	
	/*상품 삭제 구현*/
	@Override
	public int productDelete(ProductVO pvo) {
		int result = 0;
		result = productDAO.productDelete(pvo);
		return result;
	}

	@Override
	public String createP_no() {
		String createP_no = "";
		createP_no = productDAO.createP_no();
		return createP_no;
	}

	@Override
	public List<ProductVO> productForCategory(ProductVO pvo) {
		List<ProductVO> productForCategory =null;
		productForCategory = productDAO.productForCategory(pvo);
		return productForCategory;
	}

	@Override
	public ProductVO productDetailSupport(String p_no) {
		ProductVO productDetailSupport = productDAO.productDetailSupport(p_no);
		return productDetailSupport;
	}

	@Override
	public int productCnt(ProductVO pvo) {
		int result = 0;
		result = productDAO.productCnt(pvo);
		return result;
	}

	

}
