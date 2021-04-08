<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 17.03.2021
  Time: 18:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<jsp:useBean id="service" scope="session" class="com.klasevich.cms.model.entity.ComputerService"/>

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
    <title><fmt:message key="label.user.main"/></title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="content">
        <div class="main">
            <br/>
            ${pageScope.messageWarning}
            <br/>
            <form method="post" action="controller">
                <input type="hidden" name="command" value="change_service">
                <input type="hidden" name="serviceId" value="${pageScope.serviceId}">
                <div class="row">
                    <label><fmt:message key="label.select"/></label>
                    <select name="category">
                        <option value="${pageScope.categoryName}">"${pageScope.categoryName}"</option>
                        <option value="laptop"><fmt:message key="label.laptop"/></option>
                        <option value="printer"><fmt:message key="label.printer"/></option>
                        <option value="UPS"><fmt:message key="label.ups"/></option>
                    </select>
                </div>
                <div class="row">
                    <label><fmt:message key="label.service.name"/></label>
                    <input type="text" name="serviceName" value="${pageScope.serviceName}"
                           pattern="[а-яА-Я,a-zA-Z]+{1,125}"
                           data-toggle="tooltip" data-placement="bottom"
                           title="<fmt:message key="rule.text"/>">
                    <fmt:message key="rule.text"/>
                </div>
                <div class="row">
                    <label><fmt:message key="label.service.price"/></label>
                    <input type="text" name="servicePrice" value="${pageScope.servicePrice}"
                           pattern="(\d+){1,3}\.?(\d){0,2}?"
                           data-toggle="tooltip"
                           title="<fmt:message key="rule.price"/>" data-placement="bottom">
                    <fmt:message key="rule.price"/>
                </div>
                <button type="submit"><fmt:message key="button.save.change"/></button>
            </form>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>
