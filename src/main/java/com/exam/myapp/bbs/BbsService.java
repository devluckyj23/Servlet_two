package com.exam.myapp.bbs;

import java.io.File;
import java.util.List;

public interface BbsService {

	List<BbsVO> selectBbsList(SearchInfo info);

	int insertBbs(BbsVO vo);

	int deleteBbs(BbsVO vo);

	BbsVO selectBbs(int bbsNo);

	int updateBbs(BbsVO vo);

	AttachVO selectAttach(int attNo);

	File getAttachFile(AttachVO vo);

	int selectBbsCount(SearchInfo info);
}