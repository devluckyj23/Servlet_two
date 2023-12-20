<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
 <%-- <!DOCTYPE html>
 <html>
 <head>
 <meta charset='UTF-8'>
 <title>게시판</title>
 </head>
 <body>
 
	 <jsp:include page="/WEB-INF/views/Menu.jsp"></jsp:include> --%>
	 
	 <hr>
	 <h1> 게시글 수정 </h1> 
	 <form action='${pageContext.request.contextPath}/bbs/edit.do' method='post'>
		 <input type="hidden"  name='bbsNo' value="${bvo.bbsNo}"/><br>
		 제목: <input <c:if test="${bvo.bbsWriter != loginUser.memId}">readonly</c:if> type='text' name='bbsTitle' value='<c:out value="${bvo.bbsTitle}" />' /><br> 
		 내용: <textarea ${bvo.bbsWriter != loginUser.memId?'readonly':''} name="bbsContent" rows="5" cols="30" ><c:out value="${bvo.bbsContent}" /></textarea> <br>
		 작성자 : <c:out value="${bvo.bbsWriter}"/> <br>
		 등록일 : <fmt:formatDate value="${bvo.bbsRegDate}" pattern="yyyy/MM/dd HH:mm:ss"/><br>
		 
		 <c:forEach var="vo" items="${bvo.attachList}" >
		 	첨부파일 : <a href="${pageContext.request.contextPath}/bbs/down.do?attNo=${vo.attNo}">
		 				<c:out value= "${vo.attOrgName}"/></a> <br>
		 </c:forEach>
		 <br>
	<!-- 자신이 작성한 글이 아닌 경우, 제목과 내용을 키보드로 입력할 수 없도록 구현  -->
		 <c:if test="${bvo.bbsWriter == loginUser.memId}">
		 <input type='submit' value="제출한다잉"/>
		 <a id="dellink" href='${pageContext.request.contextPath}/bbs/del.do?bbsNo=${bvo.bbsNo}'><button type="button">삭제</button></a>
		 
		 </c:if>
 	</form>
	<hr>
	<form id="replyForm" action='${pageContext.request.contextPath}/reply/add.do' method='post'>
		<input type="hidden" name="repBbsNo" value="${bvo.bbsNo}">
		<h3>댓글</h3> <br>
		<textarea name="repContent" rows="5" cols="100"></textarea>
		<input id="btn" type="button" value="댓글달기" />
	</form>
	
	<div id="replyList"></div>
          
    <template id="replyTemp">
	    <div class="repContent"></div>
	    <div class="repWriter"></div>
	    <div class="repRegDate"></div>
	    <input type="button" value="삭제" class="delBtn" data-no="" />
	    <hr>
    </template>     
          
          
          
	
	<script type="text/javascript">
	
	$('#dellink').on('click', function(ev) {
		var ok = confirm('삭제삭제??');
		if(!ok){ 
			//ev.preventDefault(); //이벤트에 대한 브라우저의 기본동작을 취소
			return false; //이벤트 전파를 중단하고, 이벤트에 대한 브라우저의 기본동작을 취소
		}
	});
	
	//<templet> 엘리먼트의 내용은 content 속성을 사용하여 접근
	//tmeplet의 내용을 담고있는 제이쿼리 객체를 만들어 repTemp 변수에 저장
	var $repTemp = $(document.querySelector('#replyTemp').content);
	//로그인한 사용자가 작성한 댓글에만 삭제버튼 출력
	//삭제버튼 클릭시, 삭제여부를 묻는 창을 띄우고, 삭제하겠다고 선택한 경우에만 삭제
	//댓글 저장 성공시, 댓글 입력란의 내용 초기화
	 	
	 	
		function refreshReplyList(){
			
	 	$.ajax({
			  url: "${pageContext.request.contextPath}/reply/list.do", // 요청 주소
			  method: "GET", // 요청 방식 
			  data: { repBbsNo : ${bvo.bbsNo} }, // 요청 파라미터
	 		  dataType: "json" // 응답 데이터 타입
	 		  // "json"으로 설정하면, 응답으로 받은 JSON 문자열을 자바스크립트 객체로 변환하여
	 		  // 응답처리 함수에게 인자로 전달
			}).done(function( data ) { // 요청 전송 후 성공적으로 응답을 받았을 때 실행
			 	//msg == '{"result":1,"ok":true}'
			 	//msg == {"result":1,"ok":true}
			 	//var data = JSON.parse(msg);
			 	console.log(data); // 1개의 댓글 저장이라고 출력되도록
			 	
			 	
			 	
			 	var listHtml = [];
			 	for( var i =0; i<data.length; i++){
			 		var repVo = data[i];
			 		//console.log(repVo.repContent,repVo.repWriter,repVo.repRegDate);
			 		
			 		//listHtml += $('<div>' + repVo.repContent + '</div>');
			 /*	    listHtml.push( $('<div>').text(repVo.repContent)); // <div>repVo.repContent</div>
			 		listHtml.push( $('<div>')./text(repVo.repWriter)); // <div>repVo.repWriter</div>
			 		listHtml.push( $('<div>').text(repVo.repRegDate)); // <div>repVo.repRegDate</div>
			 		if(repVo.repWriter == '${loginUser.memId}' ){
			 		//listHtml += '<div><input data-no="'+repVo.repNo+'" class="delBtn" type="button" value="삭제"></div>';
			 		listHtml.push( $('<input>').attr('data-no',repVo.repNo)
								 				.attr('type',"button")
								 				//여러개의 속성을 한번에 setting하고 싶으면 아래와같이 가능하다.
								 				//.attr({'data-no': repVo.repNo,type,'button'})
								 				.addClass('delBtn')	//.attr('class',"delBtn")
								 				.val('삭제') 	//.attr('value',"삭제")
								 				);
			 		}
			 		listHtml.push($('<hr>'));
			 	*/
			 	
			 	var $newRep = $repTemp.clone();
			 	$newRep.find('.repContent').text(repVo.repContent); //(1)
			 	$newRep.find('.repWriter').text(repVo.repWriter); //(2)
			 	$newRep.find('.repRegDate').text(repVo.repRegDate);//(3)
			 	
			 	if(repVo.repWriter == '${loginUser.memId}' ){
			 	$newRep.find('.delBtn').attr('data-no',repVo.repNo);
			 		
			 	}else{
			 		$newRep.find('.delBtn').remove();
			 	}
			 	listHtml.push($newRep);
			    // <div class="repContent">(1)</div>
	    		//<div class="repWriter">(2)</div>
	   	 		//<div class="repRegDate">(3)</div>
			    //<input type="button" value="삭제" class="delBtn" data-no="" />
			    //<hr> 
		   
			 	
			 	}
			 	
			 	//console.log(listHtml); //listHtml 값을 id="replyList"인 div엘리먼트의 내용으로 출력
			 	$('#replyList').empty().append(listHtml);
			 	
			 	
			}).fail(function( jqXHR, textStatus ) {// 요청 처리에 오류가 발생했을 때 실행
			  alert( "Request failed: " + textStatus );
			});
			
		}
		//삭제버튼을 클릭하면 해당 댓글이 삭제되도록, ReplyController.java, ReplyService.java,ReplyServiceImpl.java,ReplyDao.java
		//ReplyMapper.xml 파일을 변경
		$('#replyList').on('click','.delBtn', function() {
			var ok = confirm('즨짜 삭제함??');			
			if(!ok) return;			
			$.ajax({
				  url: "${pageContext.request.contextPath}/reply/del.do", // 요청 주소
				  method: "GET", // 요청 방식 
				  data: { repNo : $(this).attr('data-no') }, // 요청파라미터
		 		  dataType: "json" // 응답 데이터 타입
		 		  // "json"으로 설정하면, 응답으로 받은 JSON 문자열을 자바스크립트 객체로 변환하여
		 		  // 응답처리 함수에게 인자로 전달
				}).done(function( msg ) { // 요청 전송 후 성공적으로 응답을 받았을 때 실행
				 	refreshReplyList(); // 댓글 저장 성공 후 새로고침을 자동으로 ! 바로 보여주는 메서드
				 	alert( msg.result + "개의 댓글 삭제" ); // 1개의 댓글 저장이라고 출력되도록
				}).fail(function( jqXHR, textStatus ) {// 요청 처리에 오류가 발생했을 때 실행
				  alert( "Request failed: " + textStatus );
				});
		});
		refreshReplyList();
		
		// 저장버튼을 클릭했을 때 AJAX로 댓글 저장 요청을 전송하려고 함 
		// (1) XmlHttpRequest 객체 사용 -> 전통적인 방식
		// (2) fetch() 함수 사용 -> html5가 나오면서 새롭게 지원되는 방식 
		// (3) $.ajax() 메소드 사용 -> jquery 사용 
		$('#btn').on('click', function(){
			$.ajax({
			  url: "${pageContext.request.contextPath}/reply/add.do", // 요청 주소
			  method: "POST", // 요청 방식 
			  data: { repBbsNo : ${bvo.bbsNo}, repContent : $('[name="repContent"]').val() }, // 요청 파라미터
	 		  //data:{repBbsNo : ${bvo.bbsNo},repContent : $('[name="repContent"]').val()}
			  //data:$('replyForm').serialize(), 입력요소가 너무 많다고 느껴진다면 이것을 사용!
			  dataType: "json" // 응답 데이터 타입
	 		  // "json"으로 설정하면, 응답으로 받은 JSON 문자열을 자바스크립트 객체로 변환하여
	 		  // 응답처리 함수에게 인자로 전달
			}).done(function( msg ) { // 요청 전송 후 성공적으로 응답을 받았을 때 실행
				refreshReplyList();
			 	//msg == '{"result":1,"ok":true}'
			 	//msg == {"result":1,"ok":true}
			 	//var data = JSON.parse(msg);
			 	alert( msg.result + "개의 댓글 저장" ); // 1개의 댓글 저장이라고 출력되도록
			 	$('[name="repContent"]').val('');
			}).fail(function( jqXHR, textStatus ) {// 요청 처리에 오류가 발생했을 때 실행
			  alert( "Request failed: " + textStatus );
			});
		});
	</script>
 <!-- </body>
 </html> -->
