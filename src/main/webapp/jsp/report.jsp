<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>

    <title>Report - <spring:message code="global.app_name"/></title>

    <jsp:include page="fragments/static-content.jsp"/>

</head>

<body>

    <jsp:include page="fragments/header.jsp"/>


    <div class="container">
        <div class="section">



        </div>
    </div>


    <jsp:include page="fragments/footer.jsp"/>
    <jsp:include page="fragments/static-content-js.jsp"/>

    <!-- Custom JS for page -->
    <script src="<c:url value="/resources/js/pages/report.js"/>"></script>

</body>

</html>