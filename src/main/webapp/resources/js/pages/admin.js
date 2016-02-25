;$("#add-index-btn").click(addNewIndex);


function addNewIndex() {
    var form = $("#add-index-form");
    var selectedPoses = $("#selected-poses").val();

    var poses = [];
    $.each(selectedPoses, function(index, value) {
        poses.push({
            id: value,
            name: ""
        });
    });

    var index = form.serializeObject();
    index.availablePositions = poses;

    var jsonIndex = JSON.stringify(index);

    $.ajax({
        url: form.attr("action"),
        type: form.attr("method"),
        dataType: "json",
        data: jsonIndex,
        success: function (result) {
            alert("success: " + result);
        },
        error: function (error) {
            alert("error: " + error);
        }
    });
}