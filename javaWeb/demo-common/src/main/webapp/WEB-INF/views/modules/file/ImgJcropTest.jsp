<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 2016/8/3
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/views/includes/taglib.jsp" %>
<html>
<head>
    <title>图片上传及裁剪</title>
    <%@include file="/WEB-INF/views/includes/head.jsp" %>
    <script src="${ctxStatic}/ajaxfileupload/ajaxfileupload.js"
            type="text/javascript"/>
    <%--裁剪上传 遮罩test--%>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#loginForm").validate({
                errorLabelContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    error.appendTo($("#loginError").parent());
                }
            });
        });
    </script>
    <script type="text/javascript">
        //隐藏div
        function delCrop() {
            $("#target").attr('src', "");
            $("#imgPreview").attr('src', "");
            $("#bg").css("display", "none");
            $("#imgCropDiv").css("display", "none");


        }
        //显示div
        //启动sureCrop()函数
        //并选择图片后传递图片的URI给sureCrop()
        function mytest() {
            var imageWeb = document.getElementById("imageWeb");    //uploadElement

            try {
                $.ajaxFileUpload({
                    url: _ctxPath + '/img/uploadImg',
                    secureuri: false,
                    fileElement: imageWeb,
                    dataType: 'json',
                    type: 'post',
                    success: function (data) {
                        if (data["checkFlag"] == "yes") {
                            $("#bg").css("display", "block");
                            $("#imgCropDiv").css("display", "block");
                            $("#hrefImgCrop").attr("value", data["relativeUrl"]);
                            $("#target").attr('src', data["imageUrl"] + "?t=" + Math.random());
                            $("#imgPreview").attr('src', data["imageUrl"] + "?t=" + Math.random());

                            var img = new Image();
                            img.src = data["imageUrl"];
                            var realx = img.width;
                            var realy = img.height;
                            $("#real_x").val(realx);
                            $("#real_y").val(realy);
                            var jCrop_api = null,
                                    boundx,
                                    boundy,

                                    $preview = $('#preview-pane'),
                                    $pcnt = $('#preview-pane .preview-container'),
                                    $pimg = $('#preview-pane .preview-container img'),

                                    xsize = $pcnt.width(),
                                    ysize = $pcnt.height();

                            //裁剪及预览显示
                            $('#target').Jcrop({
                                onChange: updatePreview,
                                onSelect: updatePreview,
                                aspectRatio: xsize / ysize

                            }, function () {
                                // Use the API to get the real image size
                                var bounds = this.getBounds();
                                boundx = bounds[0];
                                boundy = bounds[1];
                                $("#show_x").val(boundx);
                                $("#show_y").val(boundy);
                                // Store the API in the jCrop_api variable
                                jCrop_api = this;

                                // Move the preview into the jCrop container for css positioning
                                $preview.appendTo(jCrop_api.ui.holder);
                            });

                            function updatePreview(c) {
                                $("#x").val(parseInt(c.x));
                                $("#y").val(parseInt(c.y));
                                $("#h").val(parseInt(c.h));
                                $("#w").val(parseInt(c.w));
                                if (parseInt(c.w) > 0) {
                                    var rx = xsize / c.w;
                                    var ry = ysize / c.h;

                                    $pimg.css({
                                        width: Math.round(rx * boundx) + 'px',
                                        height: Math.round(ry * boundy) + 'px',
                                        marginLeft: '-' + Math.round(rx * c.x) + 'px',
                                        marginTop: '-' + Math.round(ry * c.y) + 'px'
                                    });
                                }
                            }
                            ;

                        } else if (data["checkFlag"] == "errorExt") {
                            alert("图片格式不正确，请选择jpg、gif、png格式的图片！");
                            return false;
                        } else if (data["checkFlag"] == "errorPoint") {
                            alert("图片文件名中有特殊字符，请去掉特殊字符！");
                            return false;
                        } else if (data["checkFlag"] == "errorSize") {
                            alert("图片太大，请选择30M以内图片！");
                            return false;
                        }
                    },
                    error: function (data, status, e) {
                        alert("图片上传错误！");
                    }
                });
            } catch (e) {
                alert("图片上传错误！");
            }
        }

        //裁剪并保存
        function sureCrop() {
            // 获取裁剪区域的起点坐标及宽高
            var x1 = parseInt($("#x").val());
            var y1 = parseInt($("#y").val());
            var w1 = parseInt($("#w").val());
            var h1 = parseInt($("#h").val());

            var realw = parseInt($("#real_x").val());
            var realh = parseInt($("#real_y").val());
            var showw = parseInt($("#show_x").val());
            var showh = parseInt($("#show_y").val());
            if (realw != 0 && realh != 0 && showh != 0 && showw != 0) {
                //获取实际的起点区域和宽高
                if (isNaN(x1) || isNaN(y1)) {
                    alert("未选择裁剪区域，默认选定为全局");
                    x1 = 0;
                    y1 = 0;
                    w1 = realw;
                    h1 = realh;
                } else {
                    x1 = parseInt(x1 * realw / showw);
                    y1 = parseInt(y1 * realw / showw);
                    w1 = parseInt(w1 * realw / showw);
                    h1 = parseInt(h1 * realw / showw);
                }
                if (confirm("确定裁剪上传吗？")) {
                    //获取裁剪后的宽和高并返回给hrefImg
                    try {
                        $.ajax({
                            url: '${ctx}/img/imgCut',
                            data: {
                                target: function () {
                                    return $("#target")[0].src;
                                },
                                hrefImgCrop: function () {
                                    return $("#hrefImgCrop").val();
                                },
                                x: x1,
                                y: y1,
                                w: w1,
                                h: h1
                            },
                            dataType: "json",
                            type: 'post',
                            success: function (data) {
                                $("#bg").css("display", "none");
                                $("#imgCropDiv").css("display", "none");
                                $("#hrefImg").attr('src', data["target"] + "?r=" + Math.random());
                            },
                            error: function (data) {
                                alert("error！");
                            }
                        });
                    } catch (e) {
                        alert("裁剪出错！");
                    }
                }
            } else {
                alert("图片出错 请重新上传");
                return false;
            }
        }
    </script>

    <%--图片裁剪页遮罩css样式--%>
    <style type="text/css">
        <%@include file="/WEB-INF/views/includes/style/imgcrop-style.css"%>
    </style>
