package com.aonproject.client.product.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aonproject.client.product.service.ProductService;
import com.aonproject.client.product.vo.ProductVO;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
	Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	/*��ǰ����Ʈ ����*/
	@RequestMapping(value = "/itemList", method=RequestMethod.GET)
	public String itemList(@ModelAttribute ProductVO pvo, Model model){
		logger.info("itemList ȣ�� ����!");
		
		List<ProductVO> productList = productService.productList(pvo);
		
		model.addAttribute("productList", productList);
		
		return "client/product/main";
	}
			
}
