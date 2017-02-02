package com.aonproject.client.root;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RootController {
	
	// ���� ������
	@RequestMapping(value = "/")
	public String root(){
		return "client/main";
	}
	
	// �α��� ������
	@RequestMapping(value = "/login")
	public String loginForm(){
		return "client/cInfo/loginForm";
	}
	
	// Member(ȸ��) ���� ������ - 1�ܰ�
	@RequestMapping(value = "/join", method=RequestMethod.GET)
	public String joinForm(){
		return "client/cInfo/joinForm";
	};
}
