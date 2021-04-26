<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 13.03.2021
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<jsp:useBean id="order" scope="request" class="com.klasevich.cms.model.entity.Order"/>
<c:set var="readyStatus" scope="request" value="READY"/>


<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media-queries.css" type="text/css">
    <title>Show user order</title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="container">
        <div class="content">
            <%@ include file="/jsp/segment/links_user.jsp" %>
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
                            <fmt:message key="label.order.issue.date"/>
                        </td>
                        <td>
                            ${order.issueDate}
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
                            <fmt:message key="label.order.status"/>
                        </td>
                        <td>
                            ${order.status}
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
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <h1><fmt:message key="label.whole.amount"/> : ${requestScope.amount} <fmt:message
                            key="label.byn"/></h1>
                    <c:if test="${requestScope.amount>30}">
                        <p><fmt:message key="message.button.ok"/></p>
                    </c:if>
                    <p><fmt:message key="message.button.cancel"/></p>
                    <p><fmt:message key="message.warning"/></p>
                    <table>
                        <tr>
                            <td>
                                <form method="post" action="order_cancelled.do">
                                    <input type="hidden" name="command" value="change_order_status">
                                    <input type="hidden" name="status" value="cancelled">
                                    <input type="hidden" name="order_id" value="${order.id}">
                                    <button type="submit"><fmt:message key="button.cancel"/></button>
                                </form>
                            </td>
                            <c:if test="${requestScope.amount>30}">
                                <td>
                                    <form method="post" action="order_submitted.do">
                                        <input type="hidden" name="command" value="change_order_status">
                                        <input type="hidden" name="status" value="${order.status}">
                                        <input type="hidden" name="order_id" value="${order.id}">
                                        <button type="submit"><fmt:message key="button.ok"/></button>
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </table>
                </c:if>
            </div>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>
