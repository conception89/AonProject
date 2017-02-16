package com.aonproject.admin.review.dao;

import java.util.List;

import com.aonproject.admin.review.vo.ReviewVO;
import com.aonproject.client.mInfo.vo.MemberVO;

public interface ReviewDAO {
	public List<ReviewVO> reviewList(ReviewVO rvo);
	public List<ReviewVO> reviewuserList(ReviewVO rvo);
	public int reviewInsert(ReviewVO rvo);
	public int selectReno();
	public int pwdConfirm(ReviewVO rvo);
	public int reviewUpdate(ReviewVO rvo);
	public int reviewDelete(int re_no);
	public int reviewSelectNo();
	public int rechkUpdate(int re_no);
	public int cntList();
	public int mnoList();
	public int onoList();
	public int reviewUserInsert(ReviewVO rvo);
	public int reviewUserUpdate(ReviewVO rvo);
	public int InsertID(ReviewVO rvo);
	public int confirmMno(ReviewVO rvo);

	public String reviewOrderConfirm(ReviewVO rvo);

	public List<ReviewVO> myReview(MemberVO vo);
	public int myReviewCnt(MemberVO vo);
	
	//ȸ���� ��ǰ�� ���� ������ ���並 ������ �ț����� Ȯ���ϴ� ����
	public int reviewConfirm(ReviewVO rvo);
}
