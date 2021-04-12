<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 16.03.2021
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>

<html>
<head>
    <title>Manage service</title>
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
                    <div class="col">
                        <h2><fmt:message key="label.add.service"/></h2>
                        <form method="post" action="controller">
                            <input type="hidden" name="command" value="add_service">
                            <div class="row">
                                <label><fmt:message key="label.select"/></label>
                                <select name="categoryName">
                                    <option value="laptop"><fmt:message key="label.laptop"/></option>
                                    <option value="printer"><fmt:message key="label.printer"/></option>
                                    <option value="UPS"><fmt:message key="label.ups"/></option>
                                </select>
                            </div>
                            <div class="row">
                                <label><fmt:message key="edit.service.name"/></label>
                                <input type="text" name=serviceName value="${requestScope.serviceName}"
                                       pattern="[а-яА-Я,a-zA-Z]+{1,125}"
                                       placeholder="<fmt:message key="label.service.name"/>"
                                       data-toggle="tooltip" data-placement="bottom"
                                       title="<fmt:message key="rule.text"/>">
                            </div>
                            <div class="row">
                                <label><fmt:message key="label.price"/></label>
                                <input type="text" name=servicePrice value="${requestScope.servicePrice}"
                                       placeholder="00.00"
                                       pattern="(\d+){1,3}\.?(\d){0,2}?"
                                       data-toggle="tooltip"
                                       title="<fmt:message key="rule.price"/>" data-placement="bottom">
                            </div>
                            <div class="row">
                                <button type="submit"><fmt:message key="button.add"/></button>
                            </div>
                            <br/>
                            ${requestScope.messageWarning}
                            <br/>
                        </form>
                    </div>
                    <div class="main">
                        <div class="col">
                            <br/>
                            <br/>
                            <br/>
                            <form method="post" action="controller">
                                <input type="hidden" name="command" value="show_services">
                                <button type="submit"><fmt:message key="button.show.services"/></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>