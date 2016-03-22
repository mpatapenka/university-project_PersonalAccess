;
$("#publish-button").click(setupUserIndexEstimates);
$("#period").change({otherId: "year"}, viewUserIndexControlChanged);
$("#year").change({otherId: "period"}, viewUserIndexControlChanged);


function viewUserIndexControlChanged() {
    var period = $("#period").val();
    var year = $("#year").val();

    if (!period || !year) {
        return;
    }

    var url = $("#sendReloadRequest").attr("action") + "?" + $.param({periodId: period, year: year});
    location.replace(url);
}

function setupUserIndexEstimates() {
    if (!emptyValidator()) {
        Materialize.toast($("#fieldMissingError").html(), 4000);
        return;
    }

    if (!estimateValidator()) {
        Materialize.toast($("#unsupportedMarkError").html(), 4000);
        return;
    }

    var selfEstimates = {};
    $("input[name='selfEstimate']").each(function () {
        selfEstimates[$(this).attr("id")] = $(this).val();
    });

    var userIndexes = [];
    for (var key in selfEstimates) {
        if (selfEstimates.hasOwnProperty(key)) {
            var userIndex = {};
            userIndex["id"] = key;
            userIndex["selfEstimate"] = selfEstimates[key];

            userIndexes.push(userIndex);
        }
    }

    var jsonUserIndexes = convertToJsonForTransfer(userIndexes);

    var form = $("#sendEstimateForm");
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

function updateAdditionalInfo(id) {
    var form = $("#additional-form-" + id);
    var formData = new FormData(form[0]);
    $.ajax({
        url: form.attr("action"),
        type: form.attr("method"),
        data: formData,
        contentType: false,
        processData: false,
        success: function (result) {
            if (!result) {
                location.reload();
            } else {
                console.log("Server error: " + result);
                Materialize.toast("Server error when u try to save additional info of user index!", 4000);
            }
        },
        error: function (error) {
            console.log("Update additional info of user index error: " + error);
            Materialize.toast("Server error when u try to save additional info of user index!", 4000);
        }
    });
}


function estimateValidator() {
    var isValid = true;
    $("input[name='selfEstimate']").each(function () {
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