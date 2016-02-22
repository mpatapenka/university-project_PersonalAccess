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
</head>

<body>

    <jsp:include page="fragments/header.jsp"/>


    <div class="container">

        <div class="section content">

            <div class="row">
                <div class="col s12 m12">
                    <div class="col s12 m4 left-side">
                        <img src="./Starter Template - Materialize_files/user.jpg" alt=""
                             class="circle user-img responsive-img">
                        <div class="block">
                            <h5 class="center indigo-text">Waqas Hussain</h5>
                            <p class="light center">Creative Designer</p>
                        </div>
                        <hr>
                        <div class="block">
                            <h5 class="left-align"><i class="mdi-action-account-box"></i>
                                About me
                            </h5>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there
                                live the blind texts.</p>
                            <p>A small river named Duden flows by their place and supplies it with the necessary regelialia. It
                                is a paradisematic country, in which roasted parts. </p>
                        </div>
                        <div class="block">
                            <h5 class="left-align"><i class="mdi-action-perm-phone-msg"></i>
                                Contact me
                            </h5>
                            <p><i class="mdi-maps-pin-drop indigo-text"></i> Chakwal, Punjab, Pakistan<br>
                                <i class="mdi-communication-phone indigo-text"></i> +92 0332 000 0000<br>
                                <i class="mdi-content-link indigo-text"></i> <a href="http://www.mirchu.net/"
                                                                                class="indigo-text" target="_blank">http://www.mirchu.net/</a>
                            </p>
                        </div>
                        <div class="block">
                            <h5 class="left-align"><i class="mdi-action-stars"></i>
                                My Skills
                            </h5>
                            <h6>Adobe PhotoShop</h6>
                            <div class="progress">
                                <div class="progress-bar" style="width: 60%;"></div>
                            </div>
                            <h6>Web Design</h6>
                            <div class="progress">
                                <div class="progress-bar" style="width: 95%;"></div>
                            </div>
                            <h6>Print Design</h6>
                            <div class="progress">
                                <div class="progress-bar" style="width: 35%;"></div>
                            </div>
                            <h6>Wordpress</h6>
                            <div class="progress">
                                <div class="progress-bar" style="width: 65%;"></div>
                            </div>
                            <h6>Bootstrap</h6>
                            <div class="progress">
                                <div class="progress-bar" style="width: 99%;"></div>
                            </div>
                        </div>
                    </div>

                    <div class="col s12 m8 right-side">
                        <h4 class="left-align"><i class="mdi-action-group-work"></i> Work Experience</h4>
                        <div class="block">

                            <h5>Creative Designer</h5>
                            <p class="helping-text"><i class="mdi-maps-place indigo-text"></i> Mirchu <i
                                    class="mdi-action-today indigo-text"></i> 21 Feb 2012</p>
                            <p>By utilizing elements and principles of Material Design, we were able to create a framework that
                                incorporates components and animations that provide more feedback to users. Additionally, a
                                single underlying responsive system across all platforms allow for a more unified user
                                experience.</p>
                        </div>
                        <div class="block">

                            <h5>Senior Web Designer</h5>
                            <p class="helping-text"><i class="mdi-maps-place indigo-text"></i> WorldFellow <i
                                    class="mdi-action-today indigo-text"></i> 27 Jan 2009</p>
                            <p>By utilizing elements and principles of Material Design, we were able to create a framework that
                                incorporates components and animations that provide more feedback to users.</p>
                        </div>
                        <h4 class="left-align"><i class="mdi-social-school"></i> Education</h4>
                        <div class="block">

                            <h5>Master of Design</h5>
                            <p class="helping-text"><i class="mdi-maps-place indigo-text"></i> AIO University <i
                                    class="mdi-action-today indigo-text"></i> 27 Aug 2007</p>
                            <p>By utilizing elements and principles of Material Design, we were able to create a framework that
                                incorporates components and animations that provide more feedback to users. Additionally, a
                                single underlying responsive system across all platforms allow for a more unified user
                                experience. A small river named Duden flows by their place and supplies it with the necessary
                                regelialia. It is a paradisematic country, in which roasted parts.</p>
                        </div>
                        <div class="block">

                            <h5>Bachelor of Science(CS)</h5>
                            <p class="helping-text"><i class="mdi-maps-place indigo-text"></i> Myer's University <i
                                    class="mdi-action-today indigo-text"></i> 27 Aug 2005</p>
                            <p>By utilizing elements and principles of Material Design, we were able to create a framework that
                                incorporates components and animations that provide more feedback to users. </p>
                        </div>
                        <h4 class="left-align"><i class="mdi-social-pages"></i> Portfolio</h4>
                        <div class="block">
                            <div class="row">
                                <div class="col m6 s12">
                                    <div class="card">
                                        <div class="card-image">
                                            <img src="./Starter Template - Materialize_files/workstation1.jpg" alt="work">
                                            <div class="card-content">
                                        <span class="card-title grey-text darken-4">Mirchu <i
                                                class="mdi-navigation-more-vert right"></i></span>

                                                <p><a href="http://www.mirchu.net/">This is a link</a></p>
                                            </div>
                                            <div class="card-reveal">
                                        <span class="card-title grey-text darken-4">Mirchu <i
                                                class="mdi-navigation-close right"></i></span>
                                                <p>Web and technology | News and updates. Mirchu.net is Web and Technology Blog.
                                                    We represent easy and unique Information related to the Technology and Web
                                                    trends.</p>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="col m6 s12">
                                    <div class="card">
                                        <div class="card-image">
                                            <img src="./Starter Template - Materialize_files/workstation2.jpg" alt="work">
                                            <div class="card-content">
                                        <span class="card-title grey-text darken-4">ThemeSafari <i
                                                class="mdi-navigation-more-vert right"></i></span>

                                                <p><a href="http://www.themesafari.net/">This is a link</a></p>
                                            </div>
                                            <div class="card-reveal">
                                        <span class="card-title grey-text darken-4">ThemeSafari <i
                                                class="mdi-navigation-close right"></i></span>
                                                <p>Free Responsive bootstrap Themes. Download and share with friends.</p>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div>
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
