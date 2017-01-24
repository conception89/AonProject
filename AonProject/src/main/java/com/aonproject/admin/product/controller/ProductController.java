package com.aonproject.admin.product.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aonproject.admin.product.service.ProductService;
import com.aonproject.admin.product.vo.ProductVO;

@Controller
@RequestMapping(value = "/admin")
public class ProductController {
	Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	/*��ǰ����Ʈ ����*/
	@RequestMapping(value = "/product", method=RequestMethod.GET)
	public String itemList(@ModelAttribute ProductVO pvo, Model model){
		logger.info("itemList ȣ�� ����!");
		
		List<ProductVO> productList = productService.productList(pvo);
				
		model.addAttribute("productList", productList);
		
		return "admin/product/main";
	}
	
	/*��ǰ�� ȣ��*/
	@RequestMapping(value = "/productDetail", method=RequestMethod.POST)
	public String itemDetail(@ModelAttribute ProductVO pvo, Model model){
		logger.info("itemDetail ȣ�� ����!");
		
		ProductVO productDetail = productService.productDetail(pvo);
		logger.info("productDetail= "+productDetail);		
		
		model.addAttribute("productDetail" , productDetail);
		
		return "admin/product/detail";
	}
	
	/*@ResponseBody
	@RequestMapping(value = "/productDetail", method=RequestMethod.POST)
	public ProductVO itemDetil(@RequestBody ProductVO pvo, Model model){
		logger.info("itemDetail ȣ�� ����");
		logger.info("p_no= "+pvo.getP_no());
		
		ProductVO productDetail = productService.productDetail(pvo);
		logger.info("productDetail= "+productDetail);
		
		model.addAttribute("productDetail" , productDetail);
		
		return 	productDetail;
	}*/
	
	/*@RequestMapping(value = "/productDetail")
	public ResponseEntity<ProductVO> itemDetil(@RequestBody ProductVO pvo, Model model){
		logger.info("itemDetail ȣ�� ����");
		ResponseEntity<ProductVO> entity = null;
		try{
			entity = new ResponseEntity<>(productService.productDetail(pvo), HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
				
		model.addAttribute("productDetail", entity);
		
		return 	entity;
	}*/
	
			
}
