$(function() {
	$(".main-menu ul li").on("click", function() {
		var menuId = $(this).attr("id");

		if (menuId == "menuJoin") {
			location.href = "/member/join";
		}
		else if (menuId == "menuLogin") {
			showLoginDialog();
		}
		else if (menuId == "menuLogout") {
			location.href = "/security/logout";
		}
		else if (menuId == "menuScheduler") {
			location.href = "/schedule/list";
		}
	});

	$("#btnJoin").on("click", function() {
		location.href = "/member/join";
	});

	$(".btnLogin").on("click", function() {
		var email = $("#txtEmail").val();
		var password = $("#txtPassword").val();

		if (email == "") {
			alert("이메일을 입력하세요.");
			$("#txtEmail").focus();
			return;
		}
		else if (password == "") {
			alert("비밀번호를 입력하세요.");
			$("#txtPassword").focus();
			return;
		}

		$.ajax({
			url: "/api/security/login",
			method: "POST",
			data: {
				email: email,
				password: password
			}
		}).done(function(result) {
			processAfterLogin(result.name);
		}).fail(function() {
			alert("로그인을 실패하였습니다.");
		});
	});

	$(".btnLoginCancel").on("click", function() {
		hideLoginDialog();
	});

	function showLoginDialog() {
		$(".login-dialog").fadeIn();
	}

	function hideLoginDialog() {
		$(".login-dialog").fadeOut();
	}

	function processAfterLogin(name) {
		alert(name + "님 반갑습니다.");

		$("#txtEmail").val("");
		$("#txtPassword").val("");

		hideLoginDialog();
		showMenu(true);
	}

	function showMenu(loggedIn) {
		if (loggedIn) {
			$(".beforeLogin").hide();
			$(".afterLogin").css("display", "inline-block");
		}
		else {
			$(".afterLogin").hide();
			$(".beforeLogin").css("display", "inline-block");
		}
	}

	$.ajax({
		url: "/api/security/isLoggedIn",
		method: "GET"
	}).done(function(result) {
		if (result.name == "") {
			showMenu(false);
		}
		else {
			showMenu(true);
		}
	});
});