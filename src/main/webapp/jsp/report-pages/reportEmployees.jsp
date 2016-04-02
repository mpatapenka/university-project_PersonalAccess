<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>

<html>

<head>
    <jsp:include page="../_fragments/static-content-meta.jsp"/>
    <title><spring:message code="report.employees.page_name"/> - <spring:message code="global.app_name"/></title>
    <jsp:include page="../_fragments/static-content-styles.jsp"/>
</head>

<body>
    <jsp:include page="../_fragments/header.jsp"/>


    <main>
        <div class="container">
            <div class="section">
                <h4><spring:message code="report.employees.page_name"/></h4>

                <div class="row">
                    <div class="input-field col s6">
                        <select id="period">
                            <option value="" disabled selected><spring:message code="control.choose"/> <spring:message code="period.${periodNameCode}"/></option>
                            <c:forEach var="period" items="${periods}" varStatus="loop">
                                <c:set var="isSelected" value=""/>
                                <c:if test="${selectedPeriodId eq loop.index}">
                                    <c:set var="isSelected" value="selected"/>
                                </c:if>
                                <option value="${loop.index}" ${isSelected}>${loop.index + 1}. ${period.start} - ${period.end}</option>
                            </c:forEach>
                        </select>
                        <label><spring:message code="control.available_periods"/></label>
                    </div>

                    <div class="input-field col s6">
                        <select id="year">
                            <option value="" disabled selected><spring:message code="control.choose_year"/></option>
                            <c:forEach var="year" items="${availYears}">
                                <c:set var="isSelected" value=""/>
                                <c:if test="${selectedYear eq year}">
                                    <c:set var="isSelected" value="selected"/>
                                </c:if>
                                <option value="${year}" ${isSelected}>${year} <spring:message code="forms.short.year"/></option>
                            </c:forEach>
                        </select>
                        <label><spring:message code="control.available_years"/></label>
                    </div>
                </div>

                <div class="input-field col s12">
                    <select id="positions">
                        <option value="" disabled selected><spring:message code="control.choose_position"/></option>
                        <c:forEach var="pos" items="${poses}">
                            <c:set var="isSelected" value=""/>
                            <c:if test="${selectedPosId eq pos.id}">
                                <c:set var="isSelected" value="selected"/>
                            </c:if>
                            <option value="${pos.id}" ${isSelected}>${pos.name}</option>
                        </c:forEach>
                    </select>
                    <label><spring:message code="control.available_positions"/></label>
                </div>

                <c:choose>
                    <c:when test="${empty employees and empty selectedPosId}">
                        <br>
                        <h6><spring:message code="report.results_missed"/></h6>
                    </c:when>

                    <c:otherwise>



                        <c:if test="${not empty employees}">
                            <div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
                                <a class="btn-floating btn-large waves-effect waves-light blue" id="">
                                    <i class="material-icons">swap_calls</i></a>
                            </div>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </main>


    <jsp:include page="../_fragments/footer.jsp"/>
    <jsp:include page="../_fragments/static-content-scripts.jsp"/>

    <!-- Custom JS for page -->
    <script src="<c:url value="/resources/js/pages/report.js"/>"></script>
</body>

</html>