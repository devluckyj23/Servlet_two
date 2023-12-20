package com.exam.ex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // 스프링 설정파일 역할을 하는 클래스임을 표시 
@ComponentScan("com.exam.ex")
public class MyConfig {
	
	
	//@Bean("ma") // 이 메서드에서 반환하는 객체를 "ma"라는 이름으로 스프링에 등록
	public Myapp ma() {
		Myapp app = new Myapp();
		app.setMyService(msk());
		return app;
	}
	
	//@Bean("msk") // 이 메서드에서 반환하는 객체를 "msk"라는 이름으로 스프링에 등록
	public MyServiceKo msk() {
		return new MyServiceKo();
	}
	
	//@Bean("mse") // 이 메서드에서 반환하는 객체를 "mse"라는 이름으로 스프링에 등록
	public MyServiceEn mse() {
		return new MyServiceEn();
	}
	
}
