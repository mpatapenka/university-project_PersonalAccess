<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>

    <title><spring:message code="index.page_name"/> - <spring:message code="global.app_name"/></title>

    <jsp:include page="fragments/static-content.jsp"/>
</head>

<body>
    <jsp:include page="fragments/header.jsp"/>


    <div class="section no-pad-bot" id="index-banner">
        <div class="container">

            <br><br>

            <h1 class="header center orange-text"><spring:message code="index.welcome"/></h1>
            <div class="row center">
                <h5 class="header col s12 light"><spring:message code="index.main_description"/></h5>
            </div>

            <div class="row center">
                <a href="<c:url value='/login'/>" id="download-button"
                   class="btn-large waves-effect waves-light orange"><spring:message code="index.welcome_btn"/></a>
            </div>

            <br><br>

        </div>
    </div>


    <div class="container">
        <div class="section">
            <div class="row">

                <div class="col s12 m4">
                    <div class="icon-block">
                        <h2 class="center light-blue-text"><i class="material-icons">flash_on</i></h2>
                        <h5 class="center"><spring:message code="index.headers.fast_analyzing"/></h5>
                        <p class="light"><spring:message code="index.content.fast_analyzing"/></p>
                    </div>
                </div>

                <div class="col s12 m4">
                    <div class="icon-block">
                        <h2 class="center light-blue-text"><i class="material-icons">group</i></h2>
                        <h5 class="center"><spring:message code="index.headers.concentration"/></h5>
                        <p class="light"><spring:message code="index.content.concentration"/></p>
                    </div>
                </div>

                <div class="col s12 m4">
                    <div class="icon-block">
                        <h2 class="center light-blue-text"><i class="material-icons">settings</i></h2>
                        <h5 class="center"><spring:message code="index.headers.simple_using"/></h5>
                        <p class="light"><spring:message code="index.content.simple_using"/></p>
                    </div>
                </div>

            </div>
        </div>

        <br><br>

    </div>


    <jsp:include page="fragments/footer.jsp"/>
    <jsp:include page="fragments/static-content-js.jsp"/>
</body>

</html>
