(function($){
	
	var Form={
		init: function(){
			var $this=this;
			//收索
			$("#searchContent").on("click",function(){
				$this.search();
			})
			//重置密码
			$("#resetPassword").on("click",function(){
				var userId=$("#uId").val();
				var newPwd=$("#newPassword").val();
				var comfirmPwd=$("#comfirmPassword").val();
				if(newPwd==""||comfirmPwd==""){
					$.msg("密码不能为空");
					return;
				}
				if(newPwd.length<6||comfirmPwd.length<6){
					$.msg("密码长度不能小于6位");
					return;
				}
				if(newPwd!=comfirmPwd){
					$.msg("密码输入不一致");
					return;
				}
				//重置操作
				$.ajax({
					url : rootPath+"/companySetInfo/resetPwd",
					data : {"userId":userId,"pwd":comfirmPwd},
					type : "post",
					success : function(data){
						if(data == "success"){
							$.msg("密码重置成功");
							$(".addTypeProm").popup("hide");
						}
					}
				});
			})
			//延期公司服务时间
			$("#addCompanyServiceTime").on('click',function(){
//				var serviceTime=$("#endServiceTime").val();
//				var sdate=dateToStr("yyyy-MM-dd",new Date());
//				var flag=0;
//				if(serviceTime<=sdate){
//					flag=1;
//				}
				var companyId=$("#cId").val();
			    var day=$("#servicedays").val();
			    if(day==""){
			    	$.msg("请输入延长公司服务天数");
			    	return;
			    }
			    if(isNaN(day)||day<0){
			    	$.msg("请输入合法数字");
			    	return;
			    }
			    if(day>31){
			    	$.msg("公司服务最多延期一个月");
			    	return;
			    }
			    $.ajax({
					url : rootPath+"/companySetInfo/addServiceTime",
					data : {"companyId":companyId,"day":day},
					type : "post",
					success : function(data){
						if(data == "success"){
							$.msg("公司延期服务成功");
							$(".addCompanyServiceTime").popup("hide");
						}
					}
				});
			});
		},
		search: function(){
			var mobile=$("#userMobile").val();
			$(".tables").find("table").find("tr:gt(0)").remove();
			if(mobile.length<=0||mobile==""){
				$(".tables").find("table").append('<tr><td colspan="8">没有查找到数据</td></tr>');
				return;
			}
			$.ajax({
				url : rootPath+"/companySetInfo/queryDetail/"+mobile,
				type : 'post',
				dataType : 'json',
				success: function(com){
					if(!com){
						$(".tables").find("table").append('<tr><td colspan="8">没有查找到数据</td></tr>');
					}
					$(".tables").find("table").append('<tr>'+
							'<td>'+(com.loginUsername?com.loginUsername:"")+'</td>'+
							'<td>'+(com.companyName?com.companyName:"")+'</td>'+
							'<td>'+(com.domain?com.domain:"")+'</td>'+
							'<td>'+(com.loginEmail?com.loginEmail:"")+'</td>'+
							'<td>'+(com.registTime?com.registTime:"")+'</td>'+
							'<td>'+(com.memberEndDate?com.memberEndDate:"")+'</td>'+
							'<td>'+(com.contactAddress?com.contactAddress:"")+'</td>'+
							'<td><a href="javascript:;" ids='+com.id+' class="btn btn-mini btn-primary savetiketsType">密码重置</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" ids='+com.cId+' mark='+com.memberEndDate+' class="btn btn-mini btn-primary addCompanyTime">延期</a></td>'+
							'</tr>');
					//详情
					$(".savetiketsType").on('click',function(){
						$(".addTypeProm").popup("show");
						var userId=$(this).attr("ids");
						$("#uId").val(userId);
						Form.clearData();
					})
					//延期
					$(".addCompanyTime").on('click',function(){
						$(".addCompanyServiceTime").popup("show");
						var cId=$(this).attr("ids");
						var endServiceTime=$(this).attr("mark");
						$("#endServiceTime").val(endServiceTime);
						$("#cId").val(cId);
						$("#servicedays").val('');
					});
				}
			});
		},
		clearData : function(){
			$("#newPassword").val('');
			$("#comfirmPassword").val('');
		}
	}
	
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)