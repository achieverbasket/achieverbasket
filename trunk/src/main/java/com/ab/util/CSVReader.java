package com.ab.util;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import com.ab.vo.certificate.BulkCertificate;
import com.google.common.collect.Lists;


public class CSVReader {
	public static void main(String[] args) throws Exception {
	    getBulkCertificateList(getFile());
	}
	
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
				    	
		    	System.out.println("line: "+ line);
		    	int count =0;
		    	if(lineCount>0)
		    	{
		    		BulkCertificate bulkCertificate = new BulkCertificate();	
		    		String[] array = line.split(",");
		    		for(String value : array)
			        {
			        	System.out.println(count + "  "+value+" ");
			        	count++;
			        }
		    		bulkCertificate.setIssuerId(new Long(array[0]));//Mandatory
		    		bulkCertificate.setCandidateId(array[1].trim().equals("")?null:new Long(array[1]));
		    		bulkCertificate.setCertificateTemplateId(new Long(array[2]));//Mandatory
		    		bulkCertificate.setCertificateName(array[3].trim().equals("")?null:new String(array[3]));
		    		bulkCertificate.setCandidateName(array[4].trim().equals("")?null:new String(array[4]));
		    		bulkCertificate.setCandidateEmail(array[5].trim().equals("")?null:new String(array[5]));
		    		bulkCertificate.setCandidateMobileNumber(array[6].trim().equals("")?null:new Long(array[6]));
		    		bulkCertificate.setCertifcateIssueDate(new String(array[7]).replace('-', '/'));//Mandatory
		    		bulkCertificate.setCertificateEndDate(array[8].trim().equals("")?null:new String(array[8]).replace('-', '/'));
			        System.out.println("bulkCertificate: "+ bulkCertificate);
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
