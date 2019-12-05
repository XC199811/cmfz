<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">轮播图管理</a>
        </div>
    </div>
</nav>
<ul class="nav nav-tabs">
    <li class="active">
        <a href="#">轮播图信息</a></li>
    <li>
</ul>
<script>
    $(function(){
        $("#bannerTable").jqGrid(
            {
                url : "${pageContext.request.contextPath}/banner/findAll",
                datatype : "json",
                colNames : [ 'ID', '标题', '图片', '超链接', '创建时间','描述', '状态' ],
                colModel : [
                    {name : 'id',hidden:true},
                    {name : 'title',align:"center",editable:true,editrules:{required:true}},
                    {name : 'file',align:"center",formatter:function (data) {
                        return "<img style='width: 100%' src='"+data+"'/>"
                    },editable:true,edittype:"file",editoptions:{enctype:"multipart/form-data"},search:false},
                    {name : 'href',align:"center",editable:true,search:false},
                    {name : 'create_date',align:"center",search:false},
                    {name : 'describe',align:"center",editable:true},
                    {name : 'status',align:"center",formatter:function (data) {
                        if (data=="1"){
                            return "展示";
                        }else return "冻结";
                    },editable:true,edittype:"select",editoptions:{value:"1:展示;2:冻结"},search:false}
                ],
                rowNum : 10,
                rowList : [ 2,10, 20, 30 ],
                pager : '#bannerPage',
                mtype : "post",
                viewrecords : true,
                styleUI:"Bootstrap",
                autowidth:true,
                height:500,
                multiselect:true,
                /*编辑操作的url*/
                editurl:"${pageContext.request.contextPath}/banner/edit"
            });
        $("#bannerTable").jqGrid('navGrid', '#bannerPage',
            {edit : true, add : true, del : true,
                edittext:"编辑",addtext:"添加",deltext:"删除"
            },
            // edit add del
            {
                closeAfterEdit:true,
                // frm ---> 表单对象
                beforeShowForm:function (frm) {
                    frm.find("#file").attr("disabled",true);
                }
            },{closeAfterAdd:true,
                afterSubmit:function (response,postData) {
                    var bannerID = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/banner/upload",
                        datatype:"json",
                        type:"post",
                        data:{bannerId:bannerID},
                        // 指定的上传input框的id
                        fileElementId:"file",
                        success:function (data) {
                            // 输出上传成功
                            // jqgrid重新载入
                            $("#bannerTable").trigger("reloadGrid");
                        }
                    })
                    return postData;
                }
            },
            {},//对删除的操作
            {
                closeAfterSearch:true,
                sopt:['eq','ne','cn']//配置搜索条件如何
            }//对搜索面板的编辑对象
            );
    });
</script>
<div>
    <table id="bannerTable"></table>
    <div id="bannerPage" style="height: 50px"></div>
</div>
