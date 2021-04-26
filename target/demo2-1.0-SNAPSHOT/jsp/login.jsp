<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 27.12.2020
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="resource.pagecontent"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css" media="screen">
    <title>Log in</title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="container">
        <div class="content">
            <div class="main">
                <div id="login">
                    <br/>
                    <p style="color: white">
                    <h3>${requestScope.name} ${requestScope.surname}${requestScope.welcomePage}</h3></p>
                    <br/>
                    <p style="color: white"> ${requestScope.messageWarning}</p>
                    <br/>
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="login"/>
                        <fieldset class="clearfix">
                            <p><span class="fontawesome-user"></span>
                                <input type="text" name="email" value="email@mail.com"
                                       pattern="([-\w]+){4,30}@[a-zA-Z]+\.[a-zA-Z]{2,3}">
                            <p><span class="fontawesome-lock"></span>
                                <input id="password" type="password" name="password" value="password"
                                       pattern="([\w\p{Punct}]+){8,20}">
                            <p><input type="submit" value=<fmt:message key="button.login"/>></p>
                        </fieldset>
                    </form>
                    <p><fmt:message key="label.no.account"/> &nbsp;&nbsp;<a
                            href="${pageContext.request.contextPath}/controller?command=to_registration"><fmt:message
                            key="button.register"/></a><span class="fontawesome-arrow-right"></span></p>

                </div>
            </div>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>
