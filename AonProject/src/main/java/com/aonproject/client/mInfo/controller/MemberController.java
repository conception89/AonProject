package com.aonproject.client.mInfo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aonproject.admin.category.service.CategoryService;
import com.aonproject.admin.category.vo.CategoryVO;
import com.aonproject.admin.policy.service.PolicyService;
import com.aonproject.admin.qna.service.QnaService;
import com.aonproject.admin.qna.vo.QnaVO;
import com.aonproject.admin.review.service.ReviewService;
import com.aonproject.admin.review.vo.ReviewVO;
import com.aonproject.client.mInfo.service.MemberService;
import com.aonproject.client.mInfo.vo.MemberSubAddressVO;
import com.aonproject.client.mInfo.vo.MemberVO;
import com.aonproject.client.order.service.OrderService;
import com.aonproject.client.order.vo.Product_orderVO;
import com.aonproject.common.util.email.Certification;
import com.aonproject.common.util.email.Email;
import com.aonproject.common.util.email.EmailSender;
import com.aonproject.common.util.paging.PagingSet;
import com.aonproject.common.util.security.ShaEncoder;
import com.aonproject.common.util.vo.PolicyAgrVO;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	private Logger logger = Logger.getLogger(MemberController.class);
	
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
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private QnaService qnaService;
	
	@Autowired
	private OrderService orderService;
	
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
		if((mode == null || mode.trim().equals("")) && request.getMethod().equals("GET")){
			return "client/cInfo/joinForm";
		}
		else if((mode.equals("success")) && request.getMethod().equals("POST")){
			model.addAttribute("view1", policyService.policyView1());
			model.addAttribute("view2", policyService.policyView2());
			return "client/cInfo/joinForm2";
		}
		else if((mode.equals("good")) && request.getMethod().equals("POST")){
			return "client/cInfo/joinForm3";
		}
		return "client/cInfo/joinForm";
	};
	
	// ���̵� / ��й�ȣ ã��
	@RequestMapping(value="/lostme")
	public String lostme(@ModelAttribute CategoryVO cvo, Model model){
		logger.info("lostme ȣ�� ����");
		List<CategoryVO> categoryList = categoryService.categoryList(cvo);
		model.addAttribute("categoryList", categoryList);
		
		return "client/cInfo/lostme";
	}
	
	
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
		cookie.setMaxAge(60* 30);
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
				vo.setM_no(m_no);
				memberService.addAddr(vo);
		
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
	
	// ���������� - �ֹ���ȸ+��� ����
	@RequestMapping(value="/mypage/orderlist")
	public ModelAndView orderlist(Authentication auth, @ModelAttribute CategoryVO cvo, HttpServletRequest request){
		logger.info("orderlist ȣ�� ����");
		
		ModelAndView mav = new ModelAndView();
		List<CategoryVO> categoryList = categoryService.categoryList(cvo);
		mav.addObject("categoryList", categoryList);
		
		MemberVO vo = (MemberVO) auth.getPrincipal();
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum != null) vo.setPageNum(pageNum);
		
		int cnt = orderService.myOrderCnt(vo);
		PagingSet.setPageing(vo, cnt);
		List<Product_orderVO> list = orderService.myOrder(vo);
		
		if(list != null){
			mav.addObject("orderList", list);
		}
		
		mav.setViewName("client/mypage/orderlist");

		mav.addObject("memberVO", vo);
		return mav;
	}
	
	// ���������� - ���� �ı� ����
	@RequestMapping(value="/mypage/review")
	public ModelAndView review(Authentication auth, @ModelAttribute CategoryVO cvo, HttpServletRequest request){
		logger.info("review ȣ�� ����");
		
		MemberVO vo = (MemberVO) auth.getPrincipal();
		String pageNum = request.getParameter("pageNum");
		if(pageNum != null) vo.setPageNum(pageNum);
		
		ModelAndView mav = new ModelAndView();
		List<CategoryVO> categoryList = categoryService.categoryList(cvo);
		mav.addObject("categoryList", categoryList);

		int cnt = reviewService.myReviewCnt(vo);
		PagingSet.setPageing(vo, cnt);
		List<ReviewVO> list = reviewService.myReview(vo);
		
		if(list != null){
			mav.addObject("reviewList", list);
		}

		mav.addObject("memberVO", vo);
		mav.setViewName("client/mypage/review");
		
		return mav;
	}
	
	// ���������� - ��ǰ ���� ����
	@RequestMapping(value="/mypage/qna")
	public ModelAndView qna(Authentication auth, @ModelAttribute CategoryVO cvo, HttpServletRequest request){
		logger.info("qna ȣ�� ����");
		MemberVO vo = (MemberVO) auth.getPrincipal();
		String pageNum = request.getParameter("pageNum");
		if(pageNum != null) vo.setPageNum(pageNum);
		
		ModelAndView mav = new ModelAndView();

		int cnt = qnaService.myQnaCnt(vo);
		PagingSet.setPageing(vo, cnt);
		List<CategoryVO> categoryList = categoryService.categoryList(cvo);
		mav.addObject("categoryList", categoryList);
		
		List<QnaVO> list = qnaService.myQnA(vo);
		
		if(list != null){
			mav.addObject("qnaList", list);
		}
		
		mav.addObject("memberVO", vo);
		mav.setViewName("client/mypage/qna");
		return mav;
	}
	
	// ���������� - �� ����
	@RequestMapping(value="/mypage/myinfo")
	public ModelAndView myinfo(Authentication auth, @ModelAttribute CategoryVO cvo){
		logger.info("myinfo ȣ�� ����");
		ModelAndView mav = new ModelAndView();
		List<CategoryVO> categoryList = categoryService.categoryList(cvo);
		mav.addObject("categoryList", categoryList);
		
		MemberVO vo = (MemberVO) auth.getPrincipal();
		
		MemberVO mvo = memberService.memberInfo(vo);	
		List<MemberSubAddressVO> list = (List<MemberSubAddressVO>) memberService.mSubAddrs(vo);
		if(list.size() > 0) mav.addObject("msa", list);
		
		mav.addObject("vo",mvo);
		mav.setViewName("client/mypage/myinfo");
		return mav;
	}
	
	// ���������� - �� ���� ����
	@ResponseBody
	@RequestMapping(value="/mypage/myinfoU")
	public String myinfoU(@ModelAttribute MemberVO vo, Authentication auth){
		logger.info("myinfoU ȣ�� ����");
		String result = "";
		
		MemberVO mvo = (MemberVO) auth.getPrincipal();
		vo.setM_no(mvo.getM_no());
		
		if(vo.getM_pwd() != ""){
			vo.setM_pwd(encoder.encoding(vo.getM_pwd()));
		}
		
		int gogo = memberService.myInfoUpdate(vo);
		int gogo2 = memberService.myAddrUpdate(vo);
		if (gogo == 1 && gogo2 == 1){
			result = "success";
		} 
		else{
			result = "fail";
		}
		
		return result;
	}
	
	// ���������� - ���� �ּ� ����
	@ResponseBody
	@RequestMapping(value="/mypage/myinfoD")
	public String myinfoD(Authentication auth, @ModelAttribute MemberSubAddressVO vo){
		logger.info("myinfoD ȣ�� ����");
		String result = "";
		MemberVO mvo = (MemberVO) auth.getPrincipal();
		vo.setM_no(mvo.getM_no());
		
		int gogo = memberService.msaD(vo);
		if (gogo == 1){
			result = "success";
		} 
		else{
			result = "fail";
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/lostIdChk")
	public String lostIdChk(@ModelAttribute MemberVO vo){
		logger.info("lostIdChk ȣ�� ����");
		String result = "";
		int gogo = memberService.lostIdChk(vo);
		if(gogo != 1) result = "success";
		return result;
	}

	@ResponseBody
	@RequestMapping(value="/lostPwdChk")
	public String lostPwdChk(@ModelAttribute MemberVO vo){
		logger.info("lostPwdChk ȣ�� ����");
		String result = "";

		int gogo = memberService.lostPwdChk(vo);
		if(gogo == 1) result = "success";
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/sendId")
	public String sendId(@ModelAttribute MemberVO vo) throws Exception{
		logger.info("sendId ȣ�� ����");
		String result = "";
		String gogo = memberService.sendId(vo);
		
		Email email = new Email();
		email.setReciver(vo.getM_email().trim());
		email.setSubject("[AON] ���̵� �����帳�ϴ�.");
		email.setContent("ȸ���Կ� ���̵�� [" + gogo + "]�Դϴ�.");
		
		emailSender.SendEmail(email);
		result = "success";
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/sendPwd")
	public String sendPwd(@ModelAttribute MemberVO vo) throws Exception{
		logger.info("sendPwd ȣ�� ����");
		String result = "";
		String newPwd = Certification.randomPass();
		
		String security = encoder.encoding(newPwd);
		vo.setM_pwd(security);
		
		int gogo = memberService.sendPwd(vo);
		
		if(gogo==1){
			Email email = new Email();
			email.setReciver(vo.getM_email().trim());
			email.setSubject("[AON] �ӽ� ��й�ȣ�� �����帳�ϴ�.");
			email.setContent("ȸ���Կ� �ӽ� ��й�ȣ�� [" + newPwd + "]�Դϴ�. \n�� �������� ��й�ȣ�� �����ϼ���.");
			
			emailSender.SendEmail(email);
			result = "success";
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/emailChk", method={RequestMethod.GET, RequestMethod.POST})
	public String emailChk(Authentication auth, @ModelAttribute MemberVO vo, HttpServletRequest request){
		logger.info("overlapChk ȣ�⼺��");
		String result = "";
		int gogo = 0;
		if(request.getMethod().equals("GET")){
			gogo = memberService.emailChk(vo);
		}
		else if(request.getMethod().equals("POST")){
			MemberVO mvo = (MemberVO) auth.getPrincipal();
			vo.setM_no(mvo.getM_no());
			gogo = memberService.emailChk2(vo);
		}
		
		if (gogo == 1){
			result = "fail";
		} 
		else{
			result = "success";
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/idChk", method=RequestMethod.GET)
	public String idChk(@ModelAttribute MemberVO vo){
		logger.info("overlapChk ȣ�⼺��");
		String result = "";
		
		int gogo = memberService.idChk(vo);
		
		if (gogo == 1){
			result = "fail";
		} 
		else{
			result = "success";
		}
		return result;
	}
}
