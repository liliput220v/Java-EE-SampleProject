<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2>Actor list</h2>
<c:if test="${fn:length(actors) gt 0}">
    <ul>
        <c:forEach items="${actors}" var="actor">
            <li><c:out value="${actor.key} ${actor.value}" escapeXml="true"/></li>
        </c:forEach>
    </ul>
</c:if>
<button id="simpleBtn">Load something...</button>
<script type="text/javascript">

    function xhrHandler() {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/SampleProject/app/module/?_='+Date.now(), true);
        xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
        xhr.onload = function (e) {
            console.log(e);
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    console.log(JSON.parse(xhr.response));
                    console.log(xhr);
                } else {
                    console.error(xhr.statusText);
                }
            }
        };
        xhr.onerror = function (e) {
            console.error(xhr.statusText);
        };
        xhr.send(null);
    }

    var btn = document.getElementById('simpleBtn');
    btn.addEventListener('click', function() {
        xhrHandler();
    });


</script>
