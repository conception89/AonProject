package com.aonproject.admin.commoncode.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aonproject.admin.category.controller.CategoryController;
import com.aonproject.admin.commoncode.service.CommonCodeService;
import com.aonproject.admin.commoncode.vo.CommonCodeVO;

@Controller
@RequestMapping(value = "/admin")
public class CommonCodeController {
	Logger logger = Logger.getLogger(CategoryController.class);
	
	private CommonCodeService commonCodeService; 
	
	/*�����ڵ� ����Ʈ ����*/
	@RequestMapping(value = "/commonCode", method=RequestMethod.GET)
	public String commonCodeList(@ModelAttribute CommonCodeVO cvo, Model model){
		logger.info("commonCode ȣ�� ����!");
		List<CommonCodeVO> commonCodeList = commonCodeService.commonCodeList(cvo);
		
		model.addAttribute("commonCodeList", commonCodeList);
		
		return "admin/category/main";
	}
}
