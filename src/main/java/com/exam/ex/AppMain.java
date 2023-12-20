package com.exam.ex;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {
	
	public static void main(String[] args) {
		// MyApp, MyServiceKo를 사용하여 콘솔에 "하이루"가 출력되도록 구현 
		
		// MyApp의 메소드 say()를 실행시켜야 함 근데 MyApp의 객체를 생성했을 떄 
		// say()메소드를 사용할 수 있음 
		
		/*
		 * Myapp app = new Myapp(); Myapp app2 = new Myapp(); // 방금 만든 MyApp객체.say() ->
		 * 이렇게 할 수 없으니 변수명을 지정해주자 MyServiceKo msk = new MyServiceKo();
		 * app.setMyService(msk); MyServiceEn mse = new MyServiceEn();
		 * app2.setMyService(mse); app.say(); app2.say();
		 */
	
		// 스프링 == (IOC/DI기능을 가진)객체 컨테이너 == BeanFactory == ApplicationContext 이렇게 유사한 표현(완전 동일 x) 
		// Bean = 객체를 의미 , Application이 실행되는 환경 
		// 스프링을 쓴다고 하는거는 객체 컨테이너를 만들어서 쓴다는 것 한번 만들어보자 (여러 가지 방법이 존재) 
		// 설정파일이 어떻게 되어있냐에 따라서 데이터를 읽어서 객체 컨테이너를 만들어주는 클래스들이 잇음 
		// 일단 우리는 지금은 class path에 있는 context.xml의 설정을 읽어서 spring을 지정해줄 것
		
		//ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/com/exam/ex/context.xml");
		// 클래스패스 상의 XML 파일로부터 설정을 읽어서 스프링 객체 컨테이너를 생성 
		// 이제 우리가 객체를 생성했던 걸 스프링한테 시켜서 가지고 있으라고 한 다음에 그 객체를 받아서 사용할거임 
		// 이를 설정파일에다가 적어줄거임
		
		//JAVA 클래스로부터 설정을 읽어서 스프링 객체 컨테이너를 생성 
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
		
		Myapp app = (Myapp) ctx.getBean("ma"); 
		//오류가 생기는 거는 가져오는 객체가 MyApp것의 객체인지를 모르기 때문에.. 형변환을 하거나 타입을 뒤에다가 지정해주면 됨
//		Myapp app2 = (Myapp) ctx.getBean("ma2"); 
		
		app.say(); 
//		app2.say();
	}
}
