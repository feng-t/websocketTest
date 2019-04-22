function login(){
    var username = $("#username").val();
    var password = $("#password").val();
    $.ajax({
        url:"/user/singin",
        type:"post",
        contentType:"application/json;charset=UTF-8",
        data: JSON.stringify({"username":username,"password":password}),
        success:function(r){
            if (r.falg){
                location.href="/ws"
            }else{
                console.log(r)
            }

        },
        error:function(r){
            console.log(r);
        }
    })

}

function registered(){
    var username = $("#username").val();
    var password = $("#password").val();
    $.ajax({
        url:"/user/singup",
        type:"post",
        contentType:"application/json;charset=UTF-8",
        data: JSON.stringify({"username":username,"password":password,email:"137714477@qq.com",nickname:"jkjkj"}),
        success:function(r){
            if (r.falg){
                location.href="/index"
            }else{
                console.log(r)
            }

        },
        error:function(r){
            console.log(r);
        }
    })
}
