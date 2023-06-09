<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h3>공지사항 상세페이지.</h3>
<style>
#content {
	padding: 15px auto;
}
</style>

<form action="modifyNotice.do" method="GET">
	<table class="table">
		<tr>
			<th>글번호</th>
			<td><input type="text" name="nid"
				value="${noticeInfo.noticeId }" readonly></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"
				value="${noticeInfo.noticeTitle }" readonly></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="3" cols="20" name="subject" readonly>${noticeInfo.noticeSubject }</textarea></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer"
				value="${noticeInfo.noticeWriter }" readonly></td>
		</tr>
		<tr>
			<th>첨부파일: ${fileType }</th>
			<td><c:if test="${noticeInfo.attachFile != null}">
					<c:choose>
						<c:when test="${fileType == 'image' }">
							<img width="200px" src="images/${noticeInfo.attachFile }">
						</c:when>
						<c:otherwise>
							<a href="images/${noticeInfo.attachFile }">${noticeInfo.attachFile }</a>
						</c:otherwise>
					</c:choose>
				</c:if></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><c:if
					test="${noticeInfo.noticeWriter == id}">
					<button type="submit">수정</button>
				</c:if>
				<button type="button"
					onclick="location.href='noticeList.do?page=${pageNum}'">목록</button>
			</td>
		</tr>
	</table>
</form>

<!-- 댓글등록 -->
<div id="content">
	<input type="text" id="reply"> <span>${id }</span>
	<button type="button" id="addBtn">등록</button>
</div>



<!-- 댓글정보. -->
<table class="table">
	<thead>
		<tr>
			<th>댓글번호</th>
			<th>글내용</th>
			<th>작성자</th>
		</tr>
	</thead>
	<tbody id="tlist">
	</tbody>
</table>

<table style ="display:none;">
	<tbody>
		<tr id="template">
			<td>10</td>
			<td><input type ="text" class="reply"></td>
			<td>user01</td>
			<td><button>수정</button>
		</tr>
	</tbody>
</table>

