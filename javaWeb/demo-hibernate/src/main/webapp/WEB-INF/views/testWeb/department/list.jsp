<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/9/10
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>部门列表</title>
    <style type="text/css">
        #customers {
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            width: 80%;
            border-collapse: collapse;
        }

        #customers td, #customers th {
            font-size: 1em;
            border: 1px solid #98bf21;
            padding: 3px 7px 2px 7px;
        }

        #customers th {
            font-size: 1.1em;
            text-align: left;
            padding-top: 5px;
            padding-bottom: 4px;
            background-color: #A7C942;
            color: #ffffff;
        }

        #customers tr.alt td {
            color: #000000;
            background-color: #EAF2D3;
        }

        #btnSubmit {
            font-size: 1em;
            border-radius: 5px;
            color: #ffffff;
            background-color: #98bf21;
            width: 60px;
            height: 30px;
        }
    </style>
</head>
<body>
<div class="container">
    [<a href="${ctx}/departmentServlet?action=form">添加部门</a>|
    <a href="${ctx}/employeeServlet?action=form">添加员工(随机数据)</a>|
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
                <tr>
                    <td>${department.id}</td>
                    <td>${department.name}</td>
                    <td>${department.manager}</td>
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
