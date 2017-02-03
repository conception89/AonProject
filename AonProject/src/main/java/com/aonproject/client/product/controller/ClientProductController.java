package com.aonproject.client.product.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aonproject.admin.category.service.CategoryService;
import com.aonproject.admin.category.vo.CategoryVO;
import com.aonproject.admin.product.service.ProductService;
import com.aonproject.admin.product.vo.ProductVO;
import com.aonproject.client.root.RootController;

@Controller
public class ClientProductController {
	Logger logger = Logger.getLogger(RootController.class);
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	//��ǰ ����Ʈ ���
		@RequestMapping(value = "category")
		public String productList (@ModelAttribute CategoryVO cvo, ProductVO pvo, Model model, @RequestParam("no") int no){
			/*ī�װ� ����Ʈ ���*/
			List<CategoryVO> categoryList = categoryService.categoryList(cvo);
			model.addAttribute("categoryList", categoryList);
			
			logger.info(no);
			
			/*ī�װ� ���� �̸� ���*/
			cvo.setCa_no(no);
			List<CategoryVO> categorySelect = categoryService.categoryList(cvo);
			logger.info(cvo.getCa_name());
			model.addAttribute("categorySelect", categorySelect);
			
			/*��ǰ����Ʈ ���� ī�װ� ��ȣ ����*/
			pvo.setCa_no(no);
			/*��ǰ ����Ʈ ���*/
			List<ProductVO> productList = productService.productList(pvo);
			model.addAttribute("productList", productList);
			
			/*��ǰ �̹��� ���*/
			
			
			return "client/product/productMain";
		}
}
