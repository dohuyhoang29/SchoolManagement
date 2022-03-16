$(document).ready(function() {

	$('#test').change(function() {

		var val = $('#test').val();
		let a = '<td><input type ="number" class="a form-control" name="mark" min=0 max=10 placeholder="Enter" required="required"></td>';
		let b = '<td><input type ="number" class="b form-control" name="mark" min=0 max=10 placeholder="Enter" required="required"></td>';
		let c = '<td><input type ="number" class="c form-control" name="mark" min=0 max=10 placeholder="Enter" required="required"></td>';

		if (val == 1) {
			$("table tbody").find('input[name="mark"]').each(function() {
				$(this).parents("td").remove();
			});
			$('#tblB').find("tbody.bodyTable").find("tr").append($(a + b + c));
		} else if (val == 2 || val == 3) {
			$("table tbody").find('input[name="mark"]').each(function() {
				$(this).parents("td").remove();
			});
			$('#tblB').find("tbody.bodyTable").find("tr").append($(b + c));
		} else {
			$("table tbody").find('input[name="mark"]').each(function() {
				$(this).parents("td").remove();
			});
			$('#tblB').find("tbody.bodyTable").find("tr").append($(a));
		}
	});
	
	
	$('#click').click(function() {
		const lista = [];
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

		$.ajax({
			type: "POST",
			contentType: "application/json; charset=utf-8",
			url: "http://localhost:8080/save/mark",
			data: JSON.stringify(listAll),
			cache: false,
			success: function(result) {
				if (result != null) {
					$('#message').html('<div  id="alertFadeOut" style="color: green">Edit Succesfully !</div>');
					console.log("succes");
					$('#alertFadeOut').fadeOut(1000, function() {
						$('#alertFadeOut').text('');
						window.location.reload(true);
					});

				}
			},
			error: function(err) {
				$("#msg").html("<span style='color: red'>Name is required</span>");
			}
		});

		console.log(lists);
		console.log(lista);
		console.log(listb);
		console.log(listc);
		console.log(listAll);
	});
});
