<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="GB18030">
  <head>
    <jsp:include page="include/include_header.jsp"></jsp:include>
	  <style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
  </head>

  <body>

    <jsp:include page="include/include_nav.jsp"/>

    <div class="container-fluid">
      <div class="row">
       	<jsp:include page="include/include_sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">新增</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form role="form" method="post" action="admin/add">
				  <div class="form-group">
					<label for="loginAcct">登陆账号</label>
					  <span style="color: red">${requestScope.exception.message}</span>
					<input type="text" class="form-control" name="loginAcct" id="loginAcct" placeholder="请输入登陆账号">
				  </div>
				  <div class="form-group">
					<label for="userPswd">用户密码</label>
					<input type="text" class="form-control" name="userPswd" id="userPswd" placeholder="请输入密码">
				  </div>
				  <div class="form-group">
					<label for="userName">用户名称</label>
					<input type="text" class="form-control" name="userName" id="userName" placeholder="请输入用户名称">
				  </div>
				  <div class="form-group">
					<label for="email">邮箱地址</label>
					<input type="email" class="form-control" name="email" id="email" placeholder="请输入邮箱地址">
					<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
				  </div>
				  <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增 </button>
				  <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置 </button>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">帮助</h4>
		  </div>
		  <div class="modal-body">
			<div class="bs-callout bs-callout-info">
				<h4>测试标题1</h4>
				<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>测试标题2</h4>
				<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
			  </div>
		  </div>
		  <!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
		</div>
	  </div>
	</div>
    <script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="script/docs.min.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
            });
        </script>
  </body>
</html>
