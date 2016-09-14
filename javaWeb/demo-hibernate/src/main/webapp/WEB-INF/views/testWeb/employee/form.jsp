<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/9/12
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>员工信息</title>
</head>
<body>
[<a href="${ctx}/departmentServlet?action=form">添加部门</a>|
<a href="${ctx}/employeeServlet?action=form">添加员工</a>|
<a href="${ctx}/departmentServlet">部门列表</a>|
<a href="${ctx}/employeeServlet">员工列表</a>]
${message}
<form action="${ctx}/employeeServlet" method="post">
    <input type="hidden" name="id" value="id"/>
    <input type="hidden" name="action" id="action" value="add">
    <div>
        <h3>添加员工信息</h3>
        <table>
            <tr>
                <td>部门：</td>
                <td><input type="text" name="department" id="department"
                           value="${department}"/></td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td><input type="text" name="name" id="name" value="${name}"/>
                </td>
            </tr>
            <tr>
                <td>性别：</td>
                <td><input type="text" name="sex" id="sex" value="${sex}"/></td>
            </tr>
            <tr>
                <td>年龄：</td>
                <td><input type="number" name="age" id="age" value="${age}"
                           max="70"/>
                </td>
            </tr>
            <tr>
                <td>出生日期：</td>
                <td><input type="text" name="birthday" id="birthday"
                           value="${birthday}"/></td>
            </tr>
            <tr>
                <td>上班时间：</td>
                <td><input type="text" name="startTime" id="startTime"
                           value="${startTime}"/></td>
            </tr>
            <tr>
                <td>下班时间：</td>
                <td><input type="text" name="endTime" id="endTime"
                           value="${endTime}"/>
                </td>
            </tr>
            <tr>
                <td>工资：</td>
                <td><input type="text" name="salary" id="salary"
                           value="${salary}"/>
                </td>
            </tr>
        </table>
        <input type="submit" value="提交"/>
    </div>
</form>
</body>
</html>
