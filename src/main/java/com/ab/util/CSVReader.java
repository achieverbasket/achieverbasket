package com.ab.util;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.apache.log4j.Logger;

import com.ab.service.impl.LoginServiceImpl;
import com.ab.vo.certificate.BulkCertificate;
import com.google.common.collect.Lists;


public class CSVReader {
	public static void main(String[] args) throws Exception {
	    getBulkCertificateList(getFile());
	}
	
	final static Logger logger = Logger.getLogger(CSVReader.class);
	public static File getFile() {
	    String fileLocation="C:/Users/Sara/Google Drive/DigitalResume/BulkUploadcsv.csv";
	    File file = new File(fileLocation);
	    return file;
	}
	
	public static List<BulkCertificate> getBulkCertificateList(File csvFile)
	{
		List<BulkCertificate> bulkCertificateList = Lists.newArrayList();
		
	    List<String> lines;
		try 
		{
			lines = Files.readAllLines(csvFile.toPath(), 
			        StandardCharsets.UTF_8);
			int lineCount = 0;
		    for (String line : lines) {
				    	
		    	logger.info("line: "+ line);
		    	int count =0;
		    	if(lineCount>0)
		    	{
		    		BulkCertificate bulkCertificate = new BulkCertificate();	
		    		String[] array = line.split(",");
		    		for(String value : array)
			        {
		    			logger.info(count + "  "+value+" ");
			        	count++;
			        }
		    		bulkCertificate.setIssuerId(new Long(array[0]));//Mandatory
		    		bulkCertificate.setCandidateId(array[1].trim().equals("")?null:new Long(array[1]));
		    		bulkCertificate.setCertificateTemplateId(new Long(array[2]));//Mandatory
		    		bulkCertificate.setCertificateName(array[3].trim().equals("")?null:new String(array[3]));
		    		bulkCertificate.setCandidateFirstName(array[4].trim().equals("")?null:new String(array[4]));
		    		bulkCertificate.setCandidateLastName(array[5].trim().equals("")?null:new String(array[5]));
		    		bulkCertificate.setCandidateEmail(array[6].trim().equals("")?null:new String(array[6]));
		    		bulkCertificate.setCandidateMobileNumber(array[7].trim().equals("")?null:new Long(array[7]));
		    		bulkCertificate.setCertifcateIssueDate(new String(array[8]).replace('-', '/'));//Mandatory
		    		bulkCertificate.setCertificateEndDate(array[9].trim().equals("")?null:new String(array[9]).replace('-', '/'));
		    		logger.info("bulkCertificate: "+ bulkCertificate);
			        bulkCertificateList.add(bulkCertificate);
		    	}
		    	lineCount++;
		    	
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return bulkCertificateList;
	}
}
