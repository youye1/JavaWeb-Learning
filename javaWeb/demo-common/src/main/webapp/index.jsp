<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="WEB-INF/views/includes/taglib.jsp" %>
<html>
<head>
    <title>测试主页</title>
</head>
<body>
<h3>Hello World!</h3>

<div class="container">

    <ul class="control-group">
        <li><a href="${ctx}/global/test1">国际化测试1</a></li>
        <li><a href="${ctx}/global/test2">国际化测试2</a></li>
        <li><a href="${ctx}/global/test3">国际化测试3</a></li>
    </ul>
    <hr/>
    <ul>
        <li><a href="${ctx}/toPage?pageName=imgValidate/imgtest1">图片验证码测试1</a></li>
    </ul>
    <hr/>
    <ul>
        <li><a href="${ctx}/toPage?pageName=file/oneFileUpload">单文件上传</a></li>
        <li><a href="${ctx}/toPage?pageName=file/twoFileUpload">多文件上传</a></li>
    </ul>
</div>
</body>
</html>
