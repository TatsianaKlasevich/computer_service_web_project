<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 26.02.2021
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="role" scope="session" value="${sessionScope.currentRole}"/>
<c:set var="guestRole" scope="session" value="GUEST"/>
<c:set var="adminRole" scope="session" value="ADMIN"/>
<c:set var="userRole" scope="session" value="USER"/>
<c:set var="user" scope="session" value="${sessionScope.user}"/>
<html>
<div class="headerContent">
    <ul class="nav">
        <li>
            <a href="${pageContext.request.contextPath}/home.do?command=home"><fmt:message key="button.home"/></a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/services.do?command=to_service"><fmt:message
                    key="button.services"/></a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/contacts.do?command=to_contacts"><fmt:message
                    key="button.contact"/></a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/delivery.do?command=to_delivery"><fmt:message
                    key="button.delivery.pay"/></a>
        </li>
        <li>
            <c:if test="${role.toString().equals(adminRole)}">
                <a href="${pageContext.request.contextPath}/admin_main.do?command=to_admin_main" title="link">
                    <ctg:profile/></a>
            </c:if>
        </li>
        <li>
            <c:if test="${role.toString().equals(userRole)}">
                <a href="${pageContext.request.contextPath}/profile.do?command=to_profile" title="link"> <ctg:profile/></a>
            </c:if>
        </li>
        <li>
            <c:if test="${role.toString().equals(guestRole)}">
                <a href="${pageContext.request.contextPath}/login.do?command=to_login"><fmt:message
                        key="button.login"/></a>
            </c:if>
        </li>
        <li>
            <c:if test="${role.toString().equals(adminRole)||role.toString().equals(userRole)}">
                <a href="${pageContext.request.contextPath}/logout.do?command=logout"><fmt:message
                        key="button.logout"/></a>
            </c:if>
        </li>
    </ul>
</div>
</html>
