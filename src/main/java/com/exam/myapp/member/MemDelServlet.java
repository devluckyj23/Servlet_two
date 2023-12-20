package com.exam.myapp.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//웹브라우저에서 
//http://localhost:8000/exweb/member/del.do?memId=삭제할회원아이디
//로 요청을 보내면 지정한 회원정보를 데이터베이스에서 삭제하고
//"몇 명의 회원 삭제 성공" 메세지와 "회원목록으로 이동"링크를 화면에 출력

//@WebServlet("/member/del.do")
public class MemDelServlet extends HttpServlet{
//상속받아주기
	private MemberService memberService = MemberServiceImpl.getInstance();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		System.out.println("회원 삭제중");
		
		String memId = req.getParameter("memId");
	
		
		int n = memberService.deleteMember(memId);
		System.out.println(n+ " 명의 회원 삭제");
		
		resp.sendRedirect(req.getContextPath()+"/member/list.do");


		/*
		 * resp.setCharacterEncoding("UTF-8"); resp.setContentType("text/html");
		 * 
		 * PrintWriter out = resp.getWriter();
		 * out.println("<!DOCTYPE html>                  ");
		 * out.println("<html>                           ");
		 * out.println("<head>                           ");
		 * out.println("<meta charset=\"UTF-8\">           ");
		 * out.println("<title>회원관리</title> ");
		 * out.println("</head>                          ");
		 * out.println("<body>                           "); out.println("<h1> "+ n +
		 * "명의 회원 삭제 성공 </h1>"); //오류 생긴 건 n은 try 안에서만 할 수 있기 때문 out.println("<a href='"
		 * + req.getContextPath() + "/member/list.do'>회원목록으로 이동</a>");
		 * out.println("</body>                          ");
		 * out.println("</html>                          ");
		 */
	
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