<script>
	let showFields = ['replyId', 'reply','replyWriter']
	let xhtp = new XMLHttpRequest(); //Ajax 호출.
	xhtp.open('get','replyList.do?nid=${noticeInfo.noticeId}');
	xhtp.send();
	xhtp.onload = function(){
		console.log(xhtp.response);
		let tlist = document.querySelector('#tlist');
		// 목록생성.
		let data = JSON.parse(xhtp.response);
		for(let reply of data){
			console.log(reply);
			let tr = makeTrFunc(reply);
			tlist.append(tr);
		}
	 	
	}
	
	//tr 생성해주는 함수.
	function makeTrFunc(reply = {}){
			let tr =  document.createElement('tr');
			tr.id = reply.replyId;// tr 속성추가 : 댓글번호.
			
			// this 1)Object 안에서 사용되면 object자체를 가리킴.
			// 		let obj = {name: "hong", age: 20, showInfo: function() { this.age + this.name} }
			//		2) function 선언안에서 this는 window 전역객체. <-> 지역
			//		function add() {console.log(this)}
			//		3)event 안에서 사용되는 this는 이벤트 받는 대상.
			//		 document.getElementById('tlist').addEventListener('click', function() {console.log(this --클릭되었을때 이벤트 받는 대상인 getElementById가 콘솔창에 뜬다. )})
			//tr 클릭이벤트
			tr.addEventListener('dblclick',function (e) {
				let writer = this.children[2].innerText;
				
				console.log(writer,'${id}');
				if(writer != '${id}'){
					alert('권한이 없습니다.')
					return;
					}
				console.log(this);
				let template = document.querySelector('#template').cloneNode(true);
				console.log(template);
				/* template.children[0].innerText = reply.replyId;
				template.children[1].children[0].value =reply.reply;
				template.children[2].innerText = reply.replyWriter */;
				template.querySelector('td:nth-of-type(1)').innerText = reply.replyId;
				template.querySelector('td:nth-of-type(2)>input').value = reply.reply;
				template.querySelector('td:nth-of-type(3)').innerText = reply.replyWriter;
				template.querySelector('td:nth-of-type(4)>button').addEventListener('click',function (e){
					//Ajax 호출.
					let	replyId = reply.replyId;
					let replyCon = this.parentElement.parentElement.children[1].children[0].value;
					console.log(replyId, replyCon);
					
					let xhtp = new XMLHttpRequest();
					xhtp.open('post', 'modifyReply.do');
					xhtp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
					xhtp.send('rid='+replyId+'&reply='+replyCon);
					xhtp.onload = function(){
						let result =JSON.parse(xhtp.response);
						console.log(result);
						if(result.retCode == 'Success'){
							//화면변경
							alert('성공.');
							let tr = makeTrFunc();
							oldTr = document.querySelector('#template');
							document.getElementById('tlist').replaceChild(newTr, oldTr);
							
						} else if (result.retCode == 'Fail'){
							alert('처리중 에러');
						}else{
							alert('알수 없는 반환값.');						}
					}
					
				});
				
				//화면전환
				document.getElementById('tlist').replaceChild(template, tr);
			});
			// td생성. 
			for(let prop of showFields){
				let td =  document.createElement('td');
				td.innerText = reply[prop];
				tr.append(td);
			}
			//삭제버튼.
			let btn = document.createElement('button');
			btn.addEventListener('click', function (e){
				let writer = btn.parentElement.previousElementSibling.innerText;
				
				console.log(writer, '${id}');
				if(writer != '${id}'){
					alert('권한이 없습니다.')
					return;	
				}
				
				
				console.log(btn.parentElement.parentElement);
				let 삭제글번호 = btn.parentElement.parentElement.id;
				console.log(삭제글번호);
				// db에서 삭제 후... 화면에서 삭제
				let xhtp = new XMLHttpRequest();
				xhtp.open('post','removeReply.do');
				xhtp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
				xhtp.send('rid=' + 삭제글번호);// 요청방식post일 경우에 parameter를 send() 포함.

				xhtp.onload = function(){
					let result = JSON.parse(xhtp.response);
					if(result.retCode== 'Success'){
						//화면에서 지우기.
						alert('삭제완료')
						btn.parentElement.parentElement.remove();
					}else if(result.retCode== 'Fail'){
						alert('처리중 에러발생');

					}else{
						alert('알수없는 결과값입니다.');
					}
				}
			
	})
		
			btn.innerText='삭제';
			let td= document.createElement('td');
			td.append(btn);
			tr.append(td);

			return tr;
			//생성한 tr을 반환.
	}
	
	
	//등록이벤트...
	document.querySelector("#addBtn").addEventListener('click', addReplyFnc);
	function addReplyFnc(e){
		let id = document.querySelector('#content span').innerText;
		if(id== null || id == ''){
			alert("로그인 후에 처리하세요");
			location.href = 'loginForm.do';
			return;
		}
		let reply= document.querySelector("#reply").value;
		
	
	
		//Ajax 호출.
		let xhtp = new XMLHttpRequest();
		xhtp.open('post','addReply.do');
		xhtp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhtp.send('id=${id}&reply='+reply+"&notice_id=${noticeInfo.noticeId}");
		xhtp.onload = function(){
			console.log(xhtp.response);
			let result = JSON.parse(xhtp.response);
			if(result.retCode == 'Success'){
				// 값을 활용해서 tr 생성.
				let tr = makeTrFunc(result.data);
				tlist.append(tr);
			
				//입력값 초기화하기.
				document.getElementById("reply").value = '';
				document.getElementById("reply").focus();			
			}else if(resutl.retCode == 'Fail'){
				alert('처리중 에러.');
			}
		
		}
	
	}
	
	
	
	
</script>