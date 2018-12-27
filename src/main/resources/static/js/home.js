CKEDITOR.replace('editor1');

$("#hdfb").click(function () {
    $("#myModalLabel").text("活动发布");
    $('#myModal').modal();
    $('#upload_id').val('');
    $('#upload_username').val(getCookie('user_id'));
});


function date_update(obj) {
    $('#upload_id').val(obj.name);
    $('#upload_username').val(getCookie('user_id'));
    $("#myModalLabel").text("活动更新");
    $('#myModal').modal();
}

function date_delete(obj) {
    var data = { id: obj.name };
    $.ajax({
        type: "POST",
        url: "/api/deleteActivityById",
        data: data,
        dataType: 'json',
        timeout: 5000,        //请求超时时间，毫秒
        async: false,
        success: function (msg) {
            if (msg.code == 1) {
                alert(msg.data);
                window.location.reload();
            } else {
                alert(msg.data);
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
        if ($("#btn_submit").val != '') {
            var formData = new FormData($('#form1')[0]);
            $.ajax({
                type: 'post',
                url: "/api/updateActivty",
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
            }).success(function (data) {
                alert(data);
            }).error(function (msg) {
                alert("上传失败" + msg);
            });
        }else{
            var formData = new FormData($('#form1')[0]);
            $.ajax({
                type: 'post',
                url: "/api/upload",//接口1
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
            }).success(function (data) {
                alert(data);
            }).error(function (msg) {
                alert("上传失败" + msg);
            });

        }
    });
});


window.onload = function()
{   
    var data= {studentId:getCookie("user_id")};
    $.ajax({
        type:"POST",
        url:"/api/studentName",
        data:data,
        dataType:'json',
        timeout:5000,        //请求超时时间，毫秒
        async: true, 
        success:function(msg){
            console.log(msg)
            if(msg.code == 0)
            {
                $("#username").text(msg.data.studentName);
            }else{
                alert(msg.msg);  
            }
        },
        error:function(){  //请求失败时被调用的函数
            alert("网络超时，请检查网络");
        }
    });

    var html_data = "";
        $.ajax({
        type: "POST",//方法类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/api/getAllActivty",
        success: function (result) {
            for(var i in result.data){
                h_data ='<tr><td><font style="vertical-align: inherit;"><font style="vertical-align:inherit;">'+result.data[i].id+'</font></font></td>'+'<td><font style="vertical-align: inherit;"><font style="vertical-align:inherit;">'+result.data[i].activity_title+'</font></font></td>'+'<td><font style="vertical-align: inherit;"><font style="vertical-align:inherit;">'+result.data[i].username+'</font></font></td>'+'<td><font style="vertical-align: inherit;"><font style="vertical-align:inherit;">'+result.data[i].time+'</font></font></td>'+'<td><img src="'+result.data[i].img_src+'" height="80" width="80"></td>'+'<td><font style="vertical-align: inherit;"><button class="btn btn-primary btn-sm btn-block"  href="javascript:void(0);" onClick="date_update(this)" name="'+result.data[i].id+'">更新</button></font></td>'+'<td><font style="vertical-align: inherit;"><button class="btn btn-primary btn-sm btn-block"  href="javascript:void(0);" onClick="date_delete(this)" name="'+result.data[i].id+'">删除</button></font></td></tr>';
                html_data +=h_data;
            }
            $("#html_table").html(html_data);
        },
        error: function () {
            alert("获取活动数据异常！");
        }
    });
}