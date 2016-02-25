;$("#add-index-btn").click(addNewIndex);


function addNewIndex() {
    var form = $("#index-form");
    var selectedPoses = $("#selected-poses").val();

    var poses = [];
    $.each(selectedPoses, function(index, value) {
        poses.push({
            id: value
        });
    });

    var index = form.serializeObject();
    !index.id && delete index.id;
    index.availablePositions = poses;

    var jsonIndex = JSON.stringify(index);

    $.ajax({
        url: form.attr("action"),
        type: form.attr("method"),
        data: jsonIndex,
        success: function (result) {
            console.log("success: " + result);
            location.reload();
        },
        error: function (error) {
            console.log("error: " + error);
            location.reload();
        }
    });
}

function openClearForm() {
    $('#index-form')[0].reset();
    $("input").next("label").removeClass("active");
    $("#selected-poses").val([]);
    $('select').material_select();
    $('#add-index').openModal();
}

function loadIndex(id) {
    $.ajax({
        url: "/admin/dashboard/get?id=" + id,
        type: "get",
        contentType: "text/plain; charset=UTF-8",
        success: function (result) {
            console.log("success: " + result);

            var obj = JSON.parse(result);
            $("#form-id").val(obj.id);
            $("#name").val(obj.name);
            $("#estimate").val(obj.estimate);
            $("#multiplier").val(obj.multiplier);
            $("#workName").val(obj.workName);

            var selected = [];
            obj.availablePositions.forEach(function (entry) {
                selected.push(entry.id);
            });
            $("#selected-poses").val(selected);
            $('select').material_select();

            $("input[type!=checkbox]").next("label").addClass("active");
            $('#add-index').openModal();
        },
        error: function (error) {
            console.log("error: " + error);
            Materialize.toast('Ошибка загрузки показателя!', 4000);
        }
    });
}