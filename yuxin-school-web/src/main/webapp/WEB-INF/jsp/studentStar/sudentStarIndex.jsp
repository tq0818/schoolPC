<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>学员心声</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/manager.css" />
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css" />
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/manage-screen.css" />
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.css" />
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.ttf" />
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.woff" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/resource.css" />
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css" />
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/manage.css" />
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/app/app.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/star.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/starfun.css" />
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/css/aspirations.css">
    <style type="text/css">
        .iconfont{
            cursor: auto;
        }
        .coupon-use-detail table th a {
            height: 100%;
            display: inline-block
        }

        .coupon-use-detail table th a:link,
        .coupon-use-detail table th a:visited {
            color: #474747;
        }

        .coupon-use-detail table th a:hover,
        .coupon-use-detail table th a:active {
            color: #474747;
        }

        .coupon-use-detail .sort-btn .chang_one {
            color: dodgerblue;
        }
        .iconfontH {
            color: red;
        }

        .tipsH {
            color: red;
        }

        .iii {
            position: absolute;
            top: 23px;
            right: -8px;
            width: 0;
            height: 0;
            border: 5px solid transparent;
            border-top-color: #ddd;
        }

        .cc {
            height: 62px;
            padding: 0 1%;
            line-height: 1.5;
        }

        .alert {
            padding: 15px;
            border: 1px solid transparent;
            margin: 0 auto;
        }

        .alert-warning {
            color: #c09853;
            background-color: #fcf8e3;
            border-color: #fbeed5
        }

        .alert-dismissable .cloze {
            position: relative;
            right: 50px;
            color: inherit;
            font-size: 1.6rem;
            cursor: pointer;
        }

        .cloze {
            float: right;
            font-size: 21px;
            font-weight: bold;
            line-height: 1;
            color: #000;
            text-shadow: 0 1px 0 #fff;
            opacity: .2;
            filter: alpha(opacity=20);
            display: inline-block;
            margin-top: 0px;
            margin-right: 0px;
            width: 9px;
            height: 9px;
            background-repeat: no-repeat !important;
        }

        .noalert {
            float: right;
            font-size: 12px;
            font-weight: bold;
            line-height: 1;
            color: #000;
            text-shadow: 0 1px 0 #fff;
            opacity: .2;
            filter: alpha(opacity=20);
            display: inline-block;
            margin: 1px -15px 0 15px;
            width: 80px;
            height: 9px;
            background-repeat: no-repeat !important;
            cursor: pointer;
        }

        .cloze:hover,
        .cloze:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
            opacity: .5;
            filter: alpha(opacity=50)
        }

        button.cloze {
            padding: 0;
            cursor: pointer;
            background: transparent;
            border: 0;
            -webkit-appearance: none
        }
    </style>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu/menu_operate.jsp"%>
<script type="text/javascript">
	$selectSubMenu('student_star');
</script>
<div class="u-wrap company overflow coupon-page">

    <!--证书管理开始-->
    <div class="right-side L-right-side showIn">
        <div class="u-wrap classes set-system">
            <div class="screen-right-cont set-system">
                <div class="screen-right-title">
                    <h3>
                        <i>学员心声</i>
                    </h3>
                    <div class="set-new manage-button couponCreate">添加学员心声</div>
                </div>
                <!--新加证书管理内容结束-->
                <div class="view" id="studendList">
                </div>
                <div class="pages L-pages">
                    <ul class="pagination"></ul>
                </div>
            </div>
        </div>
    </div>
    <!--证书管理结束-->
</div>

