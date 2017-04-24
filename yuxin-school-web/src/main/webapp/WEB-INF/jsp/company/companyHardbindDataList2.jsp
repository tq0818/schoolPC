<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp"%>
<title>学员分组</title>
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/system.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/footer.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage-screen.css" />
<link rel="stylesheet" href="<%=rootPath%>/stylesheets/class-set.css">

<script type="text/javascript">var rootPath='<%=rootPath%>'</script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
    
<style type="text/css">
.tips{
   	color:red;
}
</style>
<style type="text/css">
.add-first-btn{
	display:block;
	float: right;
    width: 104px;
    height: 27px;
    background-color: #237fd5;
    line-height: 27px;
    text-align: center;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
    cursor: pointer;
    font-size: 12px;
    color: #fff;
   	margin-top: -20px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
	<div class="u-wrap company overflow">
		<jsp:include page="/WEB-INF/jsp/menu/menu_studentconfig.jsp"></jsp:include>
		<div class="screen-right">
		
			<div style="margin-top: 10px;text-align:right;padding:0 10px;">
		       <span><a href="javascript:;" class="btn btn-primary addStudent">添加</a></span>
		    </div>
		   
			<div class="user-list">
		       <table class="table table-center" id="tableList" align="center" >
					<tr>
						<th width="10%">ip</th>
						<th width="10%">mac地址</th>
						<th width="10%">硬盘序列号</th>
						<th width="10%">cpu序列号</th>
						<th width="10%">域名</th>
						<th width="20%">操作</th>
					</tr>
				</table>
				<div class="pages pagination"></div>
		     </div>
		        
		        
		        <!-- ajax加载中div开始 -->
			<div class="loading lp-units-loading" style="display:none">
				<p><i></i>加载中,请稍后...</p>
			</div>
			<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
			<div class="popupwin-box addStudentPopup1 clear" style="display: none;">
			<div class="popupwin addStudentPopup" style="width:1000px; height:auto;top:10px" data-pupwin="modal">
			<form id="addStudentForm">
				<div class="popupwin-title">
					<h2 class="h5">添加信息</h2>
					<i class="close iconfont canclekuang"></i>
				</div>
				<div class="main form-horizontal" id="lsOne">
					<div class="form-body">
						
						<div class="form-group">
							<label class="col-md-2 control-label">ip<i class="iconfont ico">&#xe605;</i></label>
								<div class="col-md-2">
									<input type="text" id="sip" name="sip" maxlength="50" class="form-control" placeholder="" >
									<span class="help-block" style="color:red;"></span>
								</div>
							<label class="col-md-2 control-label">mac地址</label>	
								<div class="col-md-2">
									<input type="text" id="smacAddress" name="smacAddress" maxlength="50" class="form-control" placeholder="" >
								</div>
							</div>
							
						<div class="form-group">
							<label class="col-md-2 control-label">硬盘序列号</label>
							<div class="col-md-2">
								<input class="form-control" id="shdSerialnumber" name="shdSerialnumber" type="text" />
							</div>
							<label class="col-md-2 control-label">cpu序列号</label>
							<div class="col-md-2">
								<input class="form-control" id="scpuSerialnumber" name="scpuSerialnumber" type="text" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">域名</label>
							<div class="col-md-2">
								<input class="form-control" id="sdomain" name="sdomain" type="text" />
							</div>
							
						</div>
						
						<div class="form-group" style="text-align: center;">
		                        <div class="col-md-3" style="width: 100%;padding: 10px 0 0;">
		                            <a class="m-btn-red" href="javascript:addItem();">确&nbsp;&nbsp;定</a>
		                            <a class="m-btn-default canclekuang" data-pupwin-btn="cancle" href="javascript:;">取&nbsp;&nbsp;消</a>
		                        </div>
		                 </div>
					</div>
				</div>
				</form>
			</div>
			</div>
		
			<div class="popupwin-box updateStudentPopup1 clear" style="display: none;">
				<div class="popupwin updateStudentPopup" style="width:1000px; height: auto;top:10px" data-pupwin="modal">
					<form id="updateStudentForm">
						<div class="popupwin-title">
							<h2 class="h5">修改信息</h2>
							<i class="close iconfont canclekuang"></i>
						</div>
						<div class="main form-horizontal" id="lsOne">
							<div class="form-body">
								
								<div class="form-group">
									<label class="col-md-2 control-label">ip<i class="iconfont ico">&#xe605;</i></label>
										<div class="col-md-2">
											<input type="hidden" id="uId" value="">
											<input type="text" id="ip" name="ip" maxlength="50" class="form-control" placeholder="" >
											<span class="help-block" style="color:red;"></span>
										</div>
									<label class="col-md-2 control-label">mac地址</label>	
										<div class="col-md-2">
											<input type="text" id="macAddress" name="macAddress" maxlength="50" class="form-control" placeholder="" >
										</div>
									</div>
									
								<div class="form-group">
									<label class="col-md-2 control-label">硬盘序列号</label>
									<div class="col-md-2">
										<input class="form-control" id="hdSerialnumber" name="hdSerialnumber" type="text" />
									</div>
									<label class="col-md-2 control-label">cpu序列号</label>
									<div class="col-md-2">
										<input class="form-control" id="cpuSerialnumber" name="cpuSerialnumber" type="text" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">域名</label>
									<div class="col-md-2">
										<input class="form-control" id="domain" name="domain" type="text" />
									</div>
								</div>
								
								<div class="form-group" style="text-align: center;">
				                        <div class="col-md-3" style="width: 100%;padding: 10px 0 0;">
				                            <a class="m-btn-red" href="javascript:update.updateStudent();">确&nbsp;&nbsp;定</a>
				                            <a class="m-btn-default canclekuang" data-pupwin-btn="cancle" href="javascript:;">取&nbsp;&nbsp;消</a>
				                        </div>
				                 </div>
							</div>
						</div>
					</form>
				</div>
			</div>
			<input type="hidden" id="selectCounts" value="10">
			
		</div>
	</div>
	<div class="add-grade-pop-box">
		<div class="add-grade-pop">
			<div class="add-grade-title" id="group_type_name">添加一级分组</div>
			<div class="add-grade-content">
				<p>
					<span>分类名称：</span> 
					<input type="text" maxlength="10" id="new_group_name">
				</p>
				<p class="care">注：最多可输入10个字</p>
				<div class="addpop-btn-box">
					<span class="add-yes">确定</span>
					<span class="add-cancel">取消</span>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="<%=rootPath %>/javascripts/company/companyhardbingdata.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
	<script type="text/javascript">
		function addItem(){
			
			var data={};
			data.ip=$("#sip").val();
		    data.macAddress = $("#smacAddress").val();
		    data.hdSerialnumber = $("#shdSerialnumber").val();
		    data.cpuSerialnumber = $("#scpuSerialnumber").val();
		    data.domain = $("#sdomain").val();
		   
		    $.ajax({
		        type: 'post',
		        url: rootPath + "/companyHardbindData/insertItem",
		        data: data,
		        dataType: 'json',
		        success: function (jsonData) {
		            if (jsonData == "success") {
		                $.msg("添加成功");
		                var pageNo=$("#pageNo").val();
		                hardbingdata.search(pageNo);
		                $(".addStudentPopu").popup("hide");
		                $(".addStudentPopup1").hide();
		                update.clearData();
		            }
		        }
		    });
		
		}
	</script>
	
</body>

</html>