package com.exam.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
//- 컨트롤러(=핸들러) : 웹 요청을 받았을때 실행되는 객체
//- 모델 : 응답 출력시 사용(포함)할 데이터
//- 뷰 : 화면 출력을 담당하는 객체(JSP)
//컨트롤러는 실행 결과로서, 모델과 뷰에 대한 정보를 제공해야한다
@Controller // 컨트롤러(요청을 받았을 때 실행되는 객체)로서 스프링(DispatcherServlet)에 등록
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// "/" 경로로 GET 방식 요청이 오면 실행
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, Map map, ModelMap modelMap) {
		// LOCALE : 지역, 언어 를 받아서 
		logger.info("Welcome home! The client locale is {}.", locale);
		// 콘솔에다가 이 Logger를 찍고  
		
		Date date = new Date();
		// 객체를 생성해서 현재 지역, 언어에 맞는 날짜 및 시간 정보를 담음
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		// 이를 현재 날짜 및 시간 정보를 현재 로케일에 맞는 문자열로 변환 
		
		model.addAttribute("a", formattedDate);
		map.put("b", formattedDate);
		// map은 원래 자바에 있는 클래스 -> 그래서 add를 사용하고 model과 modelMap은 스프링에서 제공을 해줌 
		modelMap.addAttribute("c", formattedDate);
		
		// 이 데이터를 jsp에서 출력하기 위해서 model에다가 넣어주는게 중요!!!! 
		// 모델 : 응답/화면(JSP) 출력 시 포함할 데이터
		// 모델에 데이터를 추가(저장)하기 위해서는 인자로 받은 Model, Map, ModelMap 객체에 이름(모델명)-값 형식으로 데이터를 저장
		// JSP에서는 ${모델명}으로 값을 꺼내서 사용 가능 
		
		return "home"; // 컨트롤러가 문자열을 반환하면 스프링은 뷰 이름으로 인식을 함 
		//servlet-context.xml의 InternalResourceViewResolver 설정대로 
		//문자열 앞에 “WEB-INF/views”를 추가하고 문자열 뒤에 “.jsp”를 추가한 주소(경로)로 이동(forwrad)
		// 즉, "WEB-INF/views/home.jsp" 파일을 실행 
		
	}
	
	// 브라우저에서 "http://localhost:8000/myapp/test"로 접속하면
	// test()메소드와 test.jsp가 순서대로 실행되어 test.jsp의 h1 태그 내용에 변수 s 값이 출력되도록 구현
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(
			@RequestParam(value="x") String xv // 이름이 x인 요청파라미터 값은 변수 xv에 저장  
			,int y // 파라미터이름하고 변수 이름하고 같으면 RequestParam 어노테이션을 생략해도 됨
			,@ModelAttribute("mv") MyVo vo
			// @ModelAttribute(”모델명”)을 적용하여 매개변수 값을 지정한 이름으로 모델에 저장(추가)기능
			// 사용자가 정의한 객체를 인자로 받는 경우 객체의 속성명과 동일한 이름의 파라미터 값을 객체의 속성에 자동 저장 
			, MyVo v
			// 파라미터를 받기 위해서 배치한 매개변수는 자동으로 모델에 추가
			// 모델에서 꺼내쓰고 싶은데 못 꺼내니까 저 @ModelAttribute 어노테이션을 써주는거고 
			// @ModelAttribute에서 모델명을 생략한 경우 모델 이름은 타입명의 첫 글자를 소문자로 변환하여 사용
			,ModelMap modelMap1) {
		
		logger.info("xv : {}, y : {}.", xv, y);
		logger.info("vo.x : {}, vo.y : {}.", vo.getX(), vo.getY());
		
		String s = "JSP에서 출력할 문자열";
		modelMap1.addAttribute("text" ,s);
//		modelMap1.addAttribute("vox" ,vo.getX());
//		modelMap1.addAttribute("voy" ,vo.getY());
		
		return "test";
		
	}
	
}
