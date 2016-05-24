<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>登录界面</title>
<script src="js/jquery-1.11.1.js"></script>
<script src="js/login.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css" />

<meta http-equiv="Content-Type" content="text/html; ">
<title>Insert title here</title>
</head>
<body>
	<div id="loginpanelwrap">
		<div class="loginheader">
			<div class="logintitle">登录</div>
		</div>
		<form action="/UserLogin/Sample1/loginServlet">
			<div class="loginform">
				<div class="loginform_row">
					<label>用户名:</label> <input type="text" class="loginform_input"
						name="username" />
				</div>
				<div class="loginform_row">
					<label>密码:</label> <input type="password" class="loginform_input"
						name="password" />
				</div>
				<div class="loginform_row">
					<label>验证码:</label> <input type="text"
						class="loginform_input_validationCode" name="validationCode" /> <img
						class="validationCode_img" src="/UserLogin/Sample1/validationCode">
				</div>
				<div class="loginform_row">
					<span class="returnInfo"></span> <input type="submit"
						class="loginform_submit" value="登录" />
				</div>
				<div class="clear"></div>
			</div>
		</form>
	</div>
</body>

</html>