</head>
<body>
<div class="container">
    <h3>图片上传及裁剪</h3>
    <br/>
    <form id="inputForm" action="" method="post" enctype="multipart/form-data">
        <div class="control-group">

            <label class="control-label">图片：</label>
            <div class="controls">
                <img src="" alt="暂无图片" name="hrefImg" id="hrefImg"
                     style="width: 100px; height: 80px"/>
                <input type="file" name="imageWeb" id="imageWeb"
                       onchange="mytest()"/>
            </div>
        </div>
    </form>

</div>
<%--遮罩--%>
<div id="bg"></div>

<%--裁剪div--%>
<div id="imgCropDiv">
    <div class="jc-demo-box">
        <input type="hidden" id="hrefImgCrop" name="hrefImgCrop"/><br/>
        <img src="" id="target" alt="[Jcrop Example]"
             style="width: 65%;height: auto"/>
        <div id="preview-pane">
            <div class="preview-container">
                <img id="imgPreview" src="" class="jcrop-preview"
                     alt="Preview"/>
            </div>
        </div>
        <input type="hidden" name="x" id="x"/>
        <input type="hidden" name="y" id="y"/>
        <input type="hidden" name="w" id="w"/>
        <input type="hidden" name="h" id="h"/>
        <input type="hidden" name="real_x" id="real_x"/>
        <input type="hidden" name="real_y" id="real_y"/>
        <input type="hidden" name="show_x" id="show_x"/>
        <input type="hidden" name="show_y" id="show_y"/>
        <div class="control-group">
            <div class="controls"
                 style="text-align: center;width: 50%;float: left; margin-top: 30px ">
                <input type="button" value="裁剪并保存" onclick="sureCrop();"/>
            </div>
            <div class="controls"
                 style="text-align: center;width: 50%;float: left; margin-top: 30px">
                <input type="button" value="取消" onclick="delCrop();">
            </div>

        </div>
    </div>
</div>
</body>
</html>