$(document).ready(function () {
    $(function () {
        getSearchResult();
    });

    function getSearchResult() {
        $("#search").autocomplete({
            source: function (request, response) {
                $.ajax({
                    url: ctx + '/autocomplete',
                    data: {
                        search: request.term
                    },
                    success: function (data) {
                        response($.map(data, function (account, i) {
                            return {
                                value: request.term,
                                id: account.id,
                                label: account.firstName + " " + account.lastName
                            }
                        }));
                    },
                });
            },
            select: function (event, ui) {
                window.location = ctx + '/account/' + ui.item.id;
            },
            minLength: 2
        });
    }
});
