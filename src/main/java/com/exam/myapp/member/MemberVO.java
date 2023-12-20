package com.exam.myapp.member;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class MemberVO { 
	//VO(Value Object) -> 회원정보 한 명의 것을 저장하기 위해서 만들어줌 
	//Bean Validation2.0부터 사용가능
	//@NotEmpty ,@NotBlank
	//@Positive,@PositiveOrZero,@Negative,@NegativeOrZero
	//@Email
	//@Future,@FutureOrPresent
	//@Past,@PastOrPresent
	
	@NotNull @Size(min= 4, max = 10) @Email
	private String memId;
	@NotNull @Size(min= 4, max = 10)
	private String memPass;
	@NotNull @Size(min= 4, max = 10)
	private String memName;
	@Digits(integer =10, fraction =10, message = "회원이름은 1-10글자 입력해야햄!!")
	private int memPoint;
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public int getMemPoint() {
		return memPoint;
	}
	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}

}
