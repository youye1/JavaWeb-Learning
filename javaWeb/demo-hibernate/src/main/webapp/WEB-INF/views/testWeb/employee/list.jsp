<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/9/10
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/includes/taglibs.jsp" %>
<html>
<head>
    <title>员工页面</title>
    <style type="text/css">
        <%@include file="/WEB-INF/views/includes/css/table_style.css"%>
    </style>
</head>
<body>
<div class="container">
    [<a href="${ctx}/departmentServlet?action=form">添加部门</a>|
    <a href="${ctx}/employeeServlet?action=form">添加员工</a>|
    <a href="${ctx}/departmentServlet">部门列表</a>|
    <a href="${ctx}/employeeServlet">员工列表</a>]
    <div>
        <form action="${ctx}/employeeServlet">
            <label>查询框</label><br/>
            <table>
                <tr>
                    <td><label>姓名：</label>
                        <input type="text" name="emName"
                               id="emName"/>
                    </td>
                    <td><label>性别：</label>
                        <select id="emSex" name="emSex">
                            <option value=""></option>
                            <option value="男"> 男</option>
                            <option value="女"> 女</option>
                        </select></td>
                    <td><label>年龄：</label>
                        <select id="ageOperate" name="ageOperate">
                            <option value=""></option>
                            <option value=">"> &gt; </option>
                            <option value="="> =</option>
                            <option value="<"> &lt;</option>
                        </select>
                        <input type="number" name="emAge"
                               id="emAge" min="20" max="60"/></td>
                </tr>
                <tr>
                    <td>
                        <label>生日：</label>
                        <input type="text" id="emBirthday" name="emBirthday"/>
                    </td>
                    <td>
                        <label>上班时间</label>
                        <input type="text" name="time" id="time"/>
                    </td>
                    <td>
                        <label>薪资：</label>
                        <select id="salaryOperate" name="salaryOperate">
                            <option value=""></option>
                            <option value=">"> &gt; </option>
                            <option value="="> =</option>
                            <option value="<"> &lt; </option>
                        </select>
                        <input type="text" id="emSalary" name="emSalary"/>
                    </td>
                </tr>
            </table>
            <input id="btnSubmit" type="submit" value="查询"/>
        </form>
    </div>
    <hr/>
    ${message}
    <div class="controls">
        <table id="customers">
            <tr>
                <th width="">姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>生日</th>
                <th>薪水</th>
                <th>上班时间</th>
                <th>下班时间</th>
                <th>部门</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${list}" var="employee" varStatus="vs">
                <tr class="${ vs.count % 2==0 ?'alt':''}">
                    <td>${employee.name}</td>
                    <td>${employee.sex}</td>
                    <td>${employee.age}</td>
                    <td>${employee.birthday}</td>
                    <td>${employee.salary}</td>
                    <td>${employee.startTime}</td>
                    <td>${employee.endTime}</td>
                    <td>${employee.department.name}</td>
                    <td>${employee.disabled}</td>
                    <td>
                        <a href="${ctx}/employeeServlet?action=form&id=${employee.id}">修改</a>|
                        <a href="${ctx}/employeeServlet?action=delete&id=${employee.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
