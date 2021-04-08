<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 05.04.2021
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media-queries.css" type="text/css">
    <title><fmt:message key="label.user.main"/></title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="content">
        <div class="main">
            <br/>
            ${requestScope.messageWarning}
            <br/>
            <form method="post" action="controller">
                <input type="hidden" name="command" value="change_password">
                <div class="row">
                    <label><fmt:message key="label.password.old"/></label>
                    <input type="password" name="password"
                           pattern="(\w+){8,15}"
                           placeholder="Password" required>
                </div>
                <div class="row">
                    <label><fmt:message key="label.password.new"/></label>
                    <input id="password" type="password" name="password_new"
                           pattern="(\w+){8,15}"
                           onchange="this.setCustomValidity(this.validity.patternMismatch ? '<fmt:message
                                   key="rule.password"/>' : ''); if(this.checkValidity()) form.password_two.pattern = this.value;"
                           placeholder="Password" required>
                </div>
                <div class="row">
                    <label><fmt:message key="label.password.repeat.new"/></label>
                    <input id="password_two" type="password" name="password_two"
                           pattern="(\w+){8,20}"
                           onchange="this.setCustomValidity(this.validity.patternMismatch ? '<fmt:message
                                   key="rule.repeat.password"/>' : '');"
                           placeholder="Password" required>
                </div>
                <button type="submit"><fmt:message key="button.save.change"/></button>
            </form>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>
