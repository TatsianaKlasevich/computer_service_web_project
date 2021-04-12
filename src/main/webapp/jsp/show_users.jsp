<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24.03.2021
  Time: 11:54
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
    <title>Show users</title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="content">
        <table class="table-responsive">
            <tr>
                <td>
                    <c:if test="${requestScope.pageNumber>1}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="show_users"/>
                            <input type="hidden" name="pageNumber" value="${requestScope.pageNumber-1}">
                            <button type="submit"><fmt:message key="button.previous"/></button>
                        </form>
                    </c:if>
                </td>
                <td>
                    <a href=#>${requestScope.pageNumber}</a>
                </td>
                <td>
                    <c:if test="${!requestScope.isLastPage}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="show_users"/>
                            <input type="hidden" name="pageNumber" value="${requestScope.pageNumber+1}">
                            <button type="submit"><fmt:message key="button.next"/></button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </table>
    </div>
    <div class="main">
        <div class="mobile">
            <table class="bordered">
                <thead>
                <tr>
                    <th><fmt:message key="label.user.name"/></th>
                    <th><fmt:message key="label.user.surname"/></th>
                    <th><fmt:message key="label.user.mail"/></th>
                    <th><fmt:message key="label.user.phone"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.users}" var="user">
                    <tr>
                        <td> ${user.name}</td>
                        <td> ${user.surname}</td>
                        <td> ${user.mail}</td>
                        <td> ${user.phone}</td>
                        <td>
                            <form name="update service" method="post" action="controller">
                                <input type="hidden" name="command" value="remove_user">
                                <input type="hidden" name="parameter" value="${requestScope.parameter}">
                                <input type="hidden" name="pageNumber" value="${requestScope.pageNumber}">
                                <input type="hidden" name="userId" value="${user.id}">
                                <button type="submit"><fmt:message key="button.remove"/></button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>

