<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 29.03.2021
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<jsp:useBean id="order" scope="request" class="com.klasevich.cms.model.entity.Order"/>

<html>
<head>
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
    <title><fmt:message key="label.service.table"/></title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="container">
        <div class="content">
            <%@ include file="/jsp/segment/links_admin.jsp" %>
            <div class="main">
                <br/>
                ${requestScope.messageWarning}
                <br/>
                <table class="table table-striped">
                    <tr>
                        <td>
                            <fmt:message key="order.id"/>
                        </td>
                        <td>
                            ${order.id}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="label.order.appliance.date"/>
                        </td>
                        <td>
                            ${order.applianceDate}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="label.user.name.and.surname"/>
                        </td>
                        <td>
                            ${order.user.name}&nbsp;${order.user.surname}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="label.user.phone"/>
                        </td>
                        <td>
                            ${order.user.phone}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="order.address"/>
                        </td>
                        <td>
                            ${order.address}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="label.service.category"/>
                        </td>
                        <td>
                            ${order.category}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="label.order.problem"/>
                        </td>
                        <td>
                            ${order.problem}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <fmt:message key="label.order.has.discount"/>
                        </td>
                        <td>
                            ${requestScope.discount}
                        </td>
                    </tr>
                </table>
                <form method="post" action="controller">
                    <input type="hidden" name="command" value="change_admin_order">
                    <input type="hidden" name="order_id" value="${order.id}">
                    <div class="row">
                        <label><fmt:message key="label.change.issue.date"/></label>
                        <input id="date3" type="text" name=date value="${order.issueDate}" size="10"
                               pattern="[2][\d]{3}[\.-](0[1-9]|1[012])[\.-](0[1-9]|1[0-9]|2[0-9]|3[01])"
                               onClick="xCal()" onKeyUp="xCal()" oninput="xCal() ">
                        <a href="" onClick="xCal('date3'); return false"><fmt:message key="open.calendar"/></a>,
                        <a href="" onClick="document.getElementById('date3').value=''; return false"><fmt:message
                                key="clean.field"/></a>,
                        <a href="" onClick="document.getElementById('date3').value=xCal(2); return false"><fmt:message
                                key="current.date"/></a>
                    </div>
                    <div class="row">
                        <label><fmt:message key="change.order.status"/>:</label>
                        <select name="status">
                            <option value=" ${order.status}">${order.status}</option>
                            <option value="checking"><fmt:message key="label.checking"/></option>
                            <option value="working"><fmt:message key="label.working"/></option>
                            <option value="ready"><fmt:message key="label.ready"/></option>
                            <option value="cancelled"><fmt:message key="label.cancelled"/></option>
                        </select>
                    </div>
                    <button type="submit"><fmt:message key="button.change"/></button>
                </form>
                <br/>
                <c:if test="${requestScope.services!=null}">
                    <div class="mobile">
                        <table class="bordered">
                            <thead>
                            <tr>
                                <th><fmt:message key="label.service"/></th>
                                <th><fmt:message key="label.service.price"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.services}" var="service">
                                <tr>
                                    <td> ${service.serviceName}</td>
                                    <td> ${service.price}</td>
                                    <td>
                                        <form method="post" action="controller">
                                            <input type="hidden" name="command" value="remove_service_from_order">
                                            <input type="hidden" name="order_id" value="${order.id}">
                                            <input type="hidden" name="serviceId" value="${service.id}">
                                            <button type="submit"><fmt:message key="button.remove"/></button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <br/>
                <h1><fmt:message key="label.whole.amount"/> : ${requestScope.amount} <fmt:message key="label.byn"/></h1>
                <br/>
                <form method="post" action="controller">
                    <input type="hidden" name="command" value="admin_add_service_to_order">
                    <input type="hidden" name="order_id" value="${order.id}">
                    <div class="row">
                        <label><fmt:message key="label.order.add.service"/></label>
                        <select name="serviceId1">
                            <option value=""></option>
                            <c:forEach items="${requestScope.servicesByCategory}" var="serviceByCategory">
                                <option value="${serviceByCategory.id}">"${serviceByCategory.serviceName}"</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="row">
                        <label><fmt:message key="label.order.add.service"/></label>
                        <select name="serviceId2">
                            <option value=""></option>
                            <c:forEach items="${requestScope.servicesByCategory}" var="serviceByCategory">
                                <option value="${serviceByCategory.id}">"${serviceByCategory.serviceName}"</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="row">
                        <label><fmt:message key="label.order.add.service"/></label>
                        <select name="serviceId3">
                            <option value=""></option>
                            <c:forEach items="${requestScope.servicesByCategory}" var="serviceByCategory">
                                <option value="${serviceByCategory.id}">"${serviceByCategory.serviceName}"</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit"><fmt:message key="button.save.change"/></button>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>