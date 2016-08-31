<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/includes/taglib.jsp" %>
<html>
<head>

    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="control-group">
        <a href="${ctx}/global/test3?langType=zh">中文</a>
        <a href="${ctx}/global/test3?langType=en">英文</a>
    </div>

    下面展示的是后台获取的国际化信息：<br/>
    ${money}<br/>
    ${date}<br/><br/>
    下面展示的是视图中直接绑定的国际化信息：<br/>
    <spring:message code="money"/>:<br/>
    <spring:eval expression="contentModel.money"></spring:eval><br/>
    <spring:message code="date"/>:<br/>
    <spring:eval expression="contentModel.date"></spring:eval><br/>
    <br>
    <span>注意：基于Cookie</span>
</div>
</body>
</html>
