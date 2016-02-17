<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Личный Кабинет</title>


    <spring:url value="/resources/css/materialize.min.css" var="materializeCss"/>
    <link href="${materializeCss}" rel="stylesheet"/>


    <spring:url value="/resources/js/jquery-2.2.0.min.js" var="jQuery"/>
    <script src="${jQuery}"></script>

    <spring:url value="/resources/js/materialize.min.js" var="materializeJs"/>
    <script src="${materialize}"></script>
</head>
