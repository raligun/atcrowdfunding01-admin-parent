function authSaveBtnClick() {
// 14.给分配权限模态框中的“分配”按钮绑定单击响应函数
    $("#authSaveBtn").click(function () {
// ①收集树形结构的各个节点中被勾选的节点
// [1]声明一个专门的数组存放 id
        var authIdArray = [];
// [2]获取 zTreeObj 对象
        var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
// [3]获取全部被勾选的节点
        var checkedNodes = zTreeObj.getCheckedNodes();
// [4]遍历 checkedNodes
        for (var i = 0; i < checkedNodes.length; i++) {
            var checkedNode = checkedNodes[i];
            var authId = checkedNode.id;
            authIdArray.push(authId);
        }
// ②发送请求执行分配
//         var requestBody = {
//             "auths": authIdArray,
// 为了服务器端 handler 方法能够统一使用 List<Integer>方式接收数据，roleId 也存入数组
//             "roleId": window.roleId
//         };
        $.ajax({
            url: "auth/assignRoleNewAuth",
            type: "post",
            data: "roleId="+window.roleId+"&auths="+authIdArray,
            traditional:true,
            success: function (data) {
                var result = data.code;
                if (result == 200) {
                    layer.msg("操作成功！");
                }
                if (result == 400) {
                    layer.msg("操作失败！" + data.message);
                }
            },
            error: function (response) {
                layer.msg(response.status + " " + response.statusText);
            }
        });
        $("#assign_role_modal").modal("hide");

    });
}

function getAllAuth() {
    $.ajax({
            url: "auth/getAllAuth",
            type: "post",
            success: function (data) {
                if (data.code != 200) {
                    layer.msg(data.message);
                    return false;
                }

                var authList = data.data;

                // 3.准备对 zTree 进行设置的 JSON 对象
                var setting = {
                    "data": {
                        "simpleData": {
// 开启简单 JSON 功能
                            "enable": true,
// 使用 categoryId 属性关联父节点，不用默认的 pId 了
                            "pIdKey": "categoryId"
                        },
                        "key": {
// 使用 title 属性显示节点名称，不用默认的 name 作为属性名了
                            "name": "title"
                        }
                    },
                    "check": {
                        "enable": true
                    }
                };
// 4.生成树形结构
// <ul id="authTreeDemo" class="ztree"></ul>
                $.fn.zTree.init($("#authTreeDemo"), setting, authList);
// 获取 zTreeObj 对象
                var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
// 调用 zTreeObj 对象的方法，把节点展开
                zTreeObj.expandAll(true);
// 5.查询已分配的 Auth 的 id 组成的数组
                ajaxReturn = $.ajax({
                    "url": "auth/getAssignAuth",
                    "type": "post",
                    "data": {
                        "roleId": window.roleId
                    },
                    "dataType": "json",
                    "async": false
                });
                if (ajaxReturn.status != 200) {
                    layer.msg("请 求 处 理 出错 ！ 响 应 状 态 码 是 ： " + ajaxReturn.status + "说明是：" + ajaxReturn.statusText);
                    return;
                }
                // 从响应结果中获取 authIdArray
                var authIdArray = ajaxReturn.responseJSON.data;
                // 6.根据 authIdArray 把树形结构中对应的节点勾选上
                // ①遍历 authIdArray
                for (var i = 0; i < authIdArray.length; i++) {
                    var authId = authIdArray[i];
                // ②根据 id 查询树形结构中对应的节点
                    var treeNode = zTreeObj.getNodeByParam("id", authId);
                // ③将 treeNode 设置为被勾选
                // checked 设置为 true 表示节点勾选
                    var checked = true;
                // checkTypeFlag 设置为 false，表示不“联动”，不联动是为了避免把不该勾选的勾选上
                    var checkTypeFlag = false;
                // 执行
                    zTreeObj.checkNode(treeNode, checked, checkTypeFlag);
                }
            },
        error : function (data) {
        layer.msg("获取失败!原因:" + data.statusText);
        return null;
    }})
}


