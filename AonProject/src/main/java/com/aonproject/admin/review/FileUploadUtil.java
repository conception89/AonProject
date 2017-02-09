package com.aonproject.admin.review;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	static Logger logger = Logger.getLogger(FileUploadUtil.class);
	
	/*���� ���ε� �޼���*/
	public static String fileUpload(int fileOption, MultipartFile file, HttpServletRequest request)throws IOException{
		logger.info("fileUpload ȣ�� ����");
		
		String real_name = null;
		//MultipartFile Ŭ������ getFilr() �޼���� Ŭ���̾�Ʈ�� ���ε��� ���ϸ�
		String org_name = file.getOriginalFilename();
		logger.info("org_name : " + org_name );
		
		//���ϸ� ����(�ߺ����� �ʰ�)
		if(org_name != null && (!org_name.equals(""))){
			//real_name = "review_"+System.currentTimeMillis() + "_" + org_name; 
			//������ ���� �̸�
			//System.currentTimeMillis()�� �и��ʱ��� �����Ǿ �ߺ��� �� ����
			org_name = org_name.substring(org_name.lastIndexOf("."));
			real_name = fileOption+"."+System.currentTimeMillis()+org_name;
			String docRoot = request.getSession().getServletContext().getRealPath("/reviewFileUpload");
			//(/UploadStorage)��� ������ ����
			
			File fileDir = new File(docRoot);
			if(!fileDir.exists()){ //������ �����ϴ��� ����
				fileDir.mkdir();//���ϻ���
			}
			
			logger.info("���ε��� ���� ���(docRoot) : " + docRoot);
			File fileAdd = new File(docRoot + "/" + real_name); //���� ������
			logger.info(fileAdd);
			
			file.transferTo(fileAdd); //Ư�� ���� ����
		}
		return real_name;
	}
	
	/*���� ���� �޼���*/
	public static void fileDelete(String fileName, HttpServletRequest request)throws IOException{
		logger.info("fileDelete ȣ�� ����");
		boolean result = false;
		String docRoot = request.getSession().getServletContext().getRealPath("/reviewFileUpload");
		
		File fileDelete = new File(docRoot + "/" + fileName );
		logger.info("������ ���� ���(fileDelete) : " + fileDelete);
		if(fileDelete.exists() && fileDelete.isFile()){
			result = fileDelete.delete();
		}
		
		logger.info("���� ����(true/false) : " + result);
	}
}
