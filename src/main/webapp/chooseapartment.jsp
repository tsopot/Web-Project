<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test='${not pageContext.session.getAttribute("user").equals("admin")}'>
    <jsp:forward page="incorrect.jsp"/>
</c:if>

<html>
<link href="/css/styles.css" rel="stylesheet" type="text/css">
<head>
    <title><fmt:message key="room.title"/></title>
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

      <h1><fmt:message key="room.header"/></h1>

      <jsp:include page="/listapartmentsservlet" flush="true" />

      <form id="apSelect" name="apSelect" method="post" action="/makeorder">
        <input type="hidden" name="requestId" value=${param["reqId"]}>
        <table>
          <tr>
            <th></th>
            <th><fmt:message key="room.id"/></th>
            <th><fmt:message key="room.type"/></th>
            <th><fmt:message key="room.wifi"/></th>
            <th><fmt:message key="room.tv"/></th>
            <th><fmt:message key="room.air"/></th>
            <th><fmt:message key="room.view"/></th>
            <th><fmt:message key="room.price"/></th>
          </tr>
          <c:forEach items="${requestScope.apartmentsList}" var="app">
            <tr>
              <td><input type="radio" name="apartmentId" value=${app.id}></td>
              <td>${app.id}</td>
              <td>${app.type}</td>
              <td>${app.wifi}</td>
              <td>${app.tvSet}</td>
              <td>${app.airCondition}</td>
              <td>${app.view}</td>
              <td>${app.pricePerNight}</td>
            </tr>
          </c:forEach>
        </table>
        <button type="submit" name="submit"><fmt:message key="room.submit"/></button>
      </form>
</body>
</html>
