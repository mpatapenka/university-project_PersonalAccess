;
var eventParamsForMultipleSelectChanged = {
    params: {
        periodId: "#period",
        year: "#year",
        posId: "#positions",
        sortType: "#sort-type"
    },
    urlId: "#sendReloadRequest"
};


// Set listeners to control events
$("#period").change(eventParamsForMultipleSelectChanged, multipleSelectChangedReload);
$("#year").change(eventParamsForMultipleSelectChanged, multipleSelectChangedReload);
$("#positions").change(eventParamsForMultipleSelectChanged, multipleSelectChangedReload);
$("#sort-revert").click(changeSortType);


// Change sort type
function changeSortType() {
    const upwards = "UPWARDS";
    const downwards = "DOWNWARDS";

    var item = $("#sort-type");
    var initial = item.val();
    if (initial === upwards) {
        item.val(downwards);
    } else {
        item.val(upwards);
    }
    
    $("#period").change();
}