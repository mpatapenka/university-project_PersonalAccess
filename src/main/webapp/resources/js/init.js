// Instantiate materialize css
;$(document).ready(function () {
    $(".button-collapse").sideNav();
    $(".dropdown-button").dropdown();

    $("input#input_text, textarea#textarea1").characterCounter();

    $("select").material_select();
});