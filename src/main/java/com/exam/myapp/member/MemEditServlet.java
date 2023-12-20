package com.exam.myapp.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/member/edit.do")
public class MemEditServlet extends HttpServlet{
//상속받아주기
	private MemberService memberService = MemberServiceImpl.getInstance();

	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		MemberVO vo = memberService.selectMember(memId);
		req.setAttribute("mvo", vo);
		
		req.getRequestDispatcher("/WEB-INF/views/member/memEdit.jsp").forward(req, resp);
		
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("회원 정보 수정중");
		
		MemberVO vo = new MemberVO();
		
		vo.setMemId(req.getParameter("memId"));
		vo.setMemName(req.getParameter("memName")); 
		vo.setMemPoint(Integer.parseInt(req.getParameter("memPoint"))); 
		
		int n = memberService.updateMember(vo);
		
		System.out.println(n+ " 명의 회원 정보 수정 ");
		
		resp.sendRedirect(req.getContextPath()+"/member/list.do");
	}

	
}

/*
 * 연결을 끊으려면 pstmt.close(); 명령문 객체가 사용하던 자원 반납 
 * conn.close();conn 데이터베이스와 연결 종료
 * 근데 여기다가 적으면 try에 오류가 생겼을 떄 catch 쪽으로 이동하지를 못함 (catch 명령문이 실행하짐 못함)
 * -> final에다가 적어줘야함 
 * catch 문이 끝나는 중괄호 다음번에 finally{}여기 안에다가 적어줘야함 
 * 꼭 해야할 작업 문이 있으면 try/catch 문 다음에 적어주기 
 * 근데 finally 중괄호에 적어도 오류사항이 생기는데 pstmt cannot be resolved(해석할 수 없다-> 이런 변수가 없다) 
 * conn과 pstmt의 선언이 try 내에 있어서 try 내애서만 사용가능 => try/catch 밖으로 선언을 빼줘야함 
 * 밖으로 선언을 빼줘도 전체 문장(pstmt.close();)에 오류가 생김 -> 예외처리해달라고 말하는것 & 초기값도 줘야함(null로)
 * 그러면 finally 에서도 얘네들을 예외처리를 해줘야함.. 그리고 pstmt가 null값인지 체크하고 해줘야함  
 * =>if(conn!=null)~
 * 반복적인 일들이 너무 많아!! java7에서는 자동적으로 적어주는 일이 잇어서 안 해줘도 됨 
 * 이제 자동으로 close해주는 문법이 있음 
 */