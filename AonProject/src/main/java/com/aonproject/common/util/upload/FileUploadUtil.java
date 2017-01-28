package com.aonproject.common.util.upload;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	static Logger logger = Logger.getLogger(FileUploadUtil.class);
	
	/*���� ���ε� �޼���*/
	public static String fileUpload(MultipartFile file, HttpServletRequest request) throws IOException{
		logger.info("fileUpload ȣ�⼺��");
		
		String real_name = null;
		String org_name = file.getOriginalFilename();
		logger.info("org_name= "+org_name);
		
		String imgRoot = null;
		
		//���ϸ� ����
		if(org_name != null && (!org_name.equals(""))){
			real_name = System.currentTimeMillis()+"_"+org_name; 
			
			String docRoot = request.getSession().getServletContext().getRealPath("/productUpload");
			imgRoot = docRoot.toString();
			File fileDir = new File(docRoot);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			File fileAdd = new File(docRoot+"/"+real_name);
			logger.info("upload file= "+fileAdd);
			
			file.transferTo(fileAdd);
		}
		return imgRoot+"/"+real_name;
	}
	
	/*���� ���� �޼���*/
	public static void fileDelete(String fileName, HttpServletRequest request) throws IOException {
		logger.info("fileDelete ȣ�� ����");
		boolean result = false;
		String docRoot = request.getSession().getServletContext().getRealPath("/productUpload");
		
		File fileDelete = new File(docRoot+"/"+fileName);
		logger.info("delete file= "+fileDelete);
		
		if(fileDelete.exists() && fileDelete.isFile()){
			result = fileDelete.delete();
		}
		logger.info("��������(true/false): "+result);
	}
}
