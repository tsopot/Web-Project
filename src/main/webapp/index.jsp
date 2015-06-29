<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<link href="/css/styles.css" rel="stylesheet" type="text/css">
<head>
    <title><fmt:message key="index.title"/></title>
</head>
<body>
<header>
    <a class="logotype" href="/index.jsp">
        <img src="img/logo.png" width="60px" height="60px"/>
    </a>

    <div class="welcome"><fmt:message key="index.welcome"/></div>

    <nav>
    <ul>
        <li><a href="/localization?locale=uk-UA"><img src="img/ua.png"/></a></li>
        <li><a href="/localization?locale=en-GB"><img src="img/uk.png"/></a></li>
    </ul>
    </nav>

    <div class="logoutform">
        <c:if test='${not empty pageContext.session.getAttribute("user")}'>
            <div>${pageContext.session.getAttribute("user")}</div>
            <a class="logout" href="/logout"><fmt:message key="index.logout"/></a>
        </c:if>
    </div>
</header>

<img src="img/hotel_pic.jpg" class="picture">
<ul>
    <li><a href="/makerequest.jsp"><strong><fmt:message key="index.reservation"/></strong></a></li>
    <li><a href="/administrator.jsp"><strong><fmt:message key="index.admin"/></strong></a></li>
</ul>
</body>
</html>