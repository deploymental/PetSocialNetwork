$(document).ready(function () {
        $("#search").autocomplete({
            source: function (request, response) {
                $.ajax({
                    url: '/getEmployees',
                    data: {
                        filter: request.term
                    },
                    method: "GET",
                    success: function (data) {
                        response($.map(data, function (employee, i) {
                            return {
                                value: employee.fullName,
                                label: employee.fullName,
                                url: '/account/showInfo?id=' + employee.id
                            }
                        }));
                    },
                    error: function () {
                        console.log('myAjax.js Error');
                    }
                });
            },
            minLength: 2,
            select: function (event, ui) {
                location.href = ui.item.url;
            },
            html: true
        });
    }
);