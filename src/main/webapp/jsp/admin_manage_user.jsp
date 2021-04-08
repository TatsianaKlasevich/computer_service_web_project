<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24.03.2021
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<html>
<head>
    <title><fmt:message key="label.user.main"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media-queries.css" type="text/css">
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="content">
        <%@ include file="/jsp/segment/links_admin.jsp" %>
        <div class="container">
            <div class="content">
                <div class="main">
                    <form method="get" action="controller">
                        <input type="hidden" name="command" value="find_user_by_parameter">
                        <div class="row">
                            <label><fmt:message key="find.user.by.parameter"/>:</label>
                            <input type="text" name="parameter">
                            <button type="submit"><fmt:message key="button.search"/></button>
                        </div>
                    </form>
                    <br/>
                    <div class="main">
                        <div class="col">
                            <br/>
                            ${requestScope.messageWarning}
                            <br/>
                            <br/>
                            <form method="get" action="controller">
                                <input type="hidden" name="command" value="show_users">
                                <button type="submit"><fmt:message key="button.show.users"/></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <%@ include file="/jsp/segment/footer.jsp" %>
        </div>
    </div>
</div>
</body>
</html>
