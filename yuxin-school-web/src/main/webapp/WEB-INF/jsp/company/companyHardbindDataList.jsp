<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/decorators/import.jsp" %>
<script type="text/javascript">var rootPath='<%=rootPath%>'</script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
    
    <style type="text/css">
   	.tips{
   		color:red;
   	}
    </style>
</head>
<body>
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