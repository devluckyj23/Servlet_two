package com.exam.myapp.member;

import java.util.List;

public interface MemberService {

	List<MemberVO> selectMemberList();

	int insertMember(MemberVO vo);

	int deleteMember(String memId);

	MemberVO selectMember(String memId);

	int updateMember(MemberVO vo);

	MemberVO selectLogin(MemberVO vo);

}