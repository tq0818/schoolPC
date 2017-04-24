<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>会员列表</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/custom-page.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/popupwin.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/vip.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
      <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
    <style type="text/css">
   	.tips{
   		color:red;
   	}
   	.add-page-pop input{
   		border: transparent;
   	}
    </style>
</head>

<body>


<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
<div class="u-wrap company classes clear">
    <div class="mainbackground nopadding paddno">
        <div class="heading">
            <h2 class="h5" style="display: inline-block">会员管理</h2>
            <span class="line"></span>
            <span class="inline-btn"></span>
            <span class="inline-btn"><a href="<%=rootPath %>/companyMemberConfig/addMemberVip" class="btn btn-primary ">添加新会员</a></span>
        </div>
        <div class="c-main" style="min-height: 550px;padding-top: 0;">
         <form method="post" id="searchForm">
            <div style="margin-top: 25px;" id="dateList">
                <a href="javascript:;" mark="all" class="btn btn-default btn-primary ">全部会员</a>
                <a href="javascript:;" mark="ing" class="btn btn-default  " >即将到期会员</a>
               <a href="javascript:;" mark="old" class="btn btn-default  ">已过期会员</a>
            </div>
            <input type="hidden" name="timeMark" id="timeMark" value="all"/>
            <div style="margin-top: 15px;">
                <select id="stuMemberLevel" name="memberId" style="width: 180px;overflow: hidden">
                </select>
                <input id="stuMobile" name="mobile" placeholder="手机号" maxlength="11" type="text"  style="width:120px">
                <input id="stuName" name="name" placeholder="姓名" type="text" style="width:120px">
                <input id="username" name="username" placeholder="用户名" type="text" style="width:120px">
                <span style="margin-left: 20px;">到期日期</span>
                <span><input type="text" name="startDate" class="date-picker from" style="width:120px"/><em>到</em><input type="text" name="endDate" class="date-picker to" style="width:120px"/></span>
                <span><a href="javascript:;" class="btn btn-primary searchContents">搜索</a></span>
                <span><a href="javascript:;" class="btn btn-primary exportexcle">导出搜索结果</a></span>
            </div>
            </form>
            <div class="user-list" >
                <table class="vip-manage-box" id="tableList">
                    <tr style="background: #eee">
                        <td width="10%">姓名</td>
                        <td width="10%">用户名</td>
                        <td width="10%">手机号</td>
                        <td width="10%">会员等级</td>
                        <td width="10%">有效期</td>
                        <td width="10%">到期日</td>
                        <td width="15%">操作</td>
                    </tr>
                    <tr><td colspan="7">暂无数据</td></tr>
                </table>
                <div class="pages pagination"></div>
            </div>
        </div>
    </div>
</div>
<div class="add-page-pop">
    <div class="add-page-board">
        <p class="page-pop-title"><span>学员信息</span><em class="iconfont">&#xe610;</em></p>
        <div style="padding: 10px 50px;">
            <div>
                <div class="stu-info-title">基本信息</div>
                <div class="stu-info-cont">
                    <p>
                        <span>姓名</span><input id="name"/><span>性别</span><input id="sex"/>
                    </p>
                    <p>
                        <span>手机号</span><input id="mobile"/><span>年龄</span><input id="age"/>
                    </p>
                    <p>
                        <span>证件类型</span><input id="identityTypeCode"/><span >证件号码</span><input id="identityId"/>
                    </p>
                </div>
            </div>
            <div>
                <div class="stu-info-title">联系信息</div>
                <div class="stu-info-cont">
                    <p>
                        <span>手机号</span><input id="mobile1"/><span >家庭电话</span><input id="homePhone"/>
                    </p>
                    <p>
                        <span>办公电话</span><input id="officePhone"/><span >紧急联系人</span><input id="emergencyContact"/>
                    </p>
                    <p>
                        <span>紧急人电话</span><input id="emergencyPhone"/><span>邮箱</span><input  id="email"/>
                    </p>
                    <p>
                        <span>微信</span><input id="weixinId"/>
                    </p>
                </div>
            </div>
            <div>
                <div class="stu-info-title">其他信息</div>
                <div class="stu-info-cont">
                    <p>
                        <span>单位</span><span></span>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<!-- popupwin 修改密码界面结束 -->
<script src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
<script src="<%=rootPath %>/javascripts/student/viplist.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=rootPath %>/javascripts/splitmarket.js"></script>
<script src="<%=rootPath %>/javascripts/classedit.js"></script>

<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
<script src="<%=rootPath%>/javascripts/vip.js"></script>
<script>
$(document).ready(function(){
	$("#dateList").on("click","a.btn",function(){
		var status=$(this).hasClass("btn-primary");
		if(!status){
			$(this).addClass("btn-primary").siblings("a").removeClass("btn-primary");
		}
		$("#timeMark").val($(this).attr("mark"));
		$(".searchContents").click();
	});
	
})
</script>
</body>
</html>