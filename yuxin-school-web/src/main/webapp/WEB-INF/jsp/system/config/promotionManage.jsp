<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>优惠券配置</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/marketing.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
     <link rel="stylesheet" type="text/css"
	href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_config.jsp"></jsp:include>
	<div class="u-wrap set-system">
	    <div class="heading">
	        <h2 class="h5">优惠券配置</h2>
	    </div>
	</div>
	<div class="u-wrap">
	 	<div class="mainbackground">
	 		<div class="main-content nospace">
				<div class="marketing">
					<p class="c">
						<span class="t-title">有效期</span> 
						<span class="t-content">
							<input class="date-picker from daterangs" type="text"> 
							<em>至</em> 
							<input class="date-picker to daterangs" type="text"> 
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
			<div style="float: right;"><a href="javascript:;" class="btn btn-mini btn-primary box-select"><em class="iconfont">&#xe606;</em>添加优惠券类型</a></div>
				<div class="tables">
					<table class="table table-center">
						<tbody>
							<tr>
								<th width="10%">优惠券类型名称</th>
								<th width="15%">已使用/发行量</th>
								<th width="20%">有效期</th>
								<th width="10%">创建人</th>
								<th width="15%">创建时间</th>
								<th width="15%">状态</th>
								<th width="15%">操作</th>
							</tr>
							<tr><td colspan="7">暂无数据</td></tr>
						</tbody>
					</table>
					<div class="pages pagination"></div>
				</div>
			</div>
		</div>
	</div>
