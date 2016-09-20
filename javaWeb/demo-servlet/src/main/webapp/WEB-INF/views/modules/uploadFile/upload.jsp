<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/9/18
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/includes/taglibs.jsp" %>
<html>
<head>
    <title>文件上传</title>
    <script type="text/javascript">
//        function checkbtn2() {
//            document.getElementById("action1").setAttribute("name","");
//            document.getElementById("action2").setAttribute("name","action");
//        }
//        function checkbtn1() {
//            document.getElementById("action1").setAttribute("name","action");
//            document.getElementById("action2").setAttribute("name","");
//        }
    </script>
</head>
<body>
<div class="container">
    <form action="${ctx}/uploadFileServlet?action=one" method="post"
          enctype="multipart/form-data">
        <h3>单文件上传</h3>
        <%--<input type="hidden" id="action1" name="action" value="one"/>--%>
        <div class="control-group">
            文件：<input type="text" name="fileName"/>
            <input type="file" name="fileTest"/>
        </div>
        <div class="control-group">
            <input type="submit" value="上传" />
        </div>
    </form>
    <hr/>
    <form action="${ctx}/uploadFileServlet?action=more" method="post"
          enctype="multipart/form-data">
        <h3>多文件上传</h3>
        <%--<input type="hidden" id="action" name="action" value="more"/>--%>
        <div class="control-group">
            文件1：<input type="text" name="fileName1"/>
            <input type="file" name="fileTest1"/>
        </div>
        <div class="control-group">
            文件2：<input type="text" name="fileName2"/>
            <input type="file" name="fileTest2"/>
        </div>
        <div class="control-group">
            文件3：<input type="text" name="fileName3"/>
            <input type="file" name="fileTest3"/>
        </div>
        <div class="control-group">
            <input type="submit" value="上传"/>
        </div>
    </form>
</div>
</body>
</html>
