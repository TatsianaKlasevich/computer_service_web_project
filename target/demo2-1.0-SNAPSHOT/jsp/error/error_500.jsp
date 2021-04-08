<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 22.02.2021
  Time: 2:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resource.pagecontent"/>

<link href="${pageContext.request.contextPath}/css/error_500.css" rel="stylesheet">

<div class="page-500">
    <div class="outer">
        <div class="middle">
            <div class="inner">
                <!--BEGIN CONTENT-->
                <div class="inner-circle"><i class="fa fa-cogs"></i><span>500</span></div>
                <span class="inner-status"><fmt:message key="error.500.oops"/></span>
                <span class="inner-detail"><fmt:message key="error.500.unfortunately"/></span>
                <!--END CONTENT-->
            </div>
        </div>
    </div>
</div>