<!-- 弹出窗口 -->
	<div class="popupwin addTypeProm" data-pupwin="modal" style="width:900px">
		<div class="popupwin-title">
			<h2 class="h5">生成优惠券类型</h2>
			<i class="close iconfont"></i>
		</div>
		<div class="main form-horizontal" id="lsOne">
			<div class="form-body">
				<div class="form-group">
					  <div class="col-md-5 col-md-offset-1	">
						<label class="col-md-3 control-label">优惠券类型名称</label>
							<div class="col-md-7">
								<input type="text" id="promTypeName" class="form-control" placeholder="">
							</div>
					 </div>
					 <div class="col-md-5">
						<label class="col-md-3 control-label">面额</label>
						<div class="col-md-7">
							<input class="form-control" id="money" type="text" />
						</div>
					 </div>
				</div>
				<div class="form-group">
					  <div class="col-md-5 col-md-offset-1	">
						<label class="col-md-3 control-label">发行数量</label>
							<div class="col-md-7">
								<input type="text" id="totalNum" class="form-control" >
							</div>
					 </div>
					 <div class="col-md-5">
						<label class="col-md-3 control-label">使用限额</label>
						<div class="col-md-4" id="addRadio">
							<input type="radio" name="xiane" checked="checked" value="-1"/>无限额
							<input type="radio" name="xiane" value="1"/>单笔订单满
						</div>
						<div style="float:left;">
							<input style="width:50px;height: 20px;" id="baseMoney" type="text"/>元
						</div>
					 </div>
				</div>
				<div class="form-group">
					 <div class="col-md-5 col-md-offset-1">
					 	<label class="col-md-3 control-label">赠送视频流量</label>
						<div class="col-md-2">
							<input class="form-control" id="videoFlowRate" type="text">
						</div>
						<label class="control-label" style="float:left;">G</label>
					 </div>
					 <div class="col-md-5">
						<label class="col-md-3 control-label">赠送视频空间</label>
						<div class="col-md-2">
							<input class="form-control" id="videoSpace" type="text">
						</div>
						<label class="control-label" style="float:left;">G</label>
					 </div>
				</div>
				<div class="form-group">
					 <div class="col-md-5 col-md-offset-1">
					 	<label class="col-md-3 control-label">短信条数</label>
						<div class="col-md-2">
							<input class="form-control" id="messageNum" type="text">
						</div>
					 </div>
					 <div class="col-md-5">
						<label class="col-md-3 control-label">邮件条数</label>
						<div class="col-md-2">
							<input class="form-control" id="emailNum" type="text">
						</div>
					 </div>
				</div>
				<div class="form-group">
					 <div class="col-md-5 col-md-offset-1">
						<label class="col-md-3 control-label">招收学员数</label>
						<div class="col-md-2">
							<input class="form-control" id="stuNum" type="text">
						</div>
					 </div>
					  <div class="col-md-5">
					 	<label class="col-md-2 control-label">有效期</label>
						<div class="col-md-3">
							<input class="form-control date-picker startTime" />
						</div>
						<label class="control-label" style="float:left;">—</label>
						<div class="col-md-3">
							<input class="form-control date-picker endTime" /> 
						</div>
					 </div>
				</div>
				<div class="form-group">
					<div class="col-md-3 col-md-offset-4">
						<a class="m-btn-red"  href="javascript:;" id="savetiketsType">确&nbsp;&nbsp;定</a>
						<a class="m-btn-default" data-pupwin-btn="cancle" href="javascript:;">取&nbsp;&nbsp;消</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 详情 -->
	<div class="popupwin detailProm" data-pupwin="modal" style="width:900px">
		<div class="popupwin-title">
			<h2 class="h5">优惠券类型详情</h2>
			<i class="close iconfont"></i>
		</div>
		<div class="main form-horizontal" id="lsOne">
			<div class="form-body">
				<div class="form-group">
					  <div class="col-md-5 col-md-offset-1	">
						<label class="col-md-3 control-label">优惠券类型名称</label>
						<label class="col-md-3 control-label" id="promTypeName1"></label>
					 </div>
					 <div class="col-md-5">
						<label class="col-md-3 control-label">面额</label>
						<label class="col-md-3 control-label" id="money1"></label>
					 </div>
				</div>
				<div class="form-group">
					  <div class="col-md-5 col-md-offset-1	">
						<label class="col-md-3 control-label">发行数量</label>
						<label class="col-md-3 control-label" id="totalNum1"></label>
					 </div>
					 <div class="col-md-5">
						<label class="col-md-3 control-label">使用限额</label>
						<div id="detailRadio">
							
						</div>
					 </div>
				</div>
				<div class="form-group">
					 <div class="col-md-5 col-md-offset-1">
					 	<label class="col-md-3 control-label">赠送视频流量</label>
					 	<label class="col-md-3 control-label"  id="videoFlowRate1"></label>
					 </div>
					 <div class="col-md-5">
						<label class="col-md-3 control-label">赠送视频空间</label>
						<label class="col-md-3 control-label"  id="videoSpace1"></label>
					 </div>
				</div>
				<div class="form-group">
					 <div class="col-md-5 col-md-offset-1">
					 	<label class="col-md-3 control-label">短信条数</label>
					 	<label class="col-md-3 control-label"  id="messageNum1"></label>
					 </div>
					 <div class="col-md-5">
						<label class="col-md-3 control-label">邮件条数</label>
						<label class="col-md-3 control-label"  id="eamilNum1"></label>
					 </div>
				</div>
				<div class="form-group">
					 <div class="col-md-5 col-md-offset-1">
						<label class="col-md-3 control-label">招收学员数</label>
						<label class="col-md-3 control-label"  id="stuNum1"></label>
					 </div>
					  <div class="col-md-5">
					 	<label class="col-md-3 control-label">有效期</label>
						<label class="col-md-2 control-label"  id="sTime"></label>
						<label class="control-label" style="float:left;">—</label>
						<label class="col-md-2 control-label"  id="eTime"></label>
					 </div>
				</div>
				<div class="form-group">
					<div class="col-md-3 col-md-offset-4">
						<a class="m-btn-default" data-pupwin-btn="cancle" href="javascript:;">关&nbsp;&nbsp;闭</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$selectMenu('system_ticket_set');
		})
	</script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/system/promotionManage.js"></script>
</body>
</html>