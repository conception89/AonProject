package com.aonproject.common.util.upload.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aonproject.common.util.upload.vo.UploadVO;

public interface UploadDAO {
	//�̹��� ����Ʈ
	public List<UploadVO> uploadList(UploadVO uvo);
	
	public int uploadInsert(UploadVO uvo, List<MultipartFile> getFileList);
}
