package com.ab.util;

import java.io.File;
import java.io.IOException;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

public class CustomAWS {
	
	static AmazonS3 s3Obj = null;
	static{
		s3Obj = AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1).
		withCredentials(new ProfileCredentialsProvider("default")).build();
	}
	
	public static String uploadDocument(File file,String bucketName,String objKey) throws IOException{
			if(s3Obj.doesObjectExist(bucketName, objKey)){
				System.out.println("Object Key already exists: "+" file: "+file+" bucketName: "+bucketName+" objKey: "+objKey);
				return "Object Key already exists";
			}else{
				/*TransferManager tm = TransferManagerBuilder.standard().withS3Client(s3Obj).build();
				Upload upload = tm.upload(bucketName, objKey,file);
				
				upload.isDone();*/
				AccessControlList acl = new AccessControlList();
				acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
				PutObjectRequest  pobj = new PutObjectRequest(bucketName, objKey, file);
				pobj.setAccessControlList(acl);
				s3Obj.putObject(pobj);
				System.out.println("file: "+file+" bucketName: "+bucketName+" objKey: "+objKey);
				// return the final string
			}
			
			return null;
	}
	
	public String getDocumentUri(String bucketName,String objKey){
		
		S3Object obj = s3Obj.getObject(bucketName, objKey); 
		String uri = obj.getObjectContent().getHttpRequest().getURI().toString();
		return uri;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	System.out.println(object.getRegionName());
	
	//achieverbasketimgS3Owner
	List<Bucket> buckets = object.listBuckets();
	for (Bucket b : buckets) {
        System.out.println("bucket name : " + b.getName()+b.getOwner()+b.getCreationDate());
        object.getBucketLocation(b.getName());
        ObjectListing objList = object.listObjects(b.getName());
       
        List<S3ObjectSummary> list = objList.getObjectSummaries();
        
        for(S3ObjectSummary a:list){
        	String key = a.getKey();
        	System.out.println("keys inside this bucket "+ key);
        	
        	
        	 S3Object sobj = object.getObject(b.getName(), key);
        	 System.out.println("URL of images "+sobj.getObjectContent().getHttpRequest().getURI());
        	 
        }
        
       
	}
	//System.out.println(object.ENDPOINT_PREFIX);
	//https://s3.ap-south-1.amazonaws.com/achieverbasketimg/issuer/template/library_icons_TemplatesSquare.png
	
	 System.out.println();
	*/
}
