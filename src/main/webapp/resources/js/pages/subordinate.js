;$("#search-btn").click(search);
$("#publish-btn").click(publish);

function search() {
    var period = $("#period").val();
    var year = $("#year").val();
    var sub = $("#sub").val();

    if (period === null || year === null || sub === null) {
        Materialize.toast("Проверьте введенные данные", 4000);
        return;
    }

    $.ajax({
        url: "/user/subs?search",
        type: "get",
        contentType: "text/plain; charset=UTF-8",
        data: {
            periodNum: period,
            year: year,
            userId: sub
        },
        success: function (result) {
            console.log("success: " + result);

            $("#card-container").empty();

            var indexes = JSON.parse(result);
            var content = "<ul class='collection'>";
            for (var i = 0; i < indexes.length; i++) {
                var card = createItemContext(indexes[i]);
                content += card;
            }
            content += "</ul>"

            $("#card-container").append(content);

            var btnBox = $("#float-box");
            btnBox.addClass("click-to-toggle");
            btnBox.empty();

            var btnContent = "<a class='btn-floating btn-large red'><i class='large mdi-navigation-menu'></i></a>";
            btnContent += "<ul><li>";
            btnContent += "<a class='btn-floating cyan darken-3' id='search-btn'><i class='material-icons'>youtube_searched_for</i></a>";
            btnContent += "</li><li>";
            btnContent += "<a class='btn-floating green' id='publish-btn'><i class='material-icons'>publish</i></a>";
            btnContent += "</li></ul>";

            btnBox.append(btnContent);
        },
        error: function (error) {
            console.log("error: " + error);
            Materialize.toast("Во время поиска произошла ошибка", 4000);
        }
    });
}

function publish() {
    var error = false;

    var leadEstimates = {};
    $("input[name='leadEstimate']").each(function () {
        $(this).removeClass("invalid");
        if (!jQuery.isNumeric($(this).val())) {
            errors = true;
            $(this).addClass("invalid");
        }
        leadEstimates[$(this).attr("id")] = $(this).val();
    });

    if (errors) {
        Materialize.toast("Заполните необходимые поля", 4000);
        return;
    }


}


function createItemContext(index) {
    var card = "<li class='collection-item avatar'><i class='material-icons circle green'>"
        + "insert_chart</i><span class='title truncate'>";
    card += index.index.name;
    card += "</span>";
    card += "<p>Оценка подчиненного: " + index.selfEstimate + "<br>";
    card += "Дата заполнения: " + index.fillDate + "</p>";
    card += "<p>Ваша оценка:<br><input id=" + index.id + " name='leadEstimate' type='umber' class='validate'></p>"
    card += "<a href='JavaScript:$(\"#additional-modal-" + index.id + "\").openModal();' class='secondary-content'>"
        + "<i class='material-icons'>chat_bubble_outline</i></a>";
    card += "<div id='additional-modal-"+ index.id +"' class='modal bottom-sheet'>";
    card += "<div class='modal-content'><h4>Дополнительная информация</h4><hr>";
    card += "<h5>" + index.index.name + "</h5><br>";
    card += "<p>Максимальная оценка " + index.index.estimate + " за каждые " + index.index.multiplier + " " + index.index.workName + "</p>";

    if (index.description !== null && index.description !== undefined) {
        card += "<br><p>Описание проделанной работы: " + index.description + "</p>";
    }

    if (index.document !== null && index.document !== undefined) {
        card += "<br><p>Скачать прикрепленный документ: <a href='#'>" + index.document.name + "</a></p>";
    }

    card += "</div>";

    card += "<div class='modal-footer'><a class='modal-action modal-close waves-effect waves-red btn-flat'>Закрыть</a>";
    card += "</div>";

    return card;
}