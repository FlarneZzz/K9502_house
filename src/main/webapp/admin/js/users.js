$(function () {
    //使用datagrid显示区域
    $('#dg').datagrid({
        title: "区域信息",
        url: 'getUsersByPage',  //服务器地址
        toolbar: "#ToolBar",  //工具栏
        pagination: true,  //启用分页
        rownumbers: true,  //显示行号
        //singleSelect:true,  //实现单行选择
        pageList: [3, 6, 9, 15, 20], //设置每页大小
        /*   pageSize: 3, //每页三条*/
        columns: [[
            {field: 'ck', checkbox: true, width: 100, align: 'left'},
            {field: 'id', title: '编号', width: 100, align: 'left'},
            {field: 'name', title: '用户姓名', width: 100, align: 'left'},
            {field: 'telephone', title: '联系方式', width: 100, align: 'left'},
            {field:'x',title:'操作', width:80,align:'left',
                formatter: function(value,row,index){
                    return "<a href='javascript:do_delete("+row.id+")'>删除</a>";

                }
            }
        ]]
    });
});

//=============================封装JS=======================================>
function opendialog(id, title) {
    $("#" + id).dialog("open").dialog("setTitle", title);
}

function closedialog(id) {
    $("#" + id).dialog("close");
}

//=========================================================================>

//==============================添加JS============================================>
function addDistrict() {
    $("#addForm").form("submit", {
        url: "addDistrict",
        success: function (data) {
            data = $.parseJSON(data);
            if (data.result == 1) {
                $("#addForm").form("clear");
                closedialog("AddDialog");
                /*  $("#AddDialog").dialog("close");*/
                $.messager.alert("提示框", "添加成功", "info");
            } else {
                $.messager.alert("提示框", "添加失败", "info");
            }
        }

    })
}

//===========================================================================================
//==================================修改JS==========================================================
function confirmrows() {
    var selectRows = $("#dg").datagrid("getSelections");
    if (selectRows.length != 1) {
        $.messager.alert("提示框", "未选择一行或选择了多行", "info");
        return;
    }
    opendialog("upDialog", "修改");
    var row = selectRows[0];
    $.post("getSingleDistrict", {"id": row.id}, function (data) {
        $("#updateForm").form("load", data);
        /*#upDialog*/
    }, "json");
}

function updateDistrict() {
    $("#updateForm").form("submit", {
        url: "updateDistrict",
        success: function (data) {
            data = $.parseJSON(data)
            if (data.result == 1) {
                closedialog("upDialog");
                $("#dg").datagrid("reload")
            } else {
                $.messager.alert("提示框", "修改失败", "info")
            }
        }
    })
}
//==================================删除js===================================================
function do_delete(args) {
    $.messager.confirm('提示框', '确认删除吗?', function(r){
        if (r){
            $.post("deleteDistrict",{"id":args},function (data) {
                if(data.result==1){
                    $("#dg").datagrid("reload");
                }else{
                    $.messager.alert("提示框","删除失败","info");
                }
            },"json");
        }
    });


}
//==========================================================================================
//==================================批量删除js===================================================
function deleteDistricts() {
    var selectRows = $("#dg").datagrid("getSelections");
    if(selectRows==0){
        $.messager.alert("提示框","你还没有选中","info");
        return;
    }
    $.messager.confirm("提示框","确认删除吗?",function (flag) {
        if(flag){
            var value="";
            for (var i = 0; i < selectRows.length; i++) {
                value=value+selectRows[i].id+",";
            }
            value=value.substring(0,value.length-1);
            $.post("deleteDistricts",{"id":value},function (data) {
                if (data.result>0){
                    $("#dg").datagrid("reload");
                }else{
                    $.messager.alert("提示框","删除失败!","info");
                }
            },"json");
        }
    })
}

//==========================================================================================
//====================================搜索功能======================================================
function research() {
    var name=$("#s_name").val();
    var telephone=$("#s_telephone").val();
    $("#dg").datagrid("load",{"name":name,"telephone":telephone});
}
//==========================================================================================