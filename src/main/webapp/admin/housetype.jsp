<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/25
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="easyUI/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyUI/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyUI/css/demo.css">
</head>
<body>
<!--显示区域-->
<table id="dg"></table>
<%------------------------------------工具栏-------------------------------------------------------------------------%>
<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:opendialog('AddDialog','添加')" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:confirmrows('upDialog','修改')" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:deleteTypes()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">批量删除</a>
    </div>
</div>
<%---------------------------------------------------------------------------------%>
<%----------------------------添加对话框---------------------------------------------------------------%>
<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true" >
    <form id="addForm" method="post">
        <table>
            <tr>
                <td>类型名称:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="name" id="bname"/></td>
            </tr>
        </table>
    </form>
</div>
<%------------------------------------------------------------------------------------%>
<!-----------------------------------------定义添加对话框中的按钮--------------------------------->
<div id="AddDialogButtons">
    <a href="javascript:addType()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:closedialog('AddDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<%------------------------------------------------------------------------------------------------------%>
<%-------------------------------------修改对话框--------------------------------------%>
<div id="upDialog" class="easyui-dialog" buttons="#upDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="updateForm" method="post">
        <table>
            <tr>
                <td>类型编号:</td>
                <td><input type="text" readonly style="border: none" class="easyui-validatebox" required
                           name="id" id="bname"/>
                </td>
            </tr>
            <tr>
                <td>类型名称:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="name" id="bname"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<%-------------------------------------------------------------------------------------------------%>
<!-----------------------------定义修改对话框中的按钮------------------------------------------>
<div id="upDialogButtons">
    <a href="javascript:updateType()" class="easyui-linkbutton"
       iconCls="icon-ok">更新</a>
    <a href="javascript:closedialog('upDialog')"
       class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
<%------------------------------------------------------------------------------------------------%>
</body>
<script src="js/jquery-1.8.3.js"></script>
<!--jquery.easyui.min.js包含了easyUI中的所有插件-->
<script src="js/jquery.easyui.min.js"></script>
<script src="js/housetype.js"></script>
</html>
