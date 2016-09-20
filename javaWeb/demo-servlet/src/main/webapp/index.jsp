<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<body>
<h2>Hello World!</h2>
<ul>
    <li>
        <a href="${ctx}/toPage?pagename=uploadTest/progressUpload.jsp">上传条文件上传</a><br/>
    </li>
    <li>
        <a href="${ctx}/toPage?pagename=uploadFile/upload.jsp">文件上传</a>
    </li>
</ul>
</body>
</html>