<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 29.03.2021
  Time: 16:19
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
        <table class="table-responsive">
            <tr>
                <td>
                    <c:if test="${requestScope.pageNumber>1}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="find_orders_by_category"/>
                            <input type="hidden" name="categoryName" value="${requestScope.categoryName}"/>
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
                            <input type="hidden" name="command" value="find_orders_by_category"/>
                            <input type="hidden" name="categoryName" value="${requestScope.categoryName}"/>
                            <input type="hidden" name="pageNumber" value="${requestScope.pageNumber+1}">
                            <button type="submit"><fmt:message key="button.next"/></button>
                        </form>
                    </c:if>
                </td>
            </tr>
        </table>
    </div>
    <%@ include file="/jspf/orders_table.jspf" %>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>
