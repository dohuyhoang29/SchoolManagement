$(document).ready(function() {
	$('.yearpicker').datepicker({
		format: "yyyy",
		viewMode: "years",
		minViewMode: "years",
		autoclose: true
	});

	$(document).delegate('#addNew', 'click', function(event) {
		event.preventDefault();
		const listUser = [];
		const listSubject = [];
		$('select.users').each(function() {
			listUser.push($(this).val());
		});

		$('input.subject').each(function() {
			listSubject.push($(this).val());
		});
		for (var i = 0; i < listUser.length && i < listSubject.length; i++) {
			var classId = $('#classId').val();
			$.ajax({
				type: "POST",
				contentType: "application/json; charset=utf-8",
				url: "http://localhost:8080/classTeacherSubject/save",
				data: JSON.stringify({ 'userid': listUser[i], 'subjectId': listSubject[i], 'classid': classId }),
				cache: false,
				success: function(result) {
					$("#msg").html("<span style='color: green'>Company added successfully</span>");
					window.setTimeout(function() { location.reload() }, 1000)
				},
				error: function(err) {
					$("#msg").html("<span style='color: red'>Name is required</span>");
				}
			});



		}
	});


});