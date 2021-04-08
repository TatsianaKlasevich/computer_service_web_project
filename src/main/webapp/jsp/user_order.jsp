<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 12.03.2021
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="readyStatus" scope="request" value="READY"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media-queries.css" type="text/css">
    <title><fmt:message key="label.service.table"/></title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="container">
        <div class="content">
            <%@ include file="/jsp/segment/links_user.jsp" %>
            <div class="main">
                <br/>
                ${requestScope.messageWarning}
                <br/>
                <c:forEach items="${requestScope.orders}" var="order">
                    <a href="${pageContext.request.contextPath}/controller?command=show_user_order&order_id=${order.id}">
                        <h3>Order №${order.id} from ${order.applianceDate}</h3></a>
                    <br/>
                    <c:if test="${order.status.toString().equals(readyStatus)}">
                        <h3><p style="color: red">Order №${order.id} is READY! </p></h3>
                    </c:if>
                </c:forEach>
                    <br/>
            </div>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>
