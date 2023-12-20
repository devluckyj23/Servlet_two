package com.exam.myapp.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/member/login.do")
public class LoginServlet extends HttpServlet{
//상속받아주기
	private MemberService memberService = MemberServiceImpl.getInstance();
	

	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		String memId = req.getParameter("memId");
//		MemberVO vo = memberDao.selectMember(memId);
//		//memId를 주면 전체 회원정보를 받아오도록 
//		req.setAttribute("mvo", vo);
//		//vo값을 mvo에 저장해주고 이를 edit.jsp에서 꺼내쓰도록 해야 함
		
		req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
		
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//req.setCharacterEncoding("UTF-8");// 한글 값 안 깨지게 요청encoding할 떄 utf-8로 해줘야함
		//-> filter에다가 적용시킬 것 

		System.out.println("로그인 중");
		
		MemberVO vo = new MemberVO();
		
		vo.setMemId(req.getParameter("memId"));
		vo.setMemPass(req.getParameter("memPass")); 
		
		MemberVO mvo = memberService.selectLogin(vo);
		
		if(mvo == null) { // 로그인 실패 -> mvo의 값이 null이 됨 (select를 해줄 수 없으니까)
			// 실패하면 다시 로그인 창으로 가야 함(redirection) 아니면 로그인 jsp를 실행해도 됨(forward) 
			resp.sendRedirect(req.getContextPath()+"/member/login.do");	
			
		} else { // 로그인 성공 -> mvo에 아이디 값이 있음
			// 성공하면 리스트를 보여주도록 
			// 요청객체(하나의 요청을 처리->x), 세션객체(일정시간동안 유지), 서블릿컨텍스트객체(서블릿 종료까지)에 저장해야되는데 
			// 세션에다가 저장해야 함 (서블릿은 모든 유저가 공유 -> 한 사람만 로그인한게 아니라 모든 사람이 로그인했다고 표시가 됨)
			
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", mvo);
			// session에다가 저장을 하고 로그인한다면 저기서 값을 꺼내오면 됨 
			
			resp.sendRedirect(req.getContextPath()+"/member/list.do");
			
		}
		
	}

	
}
