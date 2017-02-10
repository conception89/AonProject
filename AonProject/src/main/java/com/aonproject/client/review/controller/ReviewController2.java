package com.aonproject.client.review.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aonproject.admin.recomment.service.RecommentService;
import com.aonproject.admin.recomment.vo.RecommentVO;
import com.aonproject.admin.review.FileUploadUtil;
import com.aonproject.admin.review.service.ReviewService;
import com.aonproject.admin.review.vo.ReviewVO;
import com.aonproject.admin.reviewImg.service.ReviewImgService;
import com.aonproject.admin.reviewImg.vo.ReviewImgVO;
import com.aonproject.client.mInfo.vo.MemberVO;
import com.aonproject.common.util.paging.PagingSet;

@Controller
@RequestMapping(value="/review")
public class ReviewController2 {
	Logger logger = Logger.getLogger(ReviewController2.class);
	String mode = "";
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private ReviewImgService reviewImgService;
	
	@Autowired
	private RecommentService recommentService;
	
	//리스트 불러오기
	@RequestMapping(value="/reviewuserList", method=RequestMethod.GET)
	public String reviewList(Authentication auth,@ModelAttribute ReviewVO rvo, @ModelAttribute ReviewImgVO revo, @ModelAttribute RecommentVO comvo, Model model){
		logger.info("reviewList호출 성공");
		//member 선언
		/*MemberVO mvo = (MemberVO) auth.getPrincipal();
		rvo.setM_no(mvo.getM_no());*/
		
		/*rvo.setP_no(p_no);
		
		int chkP_no = p_no.indexOf(",");
		if(chkP_no > -1) p_no = p_no.substring(0, chkP_no);
		
		System.out.println(p_no);*/
		System.out.println("p_no="+rvo.getP_no());
		int result = rvo.getP_no().indexOf("C");
		if(result > -1){
			rvo.setP_no(rvo.getP_no().substring(0, result));
		}
	
		//페이징 처리
		int cnt = 0;
		cnt = reviewService.cntList();
		rvo.setCountList(5);
		PagingSet.setPageing(rvo, cnt);
		
		//리스트 담기 (내용만)
		List<ReviewVO> reviewuserList = reviewService.reviewuserList(rvo);
		logger.info("reviewuserList="+reviewuserList);
		model.addAttribute("reviewuserList", reviewuserList);
		model.addAttribute("reviweVO",rvo);
		
		//리스트 담기(사진만)
		List<ReviewImgVO> reviewImgList = reviewImgService.reviewImgList(revo);
		logger.info("reviewImgList="+reviewImgList);
		model.addAttribute("reviewImgList",reviewImgList);
		
		//이건 코멘트
		List<RecommentVO> recommentList = recommentService.recommentList(comvo);
		logger.info("recommentList="+recommentList);
		model.addAttribute("recommentList",recommentList);
		
		return "client/userreviewList";
	}
	
		
	//리뷰 쓰기!!
	@ResponseBody
	@RequestMapping(value="/reviewuserInsert", method=RequestMethod.POST)
	public String reviewInsert(Authentication auth ,@ModelAttribute ReviewVO rvo, @ModelAttribute ReviewImgVO revo, HttpServletRequest request) throws IllegalAccessException, IOException{
		logger.info("reviewuserInsert호출 성공");
		MemberVO mvo = (MemberVO) auth.getPrincipal();
		rvo.setM_no(mvo.getM_no());
		/*int resultpno = rvo.getP_no().indexOf("C");
		if(resultpno > -1){
			rvo.setP_no(rvo.getP_no().substring(0, resultpno));
		}*/
		String resultS = "";
		mode = "insert";
		int result = 0;
		int reno = 0;
		int selectAll = 0;
		selectAll = reviewService.confirmMno(rvo);
		System.out.println("selectAll = " + selectAll);
		
		if(selectAll!=0){
			resultS = "fail";
			
		}else if(selectAll == 0){
			reno = reviewService.selectReno();
			rvo.setRe_no(reno);//reno에 review테이블의 re_no가 담겨있다.
			int selectreno = rvo.getRe_no();
			logger.info("selectreno="+selectreno);
			
			result = reviewService.reviewUserInsert(rvo); //글이 서버에 등록되면 1이 된다.
			logger.info("result="+result);
			int temp = rvo.getRe_no(); //temp에 re_no값이 담겨있다.
			logger.info("temp="+temp);
			
			revo.setRe_no(temp);

			if(result == 1){//글이 입력이 되었을 때
				reviewService.InsertID(rvo);
				List<MultipartFile>	files = revo.getFiles();
				logger.info("files="+files);
					
				if(files != null && files.size() > 0){
					for(MultipartFile file : files){
						revo.setFile(file);
						int fileResult = imgInsert(revo, request);
						logger.info(fileResult);
					}
				}
			}else{
				logger.info(result);
			}
			
			resultS = "success";
		}
		return resultS;
	}
	//리뷰쓰기(이미지 파일 없을 때)
	@ResponseBody
	@RequestMapping(value="/reviewUserInsert", method=RequestMethod.POST)
	public String reviewUserInsert(Authentication auth, @ModelAttribute ReviewVO rvo, HttpServletRequest request){
		logger.info("reviewUserInsert호출 성공");
		MemberVO mvo = (MemberVO) auth.getPrincipal();
		rvo.setM_no(mvo.getM_no());
		/*int resultpno = rvo.getP_no().indexOf("C");
		if(resultpno > -1){
			rvo.setP_no(rvo.getP_no().substring(0, resultpno));
		}*/
		String resultS = "";
		int selectAll = 0;
		selectAll = reviewService.confirmMno(rvo);
		System.out.println("selectAll = " + selectAll);
		mode = "insert";
		int result = 0;
		int reno = 0;
		if(selectAll!=0){
			resultS = "fail";
			
		}else if(selectAll == 0){
			reno = reviewService.selectReno();
			rvo.setRe_no(reno);//reno에 review테이블의 re_no가 담겨있다.
			int selectreno = rvo.getRe_no();
			logger.info("selectreno="+selectreno);
			
			result = reviewService.reviewUserInsert(rvo); //글이 서버에 등록되면 1이 된다.
			logger.info("result="+result);

			if(result == 1){//글이 입력이 되었을 때
				reviewService.InsertID(rvo);
				logger.info(result);
			}else{
				logger.info(result);
			}
			
			resultS = "success";
		}
		
		return resultS;
	}
	
	
	/*상품 이미지 등록 수정*/
	public int imgInsert(ReviewImgVO revo, HttpServletRequest request)throws IOException{
		int fileResult = 0;
		
		String ri_file = FileUploadUtil.fileUpload(revo.getRe_no(), revo.getFile(), request);
		revo.setRi_file(ri_file);
		if(ri_file != null){
			if(mode == "insert"){
				fileResult = reviewImgService.reviewImgInsert(revo);
			}else if(mode == "update"){
				fileResult = reviewImgService.reviewImgInsert(revo);
			}
			if(fileResult == 1){
				logger.info("fileResult="+fileResult);
			}else{
				logger.info("fileResult="+fileResult);
			}
		}
		return fileResult;
	}
	
	
	
