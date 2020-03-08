var elementClass = {};

function doValidate() {
    let formMap = {};
    let formArray = $("#dataform").serializeArray();
    let returnData = null;
    $.each(formArray,function(i, item){
        formMap[item.name] = item.value;
    });
    formMap["dtoId"] = $("#dtoId").val();
    $.ajax({
        url:"/web/validation",
        async:false,
        type:"post",
        data:JSON.stringify(formMap),
        contentType:"application/json",
        dataType:"json",
        success:function (data) {
            returnData = data;
        }
    });
    return returnData;
}

function showErrors(validationResult) {
    clearErrors();
    elementClass = {};
    if (!validationResult.valid) {
        let errorMap = validationResult.errorMsgMap;
        $.each(errorMap, function (name, msg) {
            let input = "input[name='" + name + "']";
            if ($(input).length == 0) {
                input = "select[name='" + name + "']";
                if ($(input).length == 0) {
                    input = "textarea[name='" + name + "']";
                }
            }
            let inputClass = $(input).attr("class");
            elementClass[name] = inputClass;
            $(input).removeClass(inputClass).addClass("is-invalid form-control");
            let span = "<span id='err_span_" + name + "' style='color: #cc3f44'>"+msg+"</span>"
            $(input).after(span);
        });
    }
}

function clearErrors() {
    $.each($("span[id^='err_span']"), function (i, item) {
        $(item).remove();
    });

    $.each($(".is-invalid.form-control"), function (i, item) {
        $(item).removeClass("is-invalid form-control").addClass(elementClass[$(item).attr("name")]);
    });
}