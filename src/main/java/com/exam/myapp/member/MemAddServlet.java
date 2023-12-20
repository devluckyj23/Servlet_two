package com.exam.myapp.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/member/add.do")
public class MemAddServlet extends HttpServlet{
//상속받아주기
	private MemberService memberService = MemberServiceImpl.getInstance();

	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/member/memAdd.jsp").forward(req, resp);
		
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("회원추가중");
		
		MemberVO vo = new MemberVO();
		
		vo.setMemId(req.getParameter("memId"));
		vo.setMemPass(req.getParameter("memPass")); 
		vo.setMemName(req.getParameter("memName")); 
		vo.setMemPoint(Integer.parseInt(req.getParameter("memPoint")));  
		
		int n = memberService.insertMember(vo);
		
		System.out.println(n+ " 명의 회원 추가 ");
		
		resp.sendRedirect(req.getContextPath()+"/member/list.do");
	
	}

	
}
