$(document).ready(function() {
	$('#showEvent').summernote({
		height: 300,
		minHeight: null,
		maxHeight: null,
		focus: true,
		callbacks: {
			onImageUpload: function(files, editor, welEditable) {
				for (var i = files.length - 1; i >= 0; i--) {
					let maxSize = 1048576 * 30; // according to the database validator
					if (files[i].size > maxSize) {
						alert('Image too large. Images must be less than 100 million bytes . Less than 30MB is recommended.');
						return;
					} else {
						sendFile(files[i], this);
					}
				}
			},
			onMediaDelete: function(files) {
				for (var i = files.length - 1; i >= 0; i--) {

					deleteFile(files[i].src);

				}

			}
		}
	});



	function sendFile(file, el) {
		var form_data = new FormData();
		form_data.append('file', file);
		$.ajax({
			data: form_data,
			type: "POST",
			url: '/image',
			cache: false,
			contentType: false,
			enctype: 'multipart/form-data',
			processData: false,
			success: function(url) {
				console.log(url);
				var image = $('<img>').attr('src', url);
				image.css({ "width": "500px", "height": "250px" })

				$('#showEvent').summernote("insertNode", image[0]);
			},
			error: function(data) {
				console.log(data);
			}
		});
	}

	function deleteFile(src) {
		var form_data = new FormData();
		form_data.append('src', src);
		$.ajax({
			data: form_data,
			type: "POST",
			url: "/delete/image", // replace with your url
			cache: false,
			contentType: false,
			enctype: 'multipart/form-data',
			processData: false,
			success: function(resp) {
				console.log("May Chưa Tạch");
			},
			error: function(data) {
				console.log("Tạch nốt");
			}
		});
	}
	
	
	$('#formId').submit(function(event){
		var result = false; 
		
		if($('#header').val().length < 10){
			
			$('#errorsHeader').text("Header more than 10 charters").show().fadeOut(2000);	
			result = true;
		}
		
		if($('#showEvent').val().length < 50){
			
			$('#errorsContent').text("Content more than 50 charters").show().fadeOut(2000);	
			result = true;
		}
		
		if(!result){
			return ;
		}else{
			console.log(result)
		}
		
		event.preventDefault();
	})
});