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


    <div class="container">
        <div class="section">

            <h5>Вам необходимо заполнить показатели за текущий период (${period.dateString})</h5>

            <c:forEach var="userIndex" items="${userIndexes}">



            </c:forEach>

        </div>
    </div>


    <jsp:include page="fragments/footer.jsp"/>
    <jsp:include page="fragments/static-content-js.jsp"/>

</body>

</html>
