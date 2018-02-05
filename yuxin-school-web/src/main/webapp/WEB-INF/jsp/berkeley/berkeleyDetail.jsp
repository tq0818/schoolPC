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
        #schoolSummary{height: 210px;margin-left: 10px !important;width: 196px;}
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
                <label>分校机构代码:</label>
                <input type="text" disabled="disabled" id="branchCode" value="${company.eduAreaSchool }" style="margin-left: 15px;">
                <input type="hidden"  value="${company.id}" id="companyId">
            </li>
            <li>
                <label>分校:</label>
                <input type="text" disabled="disabled" id="branchSchool" value="${company.companyName }" style="margin-left: 70px;width: 190px;">
            </li>
            <li>
                <label>所属区域:</label>
                <c:choose>
                    <c:when test="${company.eduAreaName == null || company.eduAreaName == ''}">
                        <input type="text" disabled="disabled" id="eara" value="${company.companyName }" style="margin-left: 45px;">
                    </c:when>
                    <c:otherwise>
                        <input type="text" disabled="disabled" id="eara" value="${company.eduAreaName }" style="margin-left: 45px;">
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <label>分校性质:</label>
             <c:choose>
             	<c:when test="${company.schoolProperty ne null && company.schoolProperty ne ''}">
                <input type="text" disabled="disabled" id="schoolProperties" value="${company.schoolProperty }" style="margin-left: 47px;">
            	</c:when>
            	<c:otherwise>
            	<input type="text" disabled="disabled" id="schoolProperties" value="无" style="margin-left: 47px;">
            	</c:otherwise>
            </c:choose>
            </li>
            <li>
                <label>联系人:</label>
                <input type="text" disabled="disabled" id="linkPerson" value="${company.linkPerson }" class="editState" style="margin-left: 60px;">
            </li>
            <li>
                <label>联系方式:</label>
                <input type="text" disabled="disabled" id="linkPhone" value="${company.linkPhone }" class="editState" style="margin-left: 48px;">
            </li>
            <li>
                <label>分校域名:</label>
                <input type="text" disabled="disabled" id="domain" value="${company.domain }"  style="margin-left: 50px;width: 190px;">
            </li>
            <li>
                <label>分校后台域名:</label>
                <input type="text" disabled="disabled" id="domainManage" value="${company.domainManage }"  style="margin-left: 25px;width: 190px;">
            </li>
            <li>
                <label>分校简介:</label>
                <%--<input type="text" disabled="disabled" value="${schoolProperty }" class="editState" style="margin-left: 50px;">--%>
                <textarea  cols="25" rows="5" id="schoolSummary" style="overflow:hidden; resize:none;" disabled="disabled">${company.schoolSummary }</textarea>
                <span class="showDetails showDetailsMark"><i style="color: red;" class="iconfont ico"></i></span>
            </li>
        </ul>
        <ul class="berkeleyDetailInfo berkeleyDetailInfoRight">
            <li style="margin-bottom: 30px;">
                <label>收费配置:</label>
                <p style="margin-left: 95px;margin-bottom: 5px;">
                    <label>分校私有课程收费比例:</label>
                    <input type="text" style="text-align: right;width: 24px;" id="privateCost" disabled="disabled" value="${company.privateCost }" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" class="editState">%
                    <span class="showDetails showDetailsMark"><i style="color: red;" class="iconfont ico"></i></span>
                </p>
                <p style="margin-left: 95px;">
                    <label>分校开放课程收费比例:</label>
                    <input type="text" style="text-align: right;width: 24px;" id="publicCost" disabled="disabled" value="${company.publicCost }" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" class="editState">%
                    <span class="showDetails showDetailsMark"><i style="color: red;" class="iconfont ico"></i></span>
                </p>
            </li>
            <script>
                $(function(){
                    //propertychange监听input里面的字符变化,属性改变事件
                    $('.editState').bind('input propertychange', function() {
                        var $this = $(this);
                        var text_length = $this.val().length;//获取当前文本框的长度
                        var current_width = parseInt(text_length) *10;//该16是改变前的宽度除以当前字符串的长度,算出每个字符的长度
                        $this.css("width",current_width+"px");
                    });
                })
            </script>
            <li style="margin-bottom: 30px;">
                <p style="margin-bottom: 5px;">
                    <label>分配流量: ${css.videoFlow}/
                    <input type="text" style="text-align: center;width: 70px" id="flowSize"  class="editState " disabled="disabled"
                           value="${cms.videoFlow}" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" maxlength="10">GB</label>
                    <%--<span style="margin-left: 95px;" class="showDetails showDetailsMark">增加流量:</span>--%>
                    <%--<input style="width: 115px" type="text" id="flowSize" class="editState showDetails " onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/">--%>
                    <%--<span class="showDetails showDetailsMark"><i style="color: red;" class="iconfont ico"></i></span>--%>
                </p>
                <p style="margin-bottom: 5px;">
                    <label>分配空间: ${css.videoStorage }/
                    <input type="text" style="text-align: center;width: 70px;" id="spaceSize"  class="editState " disabled="disabled"
                           value="${cms.videoStorage}" onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/" maxlength="10">GB</label>
                    <%--<span style="margin-left: 95px;" class="showDetails showDetailsMark">增加空间:</span>--%>
                    <%--<input style="width: 115px" type="text" id="spaceSize" class="editState showDetails "  onkeyup="value=value.replace(/[^\d]/g,'') " ng-pattern="/[^a-zA-Z]/">--%>
                    <%--<span class="showDetails showDetailsMark"><i style="color: red;" class="iconfont ico"></i></span>--%>
                </p>
            </li>
            <li class="accountNumber">
                <p style="margin-bottom: 5px;">
                    <label>CC账号:</label><br>

                    <input type="text" class="editState " id="ccUserName" value="${cpc.ccUserId }" style="margin-left: 95px;margin-bottom: 5px;width: 180px;" disabled="disabled">
                    <span class="showDetails showDetailsMark"><i style="color: red;" class="iconfont ico"></i></span><br/>
                    <input type="password" class="editState showDetails " id="ccPwd" value="${cpc.ccApiKey}" style="margin-left: 95px;width: 180px;">
                    <span class="showDetails showDetailsMark"><i style="color: red;" class="iconfont ico"></i></span><br/>
                </p>
                <p style="margin-bottom: 5px;">
                    <label>展示互动账号:</label><br>
                    <input type="text" class="editState " id="zsUserName" value="${clc.loginName }" style="margin-bottom: 5px;margin-left: 95px;width: 310px;" disabled="disabled">
                    <span class="showDetails showDetailsMark"> <i style="color: red;" class="iconfont ico"></i></span><br/>
                    <input type="password" class="editState showDetails" id="zsPwd" value="${clc.password}" style="margin-left: 95px;width: 180px;">
                    <span class="showDetails showDetailsMark"> <i style="color: red;" class="iconfont ico"></i></span><br/>
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
