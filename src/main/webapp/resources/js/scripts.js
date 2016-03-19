// Validate fields with class 'validate' for empty value
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

// Converting JS object to json string with replaced '%' by '%25'
// It need for transfer by http protocol
function convertToJsonForTransfer(obj) {
    return JSON.stringify(obj).replace(/%/g, "%25");
}