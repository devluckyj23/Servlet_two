package com.exam.myapp.bbs;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BbsVO { 

	private int bbsNo;
	private String bbsTitle; 
	private String bbsContent; 
	private String bbsWriter; 
	private Date bbsRegDate;
	private int bbsCount;
	
	private List<AttachVO> attachList;
	
	// 멀티파트 폼 데이터에 포함된 파일을 받기 위한 변수는 MultipartFile 타입으로 설정
	// 이름이 같은 다수의 요청 파라미터 값을 받기 위해서는 배열/List 타입으로 설정
	private List<MultipartFile> bbsFile;
	
	public int getBbsNo() {return bbsNo;}
	public void setBbsNo(int bbsNo) {this.bbsNo = bbsNo;}
	
	public String getBbsTitle() {return bbsTitle;}
	public void setBbsTitle(String bbsTitle) {this.bbsTitle = bbsTitle;}
	
	public String getBbsContent() {return bbsContent;}
	public void setBbsContent(String bbsContent) {this.bbsContent = bbsContent;}
	
	public String getBbsWriter() {return bbsWriter;}
	public void setBbsWriter(String bbsWriter) {this.bbsWriter = bbsWriter;}
	
	public Date getBbsRegDate() {return bbsRegDate;}
	public void setBbsRegDate(Date bbsRegDate) {this.bbsRegDate = bbsRegDate;}
	
	public int getBbsCount() {return bbsCount;}
	public void setBbsCount(int bbsCount) {this.bbsCount = bbsCount;}
	
	public List<MultipartFile> getBbsFile() {return bbsFile;}
	public void setBbsFile(List<MultipartFile> bbsFile) {this.bbsFile = bbsFile;}
	
	public List<AttachVO> getAttachList() {return attachList;}
	public void setAttachList(List<AttachVO> attachList) {this.attachList = attachList;}
	
	
	
}
