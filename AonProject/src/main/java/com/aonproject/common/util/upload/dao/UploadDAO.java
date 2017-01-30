package com.aonproject.common.util.upload.dao;

import java.util.List;

import com.aonproject.common.util.upload.vo.UploadVO;

public interface UploadDAO {
	//�̹��� ����Ʈ
	public List<UploadVO> uploadList(UploadVO uvo);
	
	public int uploadInsert(UploadVO uvo);
	public int uploadUpdate(UploadVO uvo);
}