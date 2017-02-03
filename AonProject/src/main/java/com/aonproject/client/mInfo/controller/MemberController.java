package com.aonproject.client.mInfo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aonproject.client.mInfo.vo.MemberVO;
import com.aonproject.common.util.email.Certification;
import com.aonproject.common.util.email.Email;
import com.aonproject.common.util.email.EmailSender;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	private EmailSender emailSender;
	
	// �α��� ������
	@RequestMapping(value = "/login")
	public String loginForm(){
		return "client/cInfo/loginForm";
	}
		
	// Member(ȸ��) ���� ������
	@RequestMapping(value = "/join")
	public String joinForm(HttpServletRequest request){
		String mode = request.getParameter("mode");
		if(mode == null || mode.trim().equals("")){
			return "client/cInfo/joinForm";
		}
		else if(mode.equals("success")){
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
}
