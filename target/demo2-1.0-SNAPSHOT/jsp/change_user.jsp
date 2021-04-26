<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 11.03.2021
  Time: 21:05
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
    <!-- jQuery -->
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
    <title>Change user</title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="content">
        <div class="main">
            <br/>
            ${requestScope.messageWarning}
            <br/>
            <form method="post" action="change_user.do">
                <input type="hidden" name="command" value="change_user">
                <table class="table table-striped">
                    <tr>
                        <td>
                            E-mail
                        </td>
                        <td>
                            ${user.mail}
                        </td>
                    </tr>
                </table>
                <div class="row">
                    <label><fmt:message key="label.name"/></label>
                    <input type="text" name="name"
                           value="${user.name}"
                           pattern="([а-яА-Яa-zA-Z]+){2,30}"
                           placeholder="<fmt:message key="label.name"/>"
                           data-toggle="tooltip" data-placement="bottom"
                           title="<fmt:message key="rule.name"/>">
                    <fmt:message key="rule.name"/>
                </div>
                <div class="row">
                    <label><fmt:message key="label.surname"/></label>
                    <input type="text" name="surname" value="${user.surname}"
                           placeholder="<fmt:message key="label.surname"/>"
                           pattern="([а-яА-Яa-zA-Z]+){2,30}" required
                           data-toggle="tooltip" data-placement="bottom"
                           title=<fmt:message key="rule.name"/>>
                    <fmt:message key="rule.name"/>
                </div>
                <div class="row">
                    <label><fmt:message key="label.phone"/></label>
                    <input type="text" name="phone" value="${user.phone}"
                           placeholder="+375440000000"
                           pattern="\+375(44|33|25|29)(\d){7}" required
                           data-toggle="tooltip" data-placement="bottom"
                           title=" <fmt:message key="rule.example"/>: +375441010101">
                    <fmt:message key="rule.example"/>: +375441010101
                </div>
                <button type="submit"><fmt:message key="button.save.change"/></button>
            </form>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>