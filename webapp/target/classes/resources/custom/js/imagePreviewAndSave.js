function previewFile() {
    var preview = document.querySelector('#previewImage');
    var file = document.querySelector('#inputImage').files[0];
    var input = document.querySelector('#imageForm');
    var reader = new FileReader();

    if (file.size > 300000) {
        setPreview(null);
        alert("file is too big");
        return;
    }
    reader.addEventListener("load", function () {
        //document.getElementById("base64textarea").value = reader.result;
        preview.src = reader.result;
        input.value = preview.src;
    }, false);

    if (file) {
        reader.readAsDataURL(file);
    }
}

function setPreview(arg) {
    var preview = document.querySelector('#previewImage');
    var file = document.querySelector('#inputImage');
    var input = document.querySelector('#imageForm');
    if (arg == null) {
        preview.src = "resources/custom/img/default.jpg";
        input.value = null;
        file.value = null;
    } else {
        input.value = arg;
    }
}

//    function setDefaultPreview() {
//        var preview = document.querySelector('#previewImage');
//        var input   = document.querySelector('#imageForm');
//        //if (arg == null) {
//            preview.src = "data:image/png;base64,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";
//            input.value = null;
//        //} else {
//        //    preview.src = arg;
//        //    input.value = arg;
//        //}
//}