<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>

    <title><spring:message code="admin.page_name"/> - <spring:message code="global.app_name"/></title>

    <jsp:include page="fragments/static-content.jsp"/>

</head>

<body>

    <jsp:include page="fragments/header.jsp"/>


    <div class="container">
        <div class="section">

            <h4>Набор показателей</h4>

            <ul class="collection">
                <c:forEach var="index" items="${indexes}">
                    <li class="collection-item avatar">
                        <i class="material-icons circle green">insert_chart</i>
                        <span class="title truncate">${index.name}</span>
                        <p>Доступные должности:<br>
                            <c:forEach var="availPos" items="${index.availablePositions}" varStatus="loop">
                                ${availPos.name}${!loop.last ? ',' : ''}
                            </c:forEach>
                        </p>
                        <a href="JavaScript:loadIndex(${index.id})"
                           class="secondary-content"><i class="material-icons">open_in_new</i></a>

                        <a href="JavaScript:openDeleteModal(${index.id})"
                           class="secondary-content-down"><i class="material-icons">delete</i></a>
                    </li>
                </c:forEach>
            </ul>

        </div>
    </div>

    <div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
        <a class="btn-floating btn-large waves-effect waves-light red" href="JavaScript:openClearForm();">
            <i class="material-icons">add</i></a>
    </div>



    <!-- Modals -->

    <!-- Add/edit modal -->
    <div id="add-index" class="modal bottom-sheet">
        <div class="modal-content">
            <h4>Добавление/изменение показателя</h4>
            <div class="row">
                <form id="index-form" class="col s12" method="post" action="<c:url value="/admin/dashboard/save"/>">
                    <input id="form-id" type="hidden" name="id" value="">
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="name" type="text" name="name" class="validate">
                            <label for="name">Наименование</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="estimate" type="number" name="estimate" class="validate">
                            <label for="estimate">Оценка</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="multiplier" type="number" name="multiplier" class="validate">
                            <label for="multiplier">Единица работы</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="workName" type="text" name="workName" class="validate">
                            <label for="workName">Наименование работы</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <select id="selected-poses" multiple>
                                <option value="" disabled selected>Выберите должности</option>
                                <c:forEach var="pos" items="${positions}">
                                    <option value="${pos.id}">${pos.name}</option>
                                </c:forEach>
                            </select>
                            <label for="selected-poses">Доступные должности</label>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="modal-footer">
            <a id="add-index-btn" class=" modal-action modal-close waves-effect waves-green btn-flat">Сохранить</a>
        </div>
    </div>

    <!-- Delete modal -->
    <div id="delete-index" class="modal bottom-sheet">
        <div class="modal-content">
            <h4>Вы действительно хотите удалить показатель?</h4>
            <input type="hidden" id="delete-candidate-id" value="">
        </div>

        <div class="modal-footer">
            <a href="JavaScript:deleteIndex();" class=" modal-action modal-close waves-effect waves-red btn-flat">Удалить</a>
            <a class="modal-action modal-close waves-effect waves-orange btn-flat">Отмена</a>
        </div>
    </div>


    <jsp:include page="fragments/footer.jsp"/>
    <jsp:include page="fragments/static-content-js.jsp"/>

    <!-- Custom JS for page -->
    <script src="<c:url value="/resources/js/pages/admin.js"/>"></script>

</body>

</html>