$(function (){
	/**
	 * 用户名输入框键盘事件
	 */
	$("#username").keyup(function(event){
		if ($(this).val() != "" && event.keyCode == 13) {
			$("#password").focus();
		}
	}).focus(function () {
		$(this).parent("span").parent("li").removeClass("has-error has-success");
	}).focus();
	/**
	 * 密码输入框键盘事件
	 */
	$("#password").keyup(function(event){
		if ($(this).val() != "" && event.keyCode == 13) {
			$("#logging").trigger("click");
		}
	}).focus(function () {
		$(this).parent("span").parent("li").removeClass("has-error has-success");
	});
	/**
	 * 登录按钮点击事件
	 */
	$("#logging").click(function(){
		var index = layer.load(1, {shade: [0.8,'#000']});
		var username = $("#username");
		var password = $("#password");
		var username_value = username.val().trim();
		var password_value = password.val();
		var username_li = username.parent("span").parent("li");
		var password_li = password.parent("span").parent("li");
		if (username_value != "" && password_value != "") {
			$.ajax({
				"async" : false,
				"data" : {"username":username_value, "password":password_value},
				"success" : function(data){
					layer.close(index);
					if (data.state != 0) {
						username_li.removeClass("has-error").addClass("has-success");
						password_li.removeClass("has-error").addClass("has-success");
						layer.msg(data.message, {icon: 6});
						window.location.href = base_path + "index.action";
					} else {
						username_li.removeClass("has-success").addClass("has-error");
						password_li.removeClass("has-success").addClass("has-error");
						layer.msg(data.message, {icon: 5});
					}
				},
				"type" : "post",
				"url" : base_path + "login/ajax.action"
			});
		}
	});
});