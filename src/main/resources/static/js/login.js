$("#login_btn").click(function(){
    var username = $.trim($("#username").val());
    var password = $.trim($("#password").val());
     if(username == ""){
         alert("请输入用户名");
         return false;
     }else if(password == ""){
         alert("请输入密码");
         return false;
     }
     //ajax去服务器端校验
     var data= {username:username,password:password};
     $.ajax({
         type:"POST",
         url:"/api/admin",
         data:data,
         dataType:'json',
         timeout:5000,        //请求超时时间，毫秒
         async: true, 
         success:function(msg){
             if(msg.code == 0)
             {
                 document.cookie="user_id="+msg.data.studentId;
                 alert(msg.msg);
                 window.location.href="admin/Home"
             }else{
                 alert(msg.msg);  
             }
         },
         error:function(){  //请求失败时被调用的函数
             alert("网络超时，请检查网络");
         }
     });
});    