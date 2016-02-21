<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<nav class="light-blue lighten-1" role="navigation">
    <div class="container">
        <div class="nav-wrapper">
            <a href="<c:url value="/"/>" class="brand-logo">
                <img src="<c:url value="/resources/img/logo_55x55.png"/>" class="responsive-img"
                     alt="<spring:message code="global.app_name"/>">
            </a>


            <ul class="right hide-on-med-and-down">
                <li>
                    <security:authorize access="isAnonymous()">
                        <a href="<c:url value="/login"/>"><spring:message code="control.sign_in"/></a>
                    </security:authorize>

                    <security:authorize access="isAuthenticated()">
                        <a href="<c:url value="/j_spring_security_logout"/>"><spring:message
                                code="control.sign_out"/></a>
                    </security:authorize>
                </li>
            </ul>


            <ul id="nav-mobile" class="side-nav">
                <li>
                    <security:authorize access="isAnonymous()">
                        <a href="<c:url value="/login"/>"><spring:message code="control.sign_in"/></a>
                    </security:authorize>

                    <security:authorize access="isAuthenticated()">
                        <a href="<c:url value="/j_spring_security_logout"/>"><spring:message
                                code="control.sign_out"/></a>
                    </security:authorize>
                </li>
            </ul>
            <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
        </div>
    </div>
</nav>