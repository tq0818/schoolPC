<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公司信息</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/marketing.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_config.jsp"></jsp:include>
	<div class="u-wrap set-system">
	    <div class="heading">
	        <h2 class="h5">公司信息</h2>
	    </div>
	</div>
	<div class="u-wrap">
	 	<div class="mainbackground">
	 		<div class="main-content nospace">
				<div class="marketing">
					<p class="c">
						<span class="t-title">手机号</span> 
						<span class="t-content">
							<input type="text" id="userMobile" placeholder="请输入手机号"/>
						</span>
						<input type="button" class="btn btn-sm btn-primary btn-searh" id="searchContent" value="查询"/>
					</p>
				</div>
			</div>
	 	</div>
	</div>
	<div class="u-wrap">
		<div class="mainbackground">
			<div class="main-content nospace">
				<div class="tables">
					<table class="table table-center">
						<tbody>
							<tr>
								<th width="10%">用户名称</th>
								<th width="15%">公司名称</th>
								<th width="15%">公司域名</th>
								<th width="10%">邮箱</th>
								<th width="10%">注册时间</th>
								<th width="10%">到期时间</th>
								<th width="12%">公司地址</th>
								<th width="15%">操作</th>
							</tr>
							<tr><td colspan="8">暂无数据</td></tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
<!-- 弹出窗口 -->
	<div class="popupwin addTypeProm" data-pupwin="modal" style="margin: 0 auto;height: 220px;">
		<div class="popupwin-title">
			<h2 class="h5">重置密码</h2>
			<i class="close iconfont"></i>
		</div>
		<div class="main form-horizontal" id="lsOne">
			<div class="form-body">
			 <input type="hidden" id="uId" value=""/>
				<div class="form-group">
					<label class="col-md-3 control-label">新密码</label>
					<div class="col-md-4">
						<input type="text" id="newPassword" class="form-control" placeholder="">
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">确认密码</label>
					<div class="col-md-4">
						<input type="text" id="comfirmPassword" class="form-control" placeholder="">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-3 col-md-offset-4">
						<a class="m-btn-red"  href="javascript:;" id="resetPassword">确&nbsp;&nbsp;定</a>
						<a class="m-btn-default" data-pupwin-btn="cancle" href="javascript:;">取&nbsp;&nbsp;消</a>
					</div>
				</div>
			</div>
		</div>
	</div>

<div class="popupwin addCompanyServiceTime" data-pupwin="modal" style="margin: 0 auto;height: 220px;">
		<div class="popupwin-title">
			<h2 class="h5">公司延期</h2>
			<i class="close iconfont"></i>
		</div>
		<div class="main form-horizontal">
			<div class="form-body">
			 <input type="hidden" id="cId" value=""/>
			 <input type="hidden" id="endServiceTime" value=""/>
				<div class="form-group">
					<label class="col-md-3 control-label">请输入延长时间天数</label>
					<div class="col-md-4">
						<input type="text" class="form-control" id="servicedays" placeholder="">
						<span class="help-block">公司服务延长时间最多为一个月</span>
					</div>
					<label class="control-label">天</label>
				</div>
				<div class="form-group">
					<div class="col-md-3 col-md-offset-4">
						<a class="m-btn-red"  href="javascript:;" id="addCompanyServiceTime">确&nbsp;&nbsp;定</a>
						<a class="m-btn-default" data-pupwin-btn="cancle" href="javascript:;">取&nbsp;&nbsp;消</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$selectMenu('system_company_select');
		})
	</script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/system/sysCompanyInfo.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
</body>
</html>