;$("#search-btn").click(search);

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
        },
        error: function (error) {
            console.log("error: " + error);
            Materialize.toast("Во время поиска произошла ошибка", 4000);
        }
    });
}

function createItemContext(index) {
    var card = "<li class='collection-item avatar'><i class='material-icons circle green'>"
        + "insert_chart</i><span class='title truncate'>";
    card += index.index.name;
    card += "</span>";
    card += "<p>Оценка подчиненного: " + index.selfEstimate + "<br>";
    card += "Дата заполнения: " + index.fillDate + "</p>";
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