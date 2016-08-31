<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/7/15
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/includes/taglib.jsp" %>
<html>
<head>
    <title>单文件上传</title>
</head>
<body>
<div class="container">
    <form action="${ctx}/file/oneUpload" method="post" enctype="multipart/form-data">
        <h3>hello</h3>
        <div class="control-group">
            文件：<input type="text" name="fileName"/>
            <input type="file" name="fileTest"/>
        </div>
        <div class="control-group">
            <input type="submit" value="上传"/>
        </div>
    </form>
</div>
</body>
</html>
