<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">专辑管理</a>
        </div>
    </div>
</nav>
<ul class="nav nav-tabs">
    <li class="active">
        <a href="#">专辑信息</a></li>
    <li>
</ul>
<script>
    $(function () {
        $("#voiceTable").jqGrid({
            url : "${pageContext.request.contextPath}/voice/findAll",
            datatype : "json",
            height : 500,
            colNames : [ 'ID', '标题','封面','分数','作者','播音','章节数','描述','状态','创建时间'],
            colModel : [
                {name : 'id',hidden:true,align:"center"},
                {name : 'title',align:"center",editable:true},
                {name : 'image',align:"center",formatter:function (data) {
                    return "<img style='width: 100%' src='"+data+"'/>";
                },editable:true,edittype:"file",editoptions:{enctype:"multipart/form-data"},search:false},
                {name : 'level',align:"center",editable:true,search:false},
                {name : 'author',align:"center",editable:true},
                {name : 'boyin',align:"center",editable:true,search:false},
                {name : 'count',align:"center",editable:true,search:false},
                {name : 'synopsis',align:"center",editable:true},
                {name : 'status',align:"center",editable:true,search:false},
                {name : 'create_date',align:"center",search:false}
            ],
            rowNum : 5,
            rowList : [ 2, 5, 20 ],
            pager : '#voicePage',
            viewrecords : true,
            multiselect : false,
            subGrid : true,
            styleUI:"Bootstrap",
            autowidth:true,
            editurl:"${pageContext.request.contextPath}/voice/edit",
            subGridRowExpanded : function(subgrid_id, row_id) {
                addTable(subgrid_id,row_id);
            },
            subGridRowColapsed : function(subgrid_id, row_id) {
                // 删除表格方法
            }
        });
        $("#voiceTable").jqGrid("navGrid","#voicePage",{
             edit:true,add:true, del:true
        },
            {
                closeAfterEdit:true
            },
            {
                afterSubmit:function (response,postData) {
                    var voiceId = response.responseJSON.voiceId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/voice/upload",
                        datatype:"json",
                        type:"post",
                        data:{voiceId:voiceId},
                        // 指定的上传input框的id
                        fileElementId:"image",
                        success:function (data) {
                            // 输出上传成功
                            // jqgrid重新载入
                            $("#voiceTable").setGridParam({url:"${pageContext.request.contextPath}/voice/findAll"}).trigger("reloadGrid");
                        }
                    })
                    return postData;
                },
                closeAfterAdd:true
            },
            {},
            {
                closeAfterSearch:true,
                sopt:['eq','ne','cn']//配置搜索条件如何
            }//对搜索面板的编辑对象
        )
    })
    function addTable(subGridId,row_id) {
        //根据subgrid_id定义对应的子表格的table的id
        subGridTableId = subGridId + "table";
        //根据subgrid_id定义对应的子表格的pager的id
        subGridPagerId = subGridId + "pager";
        //创建表单元素及尾部分页div
        $("#" + subGridId).html("<table id='" + subGridTableId + "'></table><div id='" + subGridPagerId + "'></div>");
        $("#"+subGridTableId).jqGrid({
            url: "${pageContext.request.contextPath}/chapter/findAll?voice_id=" + row_id,
            datatype: "json",
            colNames: ['编号', '标题', '大小', '时长', '上传时间', '音频', '操作'],
            colModel: [
                {name: "id", align: "center", hidden: true},
                {name: "title", align: "center", editable: true},
                {name: "size", align: "center",search:false},
                {name: "time", align: "center",search:false},
                {name: "create_date", align: "center", editable: true, edittype: "date",search:false},
                {name: "file", align: "center",editable:true,edittype:"file",editoptions:{enctype:"multpart/form-data"},search:false},
                {
                    name: "file", align: "center", formatter(data) {
                    var result = "";
                    result += "<a href='javascript:void(0)' onclick=\"playAudio('" + data + "')\" class='btn btn-lg' title='播放'><span class='glyphicon glyphicon-play-circle'></span></a>";
                    result += "<a href='javascript:void(0)' onclick=\"downloadAudio('" + data + "')\" class='btn btn-lg' title='下载'><span class='glyphicon glyphicon-download'></span></a>";
                    return result;
                },search:false
                },
            ],
            rowNum: 5,
            pager: '#' + subGridPagerId,
            sortname: 'num',
            sortorder: "asc",
            height: '100%',
            styleUI: "Bootstrap",
            rowNum : 5,
            rowList : [ 2, 5, 20 ],
            viewrecords : true,
            autowidth: true,
            editurl:"${pageContext.request.contextPath}/chapter/edit?voice_id="+row_id,
        })
        $("#"+subGridTableId).jqGrid('navGrid', '#'+subGridPagerId, {
            add: true,
            edit: true,
            del: true,
            addtext: "添加",
            edittext: "编辑",
            deltext: "删除"
        },{
            closeAfterEdit:true,
            beforeShowForm:function (frm) {
                frm.find("#create_date").attr("readOnly",true);
                frm.find("#file").attr("disabled",true);
            }
        },{
            closeAfterAdd:true,
            afterSubmit:function (response,postData) {
                var chapterId = response.responseJSON.chapterId;
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/chapter/upload",
                    type:"post",
                    datatype:"json",
                    data:{chapterId:chapterId},
                    fileElementId:"file",
                    success:function (data) {
                        $("#"+subGridTableId).setGridParam({url:"${pageContext.request.contextPath}/chapter/findAll?voice_id="+row_id}).trigger("reloadGrid");
                    }
                })
                return postData;
            }
        }
        ,{},
            {
                closeAfterSearch:true,
                sopt:['eq','ne','cn']//配置搜索条件如何
            }//对搜索面板的编辑对象
        )
    }
    function playAudio(data) {
        $("#myModal").modal("show");
        $("#myaudio").attr("src",data);
    }
    function downloadAudio(data) {
        location.href = "${pageContext.request.contextPath}/chapter/download?url="+data;
    }
</script>
<div>
    <table id="voiceTable"></table>
    <div id="voicePage" style="height: 50px"></div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <audio src="" id="myaudio" controls></audio>
    </div>
</div>
