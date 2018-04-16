$(document).ready(function () {
    $(document).on('click', '.btn-add', function (e) {
        e.preventDefault();

        var controlForm = $(this).parentsUntil('.contact-table').next(),
            currentEntry = $(controlForm).find('tr:first'),
            newEntry = $(currentEntry.clone()).appendTo(controlForm);

        newEntry.removeClass("hidden");
        var index = newEntry.index();
        newEntry.find('td:first').text(index);

        $(newEntry).find("[name]").each(function () {
            var str = $(this).attr("name").replace(/0/, index);
            $(this).attr("name", str);
        });
    });

    $(document).on('click', '.btn-remove', function (e) {
        e.preventDefault();

        var tBody = $(this).parent().parent().parent();
        $(this).parent().parent().remove();
        $(tBody).find("tr:not(.hidden)").each(function () {
            var idx = $(this).index();
            // console.log("tr idx=" + idx);
            $(this).find('td:first').text(idx);
            $(this).find("[name]").each(function () {
                //var listIndex = idx - 1;
                console.log("tr in each listIndex=" + idx);
                var str1 = $(this).attr("name");
                console.log("old attribute = " + str1);
                var str2 = str1.replace((/d+/), idx);
                console.log("new attribute = " + str2);
                $(this).attr("name", str1);
            });
        });
    });

    $(document).on('click', '')
});
