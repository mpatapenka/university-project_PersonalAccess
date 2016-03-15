function emptyValidator() {
    var isValid = true;
    $(".validate").each(function () {
        var value = $(this);
        value.removeClass("invalid");
        if (!value.val().trim()) {
            value.addClass("invalid");
            isValid = false;
        }
    });
    return isValid;
}