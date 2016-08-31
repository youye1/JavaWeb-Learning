<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/includes/taglib.jsp" %>
<%--<%--%>
    <%--String path = request.getContextPath();--%>
    <%--String basePath = request.getScheme() + "://"--%>
            <%--+ request.getServerName() + ":" + request.getServerPort()--%>
            <%--+ path + "/";--%>
<%--%>--%>
<html>
<head>
    <title>图片展示</title>
</head>
<body>
<div class="container">
    <div class="control-group">
        <h3>文件展示</h3>
        <img src="${ctxStatic}/imgs/hhh.png" alt="暂无图片"/>
    </div>
    <div class="control-group">
        <h4>多文件展示</h4>
        <%
            List<String> files = (List) request.getAttribute("files");
            for (String url : files) {
        %>
        <a href="<%=url%>">
            <img src="<%=url%>" alt="暂无图片"/>
        </a>
        <%
            }
        %>

    </div>
</div>
</body>
</html>
