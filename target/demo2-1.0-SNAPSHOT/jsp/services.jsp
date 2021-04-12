<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 22.02.2021
  Time: 13:05
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
    <title>Services</title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="container">
        <div class="content">
            <div class="main">
                <div id="content">
                    <h3><fmt:message key="label.service"/></h3>
                    <ul class="list1">
                        <li>
                            <form method="post" action="controller">
                                <input type="hidden" name="command" value="show_category_services"/>
                                <input type="hidden" name="categoryName" value="laptop"/>
                                <img src="${pageContext.request.contextPath}/images/laptop1.jpeg" alt="laptop"/>
                                <h4><fmt:message key="label.repair.laptop"/></h4>
                                <p class="zoom"><fmt:message key="text.repair.laptop"/></p>
                                <div align="right">
                                    <button type="submit"><fmt:message key="button.read.more"/></button>
                                </div>
                            </form>
                        </li>
                        <li>
                            <form method="post" action="controller">
                                <input type="hidden" name="command" value="show_category_services"/>
                                <input type="hidden" name="categoryName" value="printer"/>
                                <img src="${pageContext.request.contextPath}/images/printer1.jpg" alt="printer"/>
                                <h4><fmt:message key="label.repair.printer"/></h4>
                                <p class="zoom"><fmt:message key="text.repair.printer"/></p>
                                <div align="right">
                                    <button type="submit"><fmt:message key="button.read.more"/></button>
                                </div>
                            </form>
                        <li>
                            <form method="post" action="controller">
                                <input type="hidden" name="command" value="show_category_services"/>
                                <input type="hidden" name="categoryName" value="ups"/>
                                <img src="${pageContext.request.contextPath}/images/ups1.jpg" alt="UPS"/>
                                <h4><fmt:message key="label.repair.ups"/></h4>
                                <p class="zoom"><fmt:message key="text.repair.ups"/></p>
                                <div align="right">
                                    <button type="submit"><fmt:message key="button.read.more"/></button>
                                </div>
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>