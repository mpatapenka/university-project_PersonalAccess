<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>

<html>

<head>
    <jsp:include page="../_fragments/static-content-meta.jsp"/>
    <title><spring:message code="report.employees.page_name"/> - <spring:message code="global.app_name"/></title>
    <jsp:include page="../_fragments/static-content-styles.jsp"/>
    <link href="<c:url value='/resources/css/pages/reportEmployees.css'/>" rel="stylesheet" type="text/css" media="screen,projection"/>
</head>

<body>
    <jsp:include page="../_fragments/header.jsp"/>


    <main>
        <div class="container">
            <div class="section">
                <h4><spring:message code="report.employees.page_name"/></h4>

                <c:if test="${not empty rates}">
                    <a id="create-chart" class="waves-effect waves-light btn">
                        <i class="material-icons left">insert_chart</i>
                        <spring:message code="report.employees.chart"/></a><br><br>
                </c:if>

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
                    <c:when test="${empty rates or empty selectedPosId}">
                        <br>
                        <h6><spring:message code="report.results_missed"/></h6>
                    </c:when>

                    <c:otherwise>
                        <ul class="collection">
                            <c:forEach var="rate" items="${rates}">
                                <li class="collection-item avatar">
                                    <i class="material-icons circle light-blue lighten-1">import_export</i>

                                    <span class="title truncate truncate-card-fix">${rate.user.form.lastName}
                                    ${rate.user.form.firstName} ${rate.user.form.middleName}</span>

                                    <c:choose>
                                        <c:when test="${rate.rate lt 0}">
                                            <c:set var="rateVal"><spring:message code="control.not_fill"/></c:set>
                                        </c:when>

                                        <c:otherwise>
                                            <c:set var="rateVal" value="${rate.rate}"/>
                                        </c:otherwise>
                                    </c:choose>

                                    <p><spring:message code="report.rate"/> ${rateVal}</p>
                                </li>
                            </c:forEach>
                        </ul>


                        <c:if test="${not empty rates}">
                            <div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
                                <a class="btn-floating btn-large waves-effect waves-light blue" id="sort-revert">
                                    <i class="material-icons">swap_calls</i></a>
                            </div>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>


        <!-- Chart modal -->
        <div id="chart-modal" class="modal bottom-sheet modal-bottom-sheet-expand">
            <div class="modal-content">
                <div id="container" class="chart-config"></div>
            </div>

            <div class="modal-footer">
                <a class="modal-action modal-close waves-effect waves-red btn-flat">
                    <spring:message code="forms.cancel"/></a>
            </div>
        </div>


        <!-- Hidden bundled values -->
        <select id="sort-type" class="hide">
            <c:forEach var="sType" items="UPWARDS,DOWNWARDS">
                <c:set var="isSelected" value=""/>
                <c:if test="${sortType eq sType}">
                    <c:set var="isSelected" value="selected"/>
                </c:if>
                <option value="${sType}" ${isSelected}></option>
            </c:forEach>
        </select>

        <div id="chartTitle" class="hide"><spring:message code="chart.title"/></div>
        <div id="yAxisTitle" class="hide"><spring:message code="chart.axis.y.title"/></div>
        <div id="seriaName" class="hide"><spring:message code="chart.seria.name"/></div>

        <div id="resultMissed" class="hide"><spring:message code="report.result_mised"/></div>

        <form id="sendReloadRequest" class="hide" action="<c:url value="/report/employees"/>"></form>
        <form id="sendTop5Request" class="hide" action="<c:url value="/report/employees"/>"></form>
    </main>


    <jsp:include page="../_fragments/footer.jsp"/>
    <jsp:include page="../_fragments/static-content-scripts.jsp"/>

    <!-- Custom JS for page -->
    <script src="<c:url value="/resources/js/pages/reportEmployees.js"/>"></script>

    <!-- HighChart.js -->
    <script src="<c:url value="/resources/js/highcharts/highcharts.js"/>"></script>
    <script src="<c:url value="/resources/js/highcharts/modules/data.js"/>"></script>
    <script src="<c:url value="/resources/js/highcharts/modules/drilldown.js"/>"></script>
</body>

</html>