$(document).ready(function () {
    $(function () {
        addPhone();
        addMask();
        validatePhones();
        disableDateInput();
    });

    function addPhone() {
        $('#form-update').on('click', '#add-phone', function (e) {
            e.preventDefault();
            addRowForPhone();
        }).on('click', '.remove', function (e) {
            e.preventDefault();
            $(this).parent().remove();
        });

    };

    function addRowForPhone() {
        var phones = $('.update-form').find('#phones');
        var phoneCount = getNumbers($('.input-phone').last().attr('name'));
        var btnAdd = phones.find('.wrap-btn');
        var account = phones.find('#account-id').val();
        var newIn = '<div class="phone"><input type="text" name="phones[' + ++phoneCount + '].phone" id="phone' + phoneCount + '" class="form-phone form-control input-phone" ' +
            'placeholder="+7 (999) 999-9999" data-toggle="popover" data-placement="left" ' +
            'data-content="Поле телефона не может быть пустым">' +
            '<button class="btn btn-danger remove new-btn">-</button></div>';
        btnAdd.before(newIn);
    }

    function getNumbers(inputString) {
        var regex = /\d+\.\d+|\.\d+|\d+/g,
            results = [],
            n;

        while (n = regex.exec(inputString)) {
            results.push(parseFloat(n[0]));
        }

        return results;
    }

    function addMask() {
        $('#form-update').on("focus", ".form-phone", function () {
            var val = $(this).val();
            if (val != '') {
                $(this).attr('value', val);
            } else {
                $(this).attr('value', '+7 ');
            }
            $(this).mask("+7 (999) 999-9999");
        });
    };

    function validatePhones() {
        $('#form-update').on("click", "#modal-btn", function () {
            if (checkAllPhones()) {
                $('#exampleModal').modal('show');
            }
        });
    }

    function checkAllPhones() {
        var flag = true;
        $(".form-phone").each(function () {
            if (!validatePhoneNumber($(this).val())) {
                addContentForPopover($(this));
                $(this).popover("show");
                if ($(this).popover()) {
                    $(this).on('shown.bs.popover', function () {
                        var $pop = $(this);
                        setTimeout(function () {
                            $pop.popover('destroy');
                        }, 3000);
                    });
                }
                flag = false;
            }
        });
        return flag;
    }

    function addContentForPopover(element) {
        if (element.val() !== '') {
            element.attr('data-content', 'Введите телефон в правильном формате');
        }
    }

    function validatePhoneNumber(phone) {
        var pattern = /^\+7(?:[ ])[(]\d{3}[)][ ]\d{3}-\d{4}(?:\s*)$/;
        return pattern.test(phone);
    }

    function disableDateInput() {
        $('#form-update').on("keypress", "#date", function (e) {
            e.preventDefault();
            e.stopPropagation();
        });
    }

    $(function () {
        $('#datetimepicker1').datetimepicker({
            format: 'DD.MM.YYYY'
        });
    });

    function readURL(input) {
        var avatar = $('#form-update').find('.avatar');
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                avatar.attr('src', e.target.result);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#avatar").change(function () {
        readURL(this);
    });

});