<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 01.02.2021
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:setBundle basename="resource.pagecontent"/>

<html>
<head>
    <title>Mail</title>
</head>
<body>
<form action="controller" method="POST">
    <table>
        <tr>
            <td>Send to:</td>
            <td><input type="text" name="to" value=""/></td>
        </tr>
        <tr>
            <td>Subject:</td>
            <td><input type="text" name="subject" value=""/></td>
        </tr>
    </table>
    <hr/>
    <textarea type="text" name="body" rows="5" cols="45">Message text</textarea>
    <br/><br/>
    <input type="hidden" name="command" value="mail"/>
    <input type="submit" value="Send message!"/>
    <br/>
    ${requestScope.messageWarning}
    <br/>
</form>
</body>
</html>
