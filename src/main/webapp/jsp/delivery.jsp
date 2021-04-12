<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 02.04.2021
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media-queries.css" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/css3-mediaqueries.js"></script>
    <title>Delivery</title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="container">
        <div class="content">
            <div class="main">
                <h1><p><fmt:message key="label.courier.minsk"/></p></h1>
                <p><fmt:message key="label.courier.1"/></p>
                <p><fmt:message key="label.courier.2"/></p>
                <h1><p><fmt:message key="label.delivery.scheme"/></p></h1>
                <div class="row">
                    <div class="col">
                        <ul>
                            <li><fmt:message key="label.delivery.1"/></li>
                            <li><fmt:message key="label.delivery.2"/></li>
                            <li><fmt:message key="label.delivery.3"/></li>
                            <li><fmt:message key="label.delivery.4"/></li>
                            <li><fmt:message key="label.delivery.5"/></li>
                            <li><fmt:message key="label.delivery.6"/></li>
                            <li><fmt:message key="label.delivery.7"/></li>
                            <li><fmt:message key="label.delivery.8"/></li>
                            <li><fmt:message key="label.delivery.9"/></li>
                            <li><fmt:message key="label.delivery.10"/></li>
                            <li><fmt:message key="label.delivery.11"/></li>
                        </ul>
                        <h1><p><fmt:message key="label.pay.order"/></p></h1>
                        <ul>
                            <li><fmt:message key="label.pay.1"/></li>
                            <li><fmt:message key="label.pay.2"/></li>
                            <li><fmt:message key="label.pay.3"/></li>
                            <li><fmt:message key="label.pay.4"/></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>
