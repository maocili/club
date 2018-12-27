function getCookie(name) 
{ 
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]); 
    else 
        return null; 
} 
$(function(){ 
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

});