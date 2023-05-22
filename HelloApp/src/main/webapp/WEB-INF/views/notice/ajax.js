/**
 * 
 */

 	//$.ajax() 호출
	$(document).ready(function (){
		$('form').on('submit', function(e){
			e.preventDefault(); // 전송차단.
			console.log(decodeURIComponent($(this).serialize()));
			$.ajax({
				url:'addNotice.do',
				data: $(this).serialize(), // id=12&name=hong&age=20
				method: 'post',
				dataType: 'json',
				error : function(jqxhr, textStatus, error){
					console.log(jqxhr, textStatus, error);
				},
				success : function (data,textStatus, jqxHR){
					console.log(data,textStatus, jqxHR);
					
				let ul = $('<ul />').append($('<li />').text('작성자 :' + data.retVal.noticeWriter),
						  $('<li />').text('제목 :' + data.retVal.noticeTitle),
						  $('<li />').text('내용 :' + data.retVal.noticeSubject)
						);
					
					$('form').after(ul);
				}										  
			})
			// .done(function(result){
			// 	console.log(result);
			// })
			// .fail(function(err){
			// 	console.log(err);
			// })
			.always(function(){
				console.log('final.');
			})
		})
	});
	