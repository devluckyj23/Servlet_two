package com.exam.ex;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "ma")

//value값으로 이름을 지정해줄 수 있음 
//이 클래스의 객체를 생성하여 "ma"라는 이름으로 스프링에 등록 
public class Myapp {
	
	// @Autowired, @Inject, @Resource : 스프링에 등록된 객체를 이 변수(속성)에 주입(저장)
	// @Autowired : spring에서 제공 / @Inject, @Resource : 자바에서 제공 
	// 기능은 같지만 차이점이 존재함 
	// @Autowired, @Inject : 변수의 타입하고 같은 애를 변수에 주입함 
	// 만약 타입이 같은 애들이 있다면 이름으로 찾음 이름을 만약 지정하지 않으면 변수이름을 이름으로 사용함 
	// 등록된 이름이 myService인 애를 찾아서 넣어주는 
	// 명확한 걸 사용하고 싶으면 어노테이션(@Qualifier(별명)/@Named(빈이름을 지정)를 통해서 추가로 이름을 지정해줄 수 있음
	// 타입으로 찾고 이름이 같은 애가 있으면 이름으로 찾는다
	// =========================================================================================
	// @Resource : 이름을 먼저 찾음 (괄호 안에다가 이름을 바로 지정할 수 있음) 
	// 만약 이름이 지정하는 애가 없으면 그 다음으로 타입이 일치하는 지를 확인함 
	
	@Autowired
	private MyService myService;
	
	public void say() {
		System.out.println(myService.getMessage());
	}

	public MyService getMyService() {return myService;}

	public void setMyService(MyService myService) {this.myService = myService;}
	
}
