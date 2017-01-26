package com.aonproject.admin.product.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aonproject.admin.category.service.CategoryService;
import com.aonproject.admin.category.vo.CategoryVO;
import com.aonproject.admin.category.vo.CommonCodeVO;
import com.aonproject.admin.product.service.ProductService;
import com.aonproject.admin.product.vo.ProductVO;

@Controller
@RequestMapping(value = "/admin")
public class ProductController {
	Logger logger = Logger.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	/*��ǰ����Ʈ ����*/
	@RequestMapping(value = "/product", method=RequestMethod.GET)
	public String itemList(@ModelAttribute ProductVO pvo, @ModelAttribute CategoryVO cvo, Model model){
		logger.info("itemList ȣ�� ����!");
		
		List<ProductVO> productList = productService.productList(pvo);
		//List<CategoryVO> categoryList = categoryService.categoryList(cvo);
		model.addAttribute("productList", productList);
		//model.addAttribute("categoryList", categoryList);
		
		return "admin/product/main";
	}
	
	/*��ǰ�� ȣ��*/
	@RequestMapping(value = "/productDetail", method=RequestMethod.POST)
	public String itemDetail(@ModelAttribute ProductVO pvo, @ModelAttribute CategoryVO cvo, @ModelAttribute CommonCodeVO ovo, Model model){
		logger.info("itemDetail ȣ�� ����!");
		if(pvo.getP_no() == ""){
			logger.info("p_no: "+pvo.getP_no());
			model.addAttribute("mode", "insert");
		}else{
			ProductVO productDetail = productService.productDetail(pvo);		
			model.addAttribute("productDetail" , productDetail);
			model.addAttribute("mode", "update");
		}
		
		List<CategoryVO> categoryList = categoryService.categoryList(cvo);
		model.addAttribute("categoryList", categoryList);
		
		List<CommonCodeVO> commonCodeList = categoryService.commonCodeList(ovo);
		model.addAttribute("commonCodeList", commonCodeList);
		
		return "admin/product/detail";
	}
	
	/*��ǰ �űԵ��*/
	@ResponseBody
	@RequestMapping(value = "/productInsert", method=RequestMethod.POST)
	public String itemInsert (@RequestBody ProductVO pvo, Model model){
		logger.info("itemInsert ȣ�� ����!");
		
		int result = 0;
		result = productService.productInsert(pvo);
		
		model.addAttribute("mode", "update");
		
		return result+"";
	}
	
	/*��ǰ ������Ʈ*/
	@ResponseBody
	@RequestMapping(value = "/productUpdate", method=RequestMethod.POST)
	public String itemUpdate (@RequestBody ProductVO pvo){
		logger.info("itemUpdate ȣ�� ����!");
		logger.info("pvo="+pvo.getP_no());
		
		int result = 0;
		result = productService.productUpdate(pvo);
		
		return result+"";
	}
	
	
			
}
