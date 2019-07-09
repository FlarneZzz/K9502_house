<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/7/1
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type><LINK
            rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514"></HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
    <DIV class=dialog>
        <DL class=clearfix>
            <DT>新房屋信息发布</DT>
            <DD class=past>填写房屋信息</DD></DL>
        <DIV class=box>
            <FORM id=add_action method=post name=add.action
                  action=updateHouse1 enctype="multipart/form-data">
                <DIV class=infos>
                    <TABLE class=field>
                        <TBODY>
                        <TR>
                            <input type="hidden" name="id" value="${singleHouseByPrimaryKey.id}">
                            <TD class=field>标　　题：</TD>
                            <TD><INPUT id=add_action_title class=text type=text name=title value="${singleHouseByPrimaryKey.title}"> </TD></TR>
                        <TR>
                            <TD class=field>户　　型：</TD>
                            <TD><SELECT class=text name=typeId>
                                <OPTION  value="-1">请选择</OPTION>
                            </SELECT></TD></TR>
                        <TR>
                            <TD class=field>面　　积：</TD>
                            <TD><INPUT id=add_action_floorage class=text type=text
                                       name=floorage value="${singleHouseByPrimaryKey.floorage}"></TD></TR>
                        <TR>
                            <TD class=field>价　　格：</TD>
                            <TD><INPUT id=add_action_price class=text type=text name=price value="${singleHouseByPrimaryKey.price}"> </TD></TR>
                        <TR>
                            <TD class=field>发布日期：</TD>

                            <TD><INPUT class=text type=date name=pubdate value="<fmt:formatDate value="${singleHouseByPrimaryKey.pubdate}" pattern="yyyy-MM-dd"></fmt:formatDate>" ></TD></TR>
                        <TR>
                            <TD class=field>位　　置：</TD>
                            <TD>区：
                                <SELECT class=text name=district_id>
                                <OPTION selected value="-1">请选择</OPTION>
                            </SELECT> 街：
                                <SELECT class=text name=streetId>
                                    <OPTION selected value="">请选择</OPTION>
                                </SELECT> </TD></TR><!--
						<tr>
							<td class="field">坐  标：</td>
							<td><input type="text" class="text" name="point" />
							</td>
						</tr>
						--><!--  <tr>
							<td class="field">Y 坐  标：</td>
							<td><input type="text" class="text" name="point.y" /></td>
						</tr>-->
                        <TR>
                            <TD class=field>联系方式：</TD>
                            <TD><INPUT id=add_action_contact class=text type=text name=contact value="${singleHouseByPrimaryKey.contact}" }> </TD></TR>
                        <TR>
                            <TD class=field>图片：</TD>
                            <TD><INPUT  class=text type=file name=picfile> </TD></TR>
                        <img src="http://localhost:80/${singleHouseByPrimaryKey.path}" alt="">
                        <input type="hidden" value="${singleHouseByPrimaryKey.path}" name="oldpath">
                        <TR>
                            <TD class=field>详细信息：</TD>
                            <TD><TEXTAREA name=description>${singleHouseByPrimaryKey.description}</TEXTAREA></TD></TR></TBODY></TABLE>
                    <DIV class=buttons>
                        <INPUT  value=立即更新 type=submit>
                    </DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
    <DL>
        <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
        <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV>
</BODY>
<script src="../admin/js/jquery-1.8.3.js"></script>
<script type="application/javascript">
   $(function () {
       $("[name='district_id']").change(function () {
           var id=$("[name='district_id']").val();
           $.post(
               "getStreetById",
               {"id":id},
               function (data) {
                 $("[name='streetId']>option:gt(0)").remove();
                   for (var i = 0; i < data.length; i++) {
                       var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
                       $("[name='streetId']").append(node)
                   }
                   //设置选中项
                   $("[name='streetId']").val(${singleHouseByPrimaryKey.streetId})
               },"json")}
           )
       //异步加载所有房屋类型
        $.getJSON("getAllType",function (data) {
            for (var i = 0; i < data.length; i++) {
                var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                $("[name='typeId']").append(node);
            }
            //设置选中项
            $("[name='typeId']").val(${singleHouseByPrimaryKey.typeId});
        },"json")
//异步加载所有区域
       $.getJSON("getAllDistrict",function (data) {
           for (var i = 0; i < data.length; i++) {
               var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                 $("[name='district_id']").append(node);
           }
           //设置选中项
           $("[name='district_id']").val(${singleHouseByPrimaryKey.did});
       },"json")
       //获取所有街道
       $.getJSON(
           "getStreetById",
           {"id":${singleHouseByPrimaryKey.did}},
           function (data) {
               for (var i = 0; i < data.length; i++) {
                   var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
                   $("[name='streetId']").append(node)
               }
               //设置选中项
               $("[name='streetId']").val(${singleHouseByPrimaryKey.streetId})
           },"json")
   })
</script>
</HTML>
