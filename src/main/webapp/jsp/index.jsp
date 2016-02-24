<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>

    <title><spring:message code="global.app_name"/></title>

    <jsp:include page="fragments/static-content.jsp"/>

</head>

<body>

    <jsp:include page="fragments/header.jsp"/>


    <div class="section no-pad-bot" id="index-banner">
        <div class="container">
            <br><br>
            <h1 class="header center orange-text">Добро пожаловать</h1>
            <div class="row center">
                <h5 class="header col s12 light">Удобный и практичный личный кабинет сотрудников университета</h5>
            </div>
            <div class="row center">
                <a href="<c:url value='/login'/>" id="download-button"
                   class="btn-large waves-effect waves-light orange">Начать использовать</a>
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
                        <h5 class="center">Быстрый анализ</h5>

                        <p class="light">Используйте этот инструмент для быстрой оценки себя и ваших подчиненных,
                            анализируйте и собирайте информацию. Поощрите тех кто набрал высокий рейтинг или сделайте
                            выговор отстающим.</p>
                    </div>
                </div>

                <div class="col s12 m4">
                    <div class="icon-block">
                        <h2 class="center light-blue-text"><i class="material-icons">group</i></h2>
                        <h5 class="center">Сосредоточенность на пользователе</h5>

                        <p class="light">Личный кабинет предоставляет возможность сотрудникам университета
                            заниматься учетом своих успехов, а также оценивать успехи своих подчиненных.</p>
                    </div>
                </div>

                <div class="col s12 m4">
                    <div class="icon-block">
                        <h2 class="center light-blue-text"><i class="material-icons">settings</i></h2>
                        <h5 class="center">Простота в использовании</h5>

                        <p class="light">Легкий и удобный дизайн поможет вам освоится в своем кабинете.</p>
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
