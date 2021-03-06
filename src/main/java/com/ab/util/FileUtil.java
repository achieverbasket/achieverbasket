package com.ab.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUtil {
	
	final static String location = "C:/DigitalResume/documents";
	final static Logger logger = Logger.getLogger(FileUtil.class);
	
	private FileUtil()
	{
		
	}
	
	public static String saveDocumentToFile( Long userId, Long docId, MultipartFile file)
	{
		String filePath = location+"/"+userId+"/"+docId+".pdf";
		File docFilePath = new File(filePath);
		try
		{
			if(!docFilePath.exists())
			{
				docFilePath.mkdirs();
			}
			
			logger.info("filePath: "+filePath);
			file.transferTo(docFilePath);
			//FileUtils.copyFileToDirectory(certificateFile, certificateFilePath);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return filePath;
	}
	
	public static MultipartFile getDocumentFile(String filePath)
	{
		MultipartFile multipartFile=null;
		try
		{
//			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//		    multipartResolver.setMaxUploadSize(20971520);   // 20MB
//		    multipartResolver.setMaxInMemorySize(1048576);  // 1MB

		    logger.info("filepath: "+filePath);
		    if(null!=filePath)
		    {
				File file = new File(filePath);
			    FileInputStream input = new FileInputStream(file);
			    
			    multipartFile = new MockMultipartFile("file",
			            file.getName(), "text/plain", IOUtils.toByteArray(input));
		    }
		    else
		    {
		    	logger.info("file hard copy location is null");
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		logger.info("multipartFile: "+multipartFile);
		return multipartFile;
	}
	
	public static void main(String[] args) {
		try {
		MultipartFile file = FileUtil.getDocumentFile("C:/Users/Sara/Google Drive/DigitalResume/documents/12/45.pdf");
		
		FileUtil.saveDocumentToFile(99l,999l,file);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
