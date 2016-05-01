<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>

<html>

<head>
    <jsp:include page="../_fragments/static-content-meta.jsp"/>
    <title><spring:message code="admin.page_name"/> - <spring:message code="global.app_name"/></title>
    <jsp:include page="../_fragments/static-content-styles.jsp"/>
</head>

<body>
    <jsp:include page="../_fragments/header.jsp"/>


    <main>
        <div class="container">
            <div class="section">
                <h4><spring:message code="admin.header"/></h4>

                <div class="input-field col s12">
                    <select id="positions">
                        <option value="" disabled selected><spring:message code="control.choose_position"/></option>
                        <c:forEach var="pos" items="${positions}">
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
                    <c:when test="${empty indexes}">
                        <hr><br>
                        <h5><spring:message code="admin.nothing_to_show"/></h5>
                    </c:when>

                    <c:otherwise>
                        <ul class="collection">
                            <c:forEach var="index" items="${indexes}">
                                <li class="collection-item avatar">
                                    <c:choose>
                                        <c:when test="${index.archived}">
                                            <i class="material-icons circle red">perm_media</i>
                                        </c:when>

                                        <c:otherwise>
                                            <i class="material-icons circle green">insert_chart</i>
                                        </c:otherwise>
                                    </c:choose>

                                    <span class="title truncate truncate-card-fix">${index.name}</span>

                                    <c:choose>
                                        <c:when test="${empty index.availablePositions}">
                                            <p class="red-text"><spring:message code="admin.unavailable_pos_msg"/></p>
                                        </c:when>

                                        <c:otherwise>
                                            <p><spring:message code="admin.available_pos"/><br>
                                                <c:forEach var="availPos" items="${index.availablePositions}" varStatus="loop">
                                                    ${availPos.name}${!loop.last ? ',' : ''}
                                                </c:forEach>
                                            </p>
                                        </c:otherwise>
                                    </c:choose>

                                    <a href="JavaScript:showEditModal(${index.id})"
                                       class="secondary-content"><i class="material-icons">open_in_new</i></a>
                                    <a href="JavaScript:showDeleteModal(${index.id})"
                                       class="secondary-content-down"><i class="material-icons">delete</i></a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>


        <div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
            <a id="show-add-modal-btn" class="btn-floating btn-large waves-effect waves-light red">
                <i class="material-icons">add</i></a>
        </div>


        <!-- Hidden bundled values -->
        <div id="addIndexHeader" class="hide"><spring:message code="admin.header.add_new_index"/></div>
        <div id="editIndexHeader" class="hide"><spring:message code="admin.header.edit_index"/></div>

        <form id="getIndexForm" class="hide" method="get" action="<c:url value="/admin/dashboard/get"/>"></form>
        <form id="loadIndexesForm" class="hide" method="get" action="<c:url value="/admin/dashboard"/>"></form>


        <!-- Modals -->

        <!-- Add/edit modal -->
        <div id="add-index" class="modal bottom-sheet">
            <div class="modal-content">
                <h4 id="addModalHeader"><!-- This header will be inserted by JS --></h4>
                <form id="index-form" class="col s12" method="post" action="<c:url value="/admin/dashboard/save"/>">
                    <input id="form-id" type="hidden" name="id" value="">
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="isArchived" type="checkbox" name="isArchived"/>
                            <label for="isArchived"><spring:message code="forms.archived"/></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <textarea id="name" type="text" name="name" class="materialize-textarea validate"></textarea>
                            <label for="name"><spring:message code="forms.name"/></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 m6">
                            <input id="estimate" type="number" step="0.01" min="0" name="estimate" class="validate">
                            <label for="estimate"><spring:message code="forms.estimate"/></label>
                        </div>
                        <div class="input-field col s12 m6">
                            <input id="multiplier" type="number" name="multiplier" min="0" class="validate">
                            <label for="multiplier"><spring:message code="forms.work_unit"/></label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 m6">
                            <input id="workName" type="text" name="workName">
                            <label for="workName"><spring:message code="forms.work_name"/></label>
                        </div>
                        <div class="input-field col s12 m6">
                            <select id="selected-poses" multiple>
                                <option value="" disabled selected><spring:message code="forms.pos_choose"/></option>
                                <c:forEach var="pos" items="${positions}">
                                    <option value="${pos.id}">${pos.name}</option>
                                </c:forEach>
                            </select>
                            <label for="selected-poses"><spring:message code="forms.available_pos"/></label>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <a id="add-index-btn" class="waves-effect waves-green btn-flat">
                    <spring:message code="forms.save"/></a>
                <a class="modal-action modal-close waves-effect waves-red btn-flat">
                    <spring:message code="forms.cancel"/></a>
            </div>
        </div>

        <!-- Delete modal -->
        <div id="delete-index" class="modal bottom-sheet">
            <div class="modal-content">
                <h4><spring:message code="admin.header.delete_index"/></h4>
                <form id="delete-form" method="post" action="<c:url value="/admin/dashboard/delete"/>">
                    <input name="id" type="hidden" id="delete-candidate-id" value="">
                </form>
            </div>

            <div class="modal-footer">
                <a id="delete-index-btn" class=" modal-action modal-close waves-effect waves-red btn-flat">
                    <spring:message code="forms.delete"/></a>
                <a class="modal-action modal-close waves-effect waves-green btn-flat">
                    <spring:message code="forms.cancel"/></a>
            </div>
        </div>
    </main>


    <jsp:include page="../_fragments/footer.jsp"/>
    <jsp:include page="../_fragments/static-content-scripts.jsp"/>

    <!-- Custom JS for page -->
    <script src="<c:url value="/resources/js/pages/admin.js"/>"></script>
</body>

</html>