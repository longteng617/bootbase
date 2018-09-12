$(document).ready(function () {
    $("#active").on('click',function(){
        $("#signupForm").submit();
    });
});

$.validator.setDefaults({
    submitHandler: function () {
        active();
    }
});
function active() {
    var authCode = $("#authCode").val();
    console.log(authCode);
    $.ajax({
        type: "POST",
        url: ctx+"active",
        data: {
            authCode : authCode
        },
        success: function (r) {
            if (r.code == 0) {
                var index = layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });
                parent.location.href = '/bootbase/login';
            } else {
                layer.msg(r.msg);
            }
        }
    });
}



function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            authCode: {
                required: true
            }
        },
        messages: {
            authCode: {
                required: icon + "请输入您的授权码",
            }
        }
    })
}