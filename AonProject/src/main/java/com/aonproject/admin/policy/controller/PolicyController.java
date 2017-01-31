package com.aonproject.admin.policy.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aonproject.admin.aInfo.vo.AdminVO;
import com.aonproject.admin.policy.service.PolicyService;
import com.aonproject.admin.policy.vo.PolicyVO;

@Controller
@RequestMapping(value = "/admin")
public class PolicyController {
	private Logger logger = Logger.getLogger(PolicyController.class);
	
	@Autowired
	private PolicyService policyService;
	
	@RequestMapping(value = "/policy")
	public ModelAndView policyList(Authentication auth){
		logger.info("policyList ȣ�� ����");
		ModelAndView mav = new ModelAndView();
		UserDetails vo = (AdminVO) auth.getPrincipal();
		mav.addObject("vo", vo);
		mav.addObject("view1", policyService.policyView1());
		mav.addObject("view2", policyService.policyView2());
		
		mav.setViewName("admin/policy/policy");
		return mav;
	};
	
	@ResponseBody
	@RequestMapping(value= "/newPolicy", method=RequestMethod.POST)
	public String newPolicy(@ModelAttribute PolicyVO vo){
		logger.info("newPolicy ȣ�� ����");
		
		String result = "";
		
		if(vo.getPo_type().equals("1")){
			vo.setPo_name("�̿��� �� û��öȸ ��ħ");
		}
		else if(vo.getPo_type().equals("2")){
			vo.setPo_name("�������� �������̿� � ���� ����");
		}
		int gogo = policyService.newPolicy(vo);
		
		if(gogo == 1){
			result = "success";
		}
		else {
			result = "fail";
		}
		return result;
	}
	
	@RequestMapping(value="/policyAgr")
	public ModelAndView policyAgr(Authentication auth, @ModelAttribute AdminVO avo){
		logger.info("policyAgr ȣ�� ����");
		ModelAndView mav = new ModelAndView();
		UserDetails vo = (AdminVO) auth.getPrincipal();
		mav.addObject("vo", vo);
		mav.addObject("adminAgr", policyService.adminList(avo));
		/*mav.addObject("memberAgr", policyService.memberList());
		mav.addObject("nonmemberAgr", policyService.nonmemberList());
		*/
		
		mav.setViewName("admin/policy/policyAgr");
		
		return mav;
	}
}
