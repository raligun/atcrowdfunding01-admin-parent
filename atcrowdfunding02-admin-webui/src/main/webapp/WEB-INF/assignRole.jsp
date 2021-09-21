<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
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

   <jsp:include page="include/include_nav.jsp"></jsp:include>

    <div class="container-fluid">
      <div class="row">
        <jsp:include page="include/include_sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">分配角色</li>
				</ol>
			<div class="panel panel-default">
			  <div class="panel-body">
				<form role="form" class="form-inline" action="admin/roleAssign" method="post">
					<input type="hidden" name="pn" value="${param.pn}">
					<input type="hidden" name="keyword" value="${param.keyword}">
					<input type="hidden" name="adminId" value="${requestScope.adminId}">
				  <div  class="form-group">
					<label for="unas_role_table" >未分配角色列表</label><br>
					<select id="unas_role_table" class="form-control" multiple size="10" style="width:100px;overflow-y:auto;">
						<c:forEach items="${requestScope.unAssignRole}" var="role">
						  <option value="${role.id}">${role.name}</option>
						</c:forEach>
                    </select>
				  </div>
				  <div class="form-group">
                        <ul>
                            <li id="to_right" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                            <br>
                            <li id="to_left" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                        </ul>
				  </div>
				  <div  class="form-group" style="margin-left:40px;">
					<label for="role_table">已分配角色列表</label><br>
					<select name="roleIds" id="role_table" class="form-control" multiple size="10" style="width:100px;overflow-y:auto;">
						<c:forEach items="${requestScope.assignRole}" var="role">
							<option value="${role.id}">${role.name}</option>
						</c:forEach>
                    </select>
				  </div>
					<div>
						<button id="save_btn" style="width: 150px" type="submit" class="btn btn-lg btn-success btn-block">保存</button>
					</div>
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

            $("#to_right").click(function () {
				$("select:eq(0)>option:selected").appendTo($("select:eq(1)"));
			});

            $("#to_left").click(function () {
				$("select:eq(1)>option:selected").appendTo($("select:eq(0)"));
			});

			$("#save_btn").click(function () {
				$("select:eq(1)>option").prop("selected","selected");
			})

        </script>
  </body>
</html>
