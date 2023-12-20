package com.exam.myapp.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {

	List<MemberVO> selectMemberList();

	int insertMember(MemberVO vo);

	int deleteMember(String memId);

	MemberVO selectMember(String memId);

	int updateMember(MemberVO vo);

	MemberVO selectLogin(MemberVO vo);

}