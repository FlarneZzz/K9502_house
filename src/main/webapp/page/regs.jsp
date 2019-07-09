<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/7/1
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0049)http://localhost:8080/HouseRent/page/register.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 - 用户注册</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK
            rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">
</HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
</DIV>
<DIV id=regLogin class=wrap>
    <DIV class=dialog>
        <DL class=clearfix>
            <DT>新用户注册</DT>
            <DD class=past>填写个人信息</DD>
        </DL>
        <DIV class=box>
            <FORM id="regsForm" action="register" method="post">
                <DIV class=infos>
                    <TABLE class=field>
                        <TBODY>
                        <TR>
                            <TD class=field>用 户 名：</TD>
                            <TD><INPUT class=text type=text name=name>
                                <span id="CheckName"></span></TD>
                        </TR>
                        <TR>
                            <TD class=field>密　　码：</TD>
                            <TD><INPUT class=text type=password name=password></TD>
                        </TR>
                        <TR>
                            <TD class=field>确认密码：</TD>
                            <TD><INPUT class=text type=password name=repassword>
                                <span id="CheckPassword"></span></TD>
                        </TR>
                        <TR>
                            <TD class=field>电　　话：</TD>
                            <TD><INPUT class=text type=text name=telephone></TD>
                        </TR>
                        </TBODY>
                    </TABLE>
                    <DIV class=buttons>
                        <INPUT value=立即注册 type=button name="regsBtn">
                    </DIV>
                </DIV>
            </FORM>
        </DIV>
    </DIV>
</DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
        <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD>
    </DL>
</DIV>
</BODY>
<script src="../admin/js/jquery-1.8.3.js"></script>
<script type="application/javascript">

    $(function () {

        $("[name='name']").blur(function () {
            var username = $("[name='name']").val();
            $.post("userConfirm", {"name": username}, function (data) {
                if (data.result == 1) {
                    $("#CheckName").html("用户名已存在").css("color", "red");
                } else {
                    $("#CheckName").html("用户名可用").css("color", "green");
                }
            }, "json")
        });
        $("[name='repassword']").blur(function () {
            var password = $("[name='password']").val();
            var repassword = $("[name='repassword']").val();
            if (password == repassword) {
                $("#CheckPassword").html("密码一致").css("color", "green");
            } else {
                $("#CheckPassword").html("密码不一致").css("color", "red");
            }
        })
    })
    $("[name='regsBtn']").click(function () {
        var flag1 = $("[id='CheckName']").html();
        var flag2 = $("[id='CheckPassword']").html();
        if (flag1=="用户名可用" && flag2 == "密码一致") {
            $("#regsForm").submit();
    }else{
            alert("请重新输入")
        }
    })


</script>
</HTML>
