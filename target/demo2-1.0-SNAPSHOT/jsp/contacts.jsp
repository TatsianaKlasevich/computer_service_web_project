<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 21.02.2021
  Time: 14:43
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
    <title>Contacts</title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="container">
        <div class="content">
            <div class="main">
                <div align="center"><h1><a href> <span class="gray"><fmt:message key="label.find"/></span></a></h1>
                </div>
                <div class="leftCol">
                    <ul class="leftNav">
                        <li>
                            <a href><fmt:message key="label.minsk"/></a></li>
                        <li><a href> <fmt:message key="label.service.address"/></a></li>
                        <li><a href> +375 29 100 00 01</a></li>
                        <li><a href> +375 44 100 00 01</a></li>
                        <li><a href> +375 25 100 00 01</a></li>
                        <li><a href> info@kl-service.com</a></li>
                        <li><a href> <fmt:message key="label.working.hours"/> 10.00-18.00</a></li>
                    </ul>
                </div>
                <div class="map-location">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d37636.08848989599!2d27.571635!3d53
                    .873885!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x6c393429d24e33b5!
                    2z0KfQo9CfICLQkNGA0LjRgdGC0L4t0LjQvdCy0LXRgdGCIg!5e0!3m2!1sru!2sby!4v1615397910306!5m2!1sru!2sby"
                            width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>
