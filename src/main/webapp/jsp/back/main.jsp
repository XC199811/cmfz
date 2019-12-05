<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <%--引入bootstrap的css--%>
    <link rel="stylesheet" href="../../boot/css/bootstrap.min.css">
    <%--引入jqggrid的css--%>
    <link rel="stylesheet" href="../../jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入jquery--%>
    <script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
    <%--引入jqgrid的jquery--%>
    <script src="${pageContext.request.contextPath}/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <%--引入jqgrid的国际化--%>
    <script src="${pageContext.request.contextPath}/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <%--引入bootstrap的js--%>
    <script src="${pageContext.request.contextPath}/boot/js/bootstrap.min.js"></script>
    <%--文件上传操作--%>
    <script src="${pageContext.request.contextPath}/boot/js/ajaxfileupload.js"></script>
    <%--引入kindEditor的js--%>
    <script src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
    <script src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
    <title>持名法舟后台管理系统</title>
    <script>
        KindEditor.ready(function(K) {
            window.editor = K.create('#editor_id',{
                width:'600px',
                // 1. 指定图片上传路径
                uploadJson:"${pageContext.request.contextPath}/article/uploadImg",
                allowFileManager:true,
                fileManagerJson:"${pageContext.request.contextPath}/article/showAllImgs",
                /*afterBlur:function () {
                    this.sync();
                },
                allowFileManager: true,*/
                afterCreate: function() { // kindeditor创建后，将编辑器的内容设置到原来的textarea控件里
                    this.sync(editor_id);
                },
                afterChange: function() { // 编辑器内容发生变化后，将编辑器的内容设置到原来的textarea控件里
                    this.sync(editor_id);
                },
                afterBlur: function() { // 编辑器失焦后，将编辑器的内容设置到原来的textarea控件里
                    this.sync(editor_id);
                }
            });
        });
    </script>
</head>
<body>
    <%--导航栏--%>
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">持明法舟后台管理系统</a>
            </div>
            <div>
                <!--向右对齐-->
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            欢迎&nbsp;xc
                        </a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            退出登录
                        </a>
                    </li>

                </ul>
            </div>
        </div>
    </nav>
    <%--整体下面的布局--%>
    <div class="container-fluid">
        <%--左边--%>
        <div class="col-md-3">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse ">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#">用户信息管理</a></li>
                                <li><a href="#">用户注册趋势</a></li>
                                <li><a href="#">用户注册分布</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo">
                                上师管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#">用户信息管理</a></li>
                                <li><a href="#">用户注册趋势</a></li>
                                <li><a href="#">用户注册分布</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree">
                                文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#centerLay').load('./article.jsp')">文章列表</a></li>
                                <li><a href="#">文章搜索</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour">
                                专辑管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#centerLay').load('./voice.jsp')">专辑管理</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive">
                                轮播图管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="javascript:$('#centerLay').load('./banner.jsp')">轮播图管理</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--右边--%>
        <div class="col-md-9">
            <%--巨幕--%>
            <div class="container" id="centerLay">
                <div class="jumbotron" style="height: 60px; ">
                    <h3 style="margin-top: -5px;">欢迎使用持明法舟后台管理系统</h3>
                </div>
                <%--轮播--%>
                <div id="myCarousel" class="carousel slide">
                    <!-- 轮播（Carousel）指标 -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>
                    <!-- 轮播（Carousel）项目 -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <img src="${pageContext.request.contextPath}/img/3e4d03381f30e924eebbff0d40086e061d95f7b0.jpg" alt="First slide">
                            <div class="carousel-caption"></div>
                        </div>
                        <div class="item">
                            <img src="${pageContext.request.contextPath}/img/009e9dfd5266d016d30938279a2bd40735fa3576.jpg" alt="Second slide">
                            <div class="carousel-caption"></div>
                        </div>
                        <div class="item">
                            <img src="${pageContext.request.contextPath}/img/098ca7cad1c8a786b4e6a0366609c93d71cf5049.jpg" alt="Third slide">
                            <div class="carousel-caption"></div>
                        </div>
                    </div>
                    <!-- 轮播（Carousel）导航 -->
                    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>

        </div>
    </div>
    <div class="panel-footer">
        <h5 style="text-align: center">@百知教育 baizhi@zparkhr.com.cn</h5>
    </div>

    <%--模态框--%>
    <div class="modal fade" id="kind" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">文章信息</h4>
                </div>
                <div class="modal-body">
                    <form role="form" enctype="multipart/form-data" id="kindfrm">
                        <input name="id" type="hidden" id="formid">
                        <div class="form-group">
                            <label for="name">标题</label>
                            <input type="text" class="form-control" name="title" id="name" placeholder="请输入名称">
                        </div>
                        <div class="form-group">
                            <label for="inputfile">封面上传</label>
                            <input type="file" name="articleImg" id="inputfile">
                            <div id="imgD"></div>
                        </div>
                        <div class="form-group">
                            <label for="status">状态</label>
                            <select class="form-control" id="status" name="statusval" >

                            </select>
                        </div>
                        <div class="form-group">
                            <label for="name">所属上师</label>
                            <select class="form-control" id="guruList" name="author_id" >

                            </select>
                        </div>
                        <div class="form-group">
                            <label for="editor_id">内容</label>
                            <textarea id="editor_id" name="content" style="width:700px;height:300px;">
<%--&lt;strong&gt;HTML内容&lt;/strong&gt;--%>
</textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer" id="modal_foot">
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</body>

</html>