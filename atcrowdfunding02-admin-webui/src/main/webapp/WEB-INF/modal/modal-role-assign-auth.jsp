<%--
  Created by IntelliJ IDEA.
  User: 网中鱼
  Date: 2021/8/31
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="assign_role_modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">分配权限</h4>
            </div>
            <form>
            <div class="panel panel-default">
                <div class="panel-body">
                    <ul id="authTreeDemo" class="ztree"></ul>
                </div>
            </div>
            <div class="modal-footer">
                <button id="authCancelBtn" type="reset" class="btn btn-primary"><i class="glyphicon glyphicon-refresh"></i> 取消</button>
                <button id="authSaveBtn" type="button" class="btn btn-default"><i class="glyphicon glyphicon-plus"></i> 保存</button>
            </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->