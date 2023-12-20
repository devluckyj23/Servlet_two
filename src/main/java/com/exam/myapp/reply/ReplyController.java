package com.exam.myapp.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.exam.myapp.member.MemberVO;


//@Controller
@RestController // 현재 클래스의 모든 요청처리 메서드에 @ResponseBody를 적용
@RequestMapping("/reply/")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@GetMapping("list.do")
	//@ResponseBody
	public List<ReplyVO> list(int repBbsNo) {
		List<ReplyVO> repList = replyService.selectReplyList(repBbsNo);
		return repList;
	}
	//등록
	@PostMapping("add.do")
	//@ResponseBody // 메서드 반환값을 응답 메세지 내용으로 전송
	public Map<String, Object> add(ReplyVO rvo, @SessionAttribute ("loginUser") MemberVO mvo) {
		
		rvo.setRepWriter(mvo.getMemId());
		
		int num = replyService.insertReply(rvo);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ok",true);
		map.put("result", num);
		
		
		
		// 자바스크립트로 위의  v와 동일한 데이터를 저장한 객체를 정의
		// var v = { memId:"a001", memName:'정웅기',memPoint:10 }; //js에서 객체 표현 == {}
		
		//JSON 과 자바스크립트 객체 표현과 동일하지만 2가지 차이점 존재
		//(1)문자열은 반드시 큰따옴표만 가능(작은따옴표 사용 불가)
		//(2) 객체의 속성이름은 반드시 문자열로 표현
		//String jsonStr=3"{ \"memId\":\"a001\", \"memName\":\"정웅기\",memPoint:10 }"
		
		System.out.println("댓글 다는 중~");
		
		//return "redirect:/bbs/edit.do?bbsNo="+rvo.getRepBbsNo();
		//return num+"{\"ok\",true,\"result\" : " + num + "}";
		return map;
	}
	//삭제
	@GetMapping("del.do")
	//@ResponseBody // 메서드 반환값을 응답 메세지 내용으로 전송
	public Map<String, Object> del(int repNo, ReplyVO rvo 
			,@SessionAttribute ("loginUser") MemberVO mvo) {
		rvo.setRepWriter(mvo.getMemId());	
		int num = replyService.deleteReply(rvo);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ok",true);
		map.put("result", num);
		
		return map;
	}
	
	
}
