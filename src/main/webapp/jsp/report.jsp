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

            <c:choose>

                <c:when test="${not empty subs}">
                    <div class="row">
                        <div class="input-field col s6">
                            <select id="period" class="invalid">
                                <option value="" disabled selected>Выберите <spring:message code="period.${periodNameCode}"/></option>
                                <c:forEach var="period" items="${periods}" varStatus="loop">
                                    <option value="${loop.index}">${loop.index + 1}-й: ${period.start} - ${period.end}</option>
                                </c:forEach>
                            </select>
                            <label>Доступные периоды</label>
                        </div>

                        <div class="input-field col s6">
                            <select id="year">
                                <option value="" disabled selected>Выберите год</option>
                                <c:forEach var="year" items="${availYears}">
                                    <option value="${year}">${year} г.</option>
                                </c:forEach>
                            </select>
                            <label>Доступные года</label>
                        </div>
                    </div>

                    <div class="input-field col s12">
                        <select id="sub">
                            <option value="" disabled selected>Выберите пользователя</option>
                            <c:forEach var="sub" items="${subs}">
                                <option value="${sub.id}">${sub.form.firstName} ${sub.form.lastName}</option>
                            </c:forEach>
                        </select>
                        <label>Ваши подчиненные</label>
                    </div>

                    <div id="card-container">
                        <!-- Place cards here -->
                    </div>

                    <div id="float-box" class="fixed-action-btn" style="bottom: 45px; right: 24px;">
                        <a class="btn-floating btn-large waves-effect waves-light cyan darken-3" id="search-btn">
                            <i class="material-icons">youtube_searched_for</i></a>
                    </div>
                </c:when>

                <c:otherwise>
                    <h3>У вас нет подчиненных</h3>
                </c:otherwise>

            </c:choose>

        </div>
    </div>


    <jsp:include page="fragments/footer.jsp"/>
    <jsp:include page="fragments/static-content-js.jsp"/>

    <!-- Custom JS for page -->
    <script src="<c:url value="/resources/js/pages/subordinate.js"/>"></script>

</body>

</html>