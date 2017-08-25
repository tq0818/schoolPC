<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!doctype html>
<html lang="zh-cn">
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>科目管理</title>
    <style type="text/css">
        .register {
            position: fixed;
            left: 50%;
            top: 50%;
            width: 400px;
            height: 400px;
            margin-left: -200px;
            margin-top: -200px;
            padding-bottom: 15px;
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 0 30px rgba(0, 0, 0, 0.2);
            z-index: 999
        }

        .none {
            display: none;
        }

        .register .reg-close {
            position: absolute;
            top: 12px;
            right: 12px;
            width: 12px;
            height: 12px;
            background-image: url('../images/index-icons.png');
            background-repeat: no-repeat;
            background-position: 0 0;
            cursor: pointer;
        }

        .register .reg-title {
            padding: 15px 30px;
            border-bottom: 1px solid #e5e5e5;
        }

        .register .reg-form {
            padding: 0 60px;
        }

        .register .reg-bottom {
            padding: 2px 52px;
            border-top: 1px solid #e5e5e5;
        }

        .mark-bg {
            position: fixed;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            background-color: #fff;
            background-color: rgba(255, 255, 255, 0.6);
            opacity: .6 \9;
            filter: alpha(opacity=60);
        }

        .ml58 {
            margin-left: 58px;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/classes.css">
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/message.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/tikuSett.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/tiku/tikuHeader.jsp"></jsp:include>
<div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">科目管理</h2>
            <span class="line"></span>
        </div>
        <div class="c-main" style="width:95%;">
            <div class="s-title">
                <h2 class="h6">科目设置</h2>
            </div>
            <div class="c-content ml58">
                <ul>
                    <ul id="subInfo"></ul>
                    <li class="addCBtn"><a href="javascript:;" class="btn btn-sm btn-success">添加科目</a></li>
                    <li class="addCConter none"><input type="text" maxlength="22" id="addSubName"
                                                       style="width: 153px;"/>
                        <a href="javascript:;" class="btn btn-mini btn-success">保存</a>
                        <a href="javascript:;" class="btn btn-mini btn-default">取消</a></li>
                </ul>
            </div>

        </div>
    </div>
    <!-- ajax加载中div开始 -->
    <div class="loading lp-units-loading" style="display: none">
        <p>
            <i></i>加载中,请稍后...
        </p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display: none"></div>
    <!--  ajax加载中div结束 -->
    <div>
        <input type="hidden" id="tikuId" value="${tikuId}"/>
        <input type="hidden" id="setId" value="${setId}"/>
    </div>
    <script type="text/javascript">
        $(function () {
            if ($(".tiHeader .navspace li").length == 1) {
                $(".tiHeader .navspace li>a:eq(0)").addClass("active");
            } else {
                $(".tiHeader .navspace li>a:eq(3)").addClass("active");
            }
            $selectMenu('tiku_header');
            $selectSubMenu('tiku_subject');
        });
    </script>
</div>
</body>
</html>