package com.exam.myapp.member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//@Repository
public class MemberDaoBatis implements MemberDao{
	
	@Autowired
	//private SqlSessionFactory sqlSessionFactory;
	private SqlSession session;
	
	@Override
	public List<MemberVO> selectMemberList() {
		return session.selectList("com.exam.myapp.member.MemberDao.selectMemberList");
	}
	

	@Override
	public int insertMember(MemberVO vo) {
		return session.insert("com.exam.myapp.member.MemberDao.insertMember", vo);
	}

	@Override
	public int deleteMember(String memId) {
		return session.delete("com.exam.myapp.member.MemberDao.deleteMember", memId);
	}

	@Override
	public MemberVO selectMember(String memId) {
		return session.selectOne("com.exam.myapp.member.MemberDao.selectMember", memId);
	}

	@Override
	public int updateMember(MemberVO vo) {
		return session.update("com.exam.myapp.member.MemberDao.updateMember", vo);
	}

	@Override
	public MemberVO selectLogin(MemberVO mvo) {
		return session.selectOne("com.exam.myapp.member.MemberDao.selectLogin", mvo);
	}

	
}
