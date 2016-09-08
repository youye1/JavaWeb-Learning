<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<body>
<h2>Hello World!</h2>
<ul>
    <li><a href="${ctx}/studentServlet?action=list">学生信息管理界面</a> </li>
</ul>
</body>
</html>
