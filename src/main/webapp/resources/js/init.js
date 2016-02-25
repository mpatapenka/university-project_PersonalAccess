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


// Instantiate dropdowns
$(document).ready(function () {
    $('select').material_select();
});


// Replacing serializeObject method for serializing forms to Json
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();

    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};