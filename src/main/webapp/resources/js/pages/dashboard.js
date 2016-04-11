;
var eventParamsForMultipleSelectChanged = {
    params: {
        periodId: "#period",
        year: "#year"
    },
    urlId: "#sendReloadRequest"
};

var eventParamsForSetupEstimates = {
    fieldName: "selfEstimate",
    missingErrorId: "#fieldMissingError",
    unsupportedErrorId: "#unsupportedMarkError",
    sendFormId: "#sendEstimateForm"
};


// Set listeners to control events
$("#publish-button").click(eventParamsForSetupEstimates, setupUserIndexEstimates);
$("#period").change(eventParamsForMultipleSelectChanged, multipleSelectChangedReload);
$("#year").change(eventParamsForMultipleSelectChanged, multipleSelectChangedReload);


// Update additional info (user index description and document)
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