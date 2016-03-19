;
$("#add-index-btn").click(saveIndex);
$("#delete-index-btn").click(deleteIndex);
$("#show-add-modal-btn").click(showAddModal);



function saveIndex() {
    if (!emptyValidator()) {
        return;
    }

    if (!numberBiggerThenZeroValidator()) {
        return;
    }

    var form = $("#index-form");
    var selectedPoses = $("#selected-poses").val();

    var poses = [];
    $.each(selectedPoses, function(index, value) {
        poses.push({
            id: value
        });
    });

    var index = form.serializeObject();
    // Delete index if not exist. That need for use one method for add and edit index
    !index.id && delete index.id;
    index.availablePositions = poses;

    // Little trick for send percent sign by request
    var jsonIndex = JSON.stringify(index).replace(/%/g, "%25");

    $.ajax({
        url: form.attr("action"),
        type: form.attr("method"),
        data: jsonIndex,
        success: function (result) {
            console.log("Save index success: " + result);
            location.reload();
        },
        error: function (error) {
            console.log("Save index error: " + error);
            Materialize.toast("Server error when u try to save the index!", 4000);
        }
    });
}

function deleteIndex() {
    var form = $("#delete-form");
    $.ajax({
        url: form.attr("action"),
        type: form.attr("method"),
        data: form.serialize(),
        success: function (result) {
            console.log("Delete index success: " + result);
            location.reload();
        },
        error: function (error) {
            console.log("Delete index error: " + error);
            Materialize.toast("Server error when u try to delete index!", 4000);
        }
    });
}



function showAddModal() {
    $("#addModalHeader").html($("#addIndexHeader").html());
    $("#index-form")[0].reset();
    $("input,textarea").next("label").removeClass("active");
    $("#selected-poses").val([]);
    $("select").material_select();
    $('#add-index').openModal();
}

function showEditModal(id) {
    $("#addModalHeader").html($("#editIndexHeader").html());
    $.ajax({
        url: "/admin/dashboard/get?id=" + id,
        type: "get",
        contentType: "text/plain; charset=UTF-8",
        success: function (result) {
            console.log("Show edit modal success.");

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
            $("select").material_select();

            $("textarea,input[type!=checkbox]").next("label").addClass("active");
            $("#add-index").openModal();
        },
        error: function (error) {
            console.log("Show edit modal error: " + error);
            Materialize.toast("Server error when u try to get available Index for edit!", 4000);
        }
    });
}

function showDeleteModal(id) {
    $("#delete-candidate-id").val(id);
    $("#delete-index").openModal();
}