<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>

<html>

<head>
    <jsp:include page="../_fragments/static-content-meta.jsp"/>
    <title><spring:message code="report.indexes.page_name"/> - <spring:message code="global.app_name"/></title>
    <jsp:include page="../_fragments/static-content-styles.jsp"/>
</head>

<body>
    <jsp:include page="../_fragments/header.jsp"/>


    <main>
        <div class="container">
            <div class="section">
                <h4><spring:message code="report.indexes.page_name"/></h4>

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
                    <c:when test="${empty indexGroups or empty selectedPosId}">
                        <br>
                        <h6><spring:message code="report.results_missed"/></h6>
                    </c:when>

                    <c:otherwise>
                        <ul class="collection">
                            <c:forEach var="group" items="${indexGroups}" varStatus="loop">
                                <li class="collection-item avatar">
                                    <i class="material-icons circle light-blue lighten-1">import_export</i>

                                    <span class="title truncate truncate-card-fix">${group.indexes[0].name}</span>
                                    <p><spring:message code="report.max_estimate"/> ${group.indexes[0].estimate}</p>
                                    <p><spring:message code="report.sum_estimate"/> ${group.ratesSum}</p>

                                    <a href="JavaScript:$('#additional-modal-${loop.index}').openModal();"
                                       class="secondary-content"><i class="material-icons">info</i></a>

                                    <!-- Additional info modal -->
                                    <div id="additional-modal-${loop.index}" class="modal bottom-sheet">
                                        <div class="modal-content">
                                            <h5>${group.indexes[0].name}</h5><hr>

                                            <c:choose>
                                                <c:when test="${empty group.faculties}">
                                                    <h6><spring:message code="report.faculty_info_missed"/></h6>
                                                </c:when>

                                                <c:otherwise>
                                                    <h5><spring:message code="report.faculty_info"/></h5><hr>

                                                    <c:forEach var="faculty" items="${group.faculties}" varStatus="loopInternal">
                                                        <p>${faculty.shortName}: ${group.rates[loopInternal.index]}
                                                            <spring:message code="report.marks"/></p>
                                                        <hr>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>

                                        <div class="modal-footer">
                                            <a class="modal-action modal-close waves-effect waves-red btn-flat">
                                                <spring:message code="forms.cancel"/></a>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>


        <!-- Hidden bundled values -->
        <form id="sendReloadRequest" class="hide" action="<c:url value="/report/indexes"/>"></form>
    </main>


    <jsp:include page="../_fragments/footer.jsp"/>
    <jsp:include page="../_fragments/static-content-scripts.jsp"/>

    <!-- Custom JS for page -->
    <script src="<c:url value="/resources/js/pages/reportIndexes.js"/>"></script>
</body>

</html>