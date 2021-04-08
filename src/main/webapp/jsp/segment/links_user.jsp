<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 24.02.2021
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="leftCol">
    <ul class="leftNav">
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=to_profile"><fmt:message
                    key="button.profile"/></a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=to_making_order"><fmt:message
                key="make.order"/></a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=user_order"><fmt:message
                key="view.order"/></a></li>
    </ul>
</div>
