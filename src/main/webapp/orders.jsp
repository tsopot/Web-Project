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
        <jsp:include page="/listordersservlet" flush="true" />

        <h1><fmt:message key="orders.header"/></h1>
        <table>
          <tr>
            <th><fmt:message key="orders.room"/></th>
            <th><fmt:message key="orders.checkIn"/></th>
            <th><fmt:message key="orders.checkOut"/></th>
            <th><fmt:message key="orders.price"/></th>
          </tr>
          <c:forEach items="${requestScope.ordersList}" var="order">
            <tr>
              <td>${order.apartmentId}</td>
              <td>${order.checkInDate}</td>
              <td>${order.checkOutDate}</td>
              <td>${order.price}</td>
            </tr>
          </c:forEach>
        </table>
  </body>
</html>
