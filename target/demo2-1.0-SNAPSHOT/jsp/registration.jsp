<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 13.02.2021
  Time: 19:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media-queries.css" type="text/css">
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <script>
        $(document).ready(function () {
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
    <title>Registration</title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="container">
        <div class="content">
            <div class="leftCol">
                <ul class="leftNav">
                </ul>
            </div>
            <div align="centre">
                <div class="main">
                    <p>
                    <h1><fmt:message key="label.registration"/></h1>
                    <br/>
                    ${requestScope.messageWarning}
                    <br/>
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="register">
                        <div class="row">
                            <label><fmt:message key="label.name"/></label>
                            <input type="text" name="name"
                                   value="${requestScope.name}"
                                   pattern="([а-яА-Яa-zA-Z]+){2,30}"
                                   placeholder="<fmt:message key="label.name"/>"
                                   data-toggle="tooltip" data-placement="bottom"
                                   title="<fmt:message key="rule.name"/>">
                            <fmt:message key="rule.name"/>
                        </div>
                        <div class="row">
                            <label><fmt:message key="label.surname"/></label>
                            <input type="text" name="surname" value="${requestScope.surname}"
                                   placeholder="<fmt:message key="label.surname"/>"
                                   pattern="([а-яА-Яa-zA-Z]+){2,30}" required
                                   data-toggle="tooltip" data-placement="bottom"
                                   title=<fmt:message key="rule.name"/>>
                            <fmt:message key="rule.name"/>
                        </div>
                        <div class="row">
                            <label>e-mail*:</label>
                            <input type="text" name="email" value="${requestScope.email}"
                                   placeholder="email@mail.com"
                                   pattern="([-\w]+){4,30}@[a-zA-Z]+\.[a-zA-Z]{2,3}" required
                                   data-toggle="tooltip" data-placement="bottom"
                                   title="<fmt:message key="rule.email"/>">
                            <fmt:message key="rule.email"/>
                        </div>
                        <div class="row">
                            <label><fmt:message key="label.phone"/></label>
                            <input type="text" name="phone" value="${requestScope.phone}"
                                   placeholder="+375440000000"
                                   pattern="\+375(44|33|25|29)(\d){7}" required
                                   data-toggle="tooltip" data-placement="bottom"
                                   title=" <fmt:message key="rule.example"/>: +375441010101">
                            <fmt:message key="rule.example"/>: +375441010101
                        </div>
                        <div class="row">
                            <label><fmt:message key="label.password"/></label>
                            <input id="password" type="password" name="password" value="${requestScope.password}"
                                   pattern="(\w+){8,20"
                                   onchange="this.setCustomValidity(this.validity.patternMismatch ? '<fmt:message
                                           key="rule.password"/>' : ''); if(this.checkValidity()) form.password_two.pattern = this.value;"
                                   placeholder="Password" required>
                        </div>
                        <div class="row">
                            <label><fmt:message key="label.repeat.password"/></label>
                            <input id="password_two" type="password" name="password_two"
                                   pattern="(\w+){8,20}"
                                   onchange="this.setCustomValidity(this.validity.patternMismatch ? '<fmt:message
                                           key="rule.repeat.password"/>' : '');"
                                   placeholder="Password" required>
                        </div>
                        <div class="row">
                            <button type="submit"><fmt:message key="button.register"/></button>
                        </div>
                        <br/>
                        <p style="color: white"> ${requestScope.messageWarning}</p>
                        <br/>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>

