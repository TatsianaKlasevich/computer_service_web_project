<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 22.12.2020
  Time: 13:20
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/css3-mediaqueries.js"></script>
    <title>Main</title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="container">
        <div class="content">
            <div class="main">
                <br/><br/><br/>
                <div align="center"><h1><a href> <span class="gray"><fmt:message
                        key="label.computer.maintenance.service"/> KL-service.com</span></a></h1></div>
                <br><br><br/>
                <div align="center">
                    <h3><p style="color: purple"><fmt:message key="label.scheme"/></p></h3>
                </div>
                <br><br><br/>
                <table class="table table-striped">
                    <tr>
                        <td>
                            <img src="${pageContext.request.contextPath}/images/phone.png"
                                 alt="">
                        </td>
                        <td>
                            <img src="${pageContext.request.contextPath}/images/time.png"
                                 alt="">
                        </td>
                        <td>
                            <img src="${pageContext.request.contextPath}/images/delivery.jpg"
                                 alt="">
                        </td>
                        <td>
                            <img src="${pageContext.request.contextPath}/images/diagnostics.png"
                                 alt="">
                        </td>
                        <td>
                            <img src="${pageContext.request.contextPath}/images/hands.png"
                                 alt="">
                        </td>
                        <td>
                            <img src="${pageContext.request.contextPath}/images/unloading.jpg"
                                 alt="">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <h3><p style="color: purple"><fmt:message key="label.get.order"/></p></h3>
                        </td>
                        <td>
                            <h3><p style="color: purple"><fmt:message key="label.time"/></p></h3>
                        </td>
                        <td>
                            <h3><p style="color: purple"><fmt:message key="label.courier"/></p></h3>
                        </td>
                        <td>
                            <h3><p style="color: purple"><fmt:message key="label.diagnostics"/></p></h3>
                        </td>
                        <td>
                            <h3><p style="color: purple"><fmt:message key="label.work"/></p></h3>
                        </td>
                        <td>
                            <h3><p style="color: purple"><fmt:message key="label.delivery"/></p></h3>
                        </td>
                    </tr>
                    <tr>
                        <td><fmt:message key="text.get.order"/></td>
                        <td><fmt:message key="text.time"/></td>
                        <td><fmt:message key="text.courier"/></td>
                        <td><fmt:message key="text.diagnostics"/></td>
                        <td><fmt:message key="text.work"/></td>
                        <td><fmt:message key="text.delivery"/></td>
                    </tr>
                </table>
                <br/><br/><br/><br/><br/>
            </div>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>