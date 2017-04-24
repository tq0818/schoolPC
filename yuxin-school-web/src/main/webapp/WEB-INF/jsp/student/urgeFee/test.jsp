<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分期管理</title>
</head>
<body class="page-header-fixed">
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<!-- <h3 class="page-title">
                  Dashboard <small>statistics and more</small>
               </h3>-->
			<ul class="page-breadcrumb breadcrumb">
				<li><i class="icon-home"></i> <a href="<%=rootPath%>/index.jsp">Home</a>
					<i class="icon-angle-right"></i></li>
				<li>费用管理</li>
				<li class="pull-right">
					<div id="dashboard-report-range" class="dashboard-date-range tooltips" data-placement="top" data-original-title="">
						<i class="icon-calendar"></i> <span></span> <i
							class="icon-angle-down"></i>
					</div>
				</li>
			</ul>
			<!-- END PAGE TITLE & BREADCRUMB-->
		</div>
	</div>

	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-search"></i> 学员信息查询
			</div>
		</div>
		<div class="portlet-body form">
			<form id="searchForm" role="form" class="form-horizontal"
				method="post">
				<div class="form-body">
					<div class="form-group">
						<label class="col-md-2 control-label">学科</label>
						<div class="col-md-4">
							<select class="form-control input-medium" id="itemOneId" name="itemOneId">
							</select>
						</div>
						<label class="col-md-2 control-label" for="step2">学科小类</label>
						<div class="col-md-4">
							<select class="form-control input-medium" id="itemSecondId" name="itemSecondId" >
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">课程</label>
						<div class="col-md-4">
							<select class="form-control input-medium" id="classType" name="classType" >
							</select>
						</div>
						<label class="col-md-2 control-label">考期</label>
						<div class="col-md-4">
							<select  class="form-control input-medium" id="examTerm" name="examTerm" >
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">姓名</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-medium" id="name"
								name="name" />
						</div>
						<label class="col-md-2 control-label" for="step2">手机号</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-medium" id="mobile"
								name="mobile" />
						</div>
					</div>
					<div class="form-group">
						
						<label class="col-md-2 control-label" for="step2">证件号</label>
						<div class="col-md-4">
							<input type="text" class="form-control input-medium"
								id="identityId" name="identityId" />
						</div>
					</div>
				</div>
				<div class="form-actions right">
					<button type="button" class="btn blue" onclick="Form.query()">查询</button>
				</div>
			</form>
		</div>
	</div>
	<div class="portlet box blue" id="feetable">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-list"></i>缴费通知
			</div>
		</div>
		<div class="portlet-body">
			<table class="table table-striped table-bordered table-hover"
				style="margin: 0;" id="feeList">
				<thead>
					<tr>
						<th class="hidden">#</th>
						<th>姓名</th>
						<th>手机号</th>
						<th>课程</th>
						<th>报名日期</th>
						<th>总费用</th>
						<th>分期日期</th>
						<th>费用</th>
						<th>费用状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div class="pages">
				<ul class="pagination"></ul>
			</div>
		</div>
	</div>

	<div id=staging class="modal fade" data-backdrop="static" tabindex="-1" data-width="600">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title">分期缴费</h4>
		</div>
		<div class="modal-body">
				<form id="detailForm" role="form" class="form-horizontal" action="<%=rootPath%>/fee/stage" method="post">
					<div class="portlet box blue" id="feeInfo">
						<div class="portlet-title">
							<div class="caption">
								<i class="icon-search"></i> 缴费信息
							</div>
						</div>
					<div class="portlet-body form">
					<div  id="paras">
						<input type="hidden" name="id" id="id"/>
						<input type="hidden" name="stageCost" id="stageCost"/>
						<input type="hidden" name="stuId" id="stuId"/>
						<input type="hidden" name="payMasterId" id="payMasterId"/>
					</div>
						<div class="row">
							<div class="col-md-10">
								<div class="form-group">
									<label class="control-label col-md-5">POS</label>
									<div class="col-md-7">
										<input type="text" class="form-control" id="posReal"
											name="posReal" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-10">
								<div class="form-group">
									<label class="control-label col-md-5">现金</label>
									<div class="col-md-7">
										<input type="text" class="form-control" id="cashReal"
											name="cashReal" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-10">
								<div class="form-group">
									<label class="control-label col-md-5">支票</label>
									<div class="col-md-7">
										<input type="text" class="form-control" name="checkReal"
											id="checkReal" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-10">
								<div class="form-group">
									<label class="control-label col-md-5">转账</label>
									<div class="col-md-7">
										<input type="text" class="form-control" name="remitReal"
											id="remitReal">
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-10">
								<div class="form-group">
									<label class="control-label col-md-5">备注</label>
									<div class="col-md-7">
										<input type="text" class="form-control" name="remark"
											id="remark" />
									</div>
								</div>
							</div>
						</div>
						
					</div>
					</div>
				<div class="portlet box blue" style="display:none;" id="newStage">
					<div class="portlet-title">
						<div class="caption">
							<i class="icon-search"></i> 新增分期
						</div>
					</div>
					<div class="portlet-body form">
					<div class="row">
							<div class="col-md-10">
								<div class="form-group">
									<label class="control-label col-md-5">分期金额</label>
									<div class="col-md-7">
										<input type="text" class="form-control" name="stageFee" id="stageFee" />
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-10">
								<div class="form-group">
									<label class="control-label col-md-5">缴费日期</label>
									<div class="input-group  date date-picker col-md-7" data-date-format="yyyy-mm-dd" data-date-viewmode="years">
	                                    <input type="text" class="form-control" name="stageDate" id="stageDate" readonly/>
	                                    <span class="input-group-btn">
	                                    <button class="btn default" type="button"><i class="icon-calendar"></i></button>
	                                    </span>
                                 	</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn blue" onclick="Form.saveStaging()">确定</button>
				<button type="button" class="btn default" data-dismiss="modal">返回</button>
			</div>
		</div>
	<script type="text/javascript" src="<%=rootPath%>/script/fee/common.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/script/fee/staging.js"></script>
</body>
</html>