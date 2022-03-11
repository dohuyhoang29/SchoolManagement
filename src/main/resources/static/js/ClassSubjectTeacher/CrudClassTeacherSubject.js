$(document).ready(function() {
	$('#Edit').click(function(event) {
		event.preventDefault();
		const listUser = [];
		var classId = parseInt($('#classId').val());
		var hometeacher = parseInt($('#hometeacher').val());
		var classname = $('#className').val();
		var schoolYear = parseInt($('#schoolYear').val());
		var grade = parseInt($('#grade').val());

		const classRequet = { 'classId': classId, 'className': classname, 'grade': grade, 'schoolYear': schoolYear, 'userId': hometeacher };

		$('select.users').each(function(index) {
			let subject = $('input.subject')[index];
			let user = $(this).val();
			listUser.push({ 'userid': parseInt(user), 'subjectId': parseInt(subject.value), 'classid': classId, 'classReq': classRequet });
		});

		$.ajax({
			type: "POST",
			contentType: "application/json; charset=utf-8",
			url: "http://localhost:8080/classTeacherSubject/save",
			data: JSON.stringify(listUser),
			cache: false,
			success: function(result) {
				if (result != null) {
					$('#message').html('<div  id="alertFadeOut" style="color: green">Edit Succesfully !</div>');
					 	$('#alertFadeOut').fadeOut(3000, function () {
              				$('#alertFadeOut').text('');
              				window.location = "http://localhost:8080/show/class";
          			});
					
				}
			},
			error: function(err) {
				$("#msg").html("<span style='color: red'>Name is required</span>");
			}
		});

	});
	
	
	$('#change').click(function(event) {
		event.preventDefault();
		const listUser = [];
		var classId = parseInt($('#classId').val());
		
		
		$('select.users').each(function(index) {
			let subject = $('input.subject')[index];
			let user = $(this).val();
			listUser.push({ 'userid': parseInt(user), 'subjectId': parseInt(subject.value), 'classid': classId});
		});

		$.ajax({
			type: "POST",
			contentType: "application/json; charset=utf-8",
			url: "http://localhost:8080/classTeacherSubject/change",
			data: JSON.stringify(listUser),
			cache: false,
			success: function(result) {
				if (result != null) {
					$('#message').html('<div  id="alertFadeOut" style="color: green">Edit Succesfully !</div>');
					 	$('#alertFadeOut').fadeOut(3000, function () {
              				$('#alertFadeOut').text('');
              				
          			});
					
				}
			},
			error: function(err) {
				$("#msg").html("<span style='color: red'>Name is required</span>");
			}
		});

	});
	
	$('#hometeacher').change(function(){
		var oldteacher = $('#oldTeacher').val();
		var role = 3;
		
		if(oldteacher != null){
		
			$.ajax({
				type: "DELETE",
				url: "http://localhost:8080/teacherrole/delete/" + parseInt(oldteacher) + "/" + parseInt(role),
				cache: false,
				succes: function(){
					location.reload(true);
				},
				error: function() {
					$('#err').html('<span style=\'color:red; font-weight: bold; font-size: 30px;\'>Error deleting record').fadeIn().fadeOut(4000, function() {
						$(this).remove();
					});
				}
				
				
			});
		}
		
	});
});