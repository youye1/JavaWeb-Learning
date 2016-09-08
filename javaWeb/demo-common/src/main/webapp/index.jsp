<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/includes/taglib.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<h3>Hello World!</h3>
<div class="container">
    <ul>
        <li><a href="${ctx}/gotoPage?pageName='file/ImgJcropTest'">裁剪图片测试</a>
        </li>
    </ul>
    <h4>国际化测试</h4>
    <ul>
        <li><a href="${ctx}/gotoPage?pageName='i18n/globalTest'">国际化测试1</a></li>
        <li><a href="${ctx}/gotoPage?pageName='i18n/globalTest2'">国际化测试2</a>
        </li>
        <li><a href="${ctx}/gotoPage?pageName='i18n/globalTest3'">国际化测试3</a>
        </li>
    </ul>
    <h4>图片验证码测试</h4>
    <ul>
        <li><a href="${ctx}/gotoPage?pageName='imgValidate/imgtest1'">测试图片验证码</a></li>
    </ul>
</div>
</body>
</html>
