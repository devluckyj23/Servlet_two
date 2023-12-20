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
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.exam.myapp.member.MemberVO;

// 다수의 컨트롤러 실행 전후에 수행해야 하는 공통작업들은 핸들러인터셉터를 사용하여 구현 가능 
public class LoginFilter extends HandlerInterceptorAdapter{
	private List<String> whiteList = new ArrayList<String>();

	 @Override public void init(FilterConfig filterConfig) throws ServletException {
		 whiteList.add("/member/login.do");
		 whiteList.add("/member/add.do");
		 // contextPath를 다 붙여서 넣어주든가 req.getRequestURI()를 쓰든가???
	 }
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("LoginFilter의 doFilter() 실행");
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		System.out.println("URI : " + req.getRequestURI());
		System.out.println("URL : " + req.getRequestURL());
		String reqPath = req.getRequestURI().substring(req.getContextPath().length());
		System.out.println("reqPath : " + reqPath);
		
		if(!whiteList.contains(reqPath)) {
			// 요청보낸 사용자의 세션을 가져오기
			HttpSession session = req.getSession(); 
			// 세션에 로그인 정보를 꺼내 옴 (모든 정보를 저장할 수 있으니 loginUser가 MemberVo라는 걸 확실하지못해서 오류줄 생김 
			// Add cast to "MemberVo"를 눌러주면 됨 
			MemberVO vo = (MemberVO)session.getAttribute("loginUser"); 
			// 로그인 정보가 없다면  
			if(vo == null) {
				// 로그인 페이지로 이동시키기
				resp.sendRedirect(req.getContextPath()+"/member/login.do");	
				return; // 이걸 적어야 메소드를 더이상 실행시키지 않고 원래대로 돌아감 
			} 
		}
		
		
		chain.doFilter(request, response);
		//이후 실행될 필터 또는 서블릿들을 실행하는 명령 
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	
	/*
	 * @Override public void init(FilterConfig filterConfig) throws ServletException
	 * { System.out.println("CharacterEncodingFilter init() 실행"); enc =
	 * filterConfig.getInitParameter("encoding"); }
	 */
	//초기화메서드-> 필터가 처음 생성됐을 때 1번 실행 (서블릿의 init()와 같은 기능)
	
	
	 
	
	/*
	 * @override public void destroy() {
	 * system.out.println("characterencodingfilter destroy() 실행"); }
	 */
	//init()이 있었으면 destroy도 있음 -> 필터 객체가 소멸(삭제)되기 전에 1번 실행
	
}
