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
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
    <script  src="<%=rootPath%>/javascripts/tob-new.js" ></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/branchschool/editberkeley.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_berkeley.jsp"></jsp:include>
<div class="u-wrap admin overflow">
    <jsp:include page="/WEB-INF/jsp/menu/menu_berkeleyLeft.jsp"></jsp:include>
    <div class="right-side">
        <ul class="berkeleyDetailInfo berkeleyDetailInfoLeft">
            <li>
                <label>学校机构代码:</label>
                <input type="text" disabled="disabled" id="branchCode" value="${company.eduAreaSchool }" style="margin-left: 20px;">
                <input type="hidden"  value="${company.id}" id="companyId">
            </li>
            <li>
                <label>分校:</label>
                <input type="text" disabled="disabled" id="branchSchool" value="${company.companyName }" style="margin-left: 70px;width: 200px;">
            </li>
            <li>
                <label>所属区域:</label>
                <input type="text" disabled="disabled" id="eara" value="${company.eduAreaName }" style="margin-left: 45px;">
            </li>
            <li>
                <label>学校性质:</label>
                <input type="text" disabled="disabled" id="schoolProperties" value="${company.schoolProperty }" style="margin-left: 47px;">
            </li>
            <li>
                <label>联系人:</label>
                <input type="text" disabled="disabled" id="linkPerson" value="${company.linkPerson }" class="editState" style="margin-left: 60px;">
            </li>
            <li>
                <label>联系方式:</label>
                <input type="text" disabled="disabled" id="linkPhone" value="${company.linkPhone }" class="editState" style="margin-left: 50px;">
            </li>
            <li>
                <label>分校域名:</label>
                <input type="text" disabled="disabled" id="domain" value="${company.domain }"  style="margin-left: 50px;width: 200px;">
            </li>
            <li>
                <label>分校后台域名:</label>
                <input type="text" disabled="disabled" id="domainManage" value="${company.domainManage }"  style="margin-left: 25px;width: 200px;">
            </li>
            <li>
                <label>学校简介:</label>
                <%--<input type="text" disabled="disabled" value="${schoolProperty }" class="editState" style="margin-left: 50px;">--%>
                <textarea  cols="30" rows="5" id="schoolSummary" style="margin-left: 50px;" disabled="disabled">${company.schoolSummary }</textarea>
            </li>
        </ul>
        <ul class="berkeleyDetailInfo berkeleyDetailInfoRight">
            <li style="margin-bottom: 30px;">
                <label>收费配置:</label>
                <p style="margin-left: 95px;margin-bottom: 5px;">
                    <label>学校私有课程收费比例:</label>
                    <input type="text" style="text-align: right;" id="privateCost" disabled="disabled" value="${company.privateCost }" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" class="editState">%
                </p>
                <p style="margin-left: 95px;">
                    <label>学校开放课程收费比例:</label>
                    <input type="text" style="text-align: right;" id="publicCost" disabled="disabled" value="${company.publicCost }" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" class="editState">%
                </p>
            </li>
            <li style="margin-bottom: 30px;">
                <p style="margin-bottom: 5px;">
                    <label>流量 ${css.videoFlow}/${cms.videoFlow} GB</label><br/>
                    <span style="margin-left: 95px;" class="showDetails showDetailsMark">增加流量</span>
                    <input type="text" id="flowSize" class="editState showDetails " onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/">
                </p>
                <p style="margin-bottom: 5px;">
                    <label>空间 ${css.videoStorage }/${cms.videoStorage} GB </label><br/>
                    <span style="margin-left: 95px;" class="showDetails showDetailsMark">增加空间</span>
                    <input type="text" id="spaceSize" class="editState showDetails "  onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/">
                </p>
            </li>
            <li class="accountNumber">
                <p style="margin-bottom: 5px;">
                    <label>CC账号:</label>
                    <input type="text" class="editState " id="ccUserName" value="${clc.loginName }" style="margin-left: 48px;margin-bottom: 5px;" disabled="disabled"><br/>
                    <input type="password" class="editState showDetails " id="ccPwd" value="${clc.password}" style="margin-left: 95px;">
                </p>
                <p style="margin-bottom: 5px;">
                    <label>展示互动账号:</label>
                    <input type="text" class="editState " id="zsUserName" value="${clc.loginName }" style="margin-bottom: 5px;margin-left: 15px;" disabled="disabled"><br/>
                    <input type="password" class="editState showDetails" id="zsPwd" value="${clc.password}" style="margin-left: 95px;">
                </p>
            </li>
        </ul>
        <div class="berkeleyOperate">
            <button type="button" class="btn btn-warning berkeleyDetailEdit">编辑</button>
            <button type="button" onclick="addBerkeley(1)" id="save" class="btn btn-success" style="display: none">保存</button>
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
        $('#privateCost').css('text-align','left');
        $('#publicCost').css('text-align','left');
        $('.addSource').show();
        $('.editState').addClass('editStateNow').attr('disabled',false);
        $('.showDetails').removeClass('showDetails');
        $('textarea').addClass('editStateNow').attr('disabled',false);
        var currentBtn = document.getElementById("save");
        currentBtn.style.display = 'inline-block';

    });
</script>
<script>
//    左侧active切换
    $selectSubMenus('getClassInfo');
</script>
</body>
</html>
