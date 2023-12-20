package com.exam.myapp.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/member/addform.do")
public class MemAddFormServlet extends HttpServlet {
//상속받아주기

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		
		  resp.setCharacterEncoding("UTF-8"); resp.setContentType("text/html");
		  
		  PrintWriter out = resp.getWriter();
		  out.println("<!DOCTYPE html>                  ");
		  out.println("<html>                           ");
		  out.println("<head>                           ");
		  out.println("<meta charset=\"UTF-8\">           ");
		  out.println("<title>회원관리</title> ");
		  out.println("</head>                          ");
		  out.println("<body>                           ");
		  out.println("<h1> 회원 가입 </h1>"); out.println("<form action=\"" +
		  req.getContextPath() + "/member/add.do\" method=\"post\">");
		  out.println("아이디 : <input type=\"text\"  name=\"memId\" value=\"\"/><br>");
		  out.
		  println("비밀번호 : <input type=\"password\"  name=\"memPass\" value=\"\"/><br>"
		  );
		  out.println("이름 : <input type=\"text\"  name=\"memName\" value=\"\"/><br>");
		  out.
		  println("포인트 : <input type=\"number\"  name=\"memPoint\" value=\"0\"/><br>");
		  out.println("<input type=\"submit\"/>"); out.println("</form>");
		  out.println("</body>                          ");
		  out.println("</html>                          ");
		 

	}
}
/*
 * <h2>회원 가입</h2> <form action="/exweb/member/add.do" method="post"> 아이디 :
 * <input type="text" name="memId" value=""/><br> 비밀번호 : <input type="password"
 * name="memPass" value=""/><br> 이름 : <input type="text" name="memName"
 * value=""/><br> 포인트 : <input type="number" name="memPoint" value="0"/><br>
 * <input type="submit"/> </form>
 */