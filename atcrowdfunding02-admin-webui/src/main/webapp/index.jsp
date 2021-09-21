<%--
  Created by IntelliJ IDEA.
  User: 网中鱼
  Date: 2021/8/25
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
    <jsp:include page="/WEB-INF/include/include_header.jsp"></jsp:include>
  </head>
  <body>
<a href="ssm">获取用户</a><br/>
  <button id="btn1">测试ajax发送数组</button>
<div id="mes" style="color: red"></div>
<button id="btn2">点击出现弹框</button><br/>
<div>登录用户：${sessionScope.loginAdmin}</div>

<a href="admin_login">点击去登录</a><br/>

  <script type="text/javascript">
    $("#btn1").click(function () {
          $.ajax({
            url:"test/arrays",
            data:{"array":[1,3,5]},
            type:"post",
            dataType:"text",
            traditional:true,
            success:function (data) {
                $("#mes").empty().append(data);
            },
            error:function (data) {
                alert("发生错误！");
            }
          })
    });

    $("#btn2").click(function () {
        layer.msg("lays的弹框");
    })
  </script>
  </body>
</html>
