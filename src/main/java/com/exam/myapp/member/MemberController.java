package com.exam.myapp.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//회원목록의 각 회원정보 옆에 "삭제"링크를 출력하고 
//링크를 클릭하면 해당 회우너이 삭제되도록 MemListServlet클래스를 변경하세요
//삭제링크가 버튼 모양이면 더 좋을 거 같아요 


@Controller
public class MemberController extends HttpServlet{
//상속받아주기
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value ="/member/list.do" , method = RequestMethod.GET)
	public String list(Model model) {
		
		//객체를 만들어서 실체화를 시켜 밑의 메서드가 실행되도록 만들어줌 
		List<MemberVO> list = memberService.selectMemberList();
		// DB에서 회원목록 가지고 오기 
		
		model.addAttribute("memberList", list);
		// req보단 model에다가 저장해주면 됨
		
		return "member/memList";
	}
	
	@RequestMapping(value ="/member/add.do" , method = RequestMethod.GET)
	public String addform(MemberVO vo){
	
		return "member/memAdd";
	}
	// 스프링에 등록된 표준 BeanValidator를 사용하여 
	//저장된 값을 검증하고 싶은 객체 매개변수 앞에 @Valid 적용
	//BindingResult 또는 Errors 타입의 매개변수를 추가
	//modelAttribute="memberVO" 입력해야하지만 생략한다면 memberVO 로 자동으로 변경되어 jsp로 데이터를 넘겨준다.
	@RequestMapping(value ="/member/add.do" , method = RequestMethod.POST)
	public String add(@Valid MemberVO vo, BindingResult result) {
		if (result.hasErrors()) { //검증결과 오류가 있다면
//			List<FieldError> fieldErrors = result.getFieldErrors();
//			for(FieldError fe : result.getFieldErrors()) {
//				System.out.println("**" + fe.getField());
//				for(String c :fe.getCodes()) {
//					System.out.println(c);
//				}
//			}
			
			return "member/memAdd"; //회원정보 입력 화면(JSP) 출력(실행)
		}
		
		
		System.out.println("회원추가중");
		int n = memberService.insertMember(vo);
		System.out.println(n+ " 명의 회원 추가 ");
		return "redirect:/member/list.do";
	
	}

	@RequestMapping(value ="/member/edit.do" , method = RequestMethod.GET)
	public String editform(String memId, Model model){
		
		MemberVO vo = memberService.selectMember(memId);
		// JSP에다가 값을 주기 위해서 Model에다가 값을 넣어 줘야 함 (Model, Model map 등)
		model.addAttribute("mvo", vo);
		return "member/memEdit";
		
	}
	
	@RequestMapping(value ="/member/edit.do" , method = RequestMethod.POST)
	public String edit(MemberVO vo) {
		
		System.out.println("회원 정보 수정중");
		int n = memberService.updateMember(vo);
		System.out.println(n+ " 명의 회원 정보 수정 ");
		return "redirect:/member/list.do";
		
	}
	
	@RequestMapping(value ="/member/del.do" , method = RequestMethod.GET)
	public String del(String memId) {
		
		System.out.println("회원 삭제중");
		int n = memberService.deleteMember(memId);
		System.out.println(n+ " 명의 회원 삭제");
		return "redirect:/member/list.do";
		
	}
	
	
	// 로그인 동작이 수행되도록 아래 메서드들을 변경하시오
	@RequestMapping(value ="/member/login.do" , method = RequestMethod.GET)
	public String loginform() {
		
		return "member/login";
		
	}
	
	@RequestMapping(value ="/member/login.do" , method = RequestMethod.POST)
	public String login(MemberVO vo, HttpServletRequest req) {
		
		System.out.println("로그인 중");
		
		MemberVO mvo = memberService.selectLogin(vo);
		
		if(mvo == null) { // 로그인 실패 -> mvo의 값이 null이 됨 (select를 해줄 수 없으니까)
			return "redirect:/member/login.do";	
			
		} else { // 로그인 성공 -> mvo에 아이디 값이 있음
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", mvo);
			return "redirect:/member/list.do";
			
		}
		
	}
	
	@RequestMapping(value ="/member/logout.do" , method = RequestMethod.GET)
	public String logout(HttpSession session) {

		System.out.println("로그아웃 중");
		session.invalidate();
		return "redirect:/member/login.do";	
		
	}
	
}
