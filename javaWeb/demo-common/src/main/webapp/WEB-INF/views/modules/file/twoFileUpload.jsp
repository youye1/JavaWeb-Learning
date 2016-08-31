<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/7/15
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/includes/taglib.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="control-group">
        <form action="${ctx}/file/twoUpload" method="post" enctype="multipart/form-data">
            文件1：<input type="file" name="img1"/><br/>
            文件2：<input type="file" name="img2"/><br/>
            <input type="submit" name="submit" value="上传"/>
        </form>
    </div>
</div>
</body>
</html>
