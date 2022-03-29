$(document).ready(function() {

	$('#type').change(function() {

		var types = parseInt($(this).val());
		var currentPage = parseInt($('#currentPage').val());
		var classId = parseInt($('#classid').val());
		var semester = parseInt($('#semester').val());
		var subjectId = parseInt($('#subjectId').val());
		
		window.location.href = "/insert/mark/show/"+ currentPage +"/"+'z a'  + "/"+ types +"/"+'z b' +"/"+semester +"/"+'z c' +"/"+classId +"/"+'z d' +"/"+subjectId;
		
	});
	
	$('#semester').change(function() {

		var types = parseInt($('#type').val());
		var currentPage = parseInt($('#currentPage').val());
		var classId = parseInt($('#classid').val());
		var semester = parseInt($(this).val());
		var subjectId = parseInt($('#subjectId').val());
		
		window.location.href = "/insert/mark/show/"+ currentPage +"/"+'z a'  + "/"+ types +"/"+'z b' +"/"+semester +"/"+'z c' +"/"+classId +"/"+'z d' +"/"+subjectId;
		
	});
	
	$('#subjectId').change(function() {

		var types = parseInt($('#type').val());
		var currentPage = parseInt($('#currentPage').val());
		var classId = parseInt($('#classid').val());
		var semester = parseInt($('#semester').val());
		var subjectId = parseInt($(this).val());
		
		window.location.href = "/insert/mark/show/"+ currentPage +"/"+'z a'  + "/"+ types +"/"+'z b' +"/"+semester +"/"+'z c' +"/"+classId +"/"+'z d' +"/"+subjectId;
		
	});
	
	$("input").change(function() {
		if ($(this).val() > 10) {
			$(this).val(10);
		}
		if ($(this).val() < 0) {
			$(this).val(0);
		}
		var types = parseInt($('#type').val());
		var subjectId = parseInt($('#subjectId').val());
		var createdBy = parseInt($('#userId').val());
		var updateBy = parseInt($('#userId').val());
		var semester = parseInt($('#semester').val());
		var studentId = parseInt($(this).parents("tr").find("input.studentId").val());
		
		var markId = parseInt($(this).parent().find("input.markId").val()) || 0;
		const listMark ={
				'markId' : markId,
				'students': studentId,
				'subjects': subjectId,
				'type': types,
				'semester': semester,
				'coefficient': parseFloat($(this).val()),
				'createdBy': createdBy,
				'updatedBy': updateBy
 			};
		
		
		console.log(markId);
		console.log(listMark);
		var id = $(this).parent().find("input.markId");
		var now = $(this);
		$.ajax({

			type: "POST",
			contentType: "application/json; charset=utf-8",
			url: "http://localhost:8080/save/mark",
			data: JSON.stringify(listMark),
			cache: false,
			success: function(data) {
				console.log(data);
				now.attr('id', data);
				id.val(data);
			},
			error: function(err) {
				$("#messges").html("<span style='color: red'>False</span>");
			}
		});



		console.log($(this).val());
	})

	$('#click').click(function() {
		/*const lista = [];
		const listb = [];
		const listc = [];
		const lists = [];
		const listAll = [];
		const list = [];

		var types = parseInt($('#test').val());
		var subjectId = parseInt($('#subjectId').val());
		var classId = parseInt($('#classid').val());
		var createdBy = parseInt($('#userId').val());
		var updateBy = parseInt($('#userId').val());
		var semester = parseInt($('#semester').val());

		$('.studentId').each(function(index) {
			let s = parseInt($(this).val());
			let a = $('input.a')[index];
			let b = $('input.b')[index];
			let c = $('input.c')[index];
			lists.push(s);

			if (a != null) {
				lista.push(parseInt(a.value));
			}
			if (b != null && c != null) {
				listb.push(parseInt(b.value));
				listc.push(parseInt(c.value));
			}

		});

		if (lista.length > 0 && listb.length > 0 && listc.length > 0) {
			for (var i = 0; i < lists.length; i++) {
				list.push([lista[i], listb[i], listc[i]]);
				listAll.push({
					'students': lists[i],
					'subjects': subjectId,
					'type': types,
					'semester': semester,
					'coefficient': list[i],
					'createdBy': createdBy,
					'updatedBy': updateBy
				})
			}
		} else if (listb.length > 0 && listc.length > 0) {
			for (var i = 0; i < lists.length; i++) {
				list.push([listb[i], listc[i]]);
				listAll.push({
					'students': lists[i],
					'subjects': subjectId,
					'type': types,
					'semester': semester,
					'coefficient': list[i],
					'createdBy': createdBy,
					'updatedBy': updateBy
				})
			}
		} else if (lista.length > 0) {

			for (var i = 0; i < lists.length; i++) {
				list.push([lista[i]]);
				listAll.push({
					'students': lists[i],
					'subjects': subjectId,
					'type': types,
					'semester': semester,
					'coefficient': list[i],
					'createdBy': createdBy,
					'updatedBy': updateBy
				})
			}
		}
*/
		/*$.ajax({
			type: "POST",
			contentType: "application/json; charset=utf-8",
			url: "http://localhost:8080/save/mark",
			data: JSON.stringify(listAll),
			cache: false,
			success: function(result) {
				if (result != null) {
					$('#messges').html('<div  id="alertFadeOut" style="color: green">Add Mark Succesfully !</div>');
					console.log("succes");
					$('#alertFadeOut').fadeOut(3000, function() {
						$('#alertFadeOut').text('');
					});

				}
			},
			error: function(err) {
				$("#msg").html("<span style='color: red'>Name is required</span>");
			}
		});
*/
		/*console.log(lists);
		console.log(lista);
		console.log(listb);
		console.log(listc);
		console.log(listAll);*/
	});
});
