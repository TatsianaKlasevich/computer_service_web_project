<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 16.03.2021
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="leftCol">
    <ul class="leftNav">
        <li><a href="${pageContext.request.contextPath}/profile.do?command=to_profile"><fmt:message
                key="button.admin.profile"/></a></li>
        <li><a href="${pageContext.request.contextPath}/admin_add.do?command=to_add_admin"><fmt:message
                key="button.add.admin"/></a></li>
        <li><a href="${pageContext.request.contextPath}/manage_user.do?command=to_admin_manage_user"><fmt:message
                key="button.manage.user"/></a></li>
        <li><a href="${pageContext.request.contextPath}/manage_service.do?command=to_admin_manage_service"><fmt:message
                key="button.manage.service"/></a></li>
        <li><a href="${pageContext.request.contextPath}/manage_order.do?command=to_admin_manage_order"><fmt:message
                key="button.manage.order"/></a></li>
    </ul>
</div>
