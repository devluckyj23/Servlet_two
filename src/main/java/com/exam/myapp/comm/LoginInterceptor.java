package com.exam.myapp.comm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.exam.myapp.member.MemberVO;

// 다수의 컨트롤러 실행 전후에 수행해야 하는 공통작업들은 핸들러인터셉터를 사용하여 구현 가능 
// 1.
public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 컨트롤러 실행 전에 실행되는 메소드
		// handler : 현재 인터셉터 이후에 실행될 인터셉터 혹은 컨트롤러 
		// 반환값 : 이후에 실행될 인터셉터 혹은 컨트롤러의 실행 여부 -> 그래서 boolean 타입 
		
		
		// 요청보낸 사용자의 세션을 가져오기
		HttpSession session = request.getSession(); 
		// 세션에 로그인 정보를 꺼내 옴 (모든 정보를 저장할 수 있으니 loginUser가 MemberVo라는 걸 확실하지못해서 오류줄 생김 
		// Add cast to "MemberVo"를 눌러주면 됨 
		MemberVO vo = (MemberVO)session.getAttribute("loginUser"); 
		// 로그인 정보가 없다면  
		if(vo == null) {
		// 로그인 페이지로 이동시키기
			response.sendRedirect(request.getContextPath()+"/member/login.do");	
			return false; // Controller 실행을 막아야 하니까 boolean타입의 결과값인 T/F 중에서 F를 반황시킴   
			
		}
		
		return true;
		// 만약 여기까지 오면 로그인한거니까 컨트롤러를 실행하도록 true값을 적어줌 
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러 실행 후, 뷰(=JSP) 실행 전에 실행되는 메소드 
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 뷰 렌더링(JSP 실행 및 출력) 완료된 후에 실행되는 메소드 
	}
}
