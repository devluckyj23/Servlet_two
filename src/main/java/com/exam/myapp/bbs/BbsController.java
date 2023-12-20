package com.exam.myapp.bbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.exam.myapp.member.MemberVO;

//회원목록의 각 회원정보 옆에 "삭제"링크를 출력하고 
//링크를 클릭하면 해당 회우너이 삭제되도록 MemListServlet클래스를 변경하세요
//삭제링크가 버튼 모양이면 더 좋을 거 같아요 


@Controller
@RequestMapping("/bbs/")
// 현재 컨트롤러 내부의 모든 메소드들의 공통 경로 설정
public class BbsController{
//상속받아주기
	
	@Autowired
	private BbsService bbsService;
	
	@GetMapping("list.do") 
	//@RequestMapping(value ="list.do" , method = RequestMethod.GET)
	public String list(Model model, SearchInfo info, PageInfo info2) { 
		int cnt = bbsService.selectBbsCount(info); //전체 레코드 수 조회
		info.setTotalRecordCount(cnt); // 전체 레코드 수 정보 설정
		info.makePageHtml(); // 페이지 처리에 필요한 값들 계산
		
		//객체를 만들어서 실체화를 시켜 밑의 메서드가 실행되도록 만들어줌 
		List<BbsVO> list = bbsService.selectBbsList(info);
		// DB에서 회원목록 가지고 오기 
		model.addAttribute("bbsList", list);
		// req보단 model에다가 저장해주면 됨
		
		return "bbs/bbsList";
	}
	
	@GetMapping("add.do")
	//@RequestMapping(value ="add.do" , method = RequestMethod.GET)
	public String addform(){
	
		return "bbs/bbsAdd";
	}
	
	@PostMapping("add.do")
	// @RequestMapping(value ="add.do" , method = RequestMethod.POST)
	public String add(BbsVO vo
			//, HttpSession session
			, @SessionAttribute("loginUser") MemberVO mvo
			// 지정한 세션 속성값을 이 변수에 주입(전달)
			) {
		
		System.out.println("게시글 추가중");
		
		// 세션에서 로그인 정보를 꺼내와서 mvo에 저장함 
		//MemberVO mvo = (MemberVO)session.getAttribute("loginUser"); 
		
		//로그인한 사용자 아이디를 게시글 작성자로 설정 
		vo.setBbsWriter(mvo.getMemId());
		
		int n = bbsService.insertBbs(vo);
		System.out.println(n+ " 개의 게시글 추가 ");
		return "redirect:/bbs/list.do";
	
	}
	
	@GetMapping("edit.do")
	//@RequestMapping(value ="edit.do" , method = RequestMethod.GET)
	public String editform(int bbsNo, Model model){
		// 여기서 bbsNo를 String으로 받아도 되지만 원래의 타입이 int이니까 int로 바꿔줌 
		BbsVO vo = bbsService.selectBbs(bbsNo);
		// JSP에다가 값을 주기 위해서 Model에다가 값을 넣어 줘야 함 (Model, Model map 등)
		model.addAttribute("bvo", vo);
		
		return "bbs/bbsEdit";
		
	}
	
	@PostMapping("edit.do")
	//@RequestMapping(value ="edit.do" , method = RequestMethod.POST)
	public String edit(BbsVO vo,@SessionAttribute("loginUser") MemberVO mvo ) {
		vo.setBbsWriter(mvo.getMemId());
		System.out.println("게시글 정보 수정중");
		int n = bbsService.updateBbs(vo);
		System.out.println(n+ " 개의 게시글 정보 수정 ");
		return "redirect:/bbs/list.do";
		
	}
	
	@GetMapping("del.do")
	// @RequestMapping(value ="del.do" , method = RequestMethod.GET)
	public String del(BbsVO vo,@SessionAttribute("loginUser") MemberVO mvo ) {
		vo.setBbsWriter(mvo.getMemId());
		System.out.println("게시글 삭제중");
		int n = bbsService.deleteBbs(vo);
		System.out.println(n+ " 명의 게시글 삭제");
		return "redirect:/bbs/list.do";
		
	}

	// 컨트롤러 메서드가 인자로 HttpServletResponse, OutputStream, Writer를 받고 반환타입이 void이면
	// 직접 응답을 전송했다고 판단하여 스프링은 뷰에 대한 처리를 하지 않는다 
	@GetMapping("down.do")
	public void down(int attNo, HttpServletResponse resp) {
		// int attNo, AttachVO vo 둘 중에 하나만 받아주면 됨 
		
		System.out.println("첨부파일 다운 중 ");
		AttachVO vo = bbsService.selectAttach(attNo); // DB에 다운로드할 첨부파일 정보 조회
		
		File f = bbsService.getAttachFile(vo); // 디스크 상에서 첨부파일의 위치 가져오기 
		
		resp.setContentLength((int) f.length()); // 응답 메세지 본문(파일)의 크기 설정
		// resp.setContentLengthLong(f.length()); 과 같은 코드
		// setContentLength의 타입이 int여서 f.length의 형변환을 해줌
		
		resp.setContentType("application/octet-stream"); // 무조건 다운로드 팝업창을 띄우도록 설정 
		//resp.setContentType( MediaType.APPLICATION_OCTET_STREAM_VALUE);
		// jsp같은 경우에는 Contenttype이 text/html로 정해져 있는 것처럼 정해주자
		// 둘 중에 원하는 것 하나만 적도록 하자 

		
		/*
		 * 다운로드 파일을 저장할 때 사용할 디폴트 파일명 설정
		 * 지원하는 브라우저에 따라서 다른 처리가 필요할 수도 있음 
		 * 
		 * try { String fname = URLEncoder.encode(vo.getAttOrgName(),
		 * "UTF-8").replace("", "%20"); resp.setHeader("Content-Disposition","attachment; filename*=UTF-8''" + fname);
		 * 이러한 헤더 방식(filename*=UTF-8'')이 최근에 생긴거라서 오래된 브라우저? 에서는 지원을 하지 않음
		 * 만약 그 곳에서도 사용할 수 잇도록 하고 싶다면 filename=painting.png; 이런 형태로 적어줘야 한다?
		 *   
		 * } catch (UnsupportedEncodingException e1) { e1.printStackTrace(); }
		 */
		
		String cdv = ContentDisposition.attachment().filename(vo.getAttOrgName(), StandardCharsets.UTF_8).build().toString();
		resp.setHeader(HttpHeaders.CONTENT_DISPOSITION, cdv);
		
		
		try {
			// 파일 f의 내용을 응답 객체(의 출력 스트림)에 복사(전송)
			FileCopyUtils.copy(new FileInputStream(f), resp.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// text 파일은 resp.getWriter, 음악/동영상 등은 resp.getOutputStream()을 받음 
		
	}
}
