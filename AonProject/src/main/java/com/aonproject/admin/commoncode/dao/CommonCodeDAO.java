package com.aonproject.admin.commoncode.dao;

import java.util.List;

import com.aonproject.admin.commoncode.vo.CommonCodeVO;

public interface CommonCodeDAO {
	//�����ڵ� ����
	public List<CommonCodeVO> commonCodeList(CommonCodeVO cvo);
}
