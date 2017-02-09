package com.aonproject.admin.policy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.aonproject.admin.aInfo.service.AdminService;
import com.aonproject.admin.aInfo.vo.AdminVO;
import com.aonproject.admin.policy.service.PolicyService;
import com.aonproject.admin.policy.vo.PolicyVO;
import com.aonproject.client.mInfo.service.MemberService;
import com.aonproject.client.mInfo.vo.MemberVO;
import com.aonproject.common.util.excel.ExcelList;
import com.aonproject.common.util.paging.PagingSet;
import com.aonproject.common.util.vo.Numbers;
import com.aonproject.common.util.vo.PolicyAgrVO;

@Controller
@RequestMapping(value = "/admin")
public class PolicyController {
	private Logger logger = Logger.getLogger(PolicyController.class);
	
	@Autowired
	private PolicyService policyService;
	
	@Autowired 
	private MemberService memberService;
	
	@Autowired
	private AdminService adminService;
	
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
			int po_no = policyService.forLoop();
			
			List<Numbers> adminNs = adminService.numbers();
			for(int i = 0; i < adminNs.size(); i++){
				AdminVO pvo = new AdminVO();
				pvo.setPo_no(po_no);
				pvo.setA_no((int) adminNs.get(i).getNo());
				pvo.setPa_confirm("����");
				policyService.pagr(pvo);
			}
			
			List<Numbers> memberNs = memberService.numbers();
			for(int i = 0; i < adminNs.size(); i++){
				PolicyAgrVO pvo = new PolicyAgrVO();
				pvo.setPo_no(po_no);
				pvo.setM_no((int) memberNs.get(i).getNo());
				pvo.setPa_confirm("����");
				policyService.pagr2(pvo);
			}
			
			result = "success";
		}
		else {
			result = "fail";
		}
		return result;
	}
	
	@RequestMapping(value="/policyAgr")
	public ModelAndView policyAgr(Authentication auth, @ModelAttribute AdminVO avo, @ModelAttribute MemberVO mvo, HttpServletRequest request){
		logger.info("policyAgr ȣ�� ����");
		ModelAndView mav = new ModelAndView();
		UserDetails vo = (AdminVO) auth.getPrincipal();
		mav.addObject("vo", vo);
        
		String adminPageNum = request.getParameter("adminPageNum");
		if(adminPageNum != null){
			avo.setPageNum(adminPageNum);
		}
		
		int adminCnt = policyService.adminListCnt(avo);
		PagingSet.setPageing(avo, adminCnt);
		mav.addObject("adminAgr", policyService.adminList(avo));
		mav.addObject("adminVO", avo);
		
		String memberPageNum = request.getParameter("memberPageNum");
		if(memberPageNum != null){
			mvo.setPageNum(memberPageNum);
		}
		
		int memberCnt = policyService.memberListCnt(mvo);
		PagingSet.setPageing(mvo, memberCnt);
		mav.addObject("memberAgr", policyService.memberList(mvo));
		mav.addObject("memberVO", mvo);
		
		mav.setViewName("admin/policy/policyAgr");
		
		return mav;
	}
	
	// ����ź� -> ȸ�� Ż��
	@ResponseBody
	@RequestMapping(value="/policyAgr/denial")
	public String policyAgrDenial(@ModelAttribute PolicyAgrVO vo){
		logger.info("policyAgrDenial ȣ�� ����");
		String result = "";
		vo.setPa_confirm("�ź�");
		int gogo = policyService.policyAgrDenial(vo);
		if(gogo == 1){
			MemberVO mvo = new MemberVO();
			mvo.setM_no(vo.getM_no());
			mvo = memberService.memberInfo(mvo);
			int gogo2 = memberService.memberExpire(mvo);
			if(gogo2 == 1){
				mvo.setM_leave("true");
				int a = memberService.memberGoodBye(mvo);
				int b = memberService.memberAddrGoodBye(mvo);
				if(a == 1 && b == 1){
					result = "success";
				}
			}
		}
		return result;
	}
	
	// ��� ���� ��ü excel ���� 
	@RequestMapping(value="/policyAgr/excel")
	public ModelAndView excel(){
		logger.info("excel ȣ�� ����");
		List<Numbers> nList = policyService.numbers();
		
		ArrayList<List<PolicyAgrVO>> pagrList = new ArrayList<List<PolicyAgrVO>>();;
		for(int i = 0; i < nList.size(); i++){
			PolicyVO pvo = new PolicyVO();
			pvo.setPo_no(nList.get(i).getNo());
			List<PolicyAgrVO> excelList = policyService.excelList(pvo);
			if(excelList != null) pagrList.add(excelList);
		}
		logger.info("��Ʈ �� : " + pagrList.size());
		ModelAndView mav = new ModelAndView(new ExcelList());
		mav.addObject("pagrList", pagrList);
		mav.addObject("file_name", "��� ���� ���");
		mav.addObject("nList", nList);
		return mav;
	};
}