<!-- <div class="layer-tips L-allowAdmissionsTc L-allowAdmissionsTc-b" id="L-allowAdmissionsTc">
    <div class="layer-tips-title">
       <span class="optype">编辑</span>学员信息<i class="close iconfont">&#xe610;</i>
    </div>
    <div class="Layer-tips-content">
        <div class="name">
            <div class="left fl">学员名称</div>
            <input class="right fl" type="text" name="userName" placeholder="最多可输入十个字 " />
        </div>
        <div class="sort">
            <div class="left fl">学员排序</div>
            <input class="right fl" type="text" name="sortNum" placeholder="输入数字" />
        </div>
        <div class="portrait clear">
            <div class="left  fl">学员图像</div>
            <div class="left-m  fl"></div>
            <div class="right fl ">
                选择图像
                <input type="hidden" name="userPic"/>
                <input type="file" name="imgData" id="imgData" class="inputstyle" onchange="uploadImg()">
            </div>
        </div>
        <div class="idea clear">
            <div class="left  fl">学员感想</div>
            <input type="text" class="right  fl"/>
            <textarea class="right  fl" name="des"></textarea>
            <div class="number fl">0/200</div>
        </div>
    </div>
    <div class="layer-tips-btns " >
        <a href="javascript:;" class="btn btn-mini btn-default ">取消</a>
        <a href="javascript:;" class="btn btn-mini btn-default btn-info" id="L-edior">保存</a>
    </div>
</div> -->
<div class="layer-tips L-allowAdmissionsTc-b allowAdmin L-allowAdmissionsTc" id="L-allowAdmissionsTc">
    <div class="layer-tips-title">
       <span class="optype">编辑</span>学员心声<i class="close iconfont">&#xe610;</i>
    </div>
    <div class="Layer-tips-content">
        <div class="clear">
            <div class="fl">
                <div class="name">
                    <div class="left fl"><i class="star">*</i>学员名称</div>
                    <input class="right fl" type="text" placeholder="最多可输入十个字 " name="userName">
                    <label for="" class="label" id="name_tip" style="display: none;">学员名称必须在10个字以内</label>
                </div>
                <div class="portrait clear">
                    <div class="left  fl"><i class="star">*</i>学员头像</div>
                    <div class="left-m  fl userPic"></div>
                    <div class="right fl ">
                       	 选择图像<input type="hidden" name="userPic"/>
                       	 <input type="file" name="imgData" id="imgData" class="inputstyle" onchange="uploadImg()">
                    </div>
                </div>
            </div>
            <div class="fr">
                <div class="sort">
                    <div class="left fl"><i class="star">*</i>学员排序</div>
                    <input class="right fl" type="text" placeholder="输入数字" name="sortNum">
                </div>
                <div class="portrait clear">
                    <div class="left  fl">生活照</div>
                    <div class="left-m  fl userPhoto"></div>
                    <div class="right fl ">
                        	选择图像<input type="hidden" name="userPhoto"/>
                        	<input type="file" name="imgData" id="imgData1" class="inputstyle" onchange="uploadImg1()">
                    </div>
                </div>
            </div>
        </div>
        <div class="idea clear">
            <div class="left  fl"><i class="star">*</i>学员心声</div>
            <textarea class="right  fr" name="des"></textarea>
            <div class="number fl">0/200</div>
        </div>
    </div>
    <div class="layer-tips-btns " >
        <a href="javascript:;" class="btn btn-mini btn-default ">取消</a>
        <a href="javascript:;" class="btn btn-mini btn-default btn-info" id="L-edior">保存</a>
    </div>
</div>

<!--删除提示开始-->
<div class="layer-tips layer-tips-b L-allowAdmissionsTc">
    <div class="layer-tips-title">
        提示 <i class="close iconfont">&#xe610;</i>
    </div>
    <p class="title-prompt">确定要删除该学员心声吗？</p>
    <div class="layer-tips-btns " >
        <a href="javascript:;" class="btn btn-mini btn-default ">取消</a>
        <a href="javascript:;" class="btn btn-mini btn-default btn-info" id="L-delete">确定</a>
    </div>
</div>
<div class="layer-tips layer-tips-b L-allowAdmissionsTc" id="alert-msg" style="border: 1px solid #ccc;">
    <div class="layer-tips-title">
        提示 <i class="close iconfont">&#xe610;</i>
    </div>
    <p class="title-prompt"></p>
    <div class="layer-tips-btns " >
        <a href="javascript:;" class="btn btn-mini btn-default btn-info" >确定</a>
    </div>
</div>
    <!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>

<!--背景阴影-->
<div class="layer-tips-bg"></div>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/footerPosition.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/starstudent/starStudentList.js"></script>
</body>
</html>