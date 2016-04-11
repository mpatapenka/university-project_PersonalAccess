// Instantiate materialize css
;$(document).ready(function () {
    $(".button-collapse").sideNav();
    $(".dropdown-button").dropdown({
        constrain_width: false,
        belowOrigin: true
    });

    $("input#input_text, textarea#textarea1").characterCounter();

    $("select").material_select();
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