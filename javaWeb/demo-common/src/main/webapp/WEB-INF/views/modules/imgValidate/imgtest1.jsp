<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/7/15
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/includes/taglib.jsp" %>
<html>
<head>
    <title>图片验证</title>
    <script type="text/javascript">
        var i = 1;
        function changeCode() {
            document.getElementById("vcode").src = "${ctx}/img/vCode?v=" + (i++);

        }
    </script>
</head>
<body>

<div class="container">
    <div class="control-group">
        <h4>欢迎验证</h4>
    </div>
    <div class="control-group" style="margin-top: 30px">
        <form action="${ctx}/img/test1" method="post">
            <input type="text" name="vcode"/>
            <input id="vcode" type="image" src="${ctx}/img/vCode" onclick="changeCode()"><br>
            <input type="submit" value="验证"/>
        </form>
    </div>
    ${msg}
</div>

</body>
</html>
