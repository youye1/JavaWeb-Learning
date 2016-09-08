<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/9/8
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxSttic" value="${pageContext.request.contextPath}/static"/>
<html>
<head>
    <title>student表单</title>
    <script src="${ctxStatic}/My97DatePicker/WdatePicker.js"
            type="text/javascript"></script>
</head>
<body>
<div class="container">
    user表单 [<a href="${ctx}/studentServlet?action=list">返回列表</a> ]<br/>
    <form action="${ctx}/studentServlet?action=save" method="post">
        <input type="hidden" name="id" id="id" value="${student.id}"/>
        <label>姓名：</label>
        <input id="name" name="name" type="text"
               value="${student.name}"/><br/>
        <label>性别：</label>
        <input id="gender" name="gender" type="text"
               value="${student.gender}"/><br/>
        <label>地址：</label>
        <input id="address" name="address" type="text"
               value="${student.address}"/><br/>
        <label>生日：</label>
        <input id="birthday" name="birthday" type="text"
               maxlength="20" class="input-mini Wdate"
               value="${student.birthday}"
               onclick="WdatePicker();"/><br/>
        <input type="submit" value="保存"/>
    </form>
</div>
</body>
</html>
