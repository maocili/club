$("#hdfb").click(function () {
    $("#myModalLabel").text("添加社员");
    $("#studentId").val("");
    $("#departmentName").val("");
    $("#studentName").val("");
    $("#className").val("");
    $('#myModal').modal();
    $('#upload_id').val('-1');
    $('#upload_username').val(getCookie('user_id'));
});


function date_update(obj) {
    $('#upload_id').val(obj.name);
    console.log(obj.name);
    $('#upload_username').val(getCookie('user_id'));
    $("#myModalLabel").text("信息更新");

    $.ajax({
        type: "POST",
        url: "/student/getStudentById",
        data: {studentId: obj.name},
        dataType: 'json',
        timeout: 5000,        //请求超时时间，毫秒
        async: false,
        success: function (msg) {
            console.log(msg);
            $("#studentId").val(msg.data.studentId);
            $("#studentName").val(msg.data.studentName);
            $("#departmentName").val(msg.data.departmentName);
            $("#className").val(msg.data.className);
            $("#className").val(msg.data.className);

        },
        error: function () {  //请求失败时被调用的函数
            alert("网络超时，请检查网络");
        }
    });

    $('#myModal').modal();
}

function date_delete(obj) {
    var data = {studentId: obj.name};
    $.ajax({
        type: "POST",
        url: "/student/deleteStudentById",
        data: data,
        dataType: 'json',
        timeout: 5000,        //请求超时时间，毫秒
        async: false,
        success: function (msg) {
            if (msg.code == 1) {
                alert(msg.msg);
                window.location.reload();
            } else {
                alert(msg.msg);
            }
        },
        error: function () {  //请求失败时被调用的函数
            alert("网络超时，请检查网络");
        }
    });
}

function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

function CKupdate() {
    for (instance in CKEDITOR.instances)
        CKEDITOR.instances[instance].updateElement();
}

$(function () {
    $("#btn_submit").click(function () {
        CKupdate();
        var formData = new FormData($('#form1')[0]);
        $.ajax({
            type: 'post',
            url: "/student/addStudent",
            data: formData,
            cache: false,
            processData: false,
            contentType: false,
            success: function (data) {
                alert(data.msg);
                console.log(data);
            },
            error: function () {  //请求失败时被调用的函数
                alert("更新失败");
            }
        });
    });
});


    window.onload = function () {
        var data = {studentId: getCookie("user_id")};
        $.ajax({
            type: "POST",
            url: "/api/studentName",
            data: data,
            dataType: 'json',
            timeout: 5000,        //请求超时时间，毫秒
            async: true,
            success: function (msg) {
                console.log(msg)
                if (msg.code == 0) {
                    $("#username").text(msg.data.studentName);
                } else {
                    alert(msg.msg);
                    window.location.href = "/admin";
                }
            },
            error: function () {  //请求失败时被调用的函数
                alert("网络超时，请检查网络");
            }
        });

        var html_data = "";
        $.ajax({
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/student/getAllStudent",
            success: function (result) {
                console.log(result);
                for (var i in result.data) {
                    h_data = '<tr><td><font style="vertical-align: inherit;"><font style="vertical-align:inherit;">' + result.data[i].studentId + '</font></font></td>' + '<td><font style="vertical-align: inherit;"><font style="vertical-align:inherit;">' + result.data[i].studentName + '</font></font></td>' + '<td><font style="vertical-align: inherit;"><font style="vertical-align:inherit;">' + result.data[i].departmentName + '</font></font></td>' + '<td><font style="vertical-align: inherit;"><font style="vertical-align:inherit;">' + result.data[i].className + '</font></font></td><td><font style="vertical-align: inherit;"><button class="btn btn-primary btn-sm btn-block"  href="javascript:void(0);" onClick="date_delete(this)" name="' + result.data[i].studentId + '">删除</button></font></td></tr>';
                    html_data += h_data;
                }
                $("#html_table").html(html_data);
            },
            error: function () {
                alert("获取活动数据异常！");
            }
        });
    }
