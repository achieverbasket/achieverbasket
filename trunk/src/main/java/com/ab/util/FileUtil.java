package com.ab.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	final static String location = "C:/Users/Sara/Google Drive/DigitalResume/documents";
	
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
			
			System.out.println("filePath: "+filePath);
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

		    System.out.println("filepath: "+filePath);
		    if(null!=filePath)
		    {
				File file = new File(filePath);
			    FileInputStream input = new FileInputStream(file);
			    multipartFile = new MockMultipartFile("file",
			            file.getName(), "text/plain", IOUtils.toByteArray(input));
		    }
		    else
		    {
		    	System.out.println("file hard copy location is null");
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("multipartFile: "+multipartFile);
		return multipartFile;
	}

}
