window.onload = function () {
    bootlint.showLintReportForCurrentDocument([], {
        hasProblems: false,
        problemFree: false
    });

    $('[data-toggle="tooltip"]').tooltip();

    function formatDate(date) {
        return (
            date.getDate() +
            "/" +
            (date.getMonth() + 1) +
            "/" +
            date.getFullYear()
        );
    }

    var currentDate = formatDate(new Date());

    $(".due-date-button").datepicker({
        format: "dd/mm/yyyy",
        autoclose: true,
        todayHighlight: true,
        startDate: currentDate,
        orientation: "bottom right"
    });

    $(".due-date-button").on("click", function (event) {
        $(".due-date-button")
            .datepicker("show")
            .on("changeDate", function (dateChangeEvent) {
                $(".due-date-button").datepicker("hide");
                $(".due-date-label").text(formatDate(dateChangeEvent.date));
            });
    });

    $(".due-date-button").datepicker({
        format: "dd/mm/yyyy",
        autoclose: true,
        todayHighlight: true,
        startDate: new Date(),
        orientation: "bottom right"
    });

    $(".due-date-button").on("click", function () {
        $(this).datepicker("show").on("changeDate", function (e) {
            const formattedDate = e.format(); // Получить дату в формате "dd/mm/yyyy"
            $(".due-date-input").val(formattedDate); // Установить значение в скрытое поле
            $(".due-date-label").text(formattedDate).removeClass("d-none"); // Обновить текст метки
        });
    });

};
