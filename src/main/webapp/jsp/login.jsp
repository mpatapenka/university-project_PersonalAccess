<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>

    <title><spring:message code="login.page_name"/> - <spring:message code="global.app_name"/></title>

    <jsp:include page="fragments/static-content.jsp"/>
</head>

<body>
    <jsp:include page="fragments/header.jsp"/>


    <div class="section no-pad-bot" id="index-banner">
        <div class="container">
            <br>
            <h1 class="header center orange-text"><spring:message code="login.header"/></h1>
            <div class="row center">
                <h5 class="header col s12 light"><spring:message code="login.welcome"/></h5>
            </div>
            <div class="row center">
                <form method="post" class="col s12" action="<c:url value='/j_spring_security_check'/>">
                    <c:if test="${isError}">
                        <div class="row">
                            <div class="chip">
                                <span class="red-text"><spring:message code="login.error"/></span>
                                <i class="material-icons">close</i>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${isLogout}">
                        <div class="row">
                            <div class="chip">
                                <span class="green-text"><spring:message code="login.logout"/></span>
                                <i class="material-icons">close</i>
                            </div>
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="input-field col s12 m6">
                            <i class="material-icons prefix">account_circle</i>
                            <input id="icon_prefix" type="text" class="validate" name="username">
                            <label for="icon_prefix"><spring:message code="control.username"/></label>
                        </div>
                        <div class="input-field col s12 m6">
                            <i class="material-icons prefix">vpn_key</i>
                            <input id="icon_telephone" type="password" class="validate" name="password">
                            <label for="icon_telephone"><spring:message code="control.password"/></label>
                        </div>
                    </div>

                    <button type="submit" onclick="return emptyValidator();"
                            class="btn-large waves-effect waves-light green col s12 m5">
                        <spring:message code="control.sign_in"/></button>
                    <div class="col s12 m2">&nbsp;</div>
                    <button type="reset" class="btn-large waves-effect waves-light blue col s12 m5">
                        <spring:message code="control.reset"/></button>
                </form>
            </div>
            <br>
        </div>
    </div>


    <jsp:include page="fragments/footer.jsp"/>
    <jsp:include page="fragments/static-content-js.jsp"/>
</body>

</html>
