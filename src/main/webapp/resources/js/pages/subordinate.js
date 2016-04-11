;
var eventParamsForMultipleSelectChanged = {
    params: {
        periodId: "#period",
        year: "#year",
        sub: "#sub"
    },
    urlId: "#sendReloadRequest"
};

var eventParamsForSetupEstimates = {
    fieldName: "leadEstimate",
    missingErrorId: "#fieldMissingError",
    unsupportedErrorId: "#unsupportedMarkError",
    sendFormId: "#sendEstimateForm",
    userId: "#sub"
};


// Set listeners to control events
$("#publish-lead-button").click(eventParamsForSetupEstimates, setupUserIndexEstimates);
$("#period").change(eventParamsForMultipleSelectChanged, multipleSelectChangedReload);
$("#year").change(eventParamsForMultipleSelectChanged, multipleSelectChangedReload);
$("#sub").change(eventParamsForMultipleSelectChanged, multipleSelectChangedReload);