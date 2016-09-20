<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/9/10
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/includes/taglibs.jsp"%>

<html>
<head>
    <title>部门列表</title>
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
        <form action="${ctx}/departmentServlet">
            <label>查询框</label><br/>
            <table>
                <tr>
                    <td><label>名称：</label>
                        <input type="text" name="departmentName"
                               id="departmentName"/>
                    </td>
                    <td><label>经理：</label>
                        <input type="text" name="managerName"
                               id="managerName"/></td>
                    <td><label>员工数：</label>
                        <select id="employeesOperator" name="employeesOperator">
                            <option value=""></option>
                            <option value=">"> &gt; </option>
                            <option value="="> =</option>
                            <option value="<"> &lt;</option>
                        </select>
                        <input type="number" name="employeesSize"
                               id="employeesSize" min="1" max="10"/></td>
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
                <th>编号</th>
                <th>名称</th>
                <th>经理</th>
                <th>员工数</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${departments}" var="department" varStatus="vs">
                <tr  class="${ vs.count % 2==0 ?'alt':''}">
                    <td>${department.id}</td>
                    <td>${department.name}</td>
                    <td>${department.manager.name}</td>
                    <td>${department.employees.size()}</td>
                    <td>
                        <a href="${ctx}/departmentServlet?action=form&id=${department.id}">修改</a>|
                        <a href="${ctx}/departmentServlet?action=delete&id=${department.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
