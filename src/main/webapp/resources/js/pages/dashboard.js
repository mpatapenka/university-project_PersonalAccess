;$("#publish-button").click(publishAll);


function publishAll() {
    var errors = false;

    var selfEstimates = {};
    $("input[name='selfEstimate']").each(function () {
        $(this).removeClass("invalid");
        if (!jQuery.isNumeric($(this).val())) {
            errors = true;
            $(this).addClass("invalid");
        }
        selfEstimates[$(this).attr("id")] = $(this).val();
    });

    if (errors) {
        Materialize.toast("Заполните необходимые поля", 4000);
        return;
    }

    var descriptions = {};
    $("textarea[name='description']").each(function () {
        descriptions[$(this).attr("id")] = $(this).val();
    });

    var documents = {};
    $("input[name='document']").each(function () {
        documents[$(this).attr("id")] = $(this).val();
    });

    var documentNames = {};
    $("input[name='document-name']").each(function () {
        documentNames[$(this).attr("id")] = $(this).val();
    });

    var userIndexes = [];
    for (var key in selfEstimates) {
        if (selfEstimates.hasOwnProperty(key)) {
            var userIndex = {};
            userIndex["selfEstimate"] = selfEstimates[key];
            userIndex["description"] = descriptions[key];
            userIndex["index"] = {
                "id": key
            };
            userIndex["document"] = {
                "name": documentNames[key]
            };

            userIndexes.push(userIndex);
        }
    }

    var jsonUserIndexes = JSON.stringify(userIndexes);

    $.ajax({
        url: "/user/dashboard/publish",
        type: "post",
        data: jsonUserIndexes,
        success: function (result) {
            console.log("success: " + result);

            if (result === "success") {
                location.reload();
            }
        },
        error: function (error) {
            console.log("error: " + error);
        }
    });
}