<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zakre
  Date: 20.10.18
  Time: 15.10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${listUsers}" var="user">
    <div>
        <c:out value="${user.toString()}"/><br/>
    </div>
    <br/>
</c:forEach>
</body>
</html>
