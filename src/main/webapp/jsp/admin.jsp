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
        <div class="section content">



            <c:forEach var="index" items="${indexes}">



            </c:forEach>

        </div>
    </div>

    <div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
        <a class="btn-floating btn-large waves-effect waves-light red" href="JavaScript:$('#add-index').openModal();">
            <i class="material-icons">add</i></a>
    </div>



    <!-- Modals -->
    <div id="add-index" class="modal bottom-sheet">
        <div class="modal-content">
            <h4>Добавление нового индекса</h4>
            <div class="row">
                <form id="add-index-form" class="col s12" method="post" action="<c:url value="/admin/dashboard/new"/>">
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="name" type="text" class="validate">
                            <label for="name">Наименование</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="estimate" type="number" class="validate">
                            <label for="estimate">Оценка</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="multiplier" type="number" class="validate">
                            <label for="multiplier">Единица работы</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="workName" type="text" class="validate">
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
                            <label>Доступные должности</label>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="modal-footer">
            <a id="add-index-btn" class=" modal-action modal-close waves-effect waves-green btn-flat">Добавить</a>
        </div>
    </div>


    <jsp:include page="fragments/footer.jsp"/>
    <jsp:include page="fragments/static-content-js.jsp"/>

    <!-- Custom JS for page -->
    <script src="<c:url value="/resources/js/pages/admin.js"/>"></script>

</body>

</html>