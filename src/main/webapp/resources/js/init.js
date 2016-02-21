// Instantiate side navigation bar
;(function ($) {
    $(function () {

        $('.button-collapse').sideNav();

    }); // end of document ready
})(jQuery); // end of jQuery name space

// Instantiate character counter
$(document).ready(function () {
    $('input#input_text, textarea#textarea1').characterCounter();
});