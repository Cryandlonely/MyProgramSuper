<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>MyProgram</title>
</head>
<style type="text/css">

</style>
<style type="text/css">
    #first{
        position: absolute;
        top: 30%;
        left: 40%;
    }
    .div{
        font-size: 10px;
        color: #FF0000;
    }
    #aaa{
        position: absolute;
        top: 6%;
        left: 103%;
        width: 173px;
        height: 17px;
    }
    #bbb{
        position: absolute;
        left: 103%;
        top: 59px;
        width: 173px;
        height: 15px;
    }
</style>
<body>
<script type="text/javascript">
    window.onload=function(){
        //用户名error
        var Objname=document.getElementById("username")
        Objname.onblur=function(){
            if(Objname.value.trim()==""){
                document.getElementById("aaa").innerText="用户名不能为空"
            }else{
                if(Objname.value.trim().length<3||Objname.value.trim().length>10){
                    document.getElementById("aaa").innerText="用户名应为【3-10】"
                }else{
                    var name=Objname.value.trim()
                    var regExp=/^[A-Za-z0-9]+$/
                    if(!regExp.test(name)){
                        document.getElementById("aaa").innerText="用户名不合法"
                    }
                }
            }
        }
        Objname.onfocus=function(){
            document.getElementById("aaa").innerText=""
        }
        //密码error
        var Test=document.getElementById("password2")
        Test.onblur=function(){
            var passwordTest=document.getElementById("password1").value
            var test=document.getElementById("password2").value
            if(passwordTest!=test){
                document.getElementById("bbb").innerText="两次密码不同"
            }
        }
        Test.onfocus=function(){
            document.getElementById("bbb").innerText=""
        }
        document.getElementById("mybutton").onclick=function(){
            Objname.onfocus()
            Objname.onblur()
            Test.onfocus()
            Test.onblur()
            if(document.getElementById("aaa").innerText==""&&document.getElementById("bbb").innerText==""){
                document.getElementById("ObjFrom").submit()
            }
        }
    }
    function doRegister(){
        window.location="<%=request.getContextPath()%>/register.jsp"
    }
</script>.
<div id="first">
    <table>
        <form action="http://localhost:8080<%=request.getContextPath()%>/program/check" method="post" id="ObjFrom">
            <tr>
                <td>账号</td>
                <td><input type="text" name="username" id="username"/></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input type="text" name="password" id="password1"/></td>
            </tr>
            <tr>
                <td>确认密码</td>
                <td><input type="text" id="password2"/></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center">
                    <input type="button" value="登录" id="mybutton"/>&nbsp;&nbsp;&nbsp;
                    <input type="button" value="注册" onclick="doRegister()">
                    <input type="checkbox" value="1" name="checkbox">10天免登录
                </td>
            </tr>
        </form>
    </table>
    <span id="aaa" class="div"></span>
    <span id="bbb" class="div"></span>
</div>
</body>
</html>
