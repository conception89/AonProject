package com.aonproject.client.mInfo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aonproject.admin.category.service.CategoryService;
import com.aonproject.admin.category.vo.CategoryVO;
import com.aonproject.admin.policy.service.PolicyService;
import com.aonproject.client.mInfo.service.MemberService;
import com.aonproject.client.mInfo.vo.MemberVO;
import com.aonproject.common.util.email.Certification;
import com.aonproject.common.util.email.Email;
import com.aonproject.common.util.email.EmailSender;
import com.aonproject.common.util.security.ShaEncoder;
import com.aonproject.common.util.vo.PolicyAgrVO;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	Logger logger = Logger.getLogger(MemberController.class);
	
	@Resource(name = "shaEncoder")
	private ShaEncoder encoder;
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private PolicyService policyService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CategoryService categoryService;
	// �α��� ������
	@RequestMapping(value = "/login")
	public String loginForm(@ModelAttribute CategoryVO cvo, Model model){
		logger.info("loginForm ȣ�� ����");
		List<CategoryVO> categoryList = categoryService.categoryList(cvo);
		model.addAttribute("categoryList", categoryList);
		return "client/cInfo/loginForm";
	}
		
	// Member(ȸ��) ���� ������
	@RequestMapping(value = "/join")
	public String joinForm(@ModelAttribute CategoryVO cvo, HttpServletRequest request, Model model){
		logger.info("joinForm ȣ�� ����");
		
		List<CategoryVO> categoryList = categoryService.categoryList(cvo);
		model.addAttribute("categoryList", categoryList);
		
		String mode = request.getParameter("mode");
		if(mode == null || mode.trim().equals("")){
			return "client/cInfo/joinForm";
		}
		else if(mode.equals("success")){
			model.addAttribute("view1", policyService.policyView1());
			model.addAttribute("view2", policyService.policyView2());
			return "client/cInfo/joinForm2";
		}
		else if(mode.equals("good")){
			return "client/cInfo/joinForm3";
		}
		return "client/cInfo/joinForm";
	};
		
	// Member(ȸ��) ���� �̸��� ������ȣ �߼�
	@ResponseBody
	@RequestMapping(value = "/join/emailCertification")
	public String emailCertification(@ModelAttribute MemberVO vo, HttpServletResponse response) throws Exception{
		logger.info("emailCertification ȣ�� ����");
		String result = "";
		String reciver = vo.getM_email();
		String numbers = Certification.certificationNumbers();
			
		Email email = new Email();
		email.setReciver(reciver);
		email.setSubject("[AON] ������ȣ�� �����帳�ϴ�.");
		email.setContent("��û�Ͻ� ������ȣ�� [" + numbers + "]�Դϴ�.");
		
		emailSender.SendEmail(email);
		Cookie cookie = new Cookie("certificationNumbers", numbers);
		cookie.setMaxAge(60* 60 * 60);
		response.addCookie(cookie);
			
		result = "success";
			
		return result;
	};
	
	// Member(ȸ��) ���� ������ȣ Ȯ��
	@ResponseBody
	@RequestMapping(value="/join/emailCertificationChk")
	public String emailCertificationChk(@RequestParam("certificationNum") String certificationNum, HttpServletRequest request, HttpServletResponse response){
		logger.info("emailCertificationChk ȣ�� ����");
		String result = "";
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("certificationNumbers")){
					if(cookie.getValue().equals(certificationNum)){
						result = "success";
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
		}
		
		return result;
	}
	
	// Member(ȸ��) ȸ������
	@ResponseBody
	@RequestMapping(value="/joinGo")
	public String joinGo(@ModelAttribute MemberVO vo, @RequestParam("privacyChk") String privacy, @RequestParam("touChk") String tou){
		logger.info("joinGo ȣ�� ����");
		String result = "";
		
		vo.setM_pwd(encoder.encoding(vo.getM_pwd()));
		
		int gogo = memberService.joinGo(vo);
		
		if(gogo == 1){
			if(privacy != null && tou != null){
				PolicyAgrVO pavo = new PolicyAgrVO();
				int m_no = memberService.newNo();
				pavo.setM_no(m_no);
				pavo.setPo_no(policyService.policyView1().getPo_no());
				pavo.setPa_confirm(tou);
				policyService.pagr2(pavo);
				
				pavo.setPo_no(policyService.policyView2().getPo_no());
				pavo.setPa_confirm(privacy);
				policyService.pagr2(pavo);
				
				result = "good";
			}
		}
		else{
			result = "fail";
		}
		
		return result;
	};
}
