<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test='${not pageContext.session.getAttribute("user").equals("admin")}'>
    <jsp:forward page="incorrect.jsp"/>
</c:if>

<html>
<link href="/css/styles.css" rel="stylesheet" type="text/css">
<head>
    <title><fmt:message key="admin.title"/></title>
</head>
<body>
<header>
    <a class="logotype" href="/index.jsp">
        <img src="img/logo.png" width="60px" height="60px"/>
    </a>

    <div class="welcome"><fmt:message key="admin.title"/></div>

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

<jsp:include page="/listrequestsservlet" flush="true"/>

        <h1><fmt:message key="admin.header"/></h1>
            <table>
            <tr>
                <th><fmt:message key="admin.name"/></th>
                <th><fmt:message key="admin.surname"/></th>
                <th><fmt:message key="admin.gender"/></th>
                <th><fmt:message key="admin.passport"/></th>
                <th><fmt:message key="admin.email"/></th>
                <th><fmt:message key="admin.guests"/></th>
                <th><fmt:message key="admin.checkIn"/></th>
                <th><fmt:message key="admin.checkOut"/></th>
                <th><fmt:message key="admin.room"/></th>
            </tr>
            <c:forEach items="${requestScope.requestsList}" var="request">
                <tr>
                    <td>${request.customer.name}</td>
                    <td>${request.customer.surname}</td>
                    <td>${request.customer.gender}</td>
                    <td>${request.customer.passportId}</td>
                    <td>${request.customer.email}</td>
                    <td>${request.guests}</td>
                    <td>${request.checkInDate}</td>
                    <td>${request.checkOutDate}</td>
                    <td><a href="/chooseapartment.jsp?reqId=${request.id}"><fmt:message key="admin.select"/></a></td>
                </tr>
            </c:forEach>
            </table>

        <a href="/orders.jsp"><fmt:message key="admin.orders"/></a>
    </body>
</html>
