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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/xcal.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/xcal.js"></script>
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
                        <br/>
                        <br/>
                        <form method="get" action="controller">
                            <input type="hidden" name="command" value="find_order_by_id">
                            <div class="row">
                                <label><fmt:message key="find.order.by.id"/>:</label>
                                <input type="text" name="order_id">
                                <button type="submit"><fmt:message key="button.search"/></button>
                            </div>
                        </form>
                        <form method="get" action="controller">
                            <input type="hidden" name="command" value="find_orders_by_category">
                            <div class="row">
                                <label><fmt:message key="find.orders.by.category"/>:</label>
                                <select name="categoryName">
                                    <option value="laptop"><fmt:message key="label.laptop"/></option>
                                    <option value="printer"><fmt:message key="label.printer"/></option>
                                    <option value="UPS"><fmt:message key="label.ups"/></option>
                                </select>
                                <button type="submit"><fmt:message key="button.search"/></button>
                            </div>
                        </form>
                        <form method="get" action="controller">
                            <input type="hidden" name="command" value="find_orders_by_status">
                            <div class="row">
                                <label><fmt:message key="find.orders.by.status"/>:</label>
                                <select name="status">
                                    <option value="checking"><fmt:message key="label.checking"/></option>
                                    <option value="working"><fmt:message key="label.working"/></option>
                                    <option value="ready"><fmt:message key="label.ready"/></option>
                                    <option value="cancelled"><fmt:message key="label.cancelled"/></option>
                                </select>
                                <button type="submit"><fmt:message key="button.search"/></button>
                            </div>
                        </form>
                        <form method="get" action="controller">
                            <input type="hidden" name="command" value="find_orders_after_date">
                            <div class="row">
                                <label><fmt:message key="find.orders.after.date"/>:</label>
                                <input id="date3" type="text" name=date value="${requestScope.date}" size="10"
                                       pattern="[2][\d]{3}\.(0[1-9]|1[012])\.(0[1-9]|1[0-9]|2[0-9]|3[01])"
                                       onClick="xCal()" onKeyUp="xCal()" oninput="xCal() ">
                                <a href="" onClick="xCal('date3'); return false"><fmt:message key="open.calendar"/></a>,
                                <a href=""
                                   onClick="document.getElementById('date3').value=''; return false"><fmt:message
                                        key="clean.field"/></a>,
                                <a href=""
                                   onClick="document.getElementById('date3').value=xCal(2); return false"><fmt:message
                                        key="current.date"/></a>
                            </div>
                            <div class="row">
                                <button type="submit"><fmt:message key="button.search"/></button>
                            </div>
                        </form>
                    </div>
                    <div class="col">
                        <br/>
                        ${requestScope.messageWarning}
                        <br/>
                        <form method="get" action="controller">
                            <input type="hidden" name="command" value="show_orders">
                            <button type="submit"><fmt:message key="button.show.orders"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>
