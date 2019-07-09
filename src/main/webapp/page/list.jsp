<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/7/1
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD><TITLE>青鸟租房 - 首页</TITLE>
    <META content="text/html; charset=utf-8" http-equiv=Content-Type>
    <LINK rel=stylesheet type=text/css href="../css/style.css">
    <META name=GENERATOR content="MSHTML 8.00.7601.17514">
</HEAD>
<BODY>
<DIV id=header class=wrap>
    <DIV id=logo><IMG src="../images/logo.gif"></DIV>
</DIV>
<DIV id=navbar class=wrap>
    <DL class="search clearfix">
        <FORM id=sform method=post action=list>
            <input type="hidden" name="page" id="page" value="1">
            <div>
                标题：<INPUT class=text type=text name=title value="${condition.title}" >
                类型:<SELECT id="tid" name=tid>
                <OPTION selected value="-1">不限</OPTION>
            </SELECT>
                区域:<SELECT id="did" name=did>
                <OPTION selected value="-1">不限</OPTION>
            </SELECT>
                街道:<SELECT name=sid id="sid">
                <OPTION selected value="-1">不限</OPTION>
            </SELECT>
                价格:<INPUT class=text type=text name=min_price value="${condition.min_price}" >-<INPUT class=text  type=text name=max_price value="${condition.max_price}">元
                <LABEL class=ui-blue><INPUT value=搜索房屋 type=submit name=search></LABEL>
            </div>
        </FORM>
    </DL>
</DIV>
<DIV class="main wrap">
    <c:if test="${!empty houseByPageCondition}">
    <TABLE class=house-list>
        <TBODY>
        <c:forEach items="${houseByPageCondition.list}" var="house">
        <TR>
            <TD class=house-thumb><span><A href="details?id=${house.id}" target="_blank"><img src="http://localhost:80/${house.path}"
                                                                                   width="100" height="75"
                                                                                   alt=""></a></span></TD>
            <TD>
                <DL>
                    <DT><A href="details?id=${house.id}" target="_blank">${house.title}</A></DT>
                    <DD>${house.dname}${house.sname},${house.floorage}平米<BR>联系方式：${house.contact}</DD>
                </DL>
            </TD>
            <TD class=house-type>${house.tname}</TD>
            <TD class=house-price><SPAN>${house.price}</SPAN>元/月</TD>
        </TR>
        </c:forEach>
        </TBODY>
    </TABLE>
    </c:if>
    <c:if test="${empty houseByPageCondition.list}">
        <center  style="color: red; font-size: 24px;">暂无出租房信息</center>
    </c:if>
    <DIV class=pager>
        <UL>
            <LI class=current><A href="javascript:do_page(1)">首页</A></LI>
            <LI><A href="javascript:do_page(${houseByPageCondition.pageNum-1})">上一页</A></LI>
            <LI><A href="javascript:do_page(${houseByPageCondition.pageNum+1})">下一页</A></LI>
            <LI><A href="javascript:do_page(${houseByPageCondition.pages})">末页</A></LI>
        </UL>
        <SPAN
                class=total>${houseByPageCondition.pageNum}/${houseByPageCondition.pages}页</SPAN></DIV>
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
      $.getJSON("getAllType", function (data) {
          for (var i = 0; i < data.length; i++) {
              var node = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
              $("[name='tid']").append(node);
          }

        //设置选中项
          $("[name='tid']").val(${condition.tid});
      }, "json")

      $.getJSON("getAllDistrict", function (data) {
          for (var i = 0; i < data.length; i++) {
              var node = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
              $("[name='did']").append(node);
          }
          //设置选中项
              $("[name='did']").val(${condition.did});

        //假如街道被选择 下一次进入list.jsp 街道要显示
          $.post(
              "getStreetById",
              {"id":$("[name='did']").val()},
              function (data) {
                  /*alert(data)*/
                  $("[name='sid']>option:gt(0)").remove();
                  for (var i = 0; i < data.length; i++) {
                      var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
                      $("[name='sid']").append(node)
                  }
                  //设置选中项
                  $("[name='sid']").val(${condition.sid})
              },"json")
      }, "json")

      //实现区域与街道的二级联动
      $("[name='did']").change(function () {
          var id=$("[name='did']").val();
          $.post(
              "getStreetById",
              {"id":id},
              function (data) {
                  $("[name='sid']>option:gt(0)").remove();
                  for (var i = 0; i < data.length; i++) {
                      var node=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
                      $("[name='sid']").append(node)
                  }
                  //设置选中项
                $("[name='sid']").val(${condition.sid})
              },"json")}
      )
  })



    function do_page(pageIndex) {
        $("[name='page']").val(pageIndex);
        $("#sform").submit();
    }
</script>
</HTML>
