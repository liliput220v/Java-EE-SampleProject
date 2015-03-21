<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <h1>${heading}</h1>
        <jsp:include page="./views/${moduleName}/${actionTemplate}.jsp" />
    </body>
</html>
