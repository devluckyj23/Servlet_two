package com.exam.myapp.bbs;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttachDao {
	
	int insertAttach(AttachVO vo);

	AttachVO selectAttach(int attNo);
	
	int deleteAttach(int attNo);

	/*
	 * List<BbsVO> selectBbsList();
	 * 
	 * int deleteBbs(int memId);
	 * 
	 * BbsVO selectBbs(int memId);
	 * 
	 * int updateBbs(BbsVO vo);
	 */
	}