$(document).ready(function() {
	$('#showEvent').summernote({
		height: 300,
		minHeight: null,
		maxHeight: null,
		focus: true,
		callbacks: {
			onImageUpload: function(files, editor, welEditable) {
				for (var i = files.length - 1; i >= 0; i--) {
					let maxSize = 1048576; // according to the database validator
					if (files[i].size > maxSize) {
						alert('Image too large. Images must be less than 100 million bytes (9KB). Less than 1MB is recommended.');
						return;
					} else {
						sendFile(files[i], this);
					}

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

});