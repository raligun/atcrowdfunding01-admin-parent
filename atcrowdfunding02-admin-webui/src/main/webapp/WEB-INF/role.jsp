<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="GB18030">
  <head>
<jsp:include page="include/include_header.jsp"></jsp:include>
<%--	<style>--%>
<%--	.tree li {--%>
<%--        list-style-type: none;--%>
<%--		cursor:pointer;--%>
<%--	}--%>
<%--	table tbody tr:nth-child(odd){background:#F4F4F4;}--%>
<%--	table tbody td:nth-child(even){color:#C00;}--%>
<%--	</style>--%>
  </head>

  <body>
  <link rel="stylesheet" href="ztree/zTreeStyle.css">
  <script src="ztree/jquery.ztree.all-3.5.min.js"></script>
  <script type="text/javascript" src="myjs/rolePage.js"></script>

   <jsp:include page="include/include_nav.jsp"></jsp:include>

    <div class="container-fluid">
      <div class="row">
        <jsp:include page="include/include_sidebar.jsp"></jsp:include>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input id="keyword_input" class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button type="button" id="query_btn" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" id="del_btn" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" id="add_btn" class="btn btn-primary" style="float:right;" ><i class="glyphicon glyphicon-plus"></i>新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input class="checkAll" type="checkbox"></th>
                  <th>名称</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody id="rolePageBody">

              </tbody>
			  <tfoot>
              <tr>
                  <td colspan="6" align="center">
                      <ul class="pagination">
                          <li id="Pagination" class="pagination"><!-- 这里显示分页 --></li>
                      </ul>
                  </td>
              </tr>
			  </tfoot>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>
  <jsp:include page="modal/modal-role-add.jsp"></jsp:include>
  <jsp:include page="modal/modal-role-edit.jsp"></jsp:include>
  <jsp:include page="modal/modal-role-assign-auth.jsp"></jsp:include>

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
            
            $("tbody .btn-success").click(function(){
                window.location.href = "assignPermission.html";
            });

            //初始化页面
            $(function () {
                window.pn = 1;
                window.pageSize = 5;
                window.keyword = "";
                window.total = "";

                initPage();
            });

            //关键词查询功能
            $("#query_btn").click(function () {
                window.keyword = $("#keyword_input").val();

                initPage();
            });

            //打开添加角色的框
            $("#add_btn").click(function () {
                $("#add_role_modal").modal({backdrop:"static"});
                return false;
            });

            $("#authCancelBtn").click(function () {
                $("#assign_role_modal").modal("hide");
                return false;
            });

            //添加角色
            $("#add_save_btn").click(function () {
                $.ajax({
                    url:"role/add",
                    data:"name="+$("#add_role_name").val(),
                    type:"post",
                    success:function (data) {
                        if (data.code != 200){
                            layer.msg(data.message);
                            return false;
                        }
                        $("#add_role_modal").modal("hide");
                        window.pn = window.total;
                        initPage();
                    },
                    error:function (data) {
                        layer.msg("保存失败!原因:" + data.statusText);
                        return null;
                    }
                })
            });

            //打开修改角色的框
            $(document).on("click",".update_btn",function () {
                $("#update_role_modal").modal({backdrop:"static"});
                window.roleId = $(this).attr("roleId");
                var roleName = $(this).parent().prev().text();
                $("#update_role_modal [id=update_role_name]").prop("value",roleName);
                return false;
            });

            //保存更新按钮
            $("#update_save_btn").click(function () {
                alert(window.roleId)
                $.ajax({
                    url:"role/update",
                    type: "post",
                    data: "id="+window.roleId+"&name="+$("#update_role_name").prop("value"),
                    success:function (data) {
                        if (data.code != 200){
                            layer.msg(data.message);
                            return false;
                        }
                        $("#update_role_modal").modal("hide");
                        initPage();
                    },
                    error:function (data) {
                        layer.msg("更新失败!原因:" + data.statusText);
                        return null;
                    }
                })
            });

            $(document).on("click",".delete_btn",function () {
                var name = $(this).parent().prev().text();
                var id = $(this).attr("roleId");
                if (confirm("你确定要删除【"+ name +"】角色吗？")){
                    $.ajax({
                        url:"role/delete",
                        type:"post",
                        data:"ids="+ id,
                        success:function (data) {
                            if (data.code != 200){
                                layer.msg(data.message);
                                return false;
                            }
                            layer.msg("删除成功！");
                            initPage();
                        },
                        error:function (data) {
                            layer.msg("删除失败!原因:" + data.statusText);
                            return null;
                        }
                    })
                }
            });

            $(document).on("click",".check_btn",function () {
                getAllAuth();
                window.roleId = $(this).attr("roleId");
                $("#assign_role_modal").modal({backdrop:"static"});

                return false;
            });

            authSaveBtnClick();


        </script>
  </body>
</html>
