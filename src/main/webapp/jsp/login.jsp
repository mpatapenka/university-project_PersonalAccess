<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>

    <title><spring:message code="global.app_name"/> - <spring:message code="login.page_name"/></title>

    <jsp:include page="fragments/static-content.jsp"/>
</head>

<body>

    <jsp:include page="fragments/header.jsp"/>


    <div class="section no-pad-bot" id="index-banner">
        <div class="container">
            <br><br>
            <h1 class="header center orange-text"><spring:message code="login.header"/></h1>
            <div class="row center">
                <h5 class="header col s12 light"><spring:message code="login.welcome"/></h5>
            </div>
            <div class="row center">
                <form method="post" class="col s12" action="<c:url value='/j_spring_security_check'/>">
                    <div class="row">
                        <div class="input-field col s6">
                            <i class="material-icons prefix">account_circle</i>
                            <input id="icon_prefix" type="text" class="validate" name="username" length="20">
                            <label for="icon_prefix"><spring:message code="control.username"/></label>
                        </div>
                        <div class="input-field col s6">
                            <i class="material-icons prefix">vpn_key</i>
                            <input id="icon_telephone" type="password" class="validate" name="password" length="10">
                            <label for="icon_telephone"><spring:message code="control.password"/></label>
                        </div>
                    </div>
                    <div class="row">
                        <button type="submit" class="btn-large waves-effect waves-light green">
                            <spring:message code="control.sign_in"/></button>
                        <button type="reset" class="btn-large waves-effect waves-light red">
                            <spring:message code="control.reset"/></button>
                    </div>
                </form>
            </div>
            <br><br>

        </div>
    </div>

    <jsp:include page="fragments/footer.jsp"/>

    <jsp:include page="fragments/static-content-js.jsp"/>

</body>

</html>
