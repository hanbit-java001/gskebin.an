$(function() {
	$(".btnApply").on("click", function() {
		var name = $("#txtName").val().trim();
		var email = $("#txtEmail").val().trim();
		var password = $("#txtPassword").val().trim();
		var passwordConfirm = $("#txtPasswordConfirm").val().trim();
		var imgProfile = $("#imgProfile").val();

//		console.log(imgProfile.files[0]);

//		for(var i =0; i<imgProfile.files.length; i++){
//
//		} 아래것과 같은 포문

//		for(var file in imgProfile.files){
//			console.log(files)
//		}

		var data = new FormData();


		for(var i = 0 ; i<imgProfile.files.length; i++){
			var file = imgProfile.files[i];

			data.append("imgProfile" , file)
		}

		$.ajax({
			url:"api/member/join",
			method:"POST",
			data:data,
			contentType:false,
			processData:false,
			//jquery 식으로 변환하지말고 있는 그대로 보내라 true
//			contentType:"multipart/form-data"
		}).done(function(result) {
			console.log(result)
		});



		if (name == "") {
			alert("이름을 입력하세요.");
			$("#txtName").focus();
			return;
		}
		else if (email == "") {
			alert("이메일을 입력하세요.");
			$("#txtEmail").focus();
			return;
		}
		else if (password == "") {
			alert("비밀번호를 입력하세요.");
			$("#txtPassword").focus();
			return;
		}
		else if (password != passwordConfirm) {
			alert("비밀번호확인을 동일하게 입력하세요.");
			$("#txtPasswordConfirm").focus();
			return;
		}

		// send Data to server
	});
});