<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="menuBean" scope="page" class="net.a220vfor.core.MainMenuBean" />
<c:set var="reqUri" value="${requestScope['javax.servlet.forward.request_uri']}" scope="page" />

<!-- main navigation menu -->
<nav>
    <c:forEach items="${menuBean.menuItems}" var="item" varStatus="loop">
        <a href="${root}${item[1]}">
            <c:choose>
                <c:when test="${reqUri == root.concat(item[1])}">
                    <b><c:out value="${item[0]}" escapeXml="true"/></b>
                </c:when>
                <c:otherwise>
                    <c:out value="${item[0]}" escapeXml="true"/>
                </c:otherwise>
            </c:choose>
        </a>
    </c:forEach>
</nav>
