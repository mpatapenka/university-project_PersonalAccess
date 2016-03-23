;
var eventParamsForMultipleComboboxChanged = {
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
$("#period").change(eventParamsForMultipleComboboxChanged, multipleComboboxChangedReload);
$("#year").change(eventParamsForMultipleComboboxChanged, multipleComboboxChangedReload);
$("#sub").change(eventParamsForMultipleComboboxChanged, multipleComboboxChangedReload);