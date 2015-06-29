<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <link href='<c:url value="/css/incorrect.css"/>' rel="stylesheet" type="text/css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="incorrect.title"/></title>
    </head>
    <body>
        <h1><fmt:message key="incorrect.header"/></h1>
        <form id="form-login" name="login" method="post" action="/login">
            <input type="text" id="user" name="user" placeholder='<fmt:message key="incorrect.user"/>'>
            <br>
            <input class="second" type="password" id="password" name="password" placeholder='<fmt:message key="incorrect.password"/>'>
            <br>
            <button type="submit" id="submit" name="submit"><fmt:message key="incorrect.signIn"/></button>
        </form>
    </body>
</html>
