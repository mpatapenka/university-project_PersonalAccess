;
var eventParamsForMultipleSelectChanged = {
    params: {
        periodId: "#period",
        year: "#year",
        posId: "#positions"
    },
    urlId: "#sendReloadRequest"
};


// Set listeners to control events
$("#period").change(eventParamsForMultipleSelectChanged, multipleSelectChangedReload);
$("#year").change(eventParamsForMultipleSelectChanged, multipleSelectChangedReload);
$("#positions").change(eventParamsForMultipleSelectChanged, multipleSelectChangedReload);