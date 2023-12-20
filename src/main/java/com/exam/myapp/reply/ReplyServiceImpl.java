package com.exam.myapp.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDao replyDao;
	//등록
	@Override
	public int insertReply(ReplyVO rvo) {
		return replyDao.insertReply(rvo);
	}
	//삭제
	@Override
	public int deleteReply(ReplyVO rvo) {
		return replyDao.deleteReply(rvo);
	}

	@Override
	public List<ReplyVO> selectReplyList(int repBbsNo) {
		// TODO Auto-generated method stub
		return replyDao.selectReplyList(repBbsNo);
	}

}
