<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/9/8
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<div class="container">
    user列表 [<a href="${ctx}/studentServlet?action=edit">添加</a> ][<a
        href="${ctx}/studentServlet?action=list">列表</a> ]<br/>
    <table border="1px">
        <tr>
            <th>ID</th>
            <th>姓名</th>
            <th>性别</th>
            <th>地址</th>
            <th>出生日期</th>
            <th>证件照</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${list}" var="student" varStatus="index">
            <tr>
                <td>${student.getId()}</td>
                <td>${student.name}</td>
                <td>${student.gender}</td>
                <td>${student.address}</td>
                <td>${student.birthday}</td>
                <td>${student.picture}</td>
                <td>
                    <a href="${ctx}/studentServlet?action=edit&id=${student.id}">修改</a>&nbsp;
                    <a href="${ctx}/studentServlet?action=delete&id=${student.id}"
                       onclick="return confirm('确定删除？');">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>


</div>
</body>
</html>
