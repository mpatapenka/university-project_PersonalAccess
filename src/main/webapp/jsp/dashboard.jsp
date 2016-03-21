<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>

    <title><spring:message code="dashboard.page_name"/> - <spring:message code="global.app_name"/></title>

    <jsp:include page="fragments/static-content.jsp"/>
</head>

<body>
    <jsp:include page="fragments/header.jsp"/>


    <div class="container">
        <div class="section">
            <div class="row">
                <div class="input-field col s6">
                    <select id="period">
                        <option value="" disabled selected><spring:message code="control.choose"/> <spring:message code="period.${periodNameCode}"/></option>
                        <c:forEach var="period" items="${periods}" varStatus="loop">
                            <option value="${loop.index}">${loop.index + 1}. ${period.start} - ${period.end}</option>
                        </c:forEach>
                    </select>
                    <label><spring:message code="control.available_periods"/></label>
                </div>

                <div class="input-field col s6">
                    <select id="year">
                        <option value="" disabled selected><spring:message code="control.choose_year"/></option>
                        <c:forEach var="year" items="${availYears}">
                            <option value="${year}">${year} <spring:message code="forms.short.year"/></option>
                        </c:forEach>
                    </select>
                    <label><spring:message code="control.available_years"/></label>
                </div>
            </div>

            <h5><spring:message code="dashboard.header"/> (${period.dateString})</h5>

            <c:choose>
                <c:when test="${empty userIndexes}">
                    <hr>
                    <h6><spring:message code="dashboard.indexes_missed"/></h6>
                </c:when>

                <c:otherwise>
                    <ul class="collection">
                        <c:forEach var="uIndex" items="${userIndexes}">
                            <li class="collection-item avatar">
                                <i class="material-icons circle orange">assignment</i>
                                <span class="title truncate">${uIndex.index.name}</span>
                                <p><spring:message code="dashboard.your_mark"/><br>
                                    <c:choose>
                                        <c:when test="${uIndex.selfEstimate eq 0}">
                                            <c:set var="selfEstimateValue" value=""/>
                                        </c:when>

                                        <c:otherwise>
                                            <c:set var="selfEstimateValue" value="${uIndex.selfEstimate}"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <input id="${uIndex.id}" name="selfEstimate" type="number" step="0.01" min="0"
                                           max="${uIndex.index.estimate}" class="validate" value="${selfEstimateValue}"/>
                                </p>

                                <a href="JavaScript:$('#additional-modal-${uIndex.id}').openModal();"
                                   class="secondary-content"><i class="material-icons">chat_bubble_outline</i></a>

                                <!-- Additional info modal -->
                                <div id="additional-modal-${uIndex.id}" class="modal bottom-sheet">
                                    <div class="modal-content">
                                        <h4><spring:message code="dashboard.additional_info"/></h4>

                                        <hr>

                                        <h5>${uIndex.index.name}</h5>
                                        <p><spring:message code="dashboard.mark_message1"/> ${uIndex.index.estimate}
                                            <spring:message code="dashboard.mark_message2"/> ${uIndex.index.multiplier}
                                            ${uIndex.index.workName}</p>

                                        <form id="additional-form-${uIndex.id}" method="post"
                                              action="<c:url value="/user/dashboard/additional"/>" enctype="multipart/form-data">

                                            <input type="hidden" name="id" value="${uIndex.id}"/>

                                            <div class="row">
                                                <div class="input-field col s12">
                                                    <textarea id="${uIndex.id}" name="description" class="materialize-textarea"><c:out value="${uIndex.description}"/></textarea>
                                                    <label><spring:message code="dashboard.work_description"/></label>
                                                </div>
                                            </div>

                                            <div class="row">
                                                <div class="file-field input-field col s12">
                                                    <div class="btn">
                                                        <span><spring:message code="forms.document"/></span>
                                                        <input id="${uIndex.id}" type="file" name="document"/>
                                                    </div>
                                                    <div class="file-path-wrapper">
                                                        <c:set var="fileName" value=""/>
                                                        <c:set var="linkToFile" value="#"/>
                                                        <c:set var="linkMode" value="hide"/>

                                                        <c:if test="${not empty uIndex.document}">
                                                            <c:set var="fileName" value="${uIndex.document.name}"/>
                                                            <c:url var="fileUrl" value="/user/dashboard/download?id=${uIndex.document.id}"/>
                                                            <c:set var="linkToFile" value="${fileUrl}"/>
                                                            <c:set var="linkMode" value=""/>
                                                        </c:if>
                                                        <input id="${uIndex.id}" class="file-path" type="text" name="document-name" value="${fileName}"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>

                                        <div id="download-btn-area-${uIndex.id}" class="${linkMode}">
                                            <a id="download-link-${uIndex.id}" href="${linkToFile}" class="btn right waves-effect waves-light">
                                                <spring:message code="control.download"/>
                                            </a><br>
                                        </div>
                                    </div>

                                    <div class="modal-footer">
                                        <a class="waves-effect waves-green btn-flat"
                                           onclick="updateAdditionalInfo(${uIndex.id})">
                                            <spring:message code="forms.save"/></a>
                                        <a class="modal-action modal-close waves-effect waves-red btn-flat">
                                            <spring:message code="forms.cancel"/></a>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>

                    <div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
                        <a class="btn-floating btn-large waves-effect waves-light green" id="publish-button">
                            <i class="material-icons">publish</i></a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- Hidden bundled values -->
    <div id="fieldMissingError" class="hide"><spring:message code="dashboard.message.fill_up_fields"/></div>
    <div id="unsupportedMarkError" class="hide"><spring:message code="dashboard.message.unsupported_mark"/></div>

    <form id="sendEstimateForm" class="hide" method="post" action="<c:url value="/user/dashboard/estimates"/>"></form>


    <jsp:include page="fragments/footer.jsp"/>
    <jsp:include page="fragments/static-content-js.jsp"/>

    <!-- Custom JS for page -->
    <script src="<c:url value="/resources/js/pages/dashboard.js"/>"></script>
</body>

</html>
