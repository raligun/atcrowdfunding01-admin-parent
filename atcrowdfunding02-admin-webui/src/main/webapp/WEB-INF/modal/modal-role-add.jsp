<%--
  Created by IntelliJ IDEA.
  User: 网中鱼
  Date: 2021/8/31
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--  添加角色的模态框--%>
<div id="add_role_modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">新增角色</h4>
            </div>
            <div class="media-body">
                <form class="form-signin" role="form">
                    <div class="form-group has-success has-feedback">
                        <input
                                type="text" id="add_role_name"
                                class="form-control"  placeholder="请输入角色名称" autofocus/>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="add_save_btn" class="btn btn-primary">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

