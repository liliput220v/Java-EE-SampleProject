<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- 
    Document   : sample-module
    Created on : Mar 3, 2015, 1:10:33 AM
    Author     : Andrew
--%>
<%-- Secondary application template --%>

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
        <h1>This is the template of the module page!</h1>
        
        <c:if test="${!empty module}"><b>The module name:</b> ${module}.<br></c:if>
        
        <c:if test="${!empty actions and fn:length(actions) gt 0}">
            <b>The list of actions to execute:</b>
            <c:forEach var="action" items="${actions}" varStatus="loop">
                <c:out value="${action}"/>${loop.last ? "." : ","}
            </c:forEach>
            <br>
        </c:if>
            
        <b>The path info:</b> ${pathInfo}<br>
        <b>The query string:</b> ${fn:escapeXml(queryString)}
    </body>
</html>
