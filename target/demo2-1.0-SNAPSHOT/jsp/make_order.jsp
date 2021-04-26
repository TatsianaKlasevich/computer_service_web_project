<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24.02.2021
  Time: 17:43
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
    <title>Make order</title>
</head>
<body>
<div class="wrapper">
    <%@ include file="/jsp/segment/header1.jsp" %>
    <%@ include file="/jsp/segment/header2.jsp" %>
    <div class="content">
        <%@ include file="/jsp/segment/links_user.jsp" %>
        <div class="main">
            <h2><fmt:message key="label.make.order"/></h2>
            <br/>
            ${requestScope.messageWarning}
            <br/>
            <form method="post" action="make_order.do">
                <input type="hidden" name="command" value="make_order">
                <input type="hidden" name="order_id" value="make_order">
                <div class="row">
                    <label><fmt:message key="label.date"/></label>
                    <input id="date3" type="text" name=date value="${requestScope.date}" size="10"
                           pattern="[2][\d]{3}\.(0[1-9]|1[012])\.(0[1-9]|1[0-9]|2[0-9]|3[01])"
                           onClick="xCal()" onKeyUp="xCal()" oninput="xCal() ">
                    <a href="" onClick="xCal('date3'); return false"><fmt:message key="open.calendar"/></a>,
                    <a href="" onClick="document.getElementById('date3').value=''; return false"><fmt:message
                            key="clean.field"/></a>,
                    <a href="" onClick="document.getElementById('date3').value=xCal(2); return false"><fmt:message
                            key="current.date"/></a>
                </div>
                <div class="row">
                    <label><fmt:message key="label.address"/></label>
                    <input type="text" name="address" value="${requestScope.address}"
                           placeholder=" ул. Советская, 26-34"
                           data-toggle="tooltip" data-placement="bottom"
                           title="<fmt:message key="rule.address"/>" pattern="[\s\dА-Яа-я,-.]{2,100}">
                    <fmt:message key="rule.address"/>
                </div>
                <div class="row">
                    <label><fmt:message key="label.select"/></label>
                    <select name="categoryName">
                        <option value="laptop"><fmt:message key="label.laptop"/></option>
                        <option value="printer"><fmt:message key="label.printer"/></option>
                        <option value="UPS"><fmt:message key="label.ups"/></option>
                    </select>
                </div>
                <div class="row">
                    <label><fmt:message key="label.problem"/></label>
                    <input type="text" name=problem value="${requestScope.problem}"
                           data-toggle="tooltip" data-placement="bottom"
                           title="<fmt:message key="rule.problem"/>"
                           placeholder="<fmt:message key="rule.problem"/>"
                           pattern="[\s\dА-Яа-я,-.]{2,100}">
                    <fmt:message key="rule.problem"/>
                </div>
                <div class="row">
                    <label> <input type="checkbox" name="has_discount" value="true">
                        <fmt:message key="label.discount"/></label>
                </div>
                <div class="row">
                    <button type="submit"><fmt:message key="button.add"/></button>
                </div>
            </form>
        </div>
    </div>
    <%@ include file="/jsp/segment/footer.jsp" %>
</div>
</body>
</html>
