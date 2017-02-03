package com.aonproject.client.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aonproject.admin.product.dao.ProductDAO;
import com.aonproject.admin.product.vo.ProductVO;

@Service
@Transactional
public class ClientProductServiceImpl implements ClientProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	/*��ǰ ����Ʈ ����*/
	@Override
	public List<ProductVO> productList(ProductVO pvo) {
		List<ProductVO> productList = null;
		productList = productDAO.productList(pvo);
		return productList;
	}

}
