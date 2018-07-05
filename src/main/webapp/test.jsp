<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Title</title>
<script
	src="http://cdn.static.runoob.com/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
	用户名：
	<input type="text" name="name">
	<br> 密码：
	<input type="text" name="pw">
	<br>
	<input type="button" id="b1" value="提交">
	<input type="button" id="b2" value="测试">
	<br>
</body>
<script>
	var user={
			"userId":1,
			"userName":"",
			"userPassword":"",
			"userPhone":"",
			"userType":"",
			"userImg":"",
			"userIdType":"",
			"userIdCard":"",
			"userBirthday":"",
			"userCompany":"",
			"createTime":"",
			"userLastLoginTime":"",
			"status":"",
	};
    $(function () {
        $("#b1").click(function () {
            $.ajax({
                url: "/fresh/user/test.action",        //后台url
                data: {                          //数据
                    name:$("input[name='name']").val(),
                    pw:$("input[name='pw']").val()
                },
                type: "POST",                   //类型，POST或者GET
                dataType: 'json',              //数据返回类型，可以是xml、json等
                success: function (data) {      //成功，回调函数
                    alert(data.result);
                },
                error: function (er) {          //失败，回调函数
                    alert(er);
                }
            });
        })
		$("#b2").click(function () {
            $.ajax({
                url: "/fresh/user/editItemSubmit_RequestJson.action",        //后台url
                data: JSON.stringify(user),
                type: "POST",                   //类型，POST或者GET
                dataType: 'json',              //数据返回类型，可以是xml、json等
                contentType : 'application/json;charset=UTF-8', //contentType很重要  
                success: function (data) {      //成功，回调函数
                	alert("5454");
                    alert(data.result);
                },
                error: function (er) {          //失败，回调函数
                    alert(er);
                }
            });
        })
    })
</script>
</html>