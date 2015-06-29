<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<link href="/css/styles.css" rel="stylesheet" type="text/css">
<head>
    <title><fmt:message key="request.title"/></title>
</head>
<body>
<header>
    <a class="logotype" href="/index.jsp">
        <img src="img/logo.png" width="60px" height="60px"/>
    </a>

    <div class="welcome"><fmt:message key="request.header"/></div>

    <ul>
        <li><a href="/localization?locale=uk-UA"><img src="img/ua.png"/></a></li>
        <li><a href="/localization?locale=en-GB"><img src="img/uk.png"/></a></li>
    </ul>

    <div class="logoutform">
        <c:if test='${not empty pageContext.session.getAttribute("user")}'>
            <div>${pageContext.session.getAttribute("user")}</div>
            <a class="logout" href="/logout"><fmt:message key="index.logout"/></a>
        </c:if>
    </div>
</header>

<div>
<p class="request"><fmt:message key="request.form"/></p>
<form id="make-request-form" name="make-request-form" method="post" action="/makerequestservlet">
    <input type="text" id="name" name="name" placeholder='<fmt:message key="request.name"/>'>
    <br>
    <input type="text" id="surname" name="surname" placeholder='<fmt:message key="request.surname"/>'>
    <br>
    <label for="gender" name="gender"><fmt:message key="request.gender"/></label>
    <select id="gender" name="gender">
        <option value="Male" selected="true">
            <fmt:message key="request.genderM"/>
        </option>
        <option value="Female">
            <fmt:message key="request.genderF"/>
        </option>
    </select>
    <br>
    <input type="text" id="passportId" name="passportId" placeholder='<fmt:message key="request.passport"/>'>
    <br>
    <input type="email" id="email" name="email" placeholder='<fmt:message key="request.email"/>'><br>
    <label for="guests"><fmt:message key="request.guests"/></label>
    <select id="guests" name="guests">
        <option value="1" selected="true">
            1
        </option>
        <option value="2">
            2
        </option>
        <option value="3">
            3
        </option>
        <option value="4">
            4
        </option>
    </select>
    <br>
    <input type="datetime" id="check-in-date" name="check-in-date" placeholder='<fmt:message key="request.checkIn"/>'>
    <br>
    <input type="datetime" id="check-out-date" name="check-out-date" placeholder='<fmt:message key="request.checkOut"/>'>
    <br>
    <button type="submit" id=submit" name="submit"><fmt:message key="request.submit"/></button>
</form>
</div>
</body>
</html>
