<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 
    Document   : index
    Created on : Mar 3, 2015, 1:33:34 AM
    Author     : Andrew
--%>
<%-- Primary application template --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
    <head>
        <meta charset="UTF-8">
        <title>Sample Project - ${title}</title>
        <link href="${root}/css/styles.css" type="text/css" rel="stylesheet" media="all">
    </head>
    <body>
        <h1>This is the template of the index page!</h1>
        <h2>Actor list</h2>
        <c:if test="${fn:length(actors) gt 0}">
            <ul>
                <c:forEach items="${actors}" var="actor">
                    <li><c:out value="${actor.key} ${actor.value}" escapeXml="true"/></li>
                </c:forEach>
            </ul>
        </c:if>
        
    </body>
</html>
