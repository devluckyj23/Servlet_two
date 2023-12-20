package com.exam.myapp.reply;

import java.util.List;

public interface ReplyService {
	//등록
	public int insertReply(ReplyVO rvo);
	//삭제
	public int deleteReply(ReplyVO rvo);

	public List<ReplyVO> selectReplyList(int repBbsNo);
}
