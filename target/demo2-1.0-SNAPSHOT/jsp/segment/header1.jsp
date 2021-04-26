<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 29.01.2021
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="resource.pagecontent"/>
<c:set var="currentUrl" value="${pageContext.request.queryString}"/>
<div class="header">
    <div class="headerContent">
        <div class="logo">
            <a href="${pageContext.request.contextPath}/controller?command=home">
                KL-
                <span class="pink">service</span>
                <span class="gray">.com</span>
            </a>
        </div>
        <div class="logo">
            <a href="${pageContext.request.contextPath}/controller?command=home">
                <span class="pink">+375 29 100 00 01</span>
            </a>
        </div>
        <div align="right">
            <table class="table table-striped ">
                <tr>
                    <td>
                        <form method="post" action="controller">
                            <input type="hidden" name="command" value="localization">
                            <input type="hidden" name="currentPage" value="${currentUrl}">
                            <input type="hidden" name="page" value="${pageContext.request.requestURI}">
                            <input class="btn" type="submit" name="language" value=<fmt:message
                                    key="button.ru"/>></form>
                    </td>
                    <td>
                        <form method="post" action="controller">
                            <input type="hidden" name="command" value="localization"/>
                            <input type="hidden" name="currentPage" value="${currentUrl}">
                            <input type="hidden" name="page" value="${pageContext.request.requestURI}">
                            <input class="btn" type="submit" name="language" value= <fmt:message key="button.en"/>>
                        </form>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>


