$(function () {
    //使用datagrid显示区域
    $('#dg').datagrid({
        title: "房屋信息",
        url: 'getUncheckingHouse',  //服务器地址
        toolbar: "#ToolBar",  //工具栏
        pagination: true,  //启用分页
        rownumbers: true,  //显示行号
        //singleSelect:true,  //实现单行选择
     /*   pageList: [3, 6, 9, 15, 20], //设置每页大小*/
        pageSize: 6, //每页三条
        columns: [[
            {field: 'ck', checkbox: true, width: 100, align: 'left'},
            {field: 'id', title: '房屋编号', width: 100, align: 'left'},
            {field: 'tname', title: '房屋类型', width: 100, align: 'left'},
            {field: 'title', title: '标题', width: 100, align: 'left'},
            {field: 'description', title: '描述', width: 100, align: 'left'},
            {field: 'pubdate', title: '发布日期', width: 100, align: 'left',
                formatter: function(value,row,index){
                    var date=new Date(value);
                    var year=date.getFullYear();
                    var month=date.getMonth()+1;
                    if(month<10){
                        month="0"+month;
                    }
                    var day=date.getDate();
                    if(day<10){
                        day="0"+day
                    }
                    return year+"-"+month+"-"+day;
                }},
            {field: 'floorage', title: '面积', width: 100, align: 'left'},
            {field: 'contact', title: '联系方式', width: 100, align: 'left'},
            {field: 'dname', title: '区域', width: 100, align: 'left'},
            {field: 'sname', title: '街道', width: 100, align: 'left'},
            {field:'x',title:'操作', width:80,align:'left',
                formatter: function(value,row,index){
                    return "<a href='javascript:doChecking("+row.id+")'>审核</a>";

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
//==================================审核js===================================================
function doChecking(args) {
    $.messager.confirm('提示框', '确认审核通过吗?', function(r){
        if (r){
            $.post("Checking",{"id":args},function (data) {
                if(data.result==1){
                    $("#dg").datagrid("reload");
                }else{
                    $.messager.alert("提示框","审核失败","info");
                }
            },"json");
        }
    });


}
//==========================================================================================
//==================================批量审核js===================================================
function CheckHouses() {
    var selectRows = $("#dg").datagrid("getSelections");
    if(selectRows==0){
        $.messager.alert("提示框","你还没有选中","info");
        return;
    }
    $.messager.confirm("提示框","确认审核吗?",function (flag) {
        if(flag){
            var value="";
            for (var i = 0; i < selectRows.length; i++) {
                value=value+selectRows[i].id+",";
            }
            value=value.substring(0,value.length-1);
            $.post("CheckHouses",{"id":value},function (data) {
                if (data.result>0){
                    $("#dg").datagrid("reload");
                }else{
                    $.messager.alert("提示框","审核失败!","info");
                }
            },"json");
        }
    })
}

//==========================================================================================
//====================================搜索功能======================================================
function research() {
    var sname=$("#sname").val();
    var tname=$("#tname").val();
    var dname=$("#dname").val();
    var title=$("#title").val();
    var min_price=$("#min_price").val();
    var max_price=$("#max_price").val();
    $("#dg").datagrid("load",{"sname":sname,"dname":dname,"tname":tname,"title":title,"min_price":min_price,"max_price":max_price});
}
//==========================================================================================