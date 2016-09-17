<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/9/12
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/includes/taglibs.jsp"%>
<html>
<head>
    <title>员工信息</title>
    <script type="text/javascript">

    </script>
</head>
<body>
[<a href="${ctx}/departmentServlet?action=form">添加部门</a>|
<a href="${ctx}/employeeServlet?action=form">添加员工</a>|
<a href="${ctx}/departmentServlet">部门列表</a>|
<a href="${ctx}/employeeServlet">员工列表</a>]
${message}
<form action="${ctx}/employeeServlet" method="post">
    <input type="hidden" name="id" value="${employee.id}"/>
    <input type="hidden" name="action" id="action" value="add">
    <div>
        <h3>添加员工信息</h3>
        <table>
            <tr>
                <td>部门：</td>
                <td>
                    <select id="department" name="department"
                            style="width: 150px;height: 20px">
                        <option value="${employee.department.id}">${employee.department.name}</option>
                        <c:forEach items="${departments}" var="department"
                                   varStatus="vs">
                            <option value="${department.id}">${department.name}</option>
                        </c:forEach>
                    </select>
            </tr>
            <tr>
                <td>姓名：</td>
                <td><input type="text" name="name" id="name"
                           value="${employee.name}"/>
                </td>
            </tr>
            <tr>
                <td>性别：</td>
                <td><input type="radio" name="sex" id="sex1"
                           value="男" ${'女'!=employee.sex?'checked':''}>男
                    <input type="radio" name="sex" id="sex2"
                           value="女" ${'女'==employee.sex?'checked':''}>女
                </td>
            </tr>
            <tr>
                <td>年龄：</td>
                <td><input type="number" name="age" id="age"
                           value="${employee.age}"
                           max="70"/>
                </td>
            </tr>
            <tr>
                <td>出生日期：</td>
                <td><input type="text" name="birthday" id="birthday"
                           value="${employee.birthday}"/></td>
            </tr>
            <tr>
                <td>上班时间：</td>
                <td><input type="text" name="startTime" id="startTime"
                           value="${employee.startTime}"/></td>
            </tr>
            <tr>
                <td>下班时间：</td>
                <td><input type="text" name="endTime" id="endTime"
                           value="${employee.endTime}"/>
                </td>
            </tr>
            <tr>
                <td>工资：</td>
                <td><input type="text" name="salary" id="salary"
                           value="${employee.salary}"/>
                </td>
            </tr>
            <tr>
                <td>是否可以：</td>
                <td><input type="radio" name="disabled" id="disabled1"
                           value="0" ${true==employee.disabled?'checked':''}>是
                    <input type="radio" name="disabled" id="disabled2"
                           value="1" ${true!=employee.disabled?'checked':''}>否
                </td>
            </tr>
        </table>
        <input type="submit" value="提交"/>
    </div>
</form>
</body>
</html>
