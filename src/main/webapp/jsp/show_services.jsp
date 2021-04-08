<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 17.03.2021
  Time: 11:39
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
        <div align="centre">
        <table class="table-responsive">
            <tr>
                <td>
                    <c:if test="${requestScope.pageNumber>1}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="show_services"/>
                            <input type="hidden" name="pageNumber" value="${requestScope.pageNumber-1}">
                            <button type="submit"><fmt:message key="button.previous"/></button>
                        </form>
                    </c:if>
                </td>
                <td>
                    <a href=# >${requestScope.pageNumber}</a>
                </td>
                <td>
                    <c:if test="${!requestScope.isLastPage}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="show_services"/>
                            <input type="hidden" name="pageNumber" value="${requestScope.pageNumber+1}">
                            <button type="submit"><fmt:message key="button.next"/></button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </table>
        </div>
    </div>
        <div class="main">
            <div class="mobile">
                <table class="bordered">
                    <thead>
                    <tr>
                        <th><fmt:message key="label.service.id"/></th>
                        <th><fmt:message key="label.service.category"/></th>
                        <th><fmt:message key="label.service.name"/></th>
                        <th><fmt:message key="label.service.price"/></th>
                        <th><fmt:message key="label.service.action"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.computerServices}" var="service">
                        <tr>
                            <td> ${service.id}</td>
                            <td> ${service.category}</td>
                            <td> ${service.serviceName}</td>
                            <td> ${service.price}</td>
                            <td>
                                <form method="post" action="controller">
                                    <input type="hidden" name="command" value="to_change_service">
                                    <input type="hidden" name="serviceId" value="${service.id}">
                                    <button type="submit"><fmt:message key="button.edit"/></button>
                                </form>
                                <form method="post" action="controller">
                                    <input type="hidden" name="command" value="remove_service">
                                    <input type="hidden" name="serviceId" value="${service.id}">
                                    <button type="submit"><fmt:message key="button.remove"/></button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>
