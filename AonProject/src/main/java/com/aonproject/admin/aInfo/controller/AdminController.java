package com.aonproject.admin.aInfo.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aonproject.admin.aInfo.vo.AdminVO;
import com.aonproject.common.util.security.ShaEncoder;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	Logger logger = Logger.getLogger(AdminController.class);
	
	@Resource(name = "shaEncoder")
	private ShaEncoder encoder;
	
	
	@RequestMapping(value = "/login")
	public String loginForm(){
		logger.info("loginForm ȣ�� ����");
		return "admin/login/loginForm";
	}
	
	@RequestMapping(value = "/main")
	public ModelAndView main(Authentication auth){
		logger.info("main ȣ�� ����");
		ModelAndView mav = new ModelAndView();
		if(auth != null){
			UserDetails vo = (AdminVO) auth.getPrincipal();
			mav.addObject("vo", vo);
		}
		mav.setViewName("admin/main");
		return mav;
	}
}
