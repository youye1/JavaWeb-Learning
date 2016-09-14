<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/9/12
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>部门表单</title>
    <%--<%@ include file="/WEB-INF/views/includes/heads.jsp"%>--%>
    <script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        function findManager() {
            alert("hello world");
            $.ajax({
                url: '${ctx}/departmentServlet',
                type: 'post',
                dataType: 'json',
                data: {
                    action: 'query',
                    key: function () {
                        var key = $("#key").val();
                        return key;
                    }
                },
                success: function (data) {
                    if (data != null && data != '') {
                        for (var i = 0; i < data.length; i++) {
                            $("#managerId").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                        }
                    }
                },
                error: function (data) {
                    alert(data);
                    alert(data+"--"+data.length);
                }
            });
        }
    </script>
</head>
<body>
[<a href="${ctx}/departmentServlet?action=form">添加部门</a>|
<a href="${ctx}/employeeServlet?action=form">添加员工(随机数据)</a>|
<a href="${ctx}/departmentServlet">部门列表</a>|
<a href="${ctx}/employeeServlet">员工列表</a>]
<form action="${ctx}/departmentServlet" method="post">
    <input type="hidden" name="id" value="${id}"/>
    <input type="hidden" name="action" value="save"/>
        <legend>添加部门</legend>
        <table>
            <tr>
                <td>名称：</td>
                <td><input type="text" name="name" id="name" value="${name}"/>
                </td>
            </tr>
            <tr>
                <td>经理：</td>
                <td>
                    <div>
                        <input type="text" name="key" id="key"
                               value="${managerName}"/>
                        <input type="button" name="btnQuery" id="btnQuery"
                               value="查询" onclick="findManager()"/>
                    </div>
                    <div>
                        <select name="managerId" id="managerId"
                                style="width: 150px;height: 30px;">
                            <option value="${managerId}">${managerName}</option>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td>描述：</td>
                <td>
                    <textarea name="description"
                              id="description">${description}</textarea>
                </td>
            </tr>
        </table>
        <input type="submit" id="btnSubmit" value="提交"/>
</form>
${message}
</body>
</html>
