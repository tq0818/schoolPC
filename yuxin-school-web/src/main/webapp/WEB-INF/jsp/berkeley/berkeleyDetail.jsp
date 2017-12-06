<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>分校详情</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
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
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
    <script  src="<%=rootPath%>/javascripts/tob-new.js" ></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_berkeley.jsp"></jsp:include>
<div class="u-wrap admin overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_berkeleyLeft.jsp"></jsp:include>
    <div class="right-side">
        <ul class="berkeleyDetailInfo berkeleyDetailInfoLeft">
            <li>
                <label>学校机构代码:</label>
                <input type="text" disabled="disabled" value="XXXXXX" style="margin-left: 20px;">
            </li>
            <li>
                <label>学校:</label>
                <input type="text" disabled="disabled" value="XXXXXX" style="margin-left: 70px;">
            </li>
            <li>
                <label>所属区域:</label>
                <input type="text" disabled="disabled" value="XXXXXX" style="margin-left: 45px;">
            </li>
            <li>
                <label>学校性质:</label>
                <input type="text" disabled="disabled" value="XXXXXX" style="margin-left: 47px;">
            </li>
            <li>
                <label>联系人:</label>
                <input type="text" disabled="disabled" value="XXXXXX" class="editState" style="margin-left: 60px;">
            </li>
            <li>
                <label>联系方式:</label>
                <input type="text" disabled="disabled" value="XXXXXX" class="editState" style="margin-left: 50px;">
            </li>
            <li>
                <label>分校域名:</label>
                <input type="text" disabled="disabled" value="XXXXXX" class="editState" style="margin-left: 50px;">
            </li>
            <li>
                <label>分校后台域名:</label>
                <input type="text" disabled="disabled" value="XXXXXX" class="editState" style="margin-left: 25px;">
            </li>
            <li>
                <label>学校简介:</label>
                <%--<input type="text" disabled="disabled" value="XXXXXX" class="editState" style="margin-left: 50px;">--%>
                <textarea name="" id="" cols="30" rows="5" style="margin-left: 50px;" disabled="disabled">学校简介:学校简介:学校简介:学校简介:学校简介:学校简介:
                </textarea>
            </li>
        </ul>
        <ul class="berkeleyDetailInfo berkeleyDetailInfoRight">
            <li style="margin-bottom: 30px;">
                <label>收费配置:</label>
                <p style="margin-left: 75px;margin-bottom: 5px;">
                    <label>学校私有课程收费比例:</label>
                    <input type="text"  disabled="disabled" value="1%" class="editState">
                </p>
                <p style="margin-left: 75px;">
                    <label>学校开放课程收费比例:</label>
                    <input type="text" disabled="disabled" value="2%" class="editState">
                </p>
            </li>
            <li style="margin-bottom: 30px;">
                <p style="margin-bottom: 5px;">
                    <label>流量 26,234.34/51200 GB</label><br/>
                    <span style="margin-left: 80px;" class="showDetails showDetailsMark">增加流量</span>
                    <input type="text" class="editState showDetails " value="100" >
                </p>
                <p style="margin-bottom: 5px;">
                    <label>空间 365.272/700 GB </label><br/>
                    <span style="margin-left: 80px;" class="showDetails showDetailsMark">增加空间</span>
                    <input type="text" class="editState showDetails " value="100" >
                </p>
            </li>
            <li class="accountNumber">
                <p style="margin-bottom: 5px;">
                    <label>CC账号:</label>
                    <input type="text" class="editState " value="32131321" style="margin-left: 32px;margin-bottom: 5px;" disabled="disabled"><br/>
                    <input type="password" class="editState showDetails " value="" style="margin-left: 88px;">
                </p>
                <p style="margin-bottom: 5px;">
                    <label>展示互动账号:</label>
                    <input type="text" class="editState " value="103213210" style="margin-bottom: 5px;margin-left: 2px;" disabled="disabled"><br/>
                    <input type="password" class="editState showDetails" value="" style="margin-left: 88px;">
                </p>
            </li>
        </ul>
        <div class="berkeleyOperate">
            <button class="btn btn-warning berkeleyDetailEdit">编辑</button>
            <button class="btn btn-success">保存</button>
        </div>
    </div>
</div>
<input type="hidden" value="5" id="pageSize">


<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
<!--  ajax加载中div结束 -->

<%--点击编辑进入编辑状态--%>
<script>
    $('.berkeleyDetailEdit').click(function(){
        $('.addSource').show();
        $('.editState').addClass('editStateNow').attr('disabled',false);
        $('.showDetails').removeClass('showDetails');
        $('textarea').addClass('editStateNow').attr('disabled',false);

    });
</script>
<script>
//    左侧active切换
    $selectSubMenus('getClassInfo');
</script>
</body>
</html>