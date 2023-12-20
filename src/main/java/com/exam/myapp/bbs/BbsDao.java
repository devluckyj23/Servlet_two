package com.exam.myapp.bbs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BbsDao {

	List<BbsVO> selectBbsList(SearchInfo info);

	int insertBbs(BbsVO vo);

	int deleteBbs(int memId);

	BbsVO selectBbs(int memId);

	int updateBbs(BbsVO vo);

	int selectBbsCount(SearchInfo info);
}