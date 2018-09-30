<%--
  Created by IntelliJ IDEA.
  User: acer
  Date: 22.09.2018
  Time: 13:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<header>
    <spring:message code="author"/> ${author}
    <select onchange="location = this.value;">
        <option value="${pageContext.request.contextPath}?lang=ru">Ru</option>
        <option value="${pageContext.request.contextPath}?lang=en">En</option>
    </select>
    <a href="?lang=ru">Ru</a><a href="?lang=en">En</a>
</header>

<form method="post" action="/">
    <p><b><spring:message code="input.header"/></b><br/>
        <input name="birthDate" type="text"/>
    </p>
    <spring:message code="submit" var="submitText"/>
    <p><input type="submit" value="${submitText}"></p>
</form>
<p>
    <c:if test="${birthdate != null}">
        <spring:message code="input.birth.date"/> ${birthdate.day}/${birthdate.month}/${birthdate.year}<br/>
    </c:if>
    <c:if test="${age != null}">
        <spring:message code="input.age"/> ${age}<br/>
    </c:if>
    <c:if test="${daysUntilNextBirthday != null}">
        ${daysUntilNextBirthday} <spring:message code="input.days"/> <br/>
    </c:if>
    <c:if test="${error != null}">
        ${error}<br/>
    </c:if>
</p>
<footer><spring:message code="app.version"/> ${version}</footer>
</body>
</html>