<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 29.03.2021
  Time: 16:56
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/xcal.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xcal.js"></script>
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
        <table class="table-responsive">
            <tr>
                <td>
                    <c:if test="${requestScope.pageNumber>1}">
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="find_orders_after_date"/>
                            <input type="hidden" name="date" value="${requestScope.date}"/>
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
                            <input type="hidden" name="command" value="find_orders_after_date"/>
                            <input type="hidden" name="date" value="${requestScope.date}"/>
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
