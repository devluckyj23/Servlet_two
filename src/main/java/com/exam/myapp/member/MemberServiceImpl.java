package com.exam.myapp.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public List<MemberVO> selectMemberList() {
		return memberDao.selectMemberList();
	}

	@Override
	public int insertMember(MemberVO vo) {
		return memberDao.insertMember(vo);
	}

	@Override
	public int deleteMember(String memId) {
		return memberDao.deleteMember(memId);
	}

	@Override
	public MemberVO selectMember(String memId) {
		return memberDao.selectMember(memId);
	}

	@Override
	public int updateMember(MemberVO vo) {
		return memberDao.updateMember(vo);
	}

	@Override
	public MemberVO selectLogin(MemberVO vo) {
		return memberDao.selectLogin(vo);
	}

}