function initPage() {
    getPageInfo();
}

//获取pageInfo
function getPageInfo() {
    $.ajax({
        url: "role/pageInfo",
        data: {
            "pn": window.pn,
            "pageSize": window.pageSize,
            "keyword": window.keyword
        },
        type: "post",
        dataType: "json",
        success: function (data) {
            var resultSet = data.code;
            if (resultSet != 200) {
                layer.msg(data.message);
                return;
            }
            drawTable(data.data);
            generateNavigator(data.data);
        },
        error: function (data) {
            layer.msg("获取响应失败!原因:" + data.statusText);
            return null;
        }
    })

}

function drawTable(pageInfo) {

    // 清除 tbody 中的旧的内容
    $("#rolePageBody").empty();
// 这里清空是为了让没有搜索结果时不显示页码导航条
    $("#Pagination").empty();
// 判断 pageInfo 对象是否有效
    if (pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length
        == 0) {
        $("#rolePageBody").append("<tr><td colspan='4' align='center'>抱歉！没有查询到您搜索的数据！</td></tr>");
        return;
    }
// 使用 pageInfo 的 list 属性填充 tbody
    for (var i = 0; i < pageInfo.list.length; i++) {
        var role = pageInfo.list[i];
        var roleId = role.id;
        var roleName = role.name;
        var numberTd = "<td>" + (i + 1) + "</td>";
        var checkboxTd = "<td><input class='checkItem' type='checkbox'></td>";
        var roleNameTd = "<td>" + roleName + "</td>";
        var checkBtn = "<button roleId=" + roleId + " type='button' class='check_btn btn btn-success btn-xs'><i class='glyphicon glyphicon-check'></i></button>";
        var pencilBtn = "<button roleId=" + roleId + "  type='button' class='update_btn btn btn-primary btn-xs'><i class='glyphicon glyphicon-pencil'></i></button>";
        var removeBtn = "<button roleId=" + roleId + "  type='button' class='delete_btn btn-danger btn-xs'><i class='glyphicon glyphicon-remove'></i></button>";
        var buttonTd = "<td>" + checkBtn + " " + pencilBtn + " " + removeBtn + "</td>";
        var tr = "<tr>" + numberTd + checkboxTd + roleNameTd + buttonTd + "</tr>";
        $("#rolePageBody").append(tr);
    }
}


// 翻页过程中执行的回调函数
// 点击“上一页”、“下一页”或“数字页码”都会触发翻页动作，从而导致当前函数被调用
// pageIndex 是用户在页面上点击的页码数值
function generateNavigator(pageInfo) {
// 获取分页数据中的总记录数
    window.total = pageInfo.total;
// 声明 Pagination 设置属性的 JSON 对象
    var properties = {
        num_edge_entries: 3, // 边缘页数
        num_display_entries: 5, // 主体页数
        callback: pageSelectCallback, // 用户点击“翻页”按钮之后
        // 执行翻页操作的回调函数
        current_page: pageInfo.pageNum - 1, // 当前页，pageNum 从 1 开始，
        // 必须-1 后才可以赋值
        prev_text: "上一页",
        next_text: "下一页",
        items_per_page: pageInfo.pageSize // 每页显示 1 项
    };
// 调用分页导航条对应的 jQuery 对象的 pagination()方法生成导航条
    $("#Pagination").pagination(window.total, properties);
}

function pageSelectCallback(pageIndex, jQuery) {
// pageIndex 是当前页页码的索引，相对于 pageNum 来说，pageIndex 比 pageNum 小 1
    window.pn = pageIndex + 1;

    window.keyword = $("#keyword_input").val();
// 执行页面跳转也就是实现“翻页”
    initPage();
// 取消当前超链接的默认行为
    return false;
}