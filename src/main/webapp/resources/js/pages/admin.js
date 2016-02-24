;$("#add-index-btn").click(addNewIndex);


function addNewIndex() {
    var form = $("#add-index-form");
    var data = form.serialize();
    var selectedPoses = $("#selected-poses").val();

    $.ajax({
        url: form.attr("action"),
        type: form.attr("method"),
        dataType: "json",
        data: decodeURI(data),
        success: function (result) {
            alert("success: " + result);
        },
        error: function (error) {
            alert("error: " + error);
            //location.reload();
        }
    });
}