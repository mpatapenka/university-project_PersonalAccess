<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>

    <title><spring:message code="global.app_name"/> - <spring:message code="profile.page_name"/></title>

    <jsp:include page="fragments/static-content.jsp"/>

    <link href="<c:url value='/resources/css/pages/profile.css'/>" rel="stylesheet" type="text/css" media="screen,projection"/>

</head>

<body>

    <jsp:include page="fragments/header.jsp"/>


    <div class="container">

        <div class="section content">

            <div class="row">

                <div class="col s12 m12">
                    <div class="col s12 m4 left-side">
                        <img src="<c:url value='/resources/img/profile/profile-image.png'/>"
                             class="circle user-img responsive-img">

                        <div class="block">
                            <h5 class="center flow-text blue-text text-darken2">${userFullName}</h5>
                            <p class="center">${positionName}</p>
                        </div>

                        <hr>

                        <div class="block">
                            <h5 class="left-align"><i class="mdi-action-account-box"></i>
                                Дополнительная информация
                            </h5>
                            <p>Данная страница предназначена для отображения информации о пользователе,
                                его персональная информация, рабочая информация и др.</p>
                            <p>Страница служит только для ознакомление с информацией. Возможность редактирования
                                запрещена.</p>
                        </div>

                        <div class="block">
                            <h5 class="left-align"><i class="mdi-action-perm-phone-msg"></i>
                                Свяжитесь со мной
                            </h5>
                            <p><i class="mdi-maps-pin-drop indigo-text"></i> Гомель, Беларусь<br>
                                <i class="mdi-communication-phone indigo-text"></i> +375 XX XXX-XXXX<br>
                                <i class="mdi-content-link indigo-text"></i>
                                <a href="https://www.gstu.by/" class="indigo-text" target="_blank"> https://www.gstu.by/</a>
                            </p>
                        </div>
                    </div>

                    <div class="col s12 m8 right-side">

                        <h4 class="left-align"><i class="mdi-action-group-work"></i> Личная информация</h4>
                        <div class="block">

                            <h5>${userFulledName}</h5>
                            <p class="helping-text"><i class="mdi-maps-place indigo-text"></i> Гомель <i
                                    class="mdi-action-today indigo-text"></i> 23 Февраля 2016 </p>
                            <p>Какого-то рода личная информация, которая может вестись в виде заметок с привязкой
                                по дате и месту.</p>
                        </div>

                        <h4 class="left-align"><i class="mdi-social-school"></i> Информация с места работы</h4>
                        <div class="block">
                            <h5>Должность</h5>
                            <p class="helping-text"><i class="mdi-maps-place indigo-text"></i> Гомель <i
                                    class="mdi-action-today indigo-text"></i> 24 Февраля 2016</p>
                            <p>Был принят на должность - ${positionName}.</p>
                        </div>
                        <div class="block">
                            <h5>Подразделение</h5>
                            <p class="helping-text"><i class="mdi-maps-place indigo-text"></i> ГГТУ им. П.О. Сухого <i
                                    class="mdi-action-today indigo-text"></i> 25 Февраля 2016</p>
                            <p>Был зачислен в подразделение - ${unitName}.</p>
                        </div>
                        <div class="block">
                            <h5>Факультет</h5>
                            <p class="helping-text"><i class="mdi-maps-place indigo-text"></i> ГГТУ им. П.О. Сухого <i
                                    class="mdi-action-today indigo-text"></i> 25 Февраля 2016</p>
                            <p>Был привязан к факультету - ${facultyShortName}. Полное название - ${facultyFullName}</p>
                        </div>

                        <h4 class="left-align"><i class="mdi-social-school"></i> Штат</h4>
                        <div class="block">
                            <h5>Ваши подчиненные</h5>
                            <p>
                                <c:if test="${empty subs}">
                                    У вас нет ни одного подчиненного.
                                </c:if>
                                <ul class="collection">

                                    <c:forEach var="sub" items="${subs}">
                                        <li class="collection-item avatar">
                                            <img src="<c:url value='/resources/img/profile/profile-image.png'/>" class="circle">
                                            <span class="title">${sub.form.firstName} ${sub.form.lastName}</span>
                                            <p>${sub.form.position.name}</p>
                                        </li>
                                    </c:forEach>

                                </ul>
                            </p>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>


    <jsp:include page="fragments/footer.jsp"/>

    <jsp:include page="fragments/static-content-js.jsp"/>

</body>

</html>
