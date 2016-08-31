<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/includes/taglibs.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>

    <title>Title</title>
</head>
<body>
<form id="inputform" action="${ctx}/login" method="post">
    账号：<input type="text" id="username" name="username"/><br>
    密码：<input type="text" id="password" name="password"/><br>
    <button type="submit">哈哈哈</button>
</form>

</body>
</html>