	//비밀번호 확인
	@RequestMapping(value="/reviewConfirm", method=RequestMethod.POST)
	public ResponseEntity<Integer> pwdConfirm(@ModelAttribute ReviewVO rvo){
		logger.info("pwdConfirm 호출 성공");
		ResponseEntity<Integer> entity = null;
		int result = 0;
		try{
			result = reviewService.pwdConfirm(rvo);
			if(result==1){
				entity = new ResponseEntity<Integer>(result, HttpStatus.OK);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	//review내용 사용자가 update
	@ResponseBody
	@RequestMapping(value="/reviewuserUpdate", method=RequestMethod.POST)
	public String reviewUpdate(@ModelAttribute ReviewVO rvo,@ModelAttribute ReviewImgVO revo, HttpServletRequest request) throws IllegalStateException, IOException{
		mode = "update";
		logger.info("reviewUpdate호출 성공");
		int re_no = revo.getRe_no();
		
		int result = 0;
		result = reviewService.reviewUpdate(rvo);
		logger.info("result="+result);
		
		if(result == 1){
			List<MultipartFile> files = revo.getFiles();
			logger.info("files = "+files.size());
			
			String fileName = files.get(0).getOriginalFilename().toString();
			logger.info("fileName="+fileName);
			
			if(fileName != ""){
				FileUploadUtil.fileDelete(revo.getRi_file(), request);
				reviewImgService.reviewImgDelete(re_no);
				for(MultipartFile file : files){
					revo.setFile(file);
					int resultFile = imgInsert(revo, request);
					logger.info("resultFile="+resultFile);
				}
			}
		}else{
			logger.info(result);
		}
		
		String resultData =  "success";
		
		return resultData;
	}
	
	//update 파일 없을때!!
	@ResponseBody
	@RequestMapping(value="/reviewUserUpdate", method=RequestMethod.POST)
	public String reviewUserUpdate(@ModelAttribute ReviewVO rvo, HttpServletRequest request){
		logger.info("reviewUpdate호출 성공");
		
		int result = 0;
		result = reviewService.reviewUserUpdate(rvo);
		logger.info("result="+result);
		
		if(result == 1){
			logger.info(result);
		}else{
			logger.info(result);
		}
		
		String resultData =  "success";
		
		return resultData;
	}
	
	@ResponseBody
	@RequestMapping(value="/reviewOrderConfirm", method=RequestMethod.POST)
	public String reviewOrderConfirm(Authentication auth,@ModelAttribute ReviewVO rvo, HttpServletRequest request){
		logger.info("reviewOrderConfirm호출 성공");
		MemberVO mvo = (MemberVO) auth.getPrincipal();
		rvo.setM_no(mvo.getM_no());
		String resultS = "";
		System.out.println(rvo.getM_no());
		System.out.println(rvo.getP_no());
		System.out.println(rvo.getP_no().indexOf("1"));
		System.out.println("-------------------------------");
		
		/*int result = rvo.getP_no().indexOf("C");
		System.out.println(rvo.getP_no().substring(0, result));
		
		
		System.out.println("result = "+result);
		if(result > -1){
			rvo.setP_no(rvo.getP_no().substring(0, result));
		}*/
		
		String confirm = "";
		confirm = reviewService.reviewOrderConfirm(rvo);
		logger.info("confirm = " + confirm);
		
		System.out.println("confirm = " + confirm);
		
		if(Integer.parseInt(confirm) ==0){
			resultS = "fail";
		}else if(Integer.parseInt(confirm) > 0){
			resultS = "success";
		}
		return resultS;
	}
}
