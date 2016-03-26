<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>

<html>

<head>
    <jsp:include page="../_fragments/static-content-meta.jsp"/>
    <title><spring:message code="profile.page_name"/> - <spring:message code="global.app_name"/></title>
    <jsp:include page="../_fragments/static-content-styles.jsp"/>
</head>

<body>
    <jsp:include page="../_fragments/header.jsp"/>


    <div class="container">
        <div class="section content">
            <div class="row">
                <div class="col s12 m12">
                    <div class="col s12 m4 left-side">
                        <img src="<c:url value='/resources/img/profile/profile-image.png'/>"
                             class="circle user-img responsive-img">

                        <div class="block">
                            <h5 class="center flow-text blue-text text-darken2">${uForm.firstName} ${uForm.lastName}</h5>
                            <p class="center">${uForm.position.name}</p>
                        </div>

                        <hr>

                        <div class="block">
                            <h5 class="left-align"><i class="mdi-action-account-box"></i>
                                <spring:message code="profile.additional_info_header"/>
                            </h5>
                            <p><spring:message code="profile.additional_info_content1"/></p>
                            <p><spring:message code="profile.additional_info_content2"/></p>
                        </div>

                        <div class="block">
                            <h5 class="left-align"><i class="mdi-action-perm-phone-msg"></i>
                                <spring:message code="profile.connect_me"/>
                            </h5>
                            <p><i class="mdi-maps-pin-drop indigo-text"></i> <spring:message code="global.location.gomel_full"/><br>
                                <i class="mdi-communication-phone indigo-text"></i> +375 XX XXX-XXXX<br>
                                <i class="mdi-content-link indigo-text"></i>
                                <a href="https://www.gstu.by/" class="indigo-text" target="_blank"> https://www.gstu.by/</a>
                            </p>
                        </div>
                    </div>

                    <div class="col s12 m8 right-side">

                        <h4 class="left-align"><i class="mdi-action-group-work"></i> <spring:message code="profile.personal_info"/></h4>
                        <div class="block">
                            <h5>${uForm.lastName} ${uForm.firstName} ${uForm.middleName}</h5>
                            <p class="helping-text"><i class="mdi-maps-place indigo-text"></i> <spring:message code="global.location.gomel"/>
                                <i class="mdi-action-today indigo-text"></i> 23 Февраля 2016 </p>
                            <p>Какого-то рода личная информация, которая может вестись в виде заметок с привязкой
                                по дате и месту.</p>
                        </div>

                        <h4 class="left-align"><i class="mdi-social-school"></i> <spring:message code="profile.work_info"/></h4>
                        <div class="block">
                            <h5><spring:message code="profile.position"/></h5>
                            <p class="helping-text"><i class="mdi-maps-place indigo-text"></i> <spring:message code="global.location.gomel"/> <i
                                    class="mdi-action-today indigo-text"></i> 24 Февраля 2016</p>
                            <p>Был принят на должность - ${uForm.position.name}.</p>
                        </div>
                        <div class="block">
                            <h5><spring:message code="profile.unit"/></h5>
                            <p class="helping-text"><i class="mdi-maps-place indigo-text"></i> <spring:message code="global.location.gstu"/>
                                <i class="mdi-action-today indigo-text"></i> 25 Февраля 2016</p>
                            <p>Был зачислен в подразделение - ${uForm.unit.name}.</p>
                        </div>
                        <div class="block">
                            <h5><spring:message code="profile.faculty"/></h5>
                            <p class="helping-text"><i class="mdi-maps-place indigo-text"></i> <spring:message code="global.location.gstu"/>
                                <i class="mdi-action-today indigo-text"></i> 25 Февраля 2016</p>
                            <p>Был привязан к факультету - ${uForm.faculty.shortName}. Полное название - ${uForm.faculty.fullName}</p>
                        </div>

                        <h4 class="left-align"><i class="mdi-social-school"></i> Штат</h4>
                        <div class="block">
                            <h5><spring:message code="profile.subs"/></h5>
                            <p>
                                <c:choose>
                                    <c:when test="${empty subs}">
                                        <spring:message code="profile.no_subs_msg"/>
                                    </c:when>

                                    <c:otherwise>
                                        <ul class="collection">
                                            <c:forEach var="sub" items="${subs}">
                                                <li class="collection-item avatar">
                                                    <img src="<c:url value='/resources/img/profile/profile-image.png'/>" class="circle">
                                                    <span class="title">${sub.form.firstName} ${sub.form.lastName}</span>
                                                    <p>
                                                        <spring:message code="profile.position"/> - ${sub.form.position.name}<br>
                                                        <spring:message code="profile.unit"/> - ${sub.form.unit.name}
                                                    </p>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <jsp:include page="../_fragments/footer.jsp"/>
    <jsp:include page="../_fragments/static-content-scripts.jsp"/>
</body>

</html>
