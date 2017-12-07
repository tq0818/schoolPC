<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>课程基本信息</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
    <script src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
    <%--<script type="text/javascript" src="<%=rootPath%>/javascripts/system/order.js"></script>--%>
    <%--<script type="text/javascript" src="<%=rootPath%>/javascripts/berkeley.js"></script>--%>
    <style type="text/css">
        .head-div {
            position: relative;
            margin-top: 15px;
            padding: 3px 8px;
        }

        .font-size {
            font-size: 14px;
            margin-left: 10px;
            margin-right: 11px;
        }

    </style>
    <%--tob--%>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
    <script  src="<%=rootPath%>/javascripts/tob-new.js" ></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<div class="u-wrap admin overflow baseSchoolBg">

    <div class="heading baseSchoolHead">
        <h2 class="h5">课程基本信息</h2>
        <span class="line"></span>
    </div>
    <div class="user-list informationCourseContent">
        <div class="informationContentFirst">
            <h4>基本信息</h4>
            <span class="line"></span>
            <ul>
                <li>
                    <label for="">分类</label>
                    <span>小学数学思维</span>
                </li>
                <li>
                    <label for="">学段</label>
                    <span>六年级</span>
                </li>
                <li>
                    <label for="">学科</label>
                    <span>数学</span>
                </li>
                <li>
                    <label for="" class="knowledgePoint">知识点</label>
                    <span>行测数量关系</span>
                </li>
                <li>
                    <label for="" class="courseName">课程名称</label>
                    <span>浓度问题</span>
                </li>
                <li>
                    <label for="">定价</label>
                    <span>0.0</span>
                </li>
                <li>
                    <label for="" class="concessionalRate">优惠价</label>
                    <span>0.0</span>
                </li>
                <li>
                    <label for="">类型</label>
                    <span>常规班级</span>
                </li>
            </ul>
        </div>
        <div class="informationContentSecond">
            <h4>视频信息</h4>
            <span class="line"></span>
            <ul>
                <li>
                    <p>
                        <label for="">第一章:</label>
                        <span>浓度问题</span>
                    </p>
                    <ul>
                        <li class="secondContent">
                            <p>
                                <label for="">第一节</label>
                                <span>浓度问题</span>
                            </p>
                        </li>
                    </ul>
                </li>
            </ul>
            <a href="#" class="btn btn-default" style="margin-left: 131px;">返回</a>
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


</body>
</html>