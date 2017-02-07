package com.aonproject.admin.review.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aonproject.admin.review.dao.ReviewDAO;
import com.aonproject.admin.review.vo.ReviewVO;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{
	Logger logger = Logger.getLogger(ReviewServiceImpl.class);
	
	@Autowired
	private ReviewDAO reviewDAO;
	
	//���� ����Ʈ ���
	@Override
	public List<ReviewVO> reviewList(ReviewVO rvo){
		List<ReviewVO> myList = null;
		myList = reviewDAO.reviewList(rvo);
		return myList;
	}
	
	//���Է�
	@Override
	public int reviewInsert(ReviewVO rvo){
		int result = 0;
		result = reviewDAO.reviewInsert(rvo);
		System.out.println(result);
		return result;
	}
	@Override
	public int selectReno(){
		int result = 0;
		result = reviewDAO.selectReno();
		return result;
	}
	
	@Override
	public int pwdConfirm(ReviewVO rvo) {
		int result = 0;
		result = reviewDAO.pwdConfirm(rvo);
		return result;
	}

	@Override
	public int reviewUpdate(ReviewVO rvo) {
		int result = 0;
		result = reviewDAO.reviewUpdate(rvo);
		return result;
	}

	@Override
	public int reviewDelete(int re_no) {
		int result = 0;
		result = reviewDAO.reviewDelete(re_no);
		return result;	
	}

	@Override
	public int reviewSelectNo() {
		int result = 0;
		result = reviewDAO.reviewSelectNo();
		return result;
	}

	@Override
	public int rechkUpdate(int re_no) {
		int result  = 0;
		result  = reviewDAO.rechkUpdate(re_no);
		return result;
	}

	@Override
	public int cntList() {
		int result = 0;
		result = reviewDAO.cntList();
		return result;
	}

}
