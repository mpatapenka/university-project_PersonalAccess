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
            <c:choose>

                <c:when test="${isFilled}">
                    <h5>Показатели за текущий период (${period.dateString}) заполнены</h5>

                    <ul class="collection">
                        <c:forEach var="uIndex" items="${userIndexes}">
                            <li class="collection-item avatar">
                                <i class="material-icons circle orange">assignment</i>
                                <span class="title truncate">${uIndex.index.name}</span>
                                <p>Ваша оценка: ${uIndex.selfEstimate}<br>
                                    Дата заполнения: ${uIndex.formatFillDate}
                                </p>
                                <a href="JavaScript:$('#additional-modal-${uIndex.id}').openModal();"
                                   class="secondary-content"><i class="material-icons">chat_bubble_outline</i></a>

                                <!-- Additional info modal -->
                                <div id="additional-modal-${uIndex.id}" class="modal bottom-sheet">
                                    <div class="modal-content">
                                        <h4>Дополнительная информация</h4>

                                        <hr>

                                        <h5>${uIndex.index.name}</h5><br>
                                        <p>Максимальная оценка ${uIndex.index.estimate} за каждые ${uIndex.index.multiplier} ${uIndex.index.workName}</p>

                                        <c:if test="${not empty uIndex.description}">
                                            <br>
                                            <p>Описание проделанной работы: ${uIndex.description}</p>
                                        </c:if>

                                        <c:if test="${not empty uIndex.document}">
                                            <br>
                                            <p>Скачать прикрепленный документ: <a href="#">${uIndex.document.name}</a></p>
                                        </c:if>
                                    </div>

                                    <div class="modal-footer">
                                        <a class=" modal-action modal-close waves-effect waves-red btn-flat">Закрыть</a>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </c:when>

                <c:otherwise>
                    <h5>Вам необходимо заполнить показатели за текущий период (${period.dateString})</h5>

                    <ul class="collection">
                        <c:forEach var="index" items="${availIndexes}">
                            <li class="collection-item avatar">
                                <i class="material-icons circle orange">assignment</i>
                                <span class="title truncate">${index.name}</span>
                                <p>Ваша оценка:<br>
                                    <input id="${index.id}" name="selfEstimate" type="number" class="validate">
                                </p>
                                <a href="JavaScript:$('#additional-modal-${index.id}').openModal();"
                                   class="secondary-content"><i class="material-icons">chat_bubble_outline</i></a>

                                <!-- Additional info modal -->
                                <div id="additional-modal-${index.id}" class="modal bottom-sheet">
                                    <div class="modal-content">
                                        <h4>Дополнительная информация</h4>

                                        <hr>

                                        <h5>${index.name}</h5><br>
                                        <p>Максимальная оценка ${index.estimate} за каждые ${index.multiplier} ${index.workName}</p>

                                        <div class="row">
                                            <div class="input-field col s12">
                                                <textarea id="${index.id}" name="description" class="materialize-textarea"></textarea>
                                                <label>Описание проделанной работы</label>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="file-field input-field col s12">
                                                <div class="btn">
                                                    <span>Документ</span>
                                                    <input id="${index.id}" type="file" name="document">
                                                </div>
                                                <div class="file-path-wrapper">
                                                    <input id="${index.id}" class="file-path validate" type="text" name="document-name">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="modal-footer">
                                        <a class=" modal-action modal-close waves-effect waves-green btn-flat">Сохранить</a>
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


    <jsp:include page="fragments/footer.jsp"/>
    <jsp:include page="fragments/static-content-js.jsp"/>

    <!-- Custom JS for page -->
    <script src="<c:url value="/resources/js/pages/dashboard.js"/>"></script>

</body>

</html>
