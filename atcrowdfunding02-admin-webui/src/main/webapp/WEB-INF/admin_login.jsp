<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="zh-CN">
<head>
    <meta charset="GB18030">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">
    <style>

    </style>
</head>
  <body>
  <div class="container-fluid">
      <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 控制面板</a></div>
      </div>
      <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
              <li style="padding-top:8px;">
                  <div class="btn-group">
                  </div>
              </li>
              <li style="margin-left:10px;padding-top:8px;">
                  <button type="button" class="btn btn-default btn-danger">
                      <span class="glyphicon glyphicon-question-sign"></span> 帮助
                  </button>
              </li>
          </ul>
      </div>
  </div>
  <div class="container">
      <form class="form-signin" role="form" method="post" action="do/login">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" name="loginAcct" placeholder="请输入登录账号" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" name="userPswd" placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
          <span style="text-align:left; color: red">${requestScope.exception.message}</span>
          <span style="text-align:left; color: red">${SPRING_SECURITY_LAST_EXCEPTION.message}</span>
          <div class="checkbox" style="text-align:right;"><a href="reg">我要注册</a></div>
          <input type="submit" class="btn btn-lg btn-success btn-block" value="登录">
      </form>
    </div>
  <script src="jquery/jquery-2.1.1.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>