package com.exam.myapp.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet("/member/logout.do")
public class LogoutServlet extends HttpServlet{
//상속받아주기

	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("로그아웃 중");
		
//		// 1. 세션에 지정한 이름의 속성값을 null로 지정 
		HttpSession session = req.getSession();
//		session.setAttribute("loginUser", null);
//		 
//		// 2. removeAttribute 사용하기 -> 세션에 지정한 이름의 속성을 삭제 -> 한번도 저장하지 않은 것처럼 되는 
//		session.removeAttribute("loginUser");
		
		// 3. invalidate 사용하기 -> 세션 객체를 제거(후 다시 생성), 모든 속성들도 함께 삭제 => 저장한게 많으면 이걸 쓰는  
		session.invalidate();
		
		resp.sendRedirect(req.getContextPath()+"/member/login.do");	
	}

	
}
