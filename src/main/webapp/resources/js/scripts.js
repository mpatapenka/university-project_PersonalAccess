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

// Validate fields with type 'number' for not negative values
function numberBiggerThenZeroValidator() {
    var isValid = true;
    $("input[type='number']").each(function () {
        var value = $(this);
        value.removeClass("invalid");
        if (parseFloat(value.val().trim()) < 0) {
            value.addClass("invalid");
            isValid = false;
        }
    });
    return isValid;
}

// Validate estimate fields by name
function estimateValidator(estimateFieldName) {
    var isValid = true;
    $("input[name=" + estimateFieldName + "]").each(function () {
        $(this).removeClass("invalid");
        $(this).removeClass("valid");

        var value = $(this).val();
        if (!jQuery.isNumeric(value)
            || parseFloat(value) < parseFloat($(this).attr("min"))
            || parseFloat(value) > parseFloat($(this).attr("max"))) {
            isValid = false;
            $(this).addClass("invalid");
        } else {
            $(this).addClass("valid");
        }
    });
    return isValid;
}


// Check options of all drop downs, and if they valid
// reload page with new parameters
// Store data to event: object with drop downs id's (name 'params'),
//                      id of form with url for reload (name 'urlId')
function multipleComboboxChangedReload(event) {
    var paramsKeys = event.data["params"];
    var params = {};
    for (var key in paramsKeys) {
        if (paramsKeys.hasOwnProperty(key)) {
            var value = $(paramsKeys[key]).val();
            if (!value) {
                return;
            }

            params[key] = value;
        }
    }

    var urlFormId = event.data["urlId"];
    var url = $(urlFormId).attr("action") + "?" + $.param(params);
    location.replace(url);
}

// Set up estimates (lead or self) to user indexes
// Store data to event: field estimate name (name 'fieldName'),
//                      missing error holder id (name 'missingErrorId')
//                      unsupported error holder id (name 'unsupportedErrorId')
//                      form id with send url (name 'sendFormId')
//                      user id (name 'userId', optional)
function setupUserIndexEstimates(event) {
    var estimateFieldName = event.data["fieldName"];
    var missingError = event.data["missingErrorId"];
    var unsupportedError = event.data["unsupportedErrorId"];
    var sendForm = event.data["sendFormId"];
    var userId = event.data["userId"];

    if (!emptyValidator()) {
        Materialize.toast($(missingError).html(), 4000);
        return;
    }

    if (!estimateValidator(estimateFieldName)) {
        Materialize.toast($(unsupportedError).html(), 4000);
        return;
    }

    var userIndexes = collectEstimatesToArrayUserIndexes(estimateFieldName);
    var forConvert = userIndexes;
    if (userId) {
        forConvert = {};
        forConvert[$(userId).val()] = userIndexes;
    }
    var jsonUserIndexes = convertToJsonForTransfer(forConvert);
    var form = $(sendForm);
    $.ajax({
        url: form.attr("action"),
        type: form.attr("method"),
        data: jsonUserIndexes,
        success: function (result) {
            if (!result) {
                console.log("Save user index estimates success.");
                location.reload();
            } else {
                console.log("Server error: " + result);
                Materialize.toast("Server error, validation not complete!");
            }
        },
        error: function (error) {
            console.log("Save user index estimates error: " + error);
            Materialize.toast("Server error when u try to save user index estimates!", 4000);
        }
    });
}

// Collect all estimates field into user index array, which will
// be converted to JSON
function collectEstimatesToArrayUserIndexes(estimateFieldName) {
    var estimates = {};
    $("input[name=" + estimateFieldName + "]").each(function () {
        estimates[$(this).attr("id")] = $(this).val();
    });

    var userIndexes = [];
    for (var key in estimates) {
        if (estimates.hasOwnProperty(key)) {
            var userIndex = {};
            userIndex["id"] = key;
            userIndex[estimateFieldName] = estimates[key];

            userIndexes.push(userIndex);
        }
    }

    return userIndexes;
}


// Converting JS object to json string with replaced '%' by '%25'
// It need for transfer by http protocol
function convertToJsonForTransfer(obj) {
    return JSON.stringify(obj).replace(/%/g, "%25");
}