<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<jsp:include page="include/include_header.jsp"></jsp:include>
	<link rel="stylesheet" href="ztree/zTreeStyle.css">
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

			<div class="panel panel-default">
              <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表 <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
                  <ul id="treeDemo" class="ztree"></ul>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<jsp:include page="modal/modal-menu-add.jsp"></jsp:include>
	<jsp:include page="modal/modal-menu-confirm.jsp"></jsp:include>
	<jsp:include page="modal/modal-menu-edit.jsp"></jsp:include>

	<script src="ztree/jquery.ztree.all-3.5.min.js"></script>
	<script src="myjs/menuPage.js"></script>
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

       $(function () {
			initMenu();
	   });

		// 打开增加节点的模态框
	   $("#treeDemo").on("click",".add_btn",function(){
		// 将当前节点的 id，作为新节点的 pid 保存到全局变量
		   window.pid = this.id;
		// 打开模态框
		   $("#menuAddModal").modal("show");
		   return false;
	   });
		// 保存新增节点
	   $("#menuSaveBtn").click(function(){
		// 收集表单项中用户输入的数据
		   var name = $.trim($("#menuAddModal [name=name]").val());
		   var url = $.trim($("#menuAddModal [name=url]").val());
		// 单选按钮要定位到“被选中”的那一个
		   var icon = $("#menuAddModal [name=icon]:checked").val();
		// 发送 Ajax 请求
		   $.ajax({
			   "url":"menu/add",
			   "type":"post",
			   "data":{
				   "pid": window.pid,
				   "name":name,
				   "url":url,
				   "icon":icon
			   },
			   "dataType":"json",
			   "success":function(data){

				   if(data.code == 200) {
					   layer.msg("操作成功！");
					// 重新加载树形结构，注意：要在确认服务器端完成保存操作后再刷新
					// 否则有可能刷新不到最新的数据，因为这里是异步的
					   initMenu();
				   }
				   if(data.code == 100) {
					   layer.msg("操作失败！"+response.message);
				   }
			   },
			   "error":function(response){
				   layer.msg(response.status+" "+response.statusText);
			   }
		   });
			// 关闭模态框
		   $("#menuAddModal").modal("hide");
			// 清空表单
			// jQuery 对象调用 click()函数，里面不传任何参数，相当于用户点击了一下
		   $("#menuResetBtn").click();
	   });

		//打开修改模态框 并回显要修改的数据
	   $("#treeDemo").on("click",".edit_btn",function(){
			// 将当前节点的 id 保存到全局变量
		   window.id = this.id;
			// 打开模态框
		   $("#menuEditModal").modal("show");
			// 获取 zTreeObj 对象
		   var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
			// 根据 id 属性查询节点对象
			// 用来搜索节点的属性名
		   var key = "id";
			// 用来搜索节点的属性值
		   var value = window.id;
		   var currentNode = zTreeObj.getNodeByParam(key, value);
			// 回显表单数据
		   $("#menuEditModal [name=name]").val(currentNode.name);
		   $("#menuEditModal [name=url]").val(currentNode.url);
			// 回显 radio 可以这样理解：被选中的 radio 的 value 属性可以组成一个数组，
			// 然后再用这个数组设置回 radio，就能够把对应的值选中
		   $("#menuEditModal [name=icon]").val([currentNode.icon]);
		   return false;
	   });
		// 保存修改
	   $("#menuEditBtn").click(function(){
// 收集表单数据
		   var name = $("#menuEditModal [name=name]").val();
		   var url = $("#menuEditModal [name=url]").val();
		   var icon = $("#menuEditModal [name=icon]:checked").val();
// 发送 Ajax 请求
		   $.ajax({
			   "url":"menu/update",
			   "type":"post",
			   "data":{
				   "id": window.id,
				   "name":name,
				   "url":url,
				   "icon":icon
			   },
			   "dataType":"json",
			   "success":function(data){
				   var result = data.code;
				   if(result == 200) {
					   layer.msg("操作成功！");
// 重新加载树形结构，注意：要在确认服务器端完成保存操作后再刷新
// 否则有可能刷新不到最新的数据，因为这里是异步的
					   initMenu();
				   }
				   if(result == 100) {
					   layer.msg("操作失败！"+response.message);
				   }
			   },
			   "error":function(response){
				   layer.msg(response.status+" "+response.statusText);
			   }
		   });
// 关闭模态框
		   $("#menuEditModal").modal("hide");
	   });

	   // 给“×”按钮绑定单击响应函数
	   $("#treeDemo").on("click",".remove_btn",function(){
 // 将当前节点的 id 保存到全局变量
		   window.id = this.id;
// 打开模态框
		   $("#menuConfirmModal").modal("show");
// 获取 zTreeObj 对象
		   var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
// 根据 id 属性查询节点对象
// 用来搜索节点的属性名
		   var key = "id";
// 用来搜索节点的属性值
		   var value = window.id;
		   var currentNode = zTreeObj.getNodeByParam(key, value);
		   $("#removeNodeSpan").html(" 【 <i class='"+currentNode.icon+"'></i>"+currentNode.name+"】");
		   return false;
	   });

	   $("#confirmBtn").click(function(){
		   $.ajax({
			   "url":"menu/delete",
			   "type":"post",
			   "data":{
				   "id":window.id
			   },
			   "dataType":"json",
			   "success":function(data){
				   var result = data.code;
				   if(result == 200) {
					   layer.msg("操作成功！");
// 重新加载树形结构，注意：要在确认服务器端完成保存操作后再刷新
// 否则有可能刷新不到最新的数据，因为这里是异步的
					   initMenu();
				   }
				   if(result == 100) {
					   layer.msg("操作失败！"+response.message);
				   }
			   },
			   "error":function(response){
				   layer.msg(response.status+" "+response.statusText);
			   }
	   });
// 关闭模态框
		   $("#menuConfirmModal").modal("hide");
	   });
        </script>
  </body>
</html>
