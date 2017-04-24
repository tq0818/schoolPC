<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="popupwin teacherpopuwin" data-pupwin="modal" style="top: 150px;position:fixed;">
	<form id="addTeachersForm">
		<div class="popupwin-title">
			<h2 class="h5">新增老师</h2>
			<i class="close iconfont"></i>
		</div>
		<div class="main form-horizontal" id="lsOne">
			<div class="form-body">
				<div class="form-group">
					<label class="col-md-3 control-label">用户名&nbsp;<i style="color: red;" class="iconfont ico">&#xe605;</i></label>
						<div class="col-md-4">
							<input type="text" id="tuserName" name="tuserName" class="form-control" placeholder="">
							<span class="help-block" style="color:red;"></span>
						</div>
					</div>
				<div class="form-group">
					<label class="col-md-3 control-label">密码&nbsp;<i style="color: red;" class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-4">
						<input class="form-control" id="password" name="password" type="password" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">确认密码&nbsp;<i style="color: red;" class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-4">
						<input class="form-control" id="confirmpwd" name="confirmpwd" type="password" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">姓名&nbsp;<i style="color: red;" class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-4">
						<input class="form-control" id="realName" name="realName" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label">手机号&nbsp;<i style="color: red;" class="iconfont ico">&#xe605;</i></label>
					<div class="col-md-4">
						<input class="form-control" id="tMobile" name="tMobile" type="text" />
						<span class="tips" style="color:red;"></span>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-3 col-md-offset-4">
						<a class="m-btn-red"  href="javascript:Form.addTeacher();">确&nbsp;&nbsp;定</a>
						<a class="m-btn-default" data-pupwin-btn="cancle" href="javascript:;">取&nbsp;&nbsp;消</a>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>