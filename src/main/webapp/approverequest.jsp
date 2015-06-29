<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<link href="/css/styles.css" rel="stylesheet" type="text/css">
<head>
    <title><fmt:message key="approve.title"/></title>
</head>
<body>
<header>
    <a class="logotype" href="/index.jsp">
        <img src="img/logo.png" width="60px" height="60px"/>
    </a>

    <div class="welcome"><fmt:message key="approve.ty"/></div>

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

    <div class="approve">
        <h1><fmt:message key="approve.header"/></h1>
        <img src="img/approve.png" width="370" height="400">
        <br>
        <a href="index.jsp"><strong><fmt:message key="approve.home"/></strong></a>
    </div>
</body>
</html>
