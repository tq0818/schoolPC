<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html >
<html class="">

<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>教师邀请记录</title>
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/manage.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/system.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/fatstyle.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/stylesheets/splitscreen.css" />
<link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/miniJs/css/minitip.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/sidebar/sidebar.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/classSet/classSet.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/css/footSet.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/css/distrib.css">
<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script>
  	var rootPath = '<%=rootPath%>';
</script>
<style>
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


<body class="q_box overflow-y-h">
	<jsp:include page="/header.jsp" />
	<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
	 <form method="POST" id="exportForm2" >
    
        </form> 
         <div class="u-wrap company manage-screen clear coupon-page" >
	 	<jsp:include page="/WEB-INF/jsp/menu/menu_query.jsp"></jsp:include>
	 	<script>
  			$(function(){
  				$('#chaxuntongji').addClass('active');
  				$selectSubMenus('teacher_invite');
  			})
		</script>
		<div class="right-side wxRight-side">
            <div>
                <div class="title-box titlefen-box">
                    <div class="tit-font ">
                        <span class="t ts">教师邀请记录</span>
                        <span class="kg kgCla kg-fen" level="cla">
                        
                        </span>
                    </div>
                </div>
                <div class="recordContent">
                    <div class="screenList screenList-check">
                        <span class="datel">日期 :</span>
                        <a class="btn btn-mini btn-default " href="javascript:;">全部</a>
                        <a class="btn btn-mini btn-default " href="javascript:;">上月</a>
                        <a class="btn btn-mini btn-default " href="javascript:;">本月</a>
                        <a class="btn btn-mini btn-success btn-td" href="javascript:;">指定时间</a>
                        <input type="text" name="startTime" class="date-picker from"  />
                        <em class="datetit-z">至</em>
                        <input type="text" name="endTime" class="date-picker to" />
                        <input class="nameTel" type="text" placeholder="教师姓名/手机号" />
                        <a class="btn  check" href="javascript:;" id="searchTeacInvite">查询</a>
                    </div>
                    <ul class="showList clear">
                        <li class="classific">
                            <span>邀请总人数 :</span>
                            <em>${totalInviteNumber}&nbsp;人</em>
                        </li>
                        <li class="classific">
                            <span>消费总次数 :</span>
                            <em>${totalConsumeTimes}&nbsp;次</em>
                        </li>
                        <li class="classific">
                            <span>消费总金额 :</span>
                            <input type="hidden" value="${totalConsumeBalance}" class="hiddenConsumeBalance">
                            <em></em>
                        </li>
                        <li class="classific">
                            <span>提成奖励总金额 :</span>
                            <input type="hidden" value="${totalRewardBanlance}" class="hiddenRewardBalance">
                            <em></em>
                        </li>
                        <a class="btn export fr" href="javascript:;">导出</a>
                        <input type="hidden" class="hidSortType">
                        <input type="hidden" class="hidSort">
                    </ul>
                    <table class="table table-center cashrecord-tab" id="table1">
                        <colgroup>
                            <col width="12.5%" />
                            <col width="12.5%" />
                            <col width="12.5%" />
                            <col width="11.5%" />
                            <col width="10%" />
                            <col width="12.5%" />
                            <col width="16.5%" />
                            <col width="12.5%" />
                        </colgroup>
                        <tbody>
                            <tr class="tab-htr">
                                <th>教师姓名</th>
                                <th>手机号</th>
                                <th>邀请码</th>
                                <th>
                                    <div class="headClick">
                                        <span mark="inviteNumber">邀请人数</span>
                                        <p class="sanCon">
                                            <span class="san-top  triangle up"></span>
                                            <span class="san-bottom triangle down"></span>
                                        </p>
                                    </div>
                                </th>
                                <th>
                                    <div class="headClick">
                                        <span mark="consumeTimes">消费次数</span>
                                        <p class="sanCon">
                                            <span class="san-top  triangle up"></span>
                                            <span class="san-bottom triangle down"></span>
                                        </p>
                                    </div>
                                </th>
                                <th>
                                    <div class="headClick">
                                        <span mark="consumeBalance">消费金额</span>
                                        <p class="sanCon">
                                            <span class="san-top  triangle up"></span>
                                            <span class="san-bottom triangle down"></span>
                                        </p>
                                    </div>
                                </th>
                                <th>
                                    <div class="headClick">
                                        <span mark="rewardBalance">提成奖励金额</span>
                                        <p class="sanCon">
                                            <span class="san-top  triangle up"></span>
                                            <span class="san-bottom triangle down"></span>
                                        </p>
                                    </div>
                                </th>
                                <th>操作</th>
                            </tr>
                        </tbody>
                    </table>
                    <div class="pages">
                        <ul class="pagination">
                        </ul>
                    </div>
                </div>
            </div>
        </div>
		</div>
		
		<input type="hidden" class="hiddendetailteacherId">
		<input type="hidden" class="hiddendetailsortName">
		<input type="hidden" class="hiddendetailsortType">
		<div class="detail-Parent">
		<div class="detail-Par" > 
		<div class="tips-spetitle">
			详情 <i class="iconfont spe-close">&#xe610;</i>
		     </div>
		<div class="detailsPop" > 
                        <div class="screenList formpost"> 
                            <span class="teacz">:</span> 
                            <em></em> 
                            <select name="consumeFlag" id="consumeFlag"> 
                                <option value="">消费情况</option> 
                                <option value="1">已消费</option> 
                                <option value="0">未消费</option> 
                            </select> 
                            <input class="nameTel nameTel-t" type="text" placeholder="被邀请码人手机号/用户名"/> 
                            <a class="btn btn-mini check" href="javascript:;" id="detailSearch">搜索</a> 
                        </div> 
                    <ul class="showList showList-det clear"> 
                    <li class="fr"> 
                    <span>提成金额 :</span> 
                    <em id="rewardBalance">元</em> 
                    </li> 
                    <li class="fr"> 
                    <span>消费金额 :</span> 
                    <em id="consumeBalance">元</em> 
                    </li> 
                    <li class="fr"> 
                    <span>消费次数 :</span> 
                    <em id="consumeTimes">次</em> 
                    </li> 
                    <li class="fr"> 
                    <span>注册学员人数 :</span> 
                    <em id="inviteNumber">人</em> 
                    </li> 
                    </ul> 
                    <table class="table table-center cashrecord-tab cashrecord-tabs"> 
                    <colgroup> 
                    <col width="28%" /> 
                    <col width="22%" /> 
                    <col width="12.5%" /> 
                    <col width="12.5%" /> 
                    <col width="12.5%" /> 
                    <col width="12.5%" /> 
                    </colgroup> 
                    <tbody> 
                    <tr class="tab-htr "> 
                    <th> 
                    <div class="headClick"> 
                    <span>注册学员账号</span> 
                    <p class="sanCon" mark="name"> 
                    <span class="san-top  triangle detailup"></span> 
                    <span class="san-bottom triangle detaildown"></span> 
                    </p> 
                    </div> 
                    </th> 
                    <th>消费项目</th> 
                    <th>邀请日期</th> 
                    <th>消费日期</th> 
                    <th> 
                    <div class="headClick"> 
                    <span>消费金额</span> 
                    <p class="sanCon" mark="consumeBalance"> 
                    <span class="san-top  triangle detailup"></span> 
                    <span class="san-bottom triangle detaildown"></span> 
                    </p> 
                    </div> 
                    </th> 
                    <th>提成金额</th> 
                    </tr> 
                    </tbody> 
                    </table> 

                    <div class="pages pages-invit"> 
                    <ul class="pagination2"> 
                    </ul> 
                    </div> 
                    </div>
                    </div>
                    </div>
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	
	
	<div class="loading-bg lp-units-loading-bg" style="display: none ;background-color:rgba(0,0,0,.2)"></div>
	<jsp:include page="/footer.jsp" />
	<input type="hidden" class="test12" value=""/>
	<script src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
	<script	src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
	<script src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
	<script src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script src="<%=rootPath%>/javascripts/teacher_invite.js"></script>
	<script src="<%=rootPath%>/javascripts/teacherrecorddetail.js"></script>
</body>

</html>
