<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24.02.2021
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<jsp:useBean id="user" scope="session" class="com.klasevich.cms.model.entity.User"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media-queries.css" type="text/css">
    <title>Profile</title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="container">
        <div class="content">
            <c:if test="${role.toString().equals(userRole)}">
                <%@ include file="/jsp/segment/links_user.jsp" %>
            </c:if>
            <c:if test="${role.toString().equals(adminRole)}">
                <%@ include file="/jsp/segment/links_admin.jsp" %>
            </c:if>
            <div class="main">
                <div class="col">
                    <h2><fmt:message key="label.upload.avatar"/></h2>
                    <br/>
                    ${requestScope.messageWarning}
                    <br/>
                    <img src=${pageContext.request.contextPath}/controller?command=take_avatar&file_name=${user.avatar}
                         alt="${user.name}"
                    <br/>
                    <br/>
                    <form action="upload" enctype="multipart/form-data" method="POST">
                        <INPUT TYPE="file" name="content" height="130">
                        <button type="submit"><fmt:message key="button.upload.file"/></button>
                    </form>
                </div>
                <div class="col">
                    <table class="table table-striped">
                        <tr>
                            <td>
                                <fmt:message key="label.user.name"/>Name
                            </td>
                            <td>
                                ${user.name}
                            </td>
                        </tr>
                        <br/>
                        <tr>
                            <td>
                                <fmt:message key="label.user.surname"/>
                            </td>
                            <td>
                                ${user.surname}
                            </td>
                        </tr>
                        <br/>
                        <tr>
                            <td>
                                E-mail
                            </td>
                            <td>
                                ${user.mail}
                            </td>
                        </tr>
                        <br/>
                        <tr>
                            <td>
                                <fmt:message key="label.user.phone"/>
                            </td>
                            <td>
                                ${user.phone}
                            </td>
                        </tr>
                    </table>
                    <br/>
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="to_change_user">
                        <button type="submit"><fmt:message key="button.change"/></button>
                    </form>
                    <br/>
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="to_change_password">
                        <button type="submit"><fmt:message key="button.change.password"/></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>