<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<footer class="page-footer orange">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text"><spring:message code="company_name"/></h5>
                <p class="grey-text text-lighten-4">
                    <spring:message code="footer.company_message"/>
                </p>
            </div>
            <div class="col l3 s12">
                <h5 class="white-text"><spring:message code="general.options"/></h5>
                <ul>
                    <li><a class="white-text" href="<c:url value="?lang=ru"/>"><spring:message code="lang.ru"/></a></li>
                    <li><a class="white-text" href="<c:url value="?lang=en"/>"><spring:message code="lang.en"/></a></li>
                </ul>
            </div>
            <div class="col l3 s12">
                <h5 class="white-text"><spring:message code="general.connect"/></h5>
                <ul>
                    <li>
                        <iframe src="http://ghbtns.com/github-btn.html?user=trollsrulls&repo=PersonalAccess-Project&type=watch&count=true&size=large"
                                allowtransparency="true" frameborder="0" scrolling="0" width="170" height="30"></iframe>
                    </li>
                    <li><a class="white-text" href="https://www.gstu.by/" target="_blank"><spring:message
                            code="footer.site_company"/></a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            <spring:message code="footer.made_by"/>
            <a class="orange-text text-lighten-3" href="mailto:maxim131994@gmail.com">
                <spring:message code="author"/>
            </a>
        </div>
    </div>
</footer>